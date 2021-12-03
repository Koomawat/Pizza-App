package pizzeria.pizza;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main Activity class where the user can create a new order or view a list of store orders.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Create order button.
     */
    private Button create_order_button;
    /**
     * Store orders button.
     */
    private Button store_orders_button;
    /**
     * Customer phone input field.
     */
    private EditText customer_phone;
    /**
     * Current phone number string.
     */
    private String currentPhoneNumber;
    /**
     * Store orders instance.
     */
    private StoreOrders listOfOrders;

    /**
     * On create method that initializes the store orders instance.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("RU Pizzeria");
        create_order_button = findViewById(R.id.create_order_button);
        store_orders_button = findViewById(R.id.store_orders_button);
        customer_phone = findViewById(R.id.customer_phone);
        listOfOrders = new StoreOrders();
    }

    /**
     * Create order button handler to send the user to the Pizza options acivity.
     *
     * @param view
     */
    public void createOrder(View view) {
        Intent customerOrder = new Intent(this, PizzaOptionsActivity.class);
        currentPhoneNumber = customer_phone.getText().toString();

        if (TextUtils.isEmpty(customer_phone.getText().toString()))
        {
            Toast.makeText(MainActivity.this,
                    "Empty field not allowed!",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,
                    "Creating order for: " + customer_phone.getText().toString(),
                    Toast.LENGTH_SHORT).show();
            customerOrder.putExtra("phone_num", currentPhoneNumber);
            customerOrder.putExtra("store_order", listOfOrders);
            startActivityForResult(customerOrder, Constants.STORE_ORDER_CODE);
        }
    }

    /**
     * Store orders button handler to send the user to the Store orders activity.
     *
     * @param view
     */
    public void storeOrdersClick(View view) {
        Intent storeOrders = new Intent(this, StoreOrdersActivity.class);
        storeOrders.putExtra("store_orders",listOfOrders);
        startActivityForResult(storeOrders, Constants.STORE_ORDER_CODE);
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
        if (requestCode == Constants.STORE_ORDER_CODE) {
            if (resultCode == RESULT_OK) {
                listOfOrders = (StoreOrders) data.getSerializableExtra("storeOrder");
            }
        }
    }
}