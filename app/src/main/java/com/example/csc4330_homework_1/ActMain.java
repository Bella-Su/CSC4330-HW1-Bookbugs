//====================================================================
//
// Application: Book Bugs E-book Order Application
// Activity:    ActMain
// Description:
//   This Android application enables the user to order an e-book from the company.
//
//====================================================================
package com.example.csc4330_homework_1;

// Import packages
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

//--------------------------------------------------------------------
// class ActMain
//--------------------------------------------------------------------
public class ActMain extends AppCompatActivity{

    //------------------------------------------------------------------------
    // declare constants and variables
    //------------------------------------------------------------------------
    public static final String APP_NAME = "Book Bugs";
    public static final String APP_VERSION = "1.0";
    private EditText txtCustomerName;
    private EditText txtCustomerEmail;
    private EditText txtBookSelected;
    private EditText txtBookCost;
    private EditText txtTaxCost;
    private EditText txtTotalCost;
    private Toast toast;
    private String creditType;
    private RadioGroup rdGroup;

    //----------------------------------------------------------------------
    // onCreate
    //----------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laymain);

        //define controls
         txtBookSelected = (EditText) findViewById(R.id.txtBookSelected);
         txtCustomerName = (EditText) findViewById(R.id.txtCustomerName);
         txtCustomerEmail = (EditText) findViewById(R.id.txtCustomerEmail);
         txtBookCost = (EditText) findViewById(R.id.txtBookCost);
         txtTaxCost = (EditText) findViewById(R.id.txtTaxCost);
         txtTotalCost = (EditText) findViewById(R.id.txtTotalCost);
         rdGroup = (RadioGroup) findViewById(R.id.rdGroup);
    }

    //------------------------------------------------------------------------
    // showBookListDialogBox
    //------------------------------------------------------------------------
    final String[] books= {"The Little Price   $5.99", "The Lion   $5.99", "The Witch   $6.99",
            "The Wardrobe   $6.99", "The Snow White   $7.99", "Happy Monster   $7.99",
            "Bunny Run   $8.99", "Ms Fox   $8.99"};
    public void showBookListDialogBox(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Book Options");
        builder.setSingleChoiceItems(books, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //use switch case here for selected book and price
                String s = "";
                String p = "";
                switch(which)
                {
                    case 0:
                        s = "The Little Prince";
                        p = "5.99";
                        break;
                    case 1:
                        s = "The Lion";
                        p = "5.99";
                        break;
                    case 2:
                        s = "The Witch";
                        p = "6.99";
                        break;
                    case 3:
                        s = "The Wardrobe";
                        p = "6.99";
                        break;
                    case 4:
                        s = "The Snow White";
                        p = "7.99";
                        break;
                    case 5:
                        s = "Happy Monster";
                        p = "7.99";
                        break;
                    case 6:
                        s = "Bunny Run";
                        p = "8.99";
                        break;
                    case 7:
                        s = "Ms Fox";
                        p = "8.99";
                        break;
                }
                //show book selected and book cost
                txtBookSelected.setText(s);
                txtBookCost.setText(p);

                //cast and store tax cost of book
                //EditText txtBookCost = (EditText) findViewById(R.id.txtBookCost);
                String bookCostStr = txtBookCost.getText().toString();
                double bookCostDouble = Double.parseDouble(bookCostStr);
                double taxCost = bookCostDouble *0.06;
                txtTaxCost.setText(Double.toString(taxCost));

                //store total cost value
                double totalCost = bookCostDouble+taxCost;
                txtTotalCost.setText(Double.toString(totalCost));
            }
        });
        builder.show();
    }

    //------------------------------------------------------------------------
    // onRadioButtonClicked
    //------------------------------------------------------------------------
    public void onRadioButtonClicked(View v)
    {
        //Declare variables
        boolean checked = ((RadioButton) v).isChecked();

        //Select which payment type be picked
        switch (v.getId())
        {
            case R.id.rbPayPal:
                if(checked)
                    creditType="PayPal";
                break;
            case R.id.rbCreditCard:
                if(checked)
                    creditType="Credit Card";
                break;
            case R.id.rbDebitCard:
                if(checked)
                    creditType="Debit Card";
                break;
        }
    }

    //-----------------------------------------------------------------------
    // onSubmitButtonClicked
    //-----------------------------------------------------------------------
    public void onSubmitButtonClicked(View v)
    {
        toast = Toast.makeText(getApplicationContext(),
                "Book selected:  " + txtBookSelected.getText().toString()+
                "\nCustomer Name:  " + txtCustomerName.getText().toString()+
                "\nCustomer E-mail:  " + txtCustomerEmail.getText().toString()+
                "\nBook Cost:  $"+ txtBookCost.getText().toString()+
                "\nTax Cost:  $" + txtTaxCost.getText().toString()+
                "\nTotal Cost:  $" + txtTotalCost.getText().toString()+
                "\nCredit Type:  " + creditType, Toast.LENGTH_LONG);
        toast.show();
    }

    //------------------------------------------------------------------------
    // onResetButtonClicked
    //------------------------------------------------------------------------
    public void onResetButtonClicked(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder((v.getContext()));
        builder.setTitle(("REST"));
        builder.setMessage("Do you want to reset?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txtBookSelected.setText("");
                txtCustomerName.setText("");
                txtCustomerEmail.setText("");
                txtBookCost.setText("");
                txtTaxCost.setText("");
                txtTotalCost.setText("");
                rdGroup.clearCheck();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
}