package labs.pm.app;

import labs.pm.data.Product;
import labs.pm.data.ProductManager;
import labs.pm.data.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;

/**
 * {@code Shop} class represents an application that manages Products
 * @version 4.0
 * @author Firdaouss
 */
public class Shop {
    public static void main(String[] args) {
        ProductManager pm = new ProductManager("en-GB");

        pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.THREE_STAR);
//        pm.printProductReport(101);
        pm.reviewProduct(101, Rating.FOUR_STAR, "Nice hot cup of tea");
//        pm.printProductReport(101);
        pm.reviewProduct(101, Rating.THREE_STAR, "Good tea but a bit weak");
        pm.reviewProduct(101, Rating.FIVE_STAR, "Perfect tea");
        pm.reviewProduct(101, Rating.TWO_STAR, "Too cold");
//        pm.printProductReport(101);

//        pm.changeLocale("ru-RU");

        pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.FOUR_STAR);
        pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));

//        pm.changeLocale("fr-FR");

        pm.createProduct(104, "Cookie", BigDecimal.valueOf(3.99), Rating.TWO_STAR, LocalDate.now());
        pm.createProduct(105, "Chocolate", BigDecimal.valueOf(1.99), Rating.FIVE_STAR);
        pm.createProduct(106, "Chocolate", BigDecimal.valueOf(1.99), Rating.FIVE_STAR,  LocalDate.now().plusDays(2).plusDays(3));

//        pm.printProductReport(102);
//        pm.printProductReport(103);
//        pm.printProductReport(104);
//        pm.printProductReport(105);
//        pm.printProductReport(106);

        // print all products sorted by rating (descending)
        Comparator<Product> ratingSorter = (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal();
        System.out.println("Products sorted by rating (descending):");
        pm.printProducts(ratingSorter);

        // print all products sorted by price (descending)
        Comparator<Product> priceSorter = (p1, p2) -> p2.getPrice().compareTo(p1.getPrice());
        System.out.println("Products sorted by price:");
        pm.printProducts(priceSorter);

        // Combine comparators: sort by rating,then by price
        System.out.println("Products sorted by rating, then by price:");
        pm.printProducts(ratingSorter.thenComparing(priceSorter));

        // Reverse order of comparators: sort by price, then by rating
        System.out.println("Reversing the previous sorting:");
        pm.printProducts(ratingSorter.thenComparing(priceSorter).reversed());

    }
}
