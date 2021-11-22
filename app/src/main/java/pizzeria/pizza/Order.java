package pizzeria.pizza;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Order class to create a list of pizzas for a given phone number.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class Order implements Serializable {

    /**
     * ArrayList of type Pizza for the list of pizzas.
     */
    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    /**
     * Customer's phone number.
     */
    private String phoneNumber;

    /**
     * Constructor which sets the phone number.
     * @param number customer phone number.
     */
    public Order(String number) {
        this.phoneNumber = number;
    }

    /**
     * Pizza adder to add a pizza to the array list.
     * @param pizza pizza to add.
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Pizza remover to remove a pizza to the array list.
     * @param pizza pizza to remove.
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /**
     * Pizza array list getter.
     * @return array list of pizzas.
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Phone number getter to get the customer phone number.
     * @return the phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
