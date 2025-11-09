package labs.pm.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * {@code Product} class represents properties and behaviors of
 * product objects in the Product Management System.
 * <br>
 * Each product has an id, name, and price.
 * <br>
 * Each product can have a discount, calculated based on a
 * {@link #DISCOUNT_RATE discount rate}.
 *
 * @version 4.0
 * @author Firdaouss
 */
public class Product {
    /**
     * A constant that defines a
     * {@link java.math.BigDecimal BigDecimal value of the discount rate}
     * <br>
     * Discount rate is 10%
     */
    public static final BigDecimal DISCOUNT_RATE = new BigDecimal(0.1);
    public final int id;
    public final String name;
    public final BigDecimal price;
    public final Rating rating;

    // once we removed the public access modifier, the constructor became package-private
    Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

/*    public Product(int id, String name, BigDecimal price) {
        this(id,name,price, Rating.NOT_RATED);
    }

    public Product(){
        this(0, "",BigDecimal.ZERO);
    }*/

    public Rating getRating() {
        return rating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Product applyRating(Rating newRating) {
        return new Product(id, name, price, newRating);
    }

    /**
     * Calculates discount based on a product price and
     * {@link DISCOUNT_RATE discount_rate}
     * @return a {@link java.math.BigDecimal BigDecimal}
     * value of the discount
     */
    public BigDecimal getDiscount() {
        return price.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
    }
}
