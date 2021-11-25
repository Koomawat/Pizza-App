package pizzeria.pizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Store orders class to keep a list of customer order lists..
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class StoreOrders implements Serializable {

    /**
     * Pizza order array list of type order to keep a list of user orders.
     */
    private ArrayList<Order> pizzaOrders = new ArrayList<Order>();

    /**
     * Adds an order to the pizza orders array list.
     * @param orderToAdd order to add.
     */
    public void addOrder(Order orderToAdd) {
        pizzaOrders.add(orderToAdd);
    }

    /**
     * Removes an order from the pizza orders array list.
     * @param orderToRemove order to remove.
     */
    public void removeOrder(Order orderToRemove) {
        pizzaOrders.remove(orderToRemove);
    }

    /**
     * Pizza orders array list getter.
     * @return pizza orders array list.
     */
    public ArrayList<Order> getOrders() {
        return pizzaOrders;
    }


}
