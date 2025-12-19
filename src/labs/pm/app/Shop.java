package labs.pm.app;

import labs.pm.data.Product;
import labs.pm.data.ProductManager;
import labs.pm.data.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

/**
 * {@code Shop} class represents an application that manages Products
 * @version 4.0
 * @author Firdaouss
 */
public class Shop {
    public static void main(String[] args) {
        ProductManager pm = new ProductManager(Locale.UK);

        pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.THREE_STAR);
        pm.printProductReport(101);
        pm.reviewProduct(101, Rating.FOUR_STAR, "Nice hot cup of tea");
        pm.printProductReport(101);
        pm.reviewProduct(101, Rating.THREE_STAR, "Good tea but a bit weak");
        pm.reviewProduct(101, Rating.FIVE_STAR, "Perfect tea");
        pm.reviewProduct(101, Rating.TWO_STAR, "Too cold");
        pm.printProductReport(101);

        pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.FOUR_STAR);
        pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));
        pm.createProduct(104, "Cookie", BigDecimal.valueOf(3.99), Rating.TWO_STAR, LocalDate.now());
        pm.createProduct(105, "Chocolate", BigDecimal.valueOf(1.99), Rating.FIVE_STAR);
        pm.createProduct(106, "Chocolate", BigDecimal.valueOf(1.99), Rating.FIVE_STAR,  LocalDate.now().plusDays(2).plusDays(3));

        pm.printProductReport(102);
        pm.printProductReport(103);
        pm.printProductReport(104);
        pm.printProductReport(105);
        pm.printProductReport(106);



 }
}
