package com.projectx.jomopay.main;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by hussamhyari on 1/25/18.
 */

public class LangSelector {

    Context context;

    public LangSelector(Context context) {
        this.context = context;
    }

    public void setLangRecreate(String langval) {
        Configuration config = context.getResources().getConfiguration();
        Locale locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }
}
