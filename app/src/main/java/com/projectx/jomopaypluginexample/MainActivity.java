package com.projectx.jomopaypluginexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projectx.jomopay.main.CheckOut;
import com.projectx.jomopay.main.paymentListener;

/**
 * Created by hussamhyari on 1/23/18.
 */

public class MainActivity extends Activity implements View.OnClickListener {
    CheckOut checkOut;
    EditText amountEt;
    double amount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEt = (EditText) findViewById(R.id.editText3);
        Button payBtn = (Button) findViewById(R.id.button);
        payBtn.setOnClickListener(this);

        checkOut = new CheckOut(this);
    }

    @Override
    public void onClick(View view) {
        amount = Double.parseDouble(amountEt.getText().toString());
        checkOut.startSession("", "كنفندي", amount, "en", new paymentListener() {
            @Override
            public void onPaymentSucceed() {
                Toast.makeText(getApplicationContext(), "Payment success",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPaymentFailed() {

            }
        });
    }
}
