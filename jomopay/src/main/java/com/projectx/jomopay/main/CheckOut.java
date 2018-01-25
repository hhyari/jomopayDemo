package com.projectx.jomopay.main;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.projectx.jomopay.R;
import com.projectx.jomopay.components.CircularProgress;

/**
 * Created by hussamhyari on 1/19/18.
 */

public class CheckOut implements View.OnClickListener {

    Context context;
    CircularProgress progressLogin;
    CircularProgress progressCheckout;
    Button checkOutSubmit, loginBtn;
    private Handler handler;
    private MaterialDialog loginDialog, checkOutDialog;
    private paymentListener listener;
    private String merchantPhone, merchantName;
    private double amount;
    private String lang;
    private Typeface arabicTF;


    public CheckOut(Context context) {
        this.context = context;
        arabicTF = Typeface.createFromAsset(context.getAssets(),
                "fonts/main_medium.otf");
    }

    public void startSession(String merchantPhone, String merchantName, double amount, String lang,
                             paymentListener listener) {
        this.listener = listener;
        this.merchantName = merchantName;
        this.merchantPhone = merchantPhone;
        this.amount = amount;
        this.lang = lang;
        new LangSelector(context).setLangRecreate(lang);
        openLoginDialog();
    }

    private void LoginDialogViews(View v) {
        progressLogin = (CircularProgress) v.findViewById(R.id.progressBar);
        TextView loginLabel = (TextView) v.findViewById(R.id.loginLabel);
        EditText phoneET = (EditText) v.findViewById(R.id.phoneET);
        EditText codeET = (EditText) v.findViewById(R.id.CodeET);
        loginBtn = (Button) v.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

        if (lang.equals("en")) {

        } else if (lang.equals("ar")) {
            phoneET.setGravity(Gravity.END);
            codeET.setGravity(Gravity.END);
            loginLabel.setTypeface(arabicTF);
            phoneET.setTypeface(arabicTF);
            codeET.setTypeface(arabicTF);
            loginBtn.setTypeface(arabicTF);
        }
    }

    private void CheckOutDialogViews(View v) {
        TextView amountTV = (TextView) v.findViewById(R.id.amount);
        TextView merchantTV = (TextView) v.findViewById(R.id.merchantName);
        TextView payLabel = (TextView) v.findViewById(R.id.payLabel);
        TextView welcomeLabel = (TextView) v.findViewById(R.id.welcomeLabel);
        progressCheckout = (CircularProgress) v.findViewById(R.id.progressBar);
        checkOutSubmit = (Button) v.findViewById(R.id.checkOutBtn);
        checkOutSubmit.setOnClickListener(this);

        if (lang.equals("en")) {
            merchantTV.setText("For " + merchantName);
        } else if (lang.equals("ar")) {
            merchantTV.setText("لصالح " + merchantName);
            payLabel.setTypeface(arabicTF);
            welcomeLabel.setTypeface(arabicTF);
            merchantTV.setTypeface(arabicTF);
            checkOutSubmit.setTypeface(arabicTF);

        }
        amountTV.setText(String.valueOf(amount));
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
        checkOutSubmit.setVisibility(View.INVISIBLE);
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
        loginBtn.setVisibility(View.INVISIBLE);
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
