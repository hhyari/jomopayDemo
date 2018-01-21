package com.projectx.jomopay.main;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.projectx.jomopay.R;

/**
 * Created by hussamhyari on 1/19/18.
 */

public class CheckOut {

    Context context;

    public CheckOut(Context context) {
        this.context = context;
    }

    public void startSession(String phone, double amount, paymentListener listener) {
        //Open dialog
        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_content, false)
                .backgroundColor(context.getResources().getColor(R.color.dialog_bg))
                .show();
        View v = dialog.getCustomView();
        TextView amountTV = (TextView) v.findViewById(R.id.textView);
        amountTV.setText(String.valueOf(amount));
    }
}
