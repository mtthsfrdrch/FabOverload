package de.mtthsfrdrch.faboverload;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static de.mtthsfrdrch.faboverload.Utils.setInvisible;

public class Fab extends RelativeLayout {

    private ImageButton button;
    private TextView title;

    @SuppressWarnings("UnusedDeclaration")
    public Fab(Context context) {
        this(context, null);
    }

    public Fab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Fab(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, R.layout.fab, this);

        title = (TextView) findViewById(R.id.fab_title);
        button = (ImageButton) findViewById(R.id.fab_button);

        final TypedArray attributes = context
                .obtainStyledAttributes(attrs, R.styleable.fov);

        final int buttonBackground = attributes.getResourceId(R.styleable.fov_buttonBackground,
                R.drawable.btn_fab_background);
        button.setBackgroundResource(buttonBackground);

        final int buttonImage = attributes.getResourceId(R.styleable.fov_buttonImage,
                R.drawable.ic_add);
        button.setImageResource(buttonImage);

        final int buttonId = attributes.getResourceId(R.styleable.fov_buttonId,
                R.id.fov_btn_fab_sub);
        button.setId(buttonId);

//        title.setTextAppearance(context,
//                attributes.getResourceId(R.styleable.fov_textAppearance,
//                        android.R.style.TextAppearance_Material_Button));
        String titleText = attributes.getString(R.styleable.fov_titleText);
        title.setText(titleText);

        attributes.recycle();
        setInvisible(this);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setButtonBackground(int backgroundRes) {
        button.setBackgroundResource(backgroundRes);
    }

    public void setButtonImage(int imageRes) {
        button.setImageDrawable(button.getContext().getDrawable(imageRes));
    }

    public void setButtonClickListener(OnClickListener clickListener) {
        button.setOnClickListener(clickListener);
    }
}
