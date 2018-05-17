 [![](https://jitpack.io/v/qarakenbacho/WaterTextView.svg)](https://jitpack.io/#qarakenbacho/WaterTextView)
# WaterTextView
**Step 1.** Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
  repositories {
		...
    maven { url 'https://jitpack.io' }
  }
}
  ```
**Step 2.** Add the dependency
```
dependencies {
    implementation 'com.github.qarakenbacho:WaterTextView:1.2'
}
 ```
**Usage**
**activity_main.xml**
```
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="20dp"
    tools:context=".MainActivity">
    
    <!--This is a basic part. layout width must be match_parent-->
    <uz.sadirbaev.waterbackground.WaterTextView
        android:layout_width="match_parent" 
        android:layout_height="wrap_content"
        android:textSize="30sp"
        android:id="@+id/tv"
        android:layout_marginBottom="20dp"/>

</RelativeLayout>
```
**MainActivity.class**
```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    WaterTextView textView = findViewById(R.id.tv);
    String s = "Welcome to Water Background.";
    textView.setText(s);
    textView.setBackgroundAlpha(128); // set alpha to background from 0 to 255 value
}
```
<p align="center">
  <img src="https://github.com/qarakenbacho/WaterTextView/blob/master/screenshots/photo_2018-05-14_19-52-21.jpg" width="200"/>
</p>



