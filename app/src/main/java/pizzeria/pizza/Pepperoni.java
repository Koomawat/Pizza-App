package pizzeria.pizza;

/**
 * Pepperoni class extending Pizza.
 * Creates a default pepperoni pizza with 5 toppings and calculates price.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class Pepperoni extends Pizza{

    /**
     * Small pepperoni pizza price amount.
     */
    private static final double smallPrice = 8.99;
    /**
     * Store customized toppings amount for a pepperoni pizza.
     */
    private static final int baseToppingsAmount = 1;
    /**
     * Current amount of toppings on the deluxe pizza for use if the customer changes toppings.
     */
    private int toppingsAmount;

    /**
     * Pepperoni pizza constructor.
     */
    public Pepperoni () {
        toppings.add(Topping.Pepperoni);
    }

    /**
     * Pepperoni pizza price calculator.
     * @return the price of the pepperoni pizza.
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
        return "Pepperoni pizza: " + super.toString();
    }
}
