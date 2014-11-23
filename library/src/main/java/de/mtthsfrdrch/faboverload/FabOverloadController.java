package de.mtthsfrdrch.faboverload;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

public class FabOverloadController {

    private View container;
    private FabOverload fabOverload;
    int buttonDistance;

    public FabOverloadController(Context context, View container) {
        fabOverload = new FabOverload(context);
//        fabOverload.setWindowLayoutMode();
        buttonDistance = context.getResources().getDimensionPixelSize(R.dimen.margin_medium);
        this.container = container;
    }

    public void switchOverload() {
        if (fabOverload.isOverloadVisible()) {
            fabOverload.hideSubFabs(null);
        } else {
            fabOverload.showSubFabs();
        }
    }

    public void showFAB() {
        if (!fabOverload.isShowing()) {
            container.post(new Runnable() {
                public void run() {
                    int lollipopMargin = (int) Utils.dipsToPixels(Resources.getSystem(), 50);
                    if (Utils.isLandscape(Resources.getSystem()))
                        fabOverload.showAtLocation(container, Gravity.BOTTOM | Gravity.RIGHT, lollipopMargin, 0);
                    else
                        fabOverload.showAtLocation(container, Gravity.BOTTOM | Gravity.RIGHT, 0, lollipopMargin);
                    fabOverload.showFab();
                }
            });
        } else {
            fabOverload.showFab();
        }
    }

    public void hideFAB() {
        if (fabOverload.isOverloadVisible()) {
            fabOverload.hideSubFabs(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (fabOverload.isShowing()) {
                        fabOverload.hideFab(null);
                    }
                }
            });
        } else {
            if (fabOverload.isShowing()) {
                fabOverload.hideFab(null);

            }
        }
    }

    public boolean isFabVisible() {
        return fabOverload.isFabVisible();
    }

    public void setOnClickListener(View.OnClickListener fabClickListener) {
        fabOverload.setFabClickListener(fabClickListener);
    }
}
