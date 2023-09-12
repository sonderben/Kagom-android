package com.sonderben.kagom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }


    /*
    package com.sonderben.kagom.ui.shipment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.sonderben.kagom.R;

public class ShippingTimelineView extends View {
    private Paint paint;
    private Paint paintComplete;
    private String[] steps = {"Processed", "Sent", "Customs"/*, "Center Distribution", "Retired"*/};
    private View[] stepViews;
    private int completedStep = 0; // Indicar el paso completado (0-indexed)
    private Context context;

    public ShippingTimelineView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ShippingTimelineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);

        paintComplete = new Paint();
        paintComplete.setStrokeWidth(30);

        stepViews = new View[steps.length];
    }

    public void setStepViews(View[] stepViews) {
        this.stepViews = stepViews;
        invalidate();
    }

    public void setCompletedStep(int completedStep) {
        this.completedStep = completedStep;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //circle
        int centerX = getWidth() / 2;
        int startY = getHeight() / steps.length / 2;
        int circleRadius = 100;

        //line
        int lineStartY = startY;
        //int lineEndY = startY;
        int lineEndY = 0;

        paint.setStrokeWidth(50);
        lineEndY += getHeight() ;
        canvas.drawLine(centerX, lineStartY + circleRadius, centerX, lineEndY - circleRadius, paint);

        for (int i = 0; i < steps.length; i++) {
            boolean isCompleted = i <= completedStep;

            paint.setColor( Color.GRAY);

            canvas.drawCircle(centerX, lineStartY, circleRadius, paint);

            if (i < steps.length - 1) {

            }

            if (stepViews[i] != null) {
                View stepView = stepViews[i];
                stepView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                stepView.layout(0, 0, stepView.getMeasuredWidth(), stepView.getMeasuredHeight());

                canvas.save();
                canvas.translate(centerX + circleRadius * 2, (lineStartY + circleRadius * 2)-150);
                stepView.draw(canvas);
                canvas.restore();
            }

            lineStartY += getHeight() / steps.length;
        }
    }
}

     */



}