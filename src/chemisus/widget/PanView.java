/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2010, Terrence Howard
 * @package     chemisus.widget
 */
package chemisus.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * <i>chemisus.widget.PanView</i>
 *
 * @author      Terrence Howard <chemisus@gmail.com>
 * @package     chemisus.widget.test
 * @subpackage  PanView
 * @version     0.1
 * @since       0.1
 * @abstract
 */
public class PanView
    extends RelativeLayout
    implements OnGestureListener
{
    /*\**********************************************************************\*/
    /*\                             Constants                                \*/
    /*\**********************************************************************\*/
    public final static int X = 0;

    public final static int Y = 1;

    /*\**********************************************************************\*/
    /*\                             Static Fields                            \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Static Methods                           \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Fields                                   \*/
    /*\**********************************************************************\*/
    private GestureDetector gesture;

    private boolean scrolled = false;

    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Constructors                             \*/
    /*\**********************************************************************\*/
    public PanView(Context context)
    {
        super(context);

        setup();
    }

    public PanView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        setup();
    }

    public PanView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        setup();
    }

    /*\**********************************************************************\*/
    /*\                             Private Methods                          \*/
    /*\**********************************************************************\*/
    protected void setup()
    {
        gesture = new GestureDetector(this);
    }

    /*\**********************************************************************\*/
    /*\                             Public Methods                           \*/
    /*\**********************************************************************\*/
    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        gesture.onTouchEvent(event);

        if (event.getAction() == MotionEvent.ACTION_UP && onUp(event))
        {
            return true;
        }

        super.dispatchTouchEvent(event);

        return true;
    }

    public void fling(float velocityX, float velocityY)
    {
    }

    /*\**********************************************************************\*/
    /*\                             Callbacks                                \*/
    /*\**********************************************************************\*/
    public boolean onDown(MotionEvent event)
    {
        return false;
    }

    public boolean onUp(MotionEvent event)
    {
        if (scrolled)
        {
            scrolled = false;

            return true;
        }

        return false;
    }

    public boolean onSingleTapUp(MotionEvent event)
    {
        return false;
    }

    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY)
    {
        scrollBy((int)distanceX, (int)distanceY);

        scrolled = true;

        return false;
    }

    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY)
    {
        return false;
    }

    public void onShowPress(MotionEvent event)
    {
    }

    public void onLongPress(MotionEvent event)
    {
    }
}
