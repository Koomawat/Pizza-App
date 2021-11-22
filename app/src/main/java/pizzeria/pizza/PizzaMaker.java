package pizzeria.pizza;

/**
 * Pizza maker class to create an instance of subclasses based on the chosen flavor.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class PizzaMaker {
    /**
     * Creates a pizza instance based on the provided flavor.
     * @param flavor the flavor or the pizza instance to create.
     * @return the created pizza instance.
     */
    public static Pizza createPizza(String flavor) {
        switch (flavor) {
            case "deluxe": {
                return new Deluxe();
            }
            case "hawaiian": {
                return new Hawaiian();
            }
            default: {
                return new Pepperoni();
            }
        }
    }
}
