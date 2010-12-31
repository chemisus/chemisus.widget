/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2010, Terrence Howard
 * @package     chemisus.widget
 */
package chemisus.widget;

import android.content.Intent;
import android.os.Bundle;

/**
 * <i>chemisus.widget.Activity</i>
 *
 * @author      Terrence Howard <chemisus@gmail.com>
 * @package     chemisus.widget
 * @subpackage  Activity
 * @version     0.1
 * @since       0.1
 * @abstract
 */
abstract public class Activity
    extends android.app.Activity
{
    /*\**********************************************************************\*/
    /*\                             Constants                                \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Static Fields                            \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Static Methods                           \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Fields                                   \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/
    public void newActivity(Class<?> cls)
    {
        startActivity(new Intent(this, cls));
    }

    public Object getParameter(String key)
    {
        return ParameterList.Current().getItem(key);
    }

    public Object getParameter(String key, Object defaultValue)
    {
        return ParameterList.Current().getItem(key, defaultValue);
    }

    /*\**********************************************************************\*/
    /*\                             Constructors                             \*/
    /*\**********************************************************************\*/


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
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);

        onCreating();

        onCreated();
    }

    public void onCreating()
    {
    }

    public void onCreated()
    {
    }
}