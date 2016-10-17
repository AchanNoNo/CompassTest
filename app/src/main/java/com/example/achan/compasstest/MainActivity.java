package com.example.achan.compasstest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor magneticSensor=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor accelerometerSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener,magneticSensor,SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(listener,accelerometerSensor,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sensorManager!=null){
            sensorManager.unregisterListener(listener);
        }
    }

    private SensorEventListener listener=new SensorEventListener() {
        float[] accelerometerValues=new float[3];
        float[] magneticValues=new float[3];

        @Override
        public void onSensorChanged(SensorEvent event) {
            //判断当前传感器类型
            if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
                accelerometerValues=event.values.clone();
            }else if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
                magneticValues=event.values.clone();
            }
            float[] R=new float[9];
            float[] values=new float[3];
            SensorManager.getRotationMatrix(R,null,accelerometerValues,magneticValues);
            SensorManager.getOrientation(R,values);
            Log.d("MainActivity","values[0] is "+Math.toDegrees(values[0]));

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
