package labs.pm.data;

import java.math.BigDecimal;

public class Drink extends Product{
    // once we removed the public access modifier, the constructor became package-private
    Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
    }
}
