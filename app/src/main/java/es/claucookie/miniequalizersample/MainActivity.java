package es.claucookie.miniequalizersample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import es.claucookie.miniequalizerlibrary.EqualizerView;


public class MainActivity extends ActionBarActivity {

    EqualizerView equalizer;
    Button toggleButton;
    private Button toggleColorButton;
    private Integer[] colors = new Integer[]{R.color.black, R.color.blue, R.color.pink};
    private int colorIndex = 0;
    private Integer[] durations = new Integer[]{100, 1000, 3000};
    private int durationIndex = 0;
    private Button toggleDurationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equalizer = (EqualizerView) findViewById(R.id.equalizer_view);
        toggleButton = (Button) findViewById(R.id.toggle_button);
        toggleColorButton = (Button) findViewById(R.id.toggle_color_button);
        toggleDurationButton = (Button) findViewById(R.id.toggle_duration_button);
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
        toggleColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalizer.setForegroundColor(getResources().getColor(colors[colorIndex]));
                colorIndex++;
                if (colorIndex >= colors.length) {
                    colorIndex=0;
                }
            }
        });
        toggleDurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalizer.setDuration(durations[durationIndex]);
                durationIndex++;
                if (durationIndex >= durations.length) {
                    durationIndex=0;
                }
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
