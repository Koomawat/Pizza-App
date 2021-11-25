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

public class MainActivity extends AppCompatActivity {

    private Button create_order_button;
    private Button store_orders_button;
    private EditText customer_phone;
    private String currentPhoneNumber;

    private StoreOrders listOfOrders;

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