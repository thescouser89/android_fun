package lakaz.org.accelerometer;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor sensor;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* You need to get a sensor manager first */
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        /* then from the server manager you get the server instance */
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        t = (TextView)findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /* register the methods to call when sensor is ready */
        /* methods are onSensorChanged and onAccuracyChanged */
        /* Notice that we also implemented an interface in the object definition */
        mSensorManager.registerListener(this, sensor, 500000); // delay half a second
    }

    @Override
    protected void onPause() {
        super.onPause();

        /* Do this when activity is out of focus */
        mSensorManager.unregisterListener(this);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }
    @Override
    public final void onSensorChanged(SensorEvent event) {

        // Many sensors return 3 values, one for each axis.
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        t.setText("x: " + x + "\n" +
                  "y: " + y + "\n" +
                  "z: " + z);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
