package de.mtthsfrdrch.faboverload;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class Fab extends ImageButton {

    @SuppressWarnings("UnusedDeclaration")
    public Fab(Context context) {
        this(context, null);
    }

    public Fab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Fab(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
