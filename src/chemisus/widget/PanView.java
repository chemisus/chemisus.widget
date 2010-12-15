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

    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/


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

            scrollBy((int)x, (int)y);

            touchTotal[X] += Math.abs(x);

            touchTotal[Y] += Math.abs(y);
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
