package com.coinflip.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import java.security.SecureRandom;

/** Home-screen widget: tap the coin to flip it. */
public class CoinWidget extends AppWidgetProvider {
    private static final String ACTION_FLIP = "com.coinflip.widget.FLIP";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int[] SPIN = { R.drawable.coin_edge, R.drawable.coin_tails, R.drawable.coin_edge, R.drawable.coin_heads };
    private static volatile boolean flipping = false;

    @Override
    public void onUpdate(Context ctx, AppWidgetManager mgr, int[] ids) {
        for (int id : ids) mgr.updateAppWidget(id, views(ctx, R.drawable.coin_heads, "Tap to flip"));
    }

    @Override
    public void onReceive(Context ctx, Intent intent) {
        super.onReceive(ctx, intent);
        if (!ACTION_FLIP.equals(intent.getAction()) || flipping) return;
        flipping = true;
        final PendingResult pending = goAsync();
        final Context app = ctx.getApplicationContext();
        new Thread(() -> {
            try {
                AppWidgetManager mgr = AppWidgetManager.getInstance(app);
                int[] ids = mgr.getAppWidgetIds(new ComponentName(app, CoinWidget.class));
                boolean heads = RANDOM.nextBoolean();
                // Quick spin animation: cycle through frames, slowing down.
                for (int i = 0; i < 12; i++) {
                    mgr.updateAppWidget(ids, views(app, SPIN[i % SPIN.length], "…"));
                    Thread.sleep(50 + i * 8);
                }
                mgr.updateAppWidget(ids, views(app,
                        heads ? R.drawable.coin_heads : R.drawable.coin_tails,
                        heads ? "HEADS" : "TAILS"));
            } catch (InterruptedException ignored) {
            } finally {
                flipping = false;
                pending.finish();
            }
        }).start();
    }

    private static RemoteViews views(Context ctx, int image, String label) {
        RemoteViews v = new RemoteViews(ctx.getPackageName(), R.layout.coin_widget);
        v.setImageViewResource(R.id.coin, image);
        v.setTextViewText(R.id.label, label);
        Intent i = new Intent(ctx, CoinWidget.class).setAction(ACTION_FLIP);
        PendingIntent pi = PendingIntent.getBroadcast(ctx, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        v.setOnClickPendingIntent(R.id.root, pi);
        return v;
    }
}
