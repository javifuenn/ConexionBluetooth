package com.example.conexionbluetooth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

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
                    //intent on Bluetooth
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE;
                    startActivityForResult(intent, REQUEST_ENABLE_BT);
                } else {
                    showToast("El Bluetooth ya está activado");
                }
            }
        });
        // Cuando se pulsa el boton DETECTABLE
        mBotDescubrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBlueAdapter.isEnabled()) {
                    showToast("Dispositivo detectable...");
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, REQUEST_DISCOVER_BT);
                }
            }
        });
        // Cuando se pulsa el boton OFF
        mBotOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBlueAdapter.isEnabled()) {
                    mBlueAdapter.disable();
                    showToast("Desactivando el Bluetooth...");
                    mBluetIv.setImageResource(R.drawable.ic_action_off);
                } else {
                    showToast("El Bluetooth ya está desactivado");
                }
            }
        });
        // Cuando se pulsa el boton DISPOSITIVOS EMPAREJADOS
        mBotEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBlueAdapter.isEnabled()) {
                    mEmparejadoTv.setText("Dispositivos emparejados");
                    showToast("Desactivando el Bluetooth...");
                    Set<BluetoothDevice> devices = mBlueAdapter.getBondedDevices();
                    for(BluetoothDevice device : devices) {
                        mEmparejadoTv.append("\nDevice: " + device.getName() + "," + device);
                    }
                }
                else {
                    //El bluetooth está desactivado, así que no se puede mostrar la información requerida
                    showToast("Turn on Bluetooth to get paired devices");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (resultCode == RESULT_OK) {
                    //El Bluetooth está activado
                    mBluetIv.setImageResource(R.drawable.ic_action_on);
                    showToast("Bluetooth activado");
                } else {
                    // El usuario no activa el bluetooth
                    showToast("No está activado");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // Toast message (Mensaje que aparece y desaparece abajo en negro)
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
