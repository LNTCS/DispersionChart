package kr.edcan.dispersionchart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by LNTCS on 2016-10-18.
 */

public class DispersionChartView extends View {
    public ArrayList<Dot> dotList = new ArrayList<>();

    float offsetH = 0f;
    float offsetW = 0f;

    int lineColor = Color.BLACK;
    float lineBorder = 2f;

    int dotColor = Color.BLACK;
    float dotSize = 5f;

    int bgColor = Color.WHITE;

    int mHeight = 0;
    int mWidth = 0;

    public DispersionChartView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public DispersionChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, R.attr.DPChartStyle);
    }

    public DispersionChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }
    private void init(Context context, AttributeSet attrs, int defStyle) {
        if(attrs != null){
            final TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.DispersionChartView, defStyle, 0);

            lineBorder = a.getDimension(R.styleable.DispersionChartView_lineWidth, lineBorder);
            lineColor = a.getColor(R.styleable.DispersionChartView_lineColor, lineColor);

            dotSize = a.getDimension(R.styleable.DispersionChartView_dotSize, dotSize);
            dotColor = a.getColor(R.styleable.DispersionChartView_dotColor, dotColor);

            bgColor = a.getColor(R.styleable.DispersionChartView_bgColor, bgColor);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        mWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        offsetH = mHeight / 100f;
        offsetW = mWidth / 100f;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(bgColor);
        drawLine(canvas);
        drawDot(canvas);
    }

    private void drawDot(Canvas canvas) {
        for(Dot dot : dotList){
            Paint dotPaint = new Paint();
            dotPaint.setColor((dot.color == 0)? dotColor : dot.color);
            canvas.drawCircle(dot.x * offsetW, dot.y * offsetH, dotSize, dotPaint);
        }
    }

    private void drawLine(Canvas canvas) {
        Paint linePaint = new Paint();
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineBorder);

        canvas.drawLine(0f, mHeight/3f, (float)mWidth, mHeight/3f, linePaint);
        canvas.drawLine(0f, mHeight/3f * 2, (float)mWidth, mHeight/3f * 2, linePaint);
        canvas.drawLine(mWidth/3f, 0f, mWidth/3f, (float)mHeight, linePaint);
        canvas.drawLine(mWidth/3f * 2, 0f, mWidth/3f * 2, (float)mHeight, linePaint);
    }

    public void addDot(Dot dot){
        dotList.add(dot);
        requestLayout();
    }

    public void addDots(ArrayList<Dot> dots){
        if(dots == null) return;
        for(Dot dot : dots){
            dotList.add(new Dot(dot));
        }
        requestLayout();
    }

    public void deleteDot(Dot dot){
        dotList.remove(dot);
        requestLayout();
    }

    public void deleteDot(int index){
        dotList.remove(dotList.get(index));
        requestLayout();
    }

    public void deleteAllDots(){
        dotList.clear();
        requestLayout();
    }

    public ArrayList<Dot> getDots(int i, int j){
        ArrayList<Dot> result = new ArrayList<>();
        float div = 100f/3f;
        for(int d = 0 ; d < dotList.size() ; ++d){
            Dot dot = dotList.get(d);
            if( (dot.x > (div * i) && dot.x < (div * (i+1)))
                    &&
                    (dot.y > (div * j) && dot.y <(div * (j+1)))){
                result.add(dot);
            }
        }
        return result;
    }

    public void  deleteDots(int i, int j) {
        for(int index = 0 ; index < getDots(i, j).size() ; ++index){
            deleteDot(getDots(i, j).get(0));
        }
        requestLayout();
    }



    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        requestLayout();
    }

    public float getLineBorder() {
        return lineBorder;
    }

    public void setLineBorder(float lineBorder) {
        this.lineBorder = lineBorder;
        requestLayout();
    }

    public int getDotColor() {
        return dotColor;
    }

    public void setDotColor(int dotColor) {
        this.dotColor = dotColor;
        requestLayout();
    }

    public float getDotSize() {
        return dotSize;
    }

    public void setDotSize(float dotSize) {
        this.dotSize = dotSize;
        requestLayout();
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
        requestLayout();
    }
}
