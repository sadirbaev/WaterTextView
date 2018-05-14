package uz.sadirbaev.waterBack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uz.sadirbaev.waterbackground.WaterTextView;


public class MainActivity extends AppCompatActivity {
    WaterTextView textView30;
    WaterTextView textView16;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView30 = findViewById(R.id.tv30);
        textView16 = findViewById(R.id.tv16);

        String s = "Welcome to Water Background.";

        textView30.setText(s);
        textView16.setText(s);

    }

}
