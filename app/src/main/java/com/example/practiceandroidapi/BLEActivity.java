package com.example.practiceandroidapi;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.os.Handler;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.UUID;
import static android.content.ContentValues.TAG;

public class BLEActivity extends AppCompatActivity {

    // EEGソースコードより拝借 /////////////////////////////////////////////////////////////////////
    /** 対象のサービスUUID */
    private final UUID UART_SERVICE_UUID = UUID.fromString("6E400001-B5A3-F393-E0A9-E50E24DCCA9E");
    /** 対象のキャラクタリスティックUUID:送信 */
    private final UUID TX_CHARACTERISTIC_UUID = UUID.fromString("6E400002-B5A3-F393-E0A9-E50E24DCCA9E");
    /** 対象のキャラクタリスティックUUID:受信 */
    private final UUID RX_CHARACTERISTIC_UUID = UUID.fromString("6E400003-B5A3-F393-E0A9-E50E24DCCA9E");
    /** キャラクタリスティック設定UUID(固定値) */
    private final UUID CHARACTERISTIC_CONFIG_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice device;

    private BluetoothGattCharacteristic mTxCharacteristic;
    private BluetoothGattCharacteristic mRxCharacteristic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble);
    }

    public void search(View view) {
        bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            Log.e("BLE_TEST", "No available Bluetooth adapter.");
        }
        for (BluetoothDevice device : bluetoothAdapter.getBondedDevices()) {
            if(device.getName().equals("BLE_UART_1611211")) {
                this.device = device;
            }
        }

        // ペアリング済みのデバイスがなければ処理終了
        if(this.device == null) { Log.d("BLE_TEST", "no device"); return; }

        device.connectGatt(this, false, mGattCallback, BluetoothDevice.TRANSPORT_LE);

    }

    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            String intentAction;
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.d("BLE_TEST", "connected");
                gatt.discoverServices();
            } else if(newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.d("BLE_TEST", "デバイスにアクセスできませんでした。");
                gatt.close();
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            final BluetoothGattService service = gatt.getService(UART_SERVICE_UUID);

            if (service == null) { return; }

            // 以下でキャラクタリスティックを取得可能
            mTxCharacteristic = service.getCharacteristic(TX_CHARACTERISTIC_UUID);
            mRxCharacteristic = service.getCharacteristic(RX_CHARACTERISTIC_UUID);

            if (mTxCharacteristic == null || mRxCharacteristic == null ) { return; }

            // Notificationを要求する
            final boolean isRegister = gatt.setCharacteristicNotification(mRxCharacteristic, true);

            // Notificationを有効化する処理
            final BluetoothGattDescriptor descriptor = mRxCharacteristic.getDescriptor(CHARACTERISTIC_CONFIG_UUID);
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            gatt.writeDescriptor(descriptor);

            // Notification接続確認
            if (isRegister) {
                // 接続成功
                Log.d("BLE_TEST", "Success");
                execMeasure(gatt);
            } else {
                Log.d("BLE_TEST", "Failed");
            }
        }


        public void execMeasure(BluetoothGatt gatt) {
            String cmd = "M";

            boolean setResult = mTxCharacteristic.setValue("V");

            boolean status = gatt.writeCharacteristic(mTxCharacteristic);
            Log.d("BLE_TEST", "bleCmd status :" + status);

            if (status) {
                Log.d("BLE_TEST", "write success");
            } else {
                Log.d("BLE_TEST", "write failed");
            }
        }


    };
}
