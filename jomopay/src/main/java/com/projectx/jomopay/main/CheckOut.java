package com.projectx.jomopay.main;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.projectx.jomopay.R;

/**
 * Created by hussamhyari on 1/19/18.
 */

public class CheckOut implements View.OnClickListener {

    Context context;
    RelativeLayout progressLogin;
    RelativeLayout progressCheckout;
    Button checkOutSubmit;
    private Handler handler;
    private MaterialDialog loginDialog, checkOutDialog;
    private paymentListener listener;
    private String merchantPhone, merchantName;
    private double amount;

    public CheckOut(Context context) {
        this.context = context;
    }

    public void startSession(String merchantPhone, String merchantName, double amount,
                             paymentListener listener) {
        this.listener = listener;
        this.merchantName = merchantName;
        this.merchantPhone = merchantPhone;
        this.amount = amount;
        openLoginDialog();
    }

    private void LoginDialogViews(View v) {
        progressLogin = (RelativeLayout) v.findViewById(R.id.progressLayout);
        Button login = (Button) v.findViewById(R.id.loginBtn);
        login.setOnClickListener(this);
    }

    private void CheckOutDialogViews(View v) {
        TextView amountTV = (TextView) v.findViewById(R.id.amount);
        TextView merchantTV = (TextView) v.findViewById(R.id.merchantName);
        progressCheckout = (RelativeLayout) v.findViewById(R.id.progressLayout);
        checkOutSubmit = (Button) v.findViewById(R.id.checkOutBtn);
        checkOutSubmit.setOnClickListener(this);
        amountTV.setText(String.valueOf(amount));
        merchantTV.setText("For " + merchantName);
    }

    public void openLoginDialog() {
        loginDialog = new MaterialDialog.Builder(context)
                .customView(R.layout.login_dialog, false)
                .backgroundColor(context.getResources().getColor(R.color.dialog_bg))
                .show();
        View v = loginDialog.getCustomView();
        LoginDialogViews(v);
    }

    private void openCheckOutDialog() {
        checkOutDialog = new MaterialDialog.Builder(context)
                .customView(R.layout.checkout_dialog, false)
                .backgroundColor(context.getResources().getColor(R.color.dialog_bg))
                .show();
        View v = checkOutDialog.getCustomView();
        CheckOutDialogViews(v);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.loginBtn) {
            submitLogin();
        } else if (i == R.id.checkOutBtn) {
            submitPayment();
        }
    }

    private void submitPayment() {
        progressCheckout.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkOutDialog.dismiss();
                listener.onPaymentSucceed();
            }
        }, 2000);
    }

    public void submitLogin() {
        progressLogin.setVisibility(View.VISIBLE);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loginDialog.dismiss();
                openCheckOutDialog();
            }
        }, 2000);
    }


}
