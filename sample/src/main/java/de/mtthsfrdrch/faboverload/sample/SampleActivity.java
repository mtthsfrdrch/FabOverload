package de.mtthsfrdrch.faboverload.sample;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.mtthsfrdrch.faboverload.FabOverloadController;
import de.mtthsfrdrch.faboverload.Utils;


public class SampleActivity extends Activity implements View.OnClickListener {

    private FabOverloadController fabController;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        View root = findViewById(R.id.container);

        fabController = new FabOverloadController(this, root, R.layout.sub_fabs);
        fabController.setOnClickListener(this);

        Button toggle = (Button) findViewById(R.id.btn_toggle);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isVisible = fabController.isFabVisible();
                if (isVisible) fabController.hideFAB();
                else fabController.showFAB();
            }
        });

        text = (TextView) findViewById(R.id.text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fabController.showFAB();
    }

    @Override
    protected void onPause() {
        fabController.hideFAB();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        fabController.remove();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_fab:
                fabController.switchOverload();
                break;
            case R.id.btn_fab_sub_1:

                tryShowDefaultTransitions( (ViewGroup) findViewById(R.id.container));
                Utils.setVisible(text);

                break;
            case R.id.btn_fab_sub_2:
                Toast.makeText(this, "Pressed Sub Fab 2", Toast.LENGTH_LONG).show();
                break;
            default:
                // NOP
        }
    }

    public static void tryShowDefaultTransitions(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            TransitionManager.beginDelayedTransition(viewGroup);
        }
    }
}
