package com.example.allinone;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    ListView listView;
    String[] sensorNames;
    int[] sensorPics = {R.drawable.proximity,R.drawable.accelerometer,R.drawable.gyroscope,R.drawable.shake_detector};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewId);
        sensorNames = getResources().getStringArray(R.array.sensorNames);
        CustomAdapter adapter = new CustomAdapter(this,sensorNames,sensorPics);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String senNames = sensorNames[position];
                if (senNames.equals("Proximity") ){
                    Intent intent = new Intent(getApplicationContext(),Proximity.class);
                    startActivity(intent);
                }
                else if (senNames.equals("Accelerometer") ){
                    Intent intent = new Intent(getApplicationContext(),Accelerometer.class);
                    startActivity(intent);
                }
                else if (senNames.equals("Gyroscope") ){
                    Intent intent = new Intent(getApplicationContext(),Gyroscope.class);
                    startActivity(intent);
                }
                else if (senNames.equals("Shake_Detector") ){
                    Intent intent = new Intent(getApplicationContext(),ShakeDetection.class);
                    startActivity(intent);
                }
                Toast.makeText(getApplicationContext(),senNames,Toast.LENGTH_SHORT).show();
            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                Intent intent = new Intent(getApplicationContext(),GridViewClass.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Shaked!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

}
