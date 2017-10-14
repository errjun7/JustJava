package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    //the price of whipped cream decided by the company
    //it can change in future
    final int WHIPPEDCREAMPRICE = 1;
    final int CHOCOLATEPRICE = 2;

    //this initialises the quantity of coffees ordered as a global variable
    //so when the user is entering the details of his order, these variables will keep getting updated,
    //and when the order button is called, these values will be used as a state in them.
    int quantity = 0;
    boolean whippedCream = false;
    boolean chocolate = false;

    //the method is executed when the app loads its screen for the first time
    //it also overrides the existing method from the AppCompatActivity
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
        //getting the checkbox data from the xml view via the object hierarchy
        CheckBox cb = (CheckBox) findViewById(R.id.checkbox_whipped_cream);
        whippedCream = cb.isChecked();

        //getting the checkbox data from the xml view via the object hierarchy
        CheckBox c = (CheckBox) findViewById(R.id.checkbox_chocolate);
        chocolate = c.isChecked();

        //local variable price is created
        //calling the calculate price method to separate the logic of calculating.
        int price = calculatePrice();
        //the total price is calculated and kept ready to be sent to the display method
        //calling the buildOrderSummary method to separate the logic
        String priceMessage = buildOrderSummary(price);
        displayMessage(priceMessage);
    }

    private String buildOrderSummary(int price) {
        //we are summoning the editText view and asking it to give us the text entered into it
        //since this ecitText can have various datatypes, we need to mention further what exact data type do we want
        EditText et = (EditText) findViewById(R.id.edit_text_your_name);
        String userName = et.getText().toString();
        String msg = "Hi, " + userName +
                "\nYou have ordered: " + quantity + " cups of delecious coffee" +
                "\nwhich will cost you $" + price;
        if (whippedCream) {
            msg += "\nwith whipped cream";
        } else {
            msg += "\nwithout whipped cream";
        }

        if (chocolate) {
            msg += "\nwith chocolate";
        } else {
            msg += "\nwithout chocolate";
        }

        return msg;
    }

    private int calculatePrice() {
        //coffee's base price
        int coffeeBasePrice = 5;

        //default whipped cream price
        int whippedCreamPrice = 0;

        //default chocolate price
        int chocolatePrice = 0;

        //in case the user has selected to go with the whipped cream, the price will be updated accordingly
        if (whippedCream) {
            whippedCreamPrice = WHIPPEDCREAMPRICE;
        }

        //in case the user has selected to go with the CHOCOLATE, the price will be updated accordingly
        if (chocolate) {
            chocolatePrice = CHOCOLATEPRICE;
        }

        int basicPrice = quantity * (coffeeBasePrice + whippedCreamPrice + chocolatePrice);
        return basicPrice;
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
        priceTextView.setText(message);
    }
}
