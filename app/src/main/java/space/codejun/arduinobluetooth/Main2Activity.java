package space.codejun.arduinobluetooth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView ClothSectionTV2 = findViewById(R.id.ClothSection);
        //ClothSectionTV2.setText("봄/가을 두꺼운 옷 구역");
    }
}
