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

/**
 * Store Orders Activity class to view the list of customers and their list of pizzas.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class StoreOrdersActivity extends AppCompatActivity {

    /**
     * Store orders instance.
     */
    private StoreOrders listOfOrders;
    /**
     * Current customer's list view of pizzas.
     */
    private ListView currentPhoneOrderList;
    /**
     * Phone numbers spinner.
     */
    private Spinner phoneNumsSpinner;
    /**
     * Order total text view.
     */
    private TextView orderPriceView;
    /**
     * Phone numbers array adapter.
     */
    private ArrayAdapter<String> phoneNumsAdapter;
    /**
     * Phone numbers string array list.
     */
    private ArrayList<String> phoneNumsStrings = new ArrayList<>();
    /**
     * List adapter to display the current number's pizzas.
     */
    private ArrayAdapter<String> listAdapter;
    /**
     * Pizzas array list of the current number.
     */
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    /**
     * Decimal format to format price.
     */
    private DecimalFormat df = new DecimalFormat("###,##0.00");
    /**
     * Current selected spinner item index.
     */
    private int currentItem;

    /**
     * On create method to set spinners and list view items.
     *
     * @param savedInstanceState saved instance state.
     */
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

    /**
     * Cancel button click handler to delete the current phone number's order.
     *
     * @param view view.
     */
    public void cancelOrderClick(View view) {

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

    /**
     * Back press handler to keep store order instance.
     *
     */
    @Override
    public void onBackPressed(){
        Intent result = new Intent();
        result.putExtra("storeOrder", listOfOrders);
        setResult(RESULT_OK, result);
        finish();
        super.onBackPressed();
    }

    /**
     * Upper back button press handler to keep store order instance.
     *
     * @param item item.
     * @return true if upper back button pressed.
     */
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
