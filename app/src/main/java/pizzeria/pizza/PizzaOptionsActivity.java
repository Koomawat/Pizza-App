package pizzeria.pizza;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

/**
 * Pizza Options Activity class where the user selects a pizza type or can select to view their current order.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class PizzaOptionsActivity extends AppCompatActivity {

    /**
     * Order Deluxe button.
     */
    private Button order_deluxe_button;
    /**
     * Order Hawaiian button.
     */
    private Button order_hawaiian_button;
    /**
     * Order Pepperoni button.
     */
    private Button order_pepperoni_button;
    /**
     * Pizza type string.
     */
    private String pizzaType;
    /**
     * Order instance.
     */
    private Order order;
    /**
     * Customer phone number string.
     */
    private String customerPhoneNum;
    /**
     * Store orders instance.
     */
    private StoreOrders ordersList;

    /**
     * On create method to set the customer phone and initialize the order instance.
     *
     * @param savedInstanceState saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_options);
        setTitle("Pizza Options");

        Bundle extras = getIntent().getExtras();

        customerPhoneNum = extras.getString("phone_num");
        ordersList = (StoreOrders) extras.getSerializable("store_order");

        order_deluxe_button = findViewById(R.id.order_deluxe_button);

        order = new Order(customerPhoneNum);
    }

    /**
     * Order deluxe click handler. Starts the pizza customizer activity.
     *
     * @param view view.
     */
    public void orderDeluxeClick(View view) {
        Intent deluxeIntent = new Intent(this, PizzaCustomizerActivity.class);
        pizzaType = "deluxe";
        deluxeIntent.putExtra("pizza_type", pizzaType);
        deluxeIntent.putExtra("ORDER", order);
        startActivityForResult(deluxeIntent, 1);

    }

    /**
     * Order hawaiian click handler. Starts the pizza customizer activity.
     *
     * @param view view.
     */
    public void orderHawaiianClick(View view) {
        Intent hawaiianIntent = new Intent(this, PizzaCustomizerActivity.class);
        pizzaType = "hawaiian";
        hawaiianIntent.putExtra("pizza_type",pizzaType);
        hawaiianIntent.putExtra("ORDER", order);
        startActivityForResult(hawaiianIntent, Constants.ORDER_CODE);
    }

    /**
     * Order pepperoni click handler. Starts the pizza customizer activity.
     *
     * @param view view.
     */
    public void orderPepperoniClick(View view) {
        Intent pepperoniIntent = new Intent(this, PizzaCustomizerActivity.class);
        pizzaType = "pepperoni";
        pepperoniIntent.putExtra("pizza_type",pizzaType);
        pepperoniIntent.putExtra("ORDER", order);
        startActivityForResult(pepperoniIntent, Constants.ORDER_CODE);
    }

    /**
     * Current order click handler. Starts the current order activity.
     *
     * @param view view.
     */
    public void currentOrderClick(View view) {
        Intent orderIntent = new Intent(this, CurrentOrderActivity.class);

        orderIntent.putExtra("ORDER", order);
        startActivityForResult(orderIntent, Constants.ORDER_CODE);
    }

    /**
     * On activity result method to set item based on what was return from the child activity.
     *
     * @param requestCode activity request code.
     * @param resultCode activity result code.
     * @param data data retrieved from activity.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.ORDER_CODE) {
            if (resultCode == RESULT_OK) {
                order = (Order) data.getSerializableExtra("ORDERreturn");
                boolean placed = data.getBooleanExtra("PLACED", false);
                if (placed == true) {
                    ordersList.addOrder(order);
                    Intent result = new Intent();
                    result.putExtra("storeOrder", ordersList);
                    setResult(RESULT_OK, result);
                    finish();
                }
            }
        }
    }
}