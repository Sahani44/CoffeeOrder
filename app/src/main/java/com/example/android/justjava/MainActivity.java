/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends android.app.Activity {

    int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        if(a < 100)
            display(++a);
        else
            display(a);
    }

    public void decrement(View view) {
        if (a != 0)
            display(--a);
        else
            display(a);
    }

    public void submitOrder(View view) {
        //displayPrice(a * 5);
        String priceMessage;
        CheckBox checkbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocalate);
        EditText name = (EditText) findViewById(R.id.customer_name);
        Editable cname = name.getText();
        if(a==0 || a > 99) {
            priceMessage = "";
            Toast.makeText(this,"Less than One or greater than 99 orders not accepted",Toast.LENGTH_SHORT).show();

        }
        else{
            String cb = "";String cl = "";
            if(checkbox.isChecked())
                cb = "\nWhipped Cream Added";
            if(chocolate.isChecked())
                cl = "\nChocolate Added";
            priceMessage = "Name: "+ cname.toString() + "\nQuantity: " + a + cb + cl +"\nTotal - Rs "+ calculatePrice(a,checkbox.isChecked(),chocolate.isChecked())  +"\nThank You";
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + cname);
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            startActivity(intent);
        }

//        if (intent.resolveActivity(getPackageManager()) != null) {  <-- this line is causing some error
//     this line also works --> startActivity(Intent.createChooser(intent, "Send Mail Using :"));
//        }
//        displayMessage(priceMessage);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6,-122.3"));
//        if(intent.resolveActivity(getPackageManager()) != null){
//            startActivity(intent);
//        }
    }

    private int calculatePrice(int quantity,boolean whipped,boolean choco) {
        int bp = 5;
        if(whipped)
            bp++;
        if(choco)
            bp += 2;
        return quantity * bp;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}