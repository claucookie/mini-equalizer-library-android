package es.claucookie.miniequalizersample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import es.claucookie.miniequalizerlibrary.EqualizerView;


public class MainActivity extends AppCompatActivity {

    EqualizerView equalizer;
    Button toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equalizer = findViewById(R.id.equalizer_view);
        toggleButton = findViewById(R.id.toggle_button);
        showEqualizer();
        initButton();
    }

    private void initButton() {
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleEqualizer();
            }
        });
    }

    private void toggleEqualizer() {
        if (equalizer.isAnimating()) {
            equalizer.stopBars();
        } else {
            equalizer.animateBars();
        }
    }

    private void showEqualizer() {
        equalizer.animateBars();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
