package pizzeria.pizza;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Abstract Pizza class.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public abstract class Pizza implements Serializable {

    /**
     * ArrayList of type toppings containing the toppings for a given pizza.
     */
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    /**
     * Pizza size variable.
     */
    protected Size size;

    /**
     * Abstract pizza price.
     * @return pizza price.
     */
    public abstract double price();

    /**
     * Size increase amount.
     */
    private static final double sizeIncrease = 2.00;
    /**
     * Additional toppings cost.
     */
    private static final double toppingCost = 1.49;

    /**
     * Size setter.
     * @param pizzaSize size to set.
     */
    public void setSize(Size pizzaSize) {
        size = pizzaSize;
    }

    /**
     * Toppings adder, adds a topping to the toppings array list.
     * @param topping topping to add.
     */
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    /**
     * Toppings remover, removes a topping from the toppings array list.
     * @param topping topping to remove.
     */
    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    /**
     * Size increase price getter.
     * @return price of size increase.
     */
    public double getSizeIncrease() {
        return sizeIncrease;
    }

    /**
     * Additional toppings price getter.
     * @return price of each additional topping.
     */
    public double getToppingCost() {
        return toppingCost;
    }

    /**
     * Returns a textual representation of the pizza information including toppings, size, and price.
     * @return a string containing the pizza information.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        String pizzaInfo = "";
        String pizzaSize = " [Size: " + size.name() + "]";
        String pizzaToppings = "";

        if (toppings.size() != 0) {
            for (int i = 0; i < toppings.size(); i++) {
                if (i == toppings.size() - 1) {
                    pizzaToppings += toppings.get(i).name() + "";
                }
                else {
                    pizzaToppings += toppings.get(i).name() + ", ";
                }
            }
        }

        pizzaInfo = pizzaToppings + pizzaSize + " [Price: $" + df.format(price()) + "]";

        return pizzaInfo;
    }
}