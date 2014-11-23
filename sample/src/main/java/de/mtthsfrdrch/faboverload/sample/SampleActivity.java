package de.mtthsfrdrch.faboverload.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import de.mtthsfrdrch.faboverload.FabOverloadController;


public class SampleActivity extends Activity implements View.OnClickListener {

    private FabOverloadController fabController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        View root = findViewById(R.id.container);

        fabController = new FabOverloadController(this, root);
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
                Toast.makeText(this, "Pressed Sub Fab 1", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_fab_sub_2:
                Toast.makeText(this, "Pressed Sub Fab 2", Toast.LENGTH_LONG).show();
                break;
            default:
                // NOP
        }
    }
}
