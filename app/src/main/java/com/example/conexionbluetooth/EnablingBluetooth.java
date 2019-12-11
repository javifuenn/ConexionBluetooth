package com.example.conexionbluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EnablingBluetooth extends AppCompatActivity {
    private static final String TAG = "EnablingBluetooth";

    BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enabling_bluetooth);
        Button btnONOFF = (Button) findViewById(R.id.btnONOFF);

        //Inicializo el adaptador
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btnONOFF.setOnClickListener(new View.setOnClickListener() {
            public void onClick(View view) {
                enableDisableBT();
            }
        });
    }

    public void enableDisableBT() {
        if (mBluetoothAdapter == null) {
            log.d(TAG, "enableDisableBT: Does bot have BT capabilities.");
        }
        if(!mBluetoothAdapter.isEnabled()) {
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBTTIntent);

            IntentFilter BTTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        }

    }
}
