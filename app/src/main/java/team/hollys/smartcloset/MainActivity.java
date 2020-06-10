package team.hollys.smartcloset;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class MainActivity extends AppCompatActivity {

    private BluetoothSPP bt;

    // Main2Activity에서 접근하기 위함
    // 참조한 블로그는 다음과 같음
    // https://jhshjs.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Activity%EC%97%90%EC%84%9C-%EB%8B%A4%EB%A5%B8-Activity-%EB%B3%80%EC%88%98-%EC%A0%91%EA%B7%BC%ED%95%98%EA%B8%B0
    public static Context context_main;
    public String ClothSectionVal;
    public int ClothSectionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context_main = this;

        ClothSectionVal = "라랄라라랄";

        bt = new BluetoothSPP(this); //Initializing

        if (!bt.isBluetoothAvailable()) { //블루투스 사용 불가
            Toast.makeText(getApplicationContext()
                    , "Bluetooth is not available"
                    , Toast.LENGTH_SHORT).show();
            finish();
        }

        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() { //데이터 수신
            public void onDataReceived(byte[] data, String message) {
                //message
                //Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                String noticetext = "Received data length: ";
                Toast.makeText(MainActivity.this, noticetext.concat(Integer.toString(message.length())), Toast.LENGTH_SHORT).show();

                TextView HumidityDataTV = findViewById(R.id.HumidityData);
                TextView dcfanTV = findViewById(R.id.DCFAN);
                TextView ClothSectionTV = findViewById(R.id.ClothSection);
                TextView WeatherInfoTV = findViewById(R.id.WeatherInfo);

                String hum_val, tem_val, wea_val;
                hum_val = tem_val = wea_val = null;

                if(message.length() > 5) {
                    hum_val = message.substring(message.indexOf("H") + 1, message.indexOf("W"));
                    HumidityDataTV.setText(hum_val);
                    int hum_val2 = Integer.parseInt(hum_val.substring(0, hum_val.indexOf(".")));
                    if (hum_val2 > 70) {
                        dcfanTV.setText("ON");
                    } else {
                        dcfanTV.setText("OFF");
                    }
                }

                if(message.length() > 10) {
                    tem_val = message.substring(message.indexOf("W") + 1, message.indexOf("$"));
                    wea_val = message.substring(message.indexOf("$") + 1, message.indexOf("#"));

                    WeatherInfoTV.setText(tem_val.concat("\n").concat(wea_val));

                    int temperature = Integer.parseInt(tem_val.substring(0, tem_val.indexOf("\'")));

                    String section_text = null;
                    if(temperature > 24){
                        section_text = "여름 옷 구역"; // summer
                        ClothSectionType = 1;
                    }
                    else if(temperature > 16 && temperature <= 24){
                        section_text = "봄/가을 얇은 옷 구역"; // spring
                        ClothSectionType = 2;
                    }
                    else if(temperature > 9 && temperature <= 16){
                        section_text = "봄/가을 두꺼운 옷 구역"; // fall
                        ClothSectionType = 3;
                    }
                    else {
                        section_text = "겨울 옷 구역"; // winter
                        ClothSectionType = 4;
                    }

                    ClothSectionTV.setText(section_text);
                    ClothSectionVal = section_text;
                }
            }
        });

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() { //연결됐을 때
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext()
                        , "Connected to " + name + "\n" + address
                        , Toast.LENGTH_SHORT).show();
            }

            public void onDeviceDisconnected() { //연결해제
                Toast.makeText(getApplicationContext()
                        , "Connection lost", Toast.LENGTH_SHORT).show();
            }

            public void onDeviceConnectionFailed() { //연결실패
                Toast.makeText(getApplicationContext()
                        , "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnConnect = findViewById(R.id.btnConnect); //연결시도
        btnConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        bt.stopService(); //블루투스 중지
    }

    public void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) { //
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER); //DEVICE_ANDROID는 안드로이드 기기 끼리
                setup();
            }
        }
    }

    public void setup() {
        Button leftRotateBtn = findViewById(R.id.leftRotate); // CCW Rotate
        Button rightRotateBtn = findViewById(R.id.rightRotate); // CW Rotate
        Button openDoorBtn = findViewById(R.id.openDoor); // Open Door
        Button closeDoorBtn = findViewById(R.id.closeDoor); // Close Door
        Switch enableMotionSwt = findViewById(R.id.enableMotion); // enable Motion detection
        Button recommendClothBtn = findViewById(R.id.recommendCloth); // recommend Cloth

        leftRotateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bt.send("stp_l", true);
            }
        });

        rightRotateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bt.send("stp_r", true);
            }
        });

        openDoorBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bt.send("srv_o", true);
            }
        });

        closeDoorBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bt.send("srv_c", true);
            }
        });

        enableMotionSwt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    bt.send("m_ena", true);
                }
                else {
                    bt.send("m_dis", true);
                }
            }
        });

        recommendClothBtn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent intent2 = new Intent(
                                getApplicationContext(),
                                Main2Activity.class);
                        startActivity(intent2);
                    }
                }
        );
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth was not enabled."
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
