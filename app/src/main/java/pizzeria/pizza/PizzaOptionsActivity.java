package pizzeria.pizza;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

public class PizzaOptionsActivity extends AppCompatActivity {

    private Button order_deluxe_button;
    private Button order_hawaiian_button;
    private Button order_pepperoni_button;
    private String pizzaType;
    private Order order;
    private String customerPhoneNum;
    private StoreOrders ordersList;

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

    public void orderDeluxeClick(View view) {
        Intent deluxeIntent = new Intent(this, PizzaCustomizerActivity.class);
        pizzaType = "deluxe";
        deluxeIntent.putExtra("pizza_type", pizzaType);
        deluxeIntent.putExtra("ORDER", order);
        startActivityForResult(deluxeIntent, 1);

    }

    public void orderHawaiianClick(View view) {
        Intent hawaiianIntent = new Intent(this, PizzaCustomizerActivity.class);
        pizzaType = "hawaiian";
        hawaiianIntent.putExtra("pizza_type",pizzaType);
        hawaiianIntent.putExtra("ORDER", order);
        startActivityForResult(hawaiianIntent, Constants.ORDER_CODE);
    }

    public void orderPepperoniClick(View view) {
        Intent pepperoniIntent = new Intent(this, PizzaCustomizerActivity.class);
        pizzaType = "pepperoni";
        pepperoniIntent.putExtra("pizza_type",pizzaType);
        pepperoniIntent.putExtra("ORDER", order);
        startActivityForResult(pepperoniIntent, Constants.ORDER_CODE);
    }

    public void currentOrderClick(View view) {
        Intent orderIntent = new Intent(this, CurrentOrderActivity.class);

        orderIntent.putExtra("ORDER", order);
        startActivityForResult(orderIntent, Constants.ORDER_CODE);
    }

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