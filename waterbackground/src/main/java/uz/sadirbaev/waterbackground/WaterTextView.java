package uz.sadirbaev.waterbackground;

import android.content.Context;
import android.graphics.Canvas;
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


    private void init(){
        invalidate();
        this.spanBoth = new DrawableBackgroundSpan(getResources().getDrawable(R.drawable.both9));
        this.spanTop = new DrawableBackgroundSpan(getResources().getDrawable(R.drawable.top9));
        this.spanBottom = new DrawableBackgroundSpan(getResources().getDrawable(R.drawable.bottom9));
        this.spanNone = new DrawableBackgroundSpan(getResources().getDrawable(R.drawable.none9));
        this.setGravity(Gravity.CENTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setBreakStrategy(Layout.BREAK_STRATEGY_SIMPLE);
        }
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
