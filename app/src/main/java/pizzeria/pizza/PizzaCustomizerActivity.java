package pizzeria.pizza;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Pizza Customizer Activity class to customize a given pizza.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class PizzaCustomizerActivity extends AppCompatActivity {

    /**
     * Image view of the selected pizza type.
     */
    private ImageView chosen_pizza_image;
    /**
     * Additional toppings spinner.
     */
    private Spinner additional_topping_spinner;
    /**
     * Remove toppings spinner.
     */
    private Spinner remove_topping_spinner;
    /**
     * Pizza size spinner.
     */
    private Spinner size_spinner;
    /**
     * Current price text view.
     */
    private TextView current_price;
    /**
     * Current pizza object.
     */
    private Pizza pizza;
    /**
     * Decimal format to format price.
     */
    private DecimalFormat df = new DecimalFormat("###,##0.00");
    /**
     * Additional toppings array list.
     */
    private ArrayList<String> additional = new ArrayList<>();
    /**
     * Additional toppings array adapter.
     */
    private ArrayAdapter<String> additionalAdapter;
    /**
     * Selected toppings array list.
     */
    private ArrayList<String> selected = new ArrayList<>();
    /**
     * Selected toppings array adapter.
     */
    private ArrayAdapter<String> selectedAdapter;
    /**
     * Current order instance.
     */
    private Order order;

    /**
     * On create method to set spinner items and image view.
     *
     * @param savedInstanceState saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_customizer);

        Bundle extras = getIntent().getExtras();
        String type = extras.getString("pizza_type");
        order = (Order) extras.getSerializable("ORDER");

        chosen_pizza_image = findViewById(R.id.chosen_pizza_image);
        additional_topping_spinner = findViewById(R.id.additional_topping_spinner);
        remove_topping_spinner = findViewById(R.id.remove_topping_spinner);
        current_price = findViewById(R.id.current_price);
        size_spinner = findViewById(R.id.size_spinner);

        additionalAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, additional);

        selectedAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, selected);

        String deluxe = "@drawable/deluxe_pizza";
        String hawaiian = "@drawable/hawaiian_pizza";
        String pepperoni = "@drawable/pepperoni_pizza";

        int deluxeImageResource = getResources().getIdentifier(deluxe, null, getPackageName());
        int hawaiianImageResource = getResources().getIdentifier(hawaiian, null, getPackageName());
        int pepperoniImageResource = getResources().getIdentifier(pepperoni, null, getPackageName());

        Drawable deluxeDraw = getResources().getDrawable(deluxeImageResource);
        Drawable hawaiianDraw = getResources().getDrawable(hawaiianImageResource);
        Drawable pepperoniDraw = getResources().getDrawable(pepperoniImageResource);

        if (type.equals("deluxe")) {
            chosen_pizza_image.setImageDrawable(deluxeDraw);
            setTitle("Deluxe Customizer");
            setDeluxeSpinners();
        }
        else if (type.equals("hawaiian")) {
            chosen_pizza_image.setImageDrawable(hawaiianDraw);
            setTitle("Hawaiian Customizer");
            setHawaiianSpinners();
        }
        else {
            chosen_pizza_image.setImageDrawable(pepperoniDraw);
            setTitle("Pepperoni Customizer");
            setPepperoniSpinners();
        }

        pizza = PizzaMaker.createPizza(type);

        size_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                Size pizzaSize = Size.valueOf(size_spinner.getSelectedItem().toString());
                pizza.setSize(pizzaSize);
                double pizzaCost = pizza.price();
                String pizzaCostString = df.format(pizzaCost);
                current_price.setText("$"+pizzaCostString);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                double pizzaCost = pizza.price();
                String pizzaCostString = df.format(pizzaCost);
                current_price.setText("$"+pizzaCostString);
            }
        });
    }

    /**
     * Sets deluxe default spinners choices for toppings.
     */
    public void setDeluxeSpinners() {

        additional.add("Chicken");
        additional.add("Beef");
        additional.add("Ham");
        additional.add("Pineapple");
        additional.add("BlackOlives");
        additional.add("Bacon");

        additionalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        additional_topping_spinner.setAdapter(additionalAdapter);

        selected.add("ItalianSausage");
        selected.add("GreenPepper");
        selected.add("RedOnion");
        selected.add("Pepperoni");
        selected.add("Mushroom");
        selectedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        remove_topping_spinner.setAdapter(selectedAdapter);
    }

    /**
     * Sets hawaiian default spinners choices for toppings.
     */
    public void setHawaiianSpinners() {

        additional.add("Chicken");
        additional.add("Beef");
        additional.add("BlackOlives");
        additional.add("Pepperoni");
        additional.add("Mushroom");
        additional.add("ItalianSausage");
        additional.add("GreenPepper");
        additional.add("RedOnion");

        additionalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        additional_topping_spinner.setAdapter(additionalAdapter);

        selected.add("Pineapple");
        selected.add("Ham");
        selected.add("Bacon");
        selectedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        remove_topping_spinner.setAdapter(selectedAdapter);
    }

    /**
     * Sets pepperoni default spinners choices for toppings.
     */
    public void setPepperoniSpinners() {

        additional.add("Chicken");
        additional.add("Beef");
        additional.add("BlackOlives");
        additional.add("Pineapple");
        additional.add("Mushroom");
        additional.add("ItalianSausage");
        additional.add("GreenPepper");
        additional.add("RedOnion");
        additional.add("Ham");
        additional.add("Bacon");

        additionalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        additional_topping_spinner.setAdapter(additionalAdapter);

        selected.add("Pepperoni");
        selectedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        remove_topping_spinner.setAdapter(selectedAdapter);
    }

    /**
     * Price update method.
     */
    public void updatePrice() {
        double pizzaCost = pizza.price();
        String pizzaCostString = df.format(pizzaCost);
        current_price.setText("$"+pizzaCostString);
    }

    /**
     * Add pizza to order button click handler to add a pizza to the order instance and return to previous activity.
     */
    public void addPizzaToOrder(View view) {
        Intent intent = getIntent();
        Order order = (Order) intent.getSerializableExtra("ORDER");
        order.addPizza(pizza);

        Toast.makeText(PizzaCustomizerActivity.this,
                "Pizza Added to Order.",
                Toast.LENGTH_SHORT).show();

        Intent result = new Intent();
        result.putExtra("ORDERreturn", order);
        setResult(RESULT_OK, result);
        finish();
    }

    /**
     * Add topping click handler to add a given topping and adjust spinners accordingly.
     *
     * @param view view.
     */
    public void addToppingClick(View view) {

        if (selected.size() == Constants.MAX_TOPPINGS) {
            Toast.makeText(PizzaCustomizerActivity.this,
                    "Maximum of 7 toppings.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String topping = additional_topping_spinner.getSelectedItem().toString();
        additional.remove(topping);
        selected.add(topping);
        additionalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        additional_topping_spinner.setAdapter(additionalAdapter);
        selectedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        remove_topping_spinner.setAdapter(selectedAdapter);
        pizza.addTopping(Topping.valueOf(topping));
        updatePrice();
    }

    /**
     * Remove topping click handler to remove a given topping and adjust spinners accordingly.
     *
     * @param view view.
     */
    public void removeToppingClick(View view) {

        if (selected.size() == Constants.MIN_TOPPINGS) {
            Toast.makeText(PizzaCustomizerActivity.this,
                    "Minimum of 1 topping.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String topping = remove_topping_spinner.getSelectedItem().toString();
        additional.add(topping);
        selected.remove(topping);
        additionalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        additional_topping_spinner.setAdapter(additionalAdapter);
        selectedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        remove_topping_spinner.setAdapter(selectedAdapter);
        pizza.removeTopping(Topping.valueOf(topping));
        updatePrice();
    }


}