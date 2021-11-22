package pizzeria.pizza;

/**
 * Hawaiian class extending Pizza.
 * Creates a default hawaiian pizza with 5 toppings and calculates price.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class Hawaiian extends Pizza{

    /**
     * Small hawaiian pizza price amount.
     */
    private static final double smallPrice = 10.99;
    /**
     * Store customized toppings amount for a hawaiian pizza.
     */
    private static final int baseToppingsAmount = 3;
    /**
     * Current amount of toppings on the deluxe pizza for use if the customer changes toppings.
     */
    private int toppingsAmount;

    /**
     * Hawaiian pizza constructor.
     */
    public Hawaiian() {
        toppings.add(Topping.Pineapple);
        toppings.add(Topping.Ham);
        toppings.add(Topping.Bacon);
    }

    /**
     * Hawaiian pizza price calculator.
     * @return the price of the hawaiian pizza.
     */
    public double price() {
        double pizzaPrice = 0;

        if (size.equals(Size.Small)) {
            pizzaPrice += smallPrice;
        }
        else if (size.equals(Size.Medium)) {
            pizzaPrice += smallPrice + getSizeIncrease();
        }
        else {
            pizzaPrice += smallPrice + getSizeIncrease() + getSizeIncrease();
        }

        if (toppings.size() > baseToppingsAmount) {
            toppingsAmount = toppings.size() - baseToppingsAmount;
            for (int i = 0; i < toppingsAmount; i++) {
                pizzaPrice += (getToppingCost());
            }
        }

        return pizzaPrice;
    }

    /**
     * Returns a textual representation of the pizza information including toppings, size, and price.
     * @return a string containing the pizza information.
     */
    @Override
    public String toString() {
        return "Hawaiian pizza: " + super.toString();
    }
}
