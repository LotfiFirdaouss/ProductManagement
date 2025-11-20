package labs.pm.data;

@FunctionalInterface // it satisfies the requirements of a functional interface (one abstract method)
public interface Rateable<T> {
    public static final Rating DEFAULT_RATING = Rating.NOT_RATED;

    // we ommit 'public abstract' because by default all methods in interface are public abstract
    T applyRating(Rating rating);

    public default T applyRating(int stars){
        return applyRating(convert(stars)); // call the other method that will be implemented in the subclass (T)
    }

    public default Rating getRating() {
        return DEFAULT_RATING;
    }

    public static Rating convert(int stars) {
        return (stars>=0 && stars<=5) ? Rating.values()[stars] : DEFAULT_RATING;
    }


}
