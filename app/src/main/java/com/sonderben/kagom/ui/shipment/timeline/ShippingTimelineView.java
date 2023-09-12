package com.sonderben.kagom.ui.shipment.timeline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sonderben.kagom.R;

import java.util.List;

public class ShippingTimelineView extends View {
    private Paint paint;
    private List<View>entryView;

    private float percentComplete;
    private Paint circlePaint;
    private int currentColor;




    public void setPercentComplete(float percentComplete) {
        this.percentComplete = percentComplete;
        invalidate();
    }

    public ShippingTimelineView(Context context) {
        super(context);
        init();
    }
    public void setEntryView(List<View>entryView){
        this.entryView = entryView;
        invalidate();
    }
    public ShippingTimelineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void initCirclePaint() {
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL);
    }
    private void init() {
        initCirclePaint();
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int height = getHeight();
        final int width = getWidth();
        float radiusCircle =  (width * 0.05f);
        float lineStartY =  (height * 0.1f);

        float lineEndY = height - lineStartY;

        float lineStartX = radiusCircle * 3 ;
        float circleY = lineStartY;

        paint.setStrokeWidth(width * 0.010f);

        float total = height - (lineStartY + lineStartY);

        if (entryView!=null && entryView.size()>1){

            float step = ( total / ( entryView.size() - 1) );
            canvas.drawLine(lineStartX+0,lineStartY+0,lineStartX+0,lineEndY+0,paint);

            for (int i = 0; i < entryView.size(); i++) {
                canvas.drawCircle(lineStartX,circleY,radiusCircle,paint);

                circleY += step;
            }
            circlePaint.setColor(currentColor);
            paint.setColor( getResources().getColor( R.color.secondary ) );


            circleY = lineStartY;


            for (int i = 0; i < entryView.size(); i++) {
                if (entryView.get(i) != null) {
                    View stepView = entryView.get(i);


                    final float a = (float) ( (( (i+1) *100.0)/entryView.size())/100 );

                    if (  a<=percentComplete){
                        active(stepView);

                    }else {

                    }











                    stepView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                    stepView.layout(0, 0, stepView.getMeasuredWidth(), stepView.getMeasuredHeight());

                    canvas.save();
                    canvas.translate(lineStartX + radiusCircle * 4, circleY -  (stepView.getMeasuredHeight() / 2) );
                    stepView.draw(canvas);
                    canvas.restore();
                }
                circleY += step;
            }

            circleY = lineStartY;
            canvas.drawLine(lineStartX+0,lineStartY+0,lineStartX+0,lineEndY*percentComplete,paint);
            for (int i = 1; i <= entryView.size(); i++) {

                final float a = (float) (((i*100.0)/entryView.size())/100);

                if (  a<=percentComplete){
                    System.out.println("percent: "+a);
                    canvas.drawCircle(lineStartX,circleY,radiusCircle,paint);
                    circleY += step;

                }else {
                    paint.setColor( Color.GRAY );
                    canvas.drawCircle(lineStartX,circleY,radiusCircle,paint);
                    break;
                }



            }

        }

    }

    private void active(View view){

        if (view instanceof TextView){
            TextView textView = (TextView) view;
            textView.setTextColor( getResources().getColor(R.color.primaryVariant));
            textView.setTextSize(18);
            textView.setTypeface(null, Typeface.BOLD);
        }
        if (view instanceof ViewGroup){
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {

                active(vg.getChildAt(i));

            }
        }

        //
    }

}
