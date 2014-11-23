package de.mtthsfrdrch.faboverload;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

public class Utils {
    public static Animator slideInVertically(@NonNull final View v, final boolean down,
                                              final Runnable endAction) {
        // Ignore action if the view is not in the expected state
        if (isVisible(v)) return null;

        ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.TRANSLATION_Y, v.getHeight(), 0);
//        if (down) animator.setFloatValues(value);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                setVisible(v);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (endAction != null) endAction.run();
            }
        });
        return animator;
    }

    public static Animator slideOutVertically(@NonNull final View v, final boolean down,
                                               final Runnable endAction) {
        // Ignore action if the view is not in the expected state
        if (!isVisible(v)) return null;

        ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.TRANSLATION_Y, 0, v.getHeight());
//        if (down) animator.setFloatValues(-value);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationEnd(Animator animation) {
                setInvisible(v);
                if (endAction != null) endAction.run();
            }
        });
//        if (value == 0) {
//            if (!isVisible(v)) setVisible(v);
//            // The view hasn't been measured yet, post ourselves to be executed when that happens
//            whenViewBecomesVisible(v, new Runnable() {
//                @Override public void run() {
//                    AnimationsHelper.slide(v, out, fromTop, endAction);
//                }
//            });
//        } else {

//            } else {
//                setVisible(v);
//                v.getViewTreeObserver()
//                        .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                            @Override public boolean onPreDraw() {
//                                v.getViewTreeObserver().removeOnPreDrawListener(this);
//                                ObjectAnimator.ofFloat(v, View.TRANSLATION_Y, 0f).start();
//                                return true;
//                            }
//                        });
//            }
//        }
        return animator;
    }

    public static void setGone(@NonNull View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    public static void setVisible(@NonNull View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void setInvisible(@NonNull View... views) {
        for (View view : views) {
            view.setVisibility(View.INVISIBLE);
        }
    }

//    public static void changeVisibility(int visibility, @NotNull View... views) {
//        for (View view : views) {
//            view.setVisibility(visibility);
//        }
//    }

    public static boolean isVisible(@NonNull View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    public static float dipsToPixels(@NonNull Resources res, int dips) {
        return TypedValue.applyDimension(COMPLEX_UNIT_DIP, dips, res.getDisplayMetrics());
    }

    public static boolean isLandscape(@NonNull Resources resources) {
        return resources.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
