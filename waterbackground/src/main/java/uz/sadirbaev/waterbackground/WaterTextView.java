package uz.sadirbaev.waterbackground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by QarakenBacho on 13.05.2018.
 */

public class WaterTextView extends android.support.v7.widget.AppCompatTextView {

    private DrawableBackgroundSpan spanBoth;
    private DrawableBackgroundSpan spanTop;
    private DrawableBackgroundSpan spanBottom;
    private DrawableBackgroundSpan spanNone;
    private int alpha = 255;
    private static int DIFFERENCE = 120;


    public WaterTextView(Context context) {
        super(context);
        init();
    }

    public WaterTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaterTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void setBackgroundAlpha(int alpha){
        this.alpha = alpha;
        init();
    }

    private void init(){
        Drawable both = getResources().getDrawable(R.drawable.both);
        both.setAlpha(alpha);
        this.spanBoth = new DrawableBackgroundSpan(both);

        Drawable top = getResources().getDrawable(R.drawable.top);
        top.setAlpha(alpha);
        this.spanTop = new DrawableBackgroundSpan(top);

        Drawable bottom = getResources().getDrawable(R.drawable.bottom);
        bottom.setAlpha(255);
        this.spanBottom = new DrawableBackgroundSpan(bottom);

        Drawable none = getResources().getDrawable(R.drawable.none);
        none.setAlpha(255);
        this.spanNone = new DrawableBackgroundSpan(none);

        this.setGravity(Gravity.CENTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setBreakStrategy(Layout.BREAK_STRATEGY_SIMPLE);
        }
        invalidate();
    }


    @Override
    public void onDraw(Canvas canvas){


        List<Integer> lines = new ArrayList<>();
        List<Float> widths = new ArrayList<>();
        for (int i=0; i< this.getLineCount(); i++){
            lines.add(this.getLayout().getLineEnd(i));
            widths.add(this.getLayout().getLineWidth(i));
        }

        int start = 0;
        for (int i=0; i<lines.size(); i++){
            BackgroundSpannable span = spanBoth;
            int PADDING = 20;

            if (lines.size()>1) {
                if (i == 0) {
                    if (widths.get(i + 1) - widths.get(i) > DIFFERENCE) {
                        if (this.spanBottom != null) {
                            span = spanBottom;
                            PADDING = 60;
                        }
                    }
                } else {
                    if (i == lines.size() - 1) {
                        if (widths.get(i - 1) - widths.get(i) > DIFFERENCE) {
                            if (this.spanTop != null) {
                                span = spanTop;
                                PADDING = 60;
                            }
                        }
                    } else {
                        if (widths.get(i + 1) - widths.get(i) > DIFFERENCE &&
                                widths.get(i - 1) - widths.get(i) > DIFFERENCE) {
                            if (this.spanNone != null) {
                                span = this.spanNone;
                                PADDING = 60;
                            }
                        } else {
                            if (widths.get(i - 1) - widths.get(i) > DIFFERENCE && widths.get(i + 1) - widths.get(i) < DIFFERENCE) {
                                if (this.spanTop != null) {
                                    span = spanTop;
                                    PADDING = 60;
                                }
                            } else {
                                if (widths.get(i + 1) - widths.get(i) > DIFFERENCE && widths.get(i - 1) - widths.get(i) < DIFFERENCE) {
                                    if (this.spanBottom != null) {
                                        span = spanBottom;
                                        PADDING = 60;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            span.setMainTextView(this);
            if (i==lines.size()-1) {
                span.setRange(start, lines.get(i));
            }else {
                span.setRange(start, lines.get(i) - 1);
            }
            span.updateDrawState(canvas, PADDING);

            start = lines.get(i);
        }


        super.onDraw(canvas);
    }

}
