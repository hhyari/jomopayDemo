package com.projectx.jomopay.main;

import android.app.Activity;

/**
 * Created by hussamhyari on 1/19/18.
 */

public class MainActivity extends Activity {

    CheckOut i = new CheckOut(this);
    {
        i.startSession("+9629", 10.4, new paymentListener() {
            @Override
            public void onPaymentSucceed() {

            }

            @Override
            public void onPaymentFailed() {

            }
        });
    }

}
