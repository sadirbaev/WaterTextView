package uz.sadirbaev.waterbackground;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.Layout;
import android.widget.TextView;


public abstract class BackgroundSpannable {

    private int lineStart;
    private int lineEnd;

    private Rect[] lines;

    private TextView tv;

    private int start;
    private int end;


    public enum LinePosition{
        LinePositionStart, LinePositionMiddle, LinePositionEnd, LinePositionSingle;
    }

    public void setMainTextView(TextView tv){
        this.tv = tv;
    }

    public void setRange(int start, int end){
        this.start = start;
        this.end = end;
    }

    public int getStart(){
        return this.start;
    }

    public int getEnd(){
        return this.end;
    }

    public void updateDrawState(Canvas canvas, int PADDING){
        Layout layout = tv.getLayout();

        if(layout != null){

            int saveBound = canvas.save();

            int clipLeft = tv.getPaddingLeft();
            int clipTop = tv.getTotalPaddingTop() + tv.getScrollY();
            int clipRight = canvas.getWidth() - tv.getPaddingRight();
            int clipBottom = clipTop + (tv.getHeight() - tv.getTotalPaddingTop() - tv.getTotalPaddingBottom());

            canvas.clipRect(clipLeft, clipTop, clipRight, clipBottom);

            lineStart = layout.getLineForOffset(start);
            lineEnd = layout.getLineForOffset(end);

            if(lineStart != lineEnd){
                lines = new Rect[(lineEnd + 1) - lineStart];

                for(int i = lineStart; i <= lineEnd; i++){
                    Rect rect = new Rect();
                    layout.getLineBounds(i, rect);

                    if(i == lineStart){
                        rect.left = (int) layout.getPrimaryHorizontal(start);
                    }

                    if(i == lineEnd){
                        rect.right = (int) layout.getSecondaryHorizontal(end);
                    }else{
                        float[] width = new float[1];
                        String t = layout.getText().subSequence(layout.getLineEnd(i) - 1, layout.getLineEnd(i)).toString();
                        layout.getPaint().getTextWidths(t, width);
                        rect.right = (int) (layout.getSecondaryHorizontal(layout.getLineEnd(i) - 1) + width[0]);
                    }
                    rect.left = rect.left - PADDING;
                    rect.right = rect.right + PADDING;
                    lines[i - lineStart] = rect;
                }
            }else{
                Rect rect = new Rect();
                lines = new Rect[1];
                layout.getLineBounds(lineStart, rect);
                rect.left = (int) layout.getPrimaryHorizontal(start);
                rect.right = (int) layout.getSecondaryHorizontal(end);

                rect.left = rect.left - PADDING;
                rect.right = rect.right + PADDING;
                lines[0] = rect;
            }

            //calculate x & y for padding
            int x = tv.getCompoundPaddingLeft();
            int y = tv.getTotalPaddingTop();

            int saveTranslate = canvas.save();

            canvas.translate(x, y);

            int length = lineEnd - lineStart;

            for(int i = 0; i <= lineEnd - lineStart; i++){
                Rect rect = lines[i];

                if(i == 0){
                    canvas.translate(rect.left, rect.top);
                }else{
                    canvas.translate(-lines[i - 1].left, -lines[i - 1].top);
                    canvas.translate(rect.left, rect.top);
                }

                LinePosition linePosition;

                if(length != 0){
                    if(i == 0){
                        linePosition = LinePosition.LinePositionStart;
                    }else if(i == length){
                        linePosition = LinePosition.LinePositionEnd;
                    }else{
                        linePosition = LinePosition.LinePositionMiddle;
                    }

                }else{
                    linePosition = LinePosition.LinePositionSingle;
                }

                drawLine(canvas, rect.width(), rect.height(), linePosition);
            }
            canvas.restoreToCount(saveTranslate);
            canvas.restoreToCount(saveBound);
        }
    }

    protected abstract void drawLine(Canvas canvas, int width, int height, LinePosition linePosition);
}
