package com.projectx.jomopay.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageButton;

import com.projectx.jomopay.R;

/**
 * Created by hussamhyari on 1/19/18.
 */

public class CheckOutButton extends android.support.v7.widget.AppCompatImageButton {

    Context context;

    public CheckOutButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.context = context;
        initStyleButton(attrs);
    }

    public CheckOutButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        initStyleButton(attrs);
    }

    private void initStyleButton(AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.Options, 0, 0);
        String titleText = a.getString(R.styleable.Options_titleText);
        int valueColor = a.getColor(R.styleable.Options_valueColor,
                getResources().getColor(R.color.defColor));
        a.recycle();
    }

}
