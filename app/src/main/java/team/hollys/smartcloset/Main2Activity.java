package team.hollys.smartcloset;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    int up_spring_n = 12, up_summer_n = 5, up_fall_n = 9, up_winter_n = 7;
    int down_spring_n = 2, down_summer_n = 4, down_fall_n = 2, down_winter_n = 2;

    Drawable[] up_spring = new Drawable[up_spring_n];
    Drawable[] up_summer = new Drawable[up_summer_n];
    Drawable[] up_fall = new Drawable[up_fall_n];
    Drawable[] up_winter = new Drawable[up_winter_n];

    Drawable[] down_spring = new Drawable[down_spring_n];
    Drawable[] down_summer = new Drawable[down_summer_n];
    Drawable[] down_fall = new Drawable[down_fall_n];
    Drawable[] down_winter = new Drawable[down_winter_n];

    String ClothSectionVal;
    int ClothSectionType;

    ImageView upIV, downIV;

    void RandomPick(){
        // ClothSectionType에 따라 계절별 옷 추천
        int up_random, down_random;
        Random up_rand = new Random();
        Random down_rand = new Random();
        switch(ClothSectionType){
            case 1: // summer
                up_random = up_rand.nextInt(up_summer_n);
                down_random = down_rand.nextInt(down_summer_n);

                upIV.setImageDrawable(up_summer[up_random]);
                downIV.setImageDrawable(down_summer[down_random]);
                break;
            case 2: // spring
                up_random = up_rand.nextInt(up_spring_n);
                down_random = down_rand.nextInt(down_spring_n);

                upIV.setImageDrawable(up_spring[up_random]);
                downIV.setImageDrawable(down_spring[down_random]);
                break;
            case 3: // fall
                up_random = up_rand.nextInt(up_fall_n);
                down_random = down_rand.nextInt(down_fall_n);

                upIV.setImageDrawable(up_fall[up_random]);
                downIV.setImageDrawable(down_fall[down_random]);
                break;
            case 4: // winter
                up_random = up_rand.nextInt(up_winter_n);
                down_random = down_rand.nextInt(down_winter_n);

                upIV.setImageDrawable(up_winter[up_random]);
                downIV.setImageDrawable(down_winter[down_random]);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView ClothSectionTV2 = findViewById(R.id.ClothSection);

        ClothSectionVal = ((MainActivity)MainActivity.context_main).ClothSectionVal;
        ClothSectionType = ((MainActivity)MainActivity.context_main).ClothSectionType;

        //ClothSectionVal = "봄/가을 얇은 옷 구역"; // 임시
        //ClothSectionType = 2; // 임시

        ClothSectionTV2.setText(ClothSectionVal);

        upIV = findViewById(R.id.up_image);
        downIV = findViewById(R.id.down_image);

        Button anotherRecommendBtn = findViewById(R.id.anotherRecommend);

        //Drawable draw = getResources().getDrawable(R.drawable.up_spring_1);
        //Drawable draw = res.getDrawable( R.drawable.image_name_in_drawable );
//        upIV.setImageDrawable(draw);

        // up_spring
        up_spring[0] = getResources().getDrawable(R.drawable.up_spring_1);
        up_spring[1] = getResources().getDrawable(R.drawable.up_spring_2);
        up_spring[2] = getResources().getDrawable(R.drawable.up_spring_3);
        up_spring[3] = getResources().getDrawable(R.drawable.up_spring_4);
        up_spring[4] = getResources().getDrawable(R.drawable.up_spring_5);
        up_spring[5] = getResources().getDrawable(R.drawable.up_spring_6);
        up_spring[6] = getResources().getDrawable(R.drawable.up_spring_7);
        up_spring[7] = getResources().getDrawable(R.drawable.up_spring_8);
        up_spring[8] = getResources().getDrawable(R.drawable.up_spring_9);
        up_spring[9] = getResources().getDrawable(R.drawable.up_spring_10);
        up_spring[10] = getResources().getDrawable(R.drawable.up_spring_11);
        up_spring[11] = getResources().getDrawable(R.drawable.up_spring_12);

        // up_summer
        up_summer[0] = getResources().getDrawable(R.drawable.up_summer_1);
        up_summer[1] = getResources().getDrawable(R.drawable.up_summer_2);
        up_summer[2] = getResources().getDrawable(R.drawable.up_summer_3);
        up_summer[3] = getResources().getDrawable(R.drawable.up_summer_4);
        up_summer[4] = getResources().getDrawable(R.drawable.up_summer_5);

        // up_fall
        up_fall[0] = getResources().getDrawable(R.drawable.up_fall_1);
        up_fall[1] = getResources().getDrawable(R.drawable.up_fall_2);
        up_fall[2] = getResources().getDrawable(R.drawable.up_fall_3);
        up_fall[3] = getResources().getDrawable(R.drawable.up_fall_4);
        up_fall[4] = getResources().getDrawable(R.drawable.up_fall_5);
        up_fall[5] = getResources().getDrawable(R.drawable.up_fall_6);
        up_fall[6] = getResources().getDrawable(R.drawable.up_fall_7);
        up_fall[7] = getResources().getDrawable(R.drawable.up_fall_8);
        up_fall[8] = getResources().getDrawable(R.drawable.up_fall_9);

        // up_winter
        up_winter[0] = getResources().getDrawable(R.drawable.up_winter_1);
        up_winter[1] = getResources().getDrawable(R.drawable.up_winter_2);
        up_winter[2] = getResources().getDrawable(R.drawable.up_winter_3);
        up_winter[3] = getResources().getDrawable(R.drawable.up_winter_4);
        up_winter[4] = getResources().getDrawable(R.drawable.up_winter_5);
        up_winter[5] = getResources().getDrawable(R.drawable.up_winter_6);
        up_winter[6] = getResources().getDrawable(R.drawable.up_winter_7);

        // down_spring
        down_spring[0] = getResources().getDrawable(R.drawable.down_spring_1);
        down_spring[1] = getResources().getDrawable(R.drawable.down_spring_2);

        // down_summer
        down_summer[0] = getResources().getDrawable(R.drawable.down_summer_1);
        down_summer[1] = getResources().getDrawable(R.drawable.down_summer_2);
        down_summer[2] = getResources().getDrawable(R.drawable.down_summer_3);
        down_summer[3] = getResources().getDrawable(R.drawable.down_summer_4);

        // down_fall
        down_fall[0] = getResources().getDrawable(R.drawable.down_fall_1);
        down_fall[1] = getResources().getDrawable(R.drawable.down_fall_2);

        // down_winter
        down_winter[0] = getResources().getDrawable(R.drawable.down_winter_1);
        down_winter[1] = getResources().getDrawable(R.drawable.down_winter_2);

        RandomPick();

        anotherRecommendBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                RandomPick();
            }
        });
    }
}
