package com.coinflip.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/** Opened from the app icon: explains the widget and offers a one-tap "add to home screen". */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(64, 64, 64, 64);
        root.setBackgroundColor(0xFF14161C);

        TextView text = new TextView(this);
        text.setText("Coin Flip is a home-screen widget.\n\nTap the button below, or long-press your home screen → Widgets → Coin Flip.\n\nThen tap the coin to flip it.");
        text.setTextColor(0xFFF2EFE6);
        text.setTextSize(18);
        text.setGravity(Gravity.CENTER);
        root.addView(text);

        AppWidgetManager mgr = getSystemService(AppWidgetManager.class);
        if (mgr != null && mgr.isRequestPinAppWidgetSupported()) {
            Button add = new Button(this);
            add.setText("Add widget to home screen");
            add.setOnClickListener(v -> mgr.requestPinAppWidget(new ComponentName(this, CoinWidget.class), null, null));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
            lp.topMargin = 64;
            root.addView(add, lp);
        }
        setContentView(root);
    }
}
