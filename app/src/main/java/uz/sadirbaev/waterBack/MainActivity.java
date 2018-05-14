package uz.sadirbaev.waterBack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uz.sadirbaev.waterbackground.CustomTextView;


public class MainActivity extends AppCompatActivity {
    CustomTextView textView30;
    CustomTextView textView16;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView30 = findViewById(R.id.tv30);
        textView16 = findViewById(R.id.tv16);

        String s = "Welcome to Water Background.\nThis view is like unknown view, which is used in Telegram messenger.";

        textView30.setText(s);
        textView16.setText(s);

    }

}
