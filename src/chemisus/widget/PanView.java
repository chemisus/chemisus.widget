/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2010, Terrence Howard
 * @package     chemisus.widget
 */
package chemisus.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * <i>chemisus.widget.PanView</i>
 *
 * @author      Terrence Howard <chemisus@gmail.com>
 * @package     chemisus.widget
 * @subpackage  PanView
 * @version     0.1
 * @since       0.1
 */
public class PanView
    extends RelativeLayout
    implements View.OnTouchListener
{
    /*\**********************************************************************\*/
    /*\                             Constants                                \*/
    /*\**********************************************************************\*/
    //public final int SIZE_ZERO = 1073741824;

    public final int MIN_TO_SCROLL = 5;

    public final int X = 0;

    public final int Y = 1;

    public final int TOP = 0;

    public final int BOTTOM = 1;

    public final int LEFT = 2;

    public final int RIGHT = 3;

    /*\**********************************************************************\*/
    /*\                             Static Fields                            \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Static Methods                           \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Fields                                   \*/
    /*\**********************************************************************\*/
    private boolean direct = true;

    private boolean touched = false;

    private float [] touchLast = new float[2];

    private float [] touchTotal = new float[2];

    private float [] minOffset = {0, 100};

    private float [] maxOffset = {0, 100};

    private boolean [] bounds = {true, true, true, true};

    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/
    public float getMinOffsetX()
    {
        return minOffset[X];
    }

    public float getMaxOffsetX()
    {
        return maxOffset[X];
    }

    public float getMinOffsetY()
    {
        return minOffset[Y];
    }

    public float getMaxOffsetY()
    {
        return maxOffset[Y];
    }

    public void setMinOffsetX(float value)
    {
        minOffset[X] = value;
    }

    public void setMaxOffsetX(float value)
    {
        maxOffset[X] = value;
    }

    public void setMinOffsetY(float value)
    {
        minOffset[Y] = value;
    }

    public void setMaxOffsetY(float value)
    {
        maxOffset[Y] = value;
    }

    public boolean getBoundTop()
    {
        return bounds[TOP];
    }

    public boolean getBoundBottom()
    {
        return bounds[BOTTOM];
    }

    public boolean getBoundLeft()
    {
        return bounds[LEFT];
    }

    public boolean getBoundRight()
    {
        return bounds[RIGHT];
    }

    public void setBoundTop(boolean value)
    {
        bounds[TOP] = value;
    }

    public void setBoundBottom(boolean value)
    {
        bounds[BOTTOM] = value;
    }

    public void setBoundLeft(boolean value)
    {
        bounds[LEFT] = value;
    }

    public void setBoundRight(boolean value)
    {
        bounds[RIGHT] = value;
    }

    /*\**********************************************************************\*/
    /*\                             Constructors                             \*/
    /*\**********************************************************************\*/
    public PanView(Context context)
    {
        super(context);
    }

    public PanView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public PanView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    /*\**********************************************************************\*/
    /*\                             Private Methods                          \*/
    /*\**********************************************************************\*/
    public float adjustBoundX(float x)
    {
        if (getBoundLeft() && x < getMinOffsetX())
        {
            x = getMinOffsetX();
        }
        
        if (getBoundRight() && x > getMaxOffsetX())
        {
            x = getMaxOffsetX();
        }

        return x;
    }

    public float adjustBoundY(float y)
    {
        if (getBoundTop() && y < getMinOffsetY())
        {
            y = getMinOffsetY();
        }

        if (getBoundBottom() && y > getMaxOffsetY())
        {
            y = getMaxOffsetY();
        }

        return y;
    }

    /*\**********************************************************************\*/
    /*\                             Public Methods                           \*/
    /*\**********************************************************************\*/

    /*\**********************************************************************\*/
    /*\                             Callbacks                                \*/
    /*\**********************************************************************\*/
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
                return onScrollEnd(event);

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                onScroll(event);
        }

        return direct;
    }

    public void onScroll(MotionEvent event)
    {
        if (touched)
        {
            float x = touchLast[X] - event.getRawX();

            float y = touchLast[Y] - event.getRawY();

            touchTotal[X] += Math.abs(x);

            touchTotal[Y] += Math.abs(y);

            x = adjustBoundX(getScrollX() + x);

            y = adjustBoundY(getScrollY() + y);

            scrollTo((int)x, (int)y);
        }

        touchLast[X] = event.getRawX();

        touchLast[Y] = event.getRawY();

        touched = true;
    }

    public boolean onScrollEnd(MotionEvent event)
    {
        boolean result = touchTotal[X] * touchTotal[X] + touchTotal[Y] * touchTotal[Y] >= MIN_TO_SCROLL;

        touchLast[X] = touchLast[Y] = 0;

        touchTotal[X] = touchTotal[Y] = 0;

        touched = false;

        return result;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event)
    {
        direct = false;

        boolean result = onTouchEvent(event);

        direct = true;

        return result;
    }

}
