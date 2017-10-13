package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    //this initialises the quantity of coffees ordered as a global variable
    int quantity = 0;

    //the method is executed when the app loads its screen for the first time
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //the main xml activity is painted on the screen
        //this is how the main activity java file knows which layout to print out on the screem when the application loads up
        //while parsing the xml file, in order to make the xml tags understandable in java, it starts constructing a hierarchy tree of java objects
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //local variable price is created
        int price = quantity * 5;
        //the total price is calculated and kept ready to be sent to the display method
        String priceMessage = "Total: $" + price;
        displayMessage(priceMessage);
    }

    //when the + button is clicked
    public void increment(View view) {
        quantity++;
        //the number showing the current quantity of coffees is displayed
        // and updated with the latest value upon clicking the button
        //also, here we get the element from the activity.main with the specified id,
        //and convert it to a java object, set the text, change it back to the xml file, and display on the screen
        TextView quantityTextView = (TextView) findViewById(R.id.text_view_quantity_value);
        quantityTextView.setText("" + quantity);
    }

    //when the - button is clicked
    public void decrement(View view) {
        quantity--;
        //the number showing the current quantity of coffees is displayed
        // and updated with the latest value upon clicking the button
        TextView quantityTextView = (TextView) findViewById(R.id.text_view_quantity_value);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        //the summary value element is feteched via id and
        //the text is updated with the calculated value and the updated coffees ordered.
        TextView priceTextView = (TextView) findViewById(R.id.text_view_order_summary_value);
        priceTextView.setText("You have ordered " + quantity + " cups of coffee!\n" + message + "\nThank You!");
    }
}