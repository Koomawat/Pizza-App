package pizzeria.pizza;

/**
 * Deluxe class extending Pizza.
 * Creates a default deluxe pizza with 5 toppings and calculates price.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class Deluxe extends Pizza{

    /**
     * Small deluxe pizza price amount.
     */
    private static final double smallPrice = 12.99;
    /**
     * Store customized toppings amount for a deluxe pizza.
     */
    private static final int baseToppingsAmount = 5;
    /**
     * Current amount of toppings on the deluxe pizza for use if the customer changes toppings.
     */
    private int toppingsAmount;

    /**
     * Deluxe pizza constructor.
     */
    public Deluxe() {
        toppings.add(Topping.ItalianSausage);
        toppings.add(Topping.GreenPepper);
        toppings.add(Topping.RedOnion);
        toppings.add(Topping.Pepperoni);
        toppings.add(Topping.Mushroom);
    }

    /**
     * Deluxe pizza price calculator.
     * @return the price of the deluxe pizza.
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
        return "Deluxe pizza: " + super.toString();
    }
}
