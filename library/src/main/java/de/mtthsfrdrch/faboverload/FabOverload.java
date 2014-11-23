package de.mtthsfrdrch.faboverload;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static de.mtthsfrdrch.faboverload.Utils.isVisible;

public class FabOverload extends PopupWindow {

    private View root;

    private Fab fab;
    private List<Fab> subFabs = new ArrayList<>();

    public FabOverload(Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.popup_fab, null),
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

//        setOutsideTouchable(false);
        setAnimationStyle(0);

        root = getContentView();

        fab = (Fab) root.findViewById(R.id.btn_fab);
        Fab subFab = (Fab) root.findViewById(R.id.btn_fab_sub_1);
        subFabs.add(subFab);
        subFab = (Fab) root.findViewById(R.id.btn_fab_sub_2);
        subFabs.add(subFab);
    }

    public void setFabClickListener(View.OnClickListener clickListener) {
        fab.setOnClickListener(clickListener);
        for (Fab subFab : subFabs) {
            subFab.setOnClickListener(clickListener);
        }
    }

    public void showFab() {
        Animator animator = Utils.slideInVertically(fab, false, null);
        if (animator != null) animator.start();
    }

    public void hideFab(Runnable endAction) {
        Animator animator = Utils.slideOutVertically(fab, true, endAction);
        if (animator != null) animator.start();
    }

    public void showSubFabs() {
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

//        root.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;

        List<Animator> animators = new ArrayList<>(subFabs.size());
        for (final View view : subFabs) {

            // get the center for the clipping circle
            int cx = (view.getLeft() + view.getRight()) / 2;
            int cy = (view.getTop() + view.getBottom()) / 2;

            // get the final radius for the clipping circle
            int finalRadius = Math.max(view.getWidth(), view.getHeight());

            // create the animator for this view (the start radius is zero)
            Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0,
                    finalRadius);
            animator.setStartDelay(0);
            animator.setDuration(100);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    view.setVisibility(View.VISIBLE);
                }
            });
            animators.add(animator);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animators);
        animatorSet.start();
    }

    public void hideSubFabs(final Animator.AnimatorListener animatorListener) {
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        List<Animator> animators = new ArrayList<>(subFabs.size());
        for (final View view : subFabs) {

            // get the center for the clipping circle
            int cx = (view.getLeft() + view.getRight()) / 2;
            int cy = (view.getTop() + view.getBottom()) / 2;

            // get the final radius for the clipping circle
            int finalRadius = Math.max(view.getWidth(), view.getHeight());

            // create the animator for this view (the start radius is zero)
            Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, finalRadius,
                    0);
            animator.setStartDelay(0);
            animator.setDuration(25);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(INVISIBLE);
                }
            });
            animators.add(animator);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animators);
//        animatorSet.addListener(animatorListener);
        animatorSet.start();
    }

    public boolean isFabVisible() {
        return isVisible(fab);
    }
}
