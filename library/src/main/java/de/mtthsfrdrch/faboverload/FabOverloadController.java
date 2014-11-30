package de.mtthsfrdrch.faboverload;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.View;

import java.util.List;

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

    public FabOverloadController(Context context, View container, int mergeLayout) {
        fabOverload = new FabOverload(context);
        fabOverload.inflateSubFabs(mergeLayout);
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
                    // navigationbar is overlaying the app's window, so we need extra margin
                    // on lollipop
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

    public void addFab(FabData data) {
        Fab fab = new Fab(fabOverload.getContentView().getContext(), null, R.style.Widget_FAB_sub);
        fab.setId(data.getId());
        fab.setTitle(data.getTitle());
        if (data.getBackgroundRes() != 0) fab.setButtonBackground(data.getBackgroundRes());
        if (data.getImageRes() != 0) fab.setButtonImage(data.getImageRes());
        fabOverload.addFab(fab);
    }

    public void addAll(List<FabData> data) {
        for (FabData fabData : data) addFab(fabData);
    }

    public void inflateSubFabs(int mergeLayout) {
        fabOverload.inflateSubFabs(mergeLayout);
    }

    public void remove() {
        fabOverload.dismiss();
    }
}
