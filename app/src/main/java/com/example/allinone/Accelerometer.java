package com.example.allinone;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    private Sensor mySensor;
    private SensorManager SM;
    private TextView xText,yText,zText;
    private double x,y,z;
    //DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        SM=(SensorManager)getSystemService((SENSOR_SERVICE));
        mySensor=SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //databaseReference=FirebaseDatabase.getInstance().getReference();

        xText = findViewById(R.id.x);
        yText = findViewById(R.id.y);
        zText = findViewById(R.id.z);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x=event.values[0];
        y=event.values[1];
        z=event.values[2];
        xText.setText("X: "+x);
        yText.setText("Y: "+y);
        zText.setText("Z: "+z);
        if(x>=8.0)
        {
            getWindow().getDecorView().setBackgroundColor(Color.GRAY);
            Toast.makeText(getApplicationContext(),"Left",Toast.LENGTH_SHORT).show();
        }
        else if(x<-8.0)
        {
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            Toast.makeText(getApplicationContext(),"Right",Toast.LENGTH_SHORT).show();
        }
        else if(y>=8.0)
        {
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            Toast.makeText(getApplicationContext(),"Up",Toast.LENGTH_SHORT).show();
        }
        else if(y<-8.0)
        {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            Toast.makeText(getApplicationContext(),"Down",Toast.LENGTH_SHORT).show();
        }if(z>=8.0)
        {
            getWindow().getDecorView().setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(),"Plane Up",Toast.LENGTH_SHORT).show();
        }
        else if(z<-8.0)
        {
            getWindow().getDecorView().setBackgroundColor(Color.CYAN);
            Toast.makeText(getApplicationContext(),"Plane Down",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume()
    {
        super.onResume();
        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause()
    {
        super.onPause();
        SM.unregisterListener(this);
    }
}

