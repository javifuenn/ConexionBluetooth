package com.example.conexionbluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;

    TextView mEstadoBlueTv, mEmparejadoTv;
    ImageView mBluetIv;
    Button mBotOn, mBotOff,mBotDescubrir, mBotEmparejar, mBotEmp;

    BluetoothAdapter mBlueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEstadoBlueTv = findViewById(R.id.statusBluetoothTv);
        mEmparejadoTv = findViewById(R.id.EmpTv);
        mBluetIv = findViewById(R.id.BluetoothIv);
        mBotOn = findViewById(R.id.BotOn);
        mBotOff = findViewById(R.id.BotOff);
        mBotDescubrir = findViewById(R.id.BotDesc);
        mBotEmp = findViewById(R.id.BotEmp);

        // Adaptador
        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

        // Comprobar si el Bluetooth está disponible
        if (mBlueAdapter == null) {
            mEstadoBlueTv.setText("Bluetooth no disponible.");
        } else {
            mEstadoBlueTv.setText("Bluetooth está disponible.");
        }
        // Establecer una imagen en función del estado del Bluetooth (on/off)
        if (mBlueAdapter.isEnabled()) {
            mBluetIv.setImageResource(R.drawable.ic_action_on);
        } else {
            mBluetIv.setImageResource(R.drawable.ic_action_off);
        }
        // Cuando se pulsa el boton ON
        mBotOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBlueAdapter.isEnabled()) {
                    showToast("Activando Bluetooth...");
                }
            }
        });
        // Cuando se pulsa el boton DESCUBRIR
        mBotDescubrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // Cuando se pulsa el boton OFF
        mBotOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // Cuando se pulsa el boton DISPOSITIVOS EMPAREJADOS
        mBotEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    // Toast message (Mensaje que aparece y desaparece abajo en negro)
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
