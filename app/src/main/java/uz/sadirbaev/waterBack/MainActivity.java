package uz.sadirbaev.waterBack;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import uz.sadirbaev.waterbackground.WaterTextView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WaterTextView textView = findViewById(R.id.tv);
        String s = "Welcome to Water Background.";
        textView.setText(s);
        textView.setBackgroundAlpha(128); // set alpha to background from 0 to 255 value

    }

}
