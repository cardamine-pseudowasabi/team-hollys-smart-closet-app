package space.codejun.arduinobluetooth;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView ClothSectionTV2 = findViewById(R.id.ClothSection);

        String tempText = ((MainActivity)MainActivity.context_main).ClothSectionVal;
        ClothSectionTV2.setText(tempText);

        ImageView upIV = findViewById(R.id.up_image);
        ImageView downIV = findViewById(R.id.down_image);

        Button anotherRecommendBtn = findViewById(R.id.anotherRecommend);

        Drawable draw = getResources().getDrawable(R.drawable.up_spring_1);
        //Drawable draw = res.getDrawable( R.drawable.image_name_in_drawable );
        upIV.setImageDrawable(draw); 
    }
}
