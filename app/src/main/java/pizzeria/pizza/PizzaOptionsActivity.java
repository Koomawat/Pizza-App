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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_options);
        setTitle("Pizza Options");


        Bundle extras = getIntent().getExtras();

        customerPhoneNum = extras.getString("phone_num");


        System.out.println(customerPhoneNum);
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
        startActivityForResult(hawaiianIntent, 1);
    }

    public void orderPepperoniClick(View view) {
        Intent pepperoniIntent = new Intent(this, PizzaCustomizerActivity.class);
        pizzaType = "pepperoni";
        pepperoniIntent.putExtra("pizza_type",pizzaType);
        pepperoniIntent.putExtra("ORDER", order);
        startActivityForResult(pepperoniIntent, 1);
    }

    public void currentOrderClick(View view) {
        Intent orderIntent = new Intent(this, CurrentOrderActivity.class);

        System.out.println(order.getPizzas().size());
        orderIntent.putExtra("ORDER", order);
        startActivityForResult(orderIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                order = (Order) data.getSerializableExtra("ORDERreturn");
            }
        }
    }


}