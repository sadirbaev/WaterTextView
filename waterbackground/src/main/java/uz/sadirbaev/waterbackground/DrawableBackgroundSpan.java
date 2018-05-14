package uz.sadirbaev.waterbackground;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class DrawableBackgroundSpan extends BackgroundSpannable {

    private Drawable drawable;

    public DrawableBackgroundSpan(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    protected void drawLine(Canvas canvas, int width, int height, LinePosition linePosition){
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
    }


}