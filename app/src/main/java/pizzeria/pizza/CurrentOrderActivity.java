package pizzeria.pizza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Current Order Activity class to view the current customer's order of pizzas.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class CurrentOrderActivity extends AppCompatActivity {

    /**
     * Orders list view.
     */
    private ListView orders_listview;
    /**
     * Pizza spinner array adapter.
     */
    private ArrayAdapter<String> pizzasAdapter;
    /**
     * Pizza list array adapter.
     */
    private ArrayAdapter<String> listAdapter;
    /**
     * Pizzas string array list.
     */
    private ArrayList<String> pizzasStrings = new ArrayList<>();
    /**
     * Array list of pizzas.
     */
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    /**
     * Spinner to select a given pizza.
     */
    private Spinner pizza_list_spinner;
    /**
     * Subtotal of current order.
     */
    private double subtotalAmount;
    /**
     * Sales tax of current order.
     */
    private double salesTaxAmount;
    /**
     * Total amount of current order.
     */
    private double totalAmount;
    /**
     * Subtotal text view.
     */
    private TextView subtotal_view;
    /**
     * Sales tax text view.
     */
    private TextView sales_tax_view;
    /**
     * Order total text view.
     */
    private TextView order_total_view;
    /**
     * Decimal format to format price.
     */
    private DecimalFormat df = new DecimalFormat("###,##0.00");
    /**
     * Order instance.
     */
    private Order order;

    /**
     * On create method to set spinners and list view items.
     *
     * @param savedInstanceState saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_order);
        setTitle("Current Order");

        Intent intent = getIntent();

        order = (Order) intent.getSerializableExtra("ORDER");

        orders_listview = findViewById(R.id.orders_listview);
        pizza_list_spinner = findViewById(R.id.pizza_list_spinner);
        subtotal_view = findViewById(R.id.subtotal_view);
        sales_tax_view = findViewById(R.id.sales_tax_view);
        order_total_view = findViewById(R.id.order_total_view);

        pizzas = order.getPizzas();

        listAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                pizzas
        );

        orders_listview.setAdapter(listAdapter);

        for (int i = 0; i < pizzas.size(); i++) {
            pizzasStrings.add(pizzas.get(i).toString());
        }

        pizzasAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, pizzasStrings);

        pizza_list_spinner.setAdapter(pizzasAdapter);

        for(int i = 0; i < order.getPizzas().size(); i++) {
            subtotalAmount += order.getPizzas().get(i).price();
        }

        String subtotal = df.format(subtotalAmount);
        subtotal_view.setText("$"+subtotal);

        salesTaxAmount = subtotalAmount * Constants.SALES_TAX;
        String salesTax = df.format(salesTaxAmount);
        sales_tax_view.setText("$"+salesTax);

        totalAmount = salesTaxAmount + subtotalAmount;
        String total = df.format(totalAmount);
        order_total_view.setText("$"+total);
    }

    /**
     * Removes a given pizza based on the selected spinner item.
     *
     * @param view view.
     */
    public void removeSelectedClick(View view) {

        if (order.getPizzas().size() == 0) {
            Toast.makeText(CurrentOrderActivity.this,
                    "No Pizzas to Remove.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int pizzaNumber = pizza_list_spinner.getSelectedItemPosition();
        order.removePizza(order.getPizzas().get(pizzaNumber));

        listAdapter = null;
        orders_listview.setAdapter(listAdapter);
        pizzasAdapter = null;
        pizza_list_spinner.setAdapter(pizzasAdapter);

        pizzas = order.getPizzas();

        listAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                pizzas
        );

        orders_listview.setAdapter(listAdapter);

        pizzasStrings.clear();

        for (int i = 0; i < pizzas.size(); i++) {
            pizzasStrings.add(pizzas.get(i).toString());
        }

        pizzasAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, pizzasStrings);

        pizza_list_spinner.setAdapter(pizzasAdapter);

        subtotalAmount = 0;
        salesTaxAmount = 0;
        totalAmount = 0;

        for(int i = 0; i < order.getPizzas().size(); i++) {
            subtotalAmount += order.getPizzas().get(i).price();
        }

        String subtotal = df.format(subtotalAmount);
        subtotal_view.setText("$"+subtotal);

        salesTaxAmount = subtotalAmount * Constants.SALES_TAX;
        String salesTax = df.format(salesTaxAmount);
        sales_tax_view.setText("$"+salesTax);

        totalAmount = salesTaxAmount + subtotalAmount;
        String total = df.format(totalAmount);
        order_total_view.setText("$"+total);

        Toast.makeText(CurrentOrderActivity.this,
                "Pizza Removed.",
                Toast.LENGTH_SHORT).show();

        Intent result = new Intent();
        result.putExtra("ORDERreturn", order);
        setResult(RESULT_OK, result);
    }

    /**
     * Order place button click handler to add the order to store orders.
     *
     * @param view view.
     */
    public void placeOrderClick(View view) {
        if (order.getPizzas().size() == 0) {
            Toast.makeText(CurrentOrderActivity.this,
                    "No Pizzas in Order.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent result = new Intent();
        result.putExtra("ORDERreturn", order);
        boolean orderPlaced = true;
        result.putExtra("PLACED", orderPlaced);
        setResult(RESULT_OK, result);

        Toast.makeText(CurrentOrderActivity.this,
                "Order Placed.",
                Toast.LENGTH_SHORT).show();

        finish();
    }
}
