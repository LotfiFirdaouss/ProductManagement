package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static labs.pm.data.Rateable.convert;

public class ProductManager {

    private Map<Product, List<Review>> products = new HashMap<>();
    private static Map<String, ResourceFormatter> formatters = Map.of(
            "en-GB", new ResourceFormatter(Locale.UK),
            "en-US", new ResourceFormatter(Locale.US),
            "ru-RU", new ResourceFormatter(Locale.of("ru", "RU")),
            "fr-FR", new ResourceFormatter(Locale.FRANCE),
            "zh-CN", new ResourceFormatter(Locale.CHINA)
    );
    private ResourceFormatter formatter;

    public void changeLocale(String languageTag){
        formatter = formatters.getOrDefault(languageTag, formatters.get("en-GB"));
    }

    public static Set<String> getSupportedLocales(){
        return formatters.keySet();
    }

    public ProductManager(String languageTag) {
        changeLocale(languageTag);
    }

    public ProductManager(Locale locale) {
        this(locale.toString());
    }

    public Product createProduct(int id, String name, BigDecimal price,
                                 Rating rating, LocalDate bestBefore) {
        Product product = new Food(id, name, price, rating, bestBefore);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price,
                                 Rating rating) {
        Product product = new Drink(id, name, price, rating);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product reviewProduct(Product product, Rating rating, String comments) {
        // 1 - get the list of reviews for the product
        List<Review> reviews = products.get(product);

        // 2 - remove the product from the map to avoid concurrent modification exception
        products.remove(product, reviews);

        // 3 - add the new review to the list
        reviews.add(new Review(rating, comments));

        // 4 - calculate the new average rating
        Double ratingValue = reviews.stream()
                .mapToInt(p -> p.rating().ordinal())
                .average()
                .orElse(0);

        // 5 - apply the new average rating to the product
        product.applyRating(Rateable.convert((int) Math.round(ratingValue)));

        // 6 - put the product and the updated list of reviews back to the map
        products.put(product, reviews);

        // 7 - return the updated product
        return product;
    }

    public Product reviewProduct(int id, Rating rating, String comments) {
        return reviewProduct(findProduct(id), rating, comments);
    }

    public void printProductReport(Product product) {
        // 1 - get the list of reviews for the product
        List<Review> reviews = products.get(product);
        Collections.sort(reviews);

        // 2 - prepare the report text
        StringBuilder txt = new StringBuilder();

        // we use MessageFormat to format the string with placeholders
        txt.append(formatter.formatProduct(product)); // format the product information
        txt.append("\n");

        if(reviews.isEmpty()){
            txt.append(formatter.getText("no.reviews") + '\n');
        } else {
            txt.append(reviews
                    .stream()
                    .map(review ->  formatter.formatReview(review) + '\n')
                    .collect(Collectors.joining()));
        }

        // 3 - print the report (resulting text)
        System.out.println(txt);
    }

    public void printProducts(Predicate<Product> filter, Comparator<Product> sorter){
        StringBuilder txt = new StringBuilder();
        // format each product and join them into a single string : more suitable for large number of products (if we want to use parallelStream in the future)
        txt.append(products.keySet()
                .stream()
                .sorted(sorter)
                .filter(filter)
                .map(product -> formatter.formatProduct(product) + "\n")
                .collect(Collectors.joining()));

        // OR as an alternative using forEach
        /* products.keySet()
                .stream()
                .sorted(sorter)
                .forEach(product -> txt.append(formatter.formatProduct(product) + '\n')); */

        System.out.println(txt);
    }

    public void printProductReport(int id){
        printProductReport(findProduct(id));
    }

    public Product findProduct(int id){
        return products.keySet().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Map<String, String> getDiscounts(){
        // <String,String> ==> rating(stars), discount
        return products.keySet()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                p -> p.getRating().getStars(),
                                Collectors.collectingAndThen(
                                        Collectors.summingDouble(
                                                product -> product.getDiscount().doubleValue()
                                        ),
                                        discount -> formatter.moneyFormat.format(discount)
                                )));

    }

    private static class ResourceFormatter {
        private Locale locale;
        private ResourceBundle resources;
        private DateTimeFormatter dateFormat;
        private NumberFormat moneyFormat;

        private ResourceFormatter(Locale locale) {
            this.locale = locale;
            resources = ResourceBundle.getBundle("labs.pm.data.resources", locale);
            dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                    .localizedBy(locale);
            moneyFormat = NumberFormat.getCurrencyInstance(locale);
        }

        private String formatProduct(Product product) {
            String type = switch (product) {
                case Food food -> resources.getString("food");
                case Drink drink -> resources.getString("drink");
            };
            return MessageFormat.format(resources.getString("product"),
                    product.getName(),
                    moneyFormat.format(product.getPrice()),
                    product.getRating().getStars(),
                    dateFormat.format(product.getBestBefore()),
                    type
            );
        }

        private String formatReview(Review review) {
            return MessageFormat.format(resources.getString("review"),
                    review.rating().getStars(),
                    review.comments()
            );
        }

        private String getText(String key){
            return resources.getString(key);
        }
    }
}
