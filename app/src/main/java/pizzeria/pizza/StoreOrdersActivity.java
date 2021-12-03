package pizzeria.pizza;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity {

    private StoreOrders listOfOrders;

    private ListView currentPhoneOrderList;
    private Spinner phoneNumsSpinner;
    private TextView orderPriceView;
    private ArrayAdapter<String> phoneNumsAdapter;
    private ArrayList<String> phoneNumsStrings = new ArrayList<>();
    private ArrayAdapter<String> listAdapter;
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("###,##0.00");
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders);
        setTitle("Store Orders");

        Bundle extras = getIntent().getExtras();
        listOfOrders = (StoreOrders) extras.getSerializable("store_orders");
        System.out.println(listOfOrders.getOrders().size());

        currentPhoneOrderList = findViewById(R.id.currentPhoneOrderList);
        phoneNumsSpinner = findViewById(R.id.phoneNumsSpinner);
        orderPriceView = findViewById(R.id.orderPriceView);

        for (int i = 0; i < listOfOrders.getOrders().size(); i++) {
            phoneNumsStrings.add(listOfOrders.getOrders().get(i).getPhoneNumber());
        }

        phoneNumsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, phoneNumsStrings);

        phoneNumsSpinner.setAdapter(phoneNumsAdapter);

        phoneNumsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                pizzas = listOfOrders.getOrders().get(position).getPizzas();

                listAdapter = new ArrayAdapter(
                        StoreOrdersActivity.this,
                        android.R.layout.simple_list_item_1,
                        pizzas
                );

                currentPhoneOrderList.setAdapter(listAdapter);

                double price = 0;
                double subtotalAmount = 0;
                double salesTaxAmount = 0;

                for(int i = 0; i < listOfOrders.getOrders().get(position).getPizzas().size(); i++) {
                    subtotalAmount += listOfOrders.getOrders().get(position).getPizzas().get(i).price();
                }

                salesTaxAmount = subtotalAmount * Constants.SALES_TAX;
                price = subtotalAmount + salesTaxAmount;
                String total = df.format(price);
                orderPriceView.setText("$"+total);

                currentItem = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }

    public void storeOrderClicked(View view) {

        if (listOfOrders.getOrders().size() != 0) {
            Intent result = new Intent();

            Order orderToRemove = listOfOrders.getOrders().get(currentItem);
            listOfOrders.removeOrder(orderToRemove);

            result.putExtra("storeOrder", listOfOrders);
            setResult(RESULT_OK, result);

            Toast.makeText(StoreOrdersActivity.this,
                    "Order Canceled.",
                    Toast.LENGTH_SHORT).show();

            finish();
        }
        else {
            Toast.makeText(StoreOrdersActivity.this,
                    "No Orders To Cancel.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed(){
        Intent result = new Intent();
        result.putExtra("storeOrder", listOfOrders);
        setResult(RESULT_OK, result);
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent result = new Intent();
        result.putExtra("storeOrder", listOfOrders);
        setResult(RESULT_OK, result);
        finish();
        super.onBackPressed();
        return true;
    }
}
