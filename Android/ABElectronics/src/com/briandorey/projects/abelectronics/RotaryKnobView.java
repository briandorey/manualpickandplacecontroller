package com.briandorey.projects.abelectronics;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

// RotaryKnobView based on code from http://go-lambda.blogspot.co.uk/2012/02/rotary-knob-widget-on-android.html

public class RotaryKnobView extends ImageView  {
 
    private float angle = 0f;
    private float theta_old=0f;
   private int width;
   private int height;
   
    private RotaryKnobListener listener;
    
    public interface RotaryKnobListener {
      public void onKnobChanged(float delta, float angle);
    }
    
    public void setKnobListener(RotaryKnobListener l )
    {
      listener = l;
    }
    
    public RotaryKnobView(Context context) {
      super(context);
    // TODO Auto-generated constructor stub
    }
    
    public RotaryKnobView(Context context, AttributeSet attrs)
    {
      super(context, attrs);
      initialize();
    }
    
    public RotaryKnobView(Context context, AttributeSet attrs, int defStyle)
    {
      super(context, attrs, defStyle);
    }
    
    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
      width = w;
    	height = h;
        initialize();
    }
    
    private float getTheta(float x, float y)
    {
      float sx = x - (width / 2.0f);
      float sy = y - (height / 2.0f);
 
      float length = (float)Math.sqrt( sx*sx + sy*sy);
      float nx = sx / length;
      float ny = sy / length;
      float theta = (float)Math.atan2( ny, nx );
 
      final float rad2deg = (float)(180.0/Math.PI);
      float theta2 = theta*rad2deg;
 
      return (theta2 < 0) ? theta2 + 360.0f : theta2;
    }
    
    public void initialize()
    {
 
      this.setImageResource(R.drawable.jog);
      setOnTouchListener(new OnTouchListener()
      {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          // TODO Auto-generated method stub
          int action = event.getAction();
          int actionCode = action & MotionEvent.ACTION_MASK;
          if (actionCode == MotionEvent.ACTION_POINTER_DOWN)
          {
            float x = event.getX(0);
            float y = event.getY(0);
            theta_old = getTheta(x, y);
          }
          else if (actionCode == MotionEvent.ACTION_MOVE)
          {
            invalidate();
       
            float x = event.getX(0);
            float y = event.getY(0);
       
            float theta = getTheta(x, y);
            float delta_theta = theta - theta_old;
       
            theta_old = theta;
       
            //int direction = (delta_theta > 0) ? 1 : -1;
            angle = theta - 270;
       
            notifyListener(delta_theta, (theta + 90) % 360);
 
          }
          return true;
        }
 
     });
    }
    
    private void notifyListener(float delta, float angle) {
    	if (null!=listener) {
    		listener.onKnobChanged(delta, angle);
    	}
    }
    
    protected void onDraw(Canvas c) {
    	c.rotate(angle,width/2,height/2);   
    	super.onDraw(c);
    }
    
}