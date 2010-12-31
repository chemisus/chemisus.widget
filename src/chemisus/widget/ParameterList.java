/**
 * @author      Terrence Howard <chemisus@gmail.com>
 * @copyright   Copyright (c) 2010, Terrence Howard
 * @package     chemisus.widget
 */
package chemisus.widget;

import java.util.LinkedHashMap;

/**
 * <i>chemisus.widget.ParameterList</i>
 *
 * @author      Terrence Howard <chemisus@gmail.com>
 * @package     chemisus.widget
 * @subpackage  ParameterList
 * @version     0.1
 * @since       0.1
 */
public class ParameterList
    extends android.content.Intent
{
    /*\**********************************************************************\*/
    /*\                             Constants                                \*/
    /*\**********************************************************************\*/


    /*\**********************************************************************\*/
    /*\                             Static Fields                            \*/
    /*\**********************************************************************\*/
    private static ParameterList Current;

    /*\**********************************************************************\*/
    /*\                             Static Methods                           \*/
    /*\**********************************************************************\*/
    public static ParameterList Current()
    {
        if (Current == null)
        {
            Current = new ParameterList();
        }

        return Current;
    }

    /*\**********************************************************************\*/
    /*\                             Fields                                   \*/
    /*\**********************************************************************\*/
    private LinkedHashMap<String, Object> items = new LinkedHashMap<String, Object>();

    /*\**********************************************************************\*/
    /*\                             Properties                               \*/
    /*\**********************************************************************\*/
    public Object getItem(String key)
    {
        return items.get(key);
    }

    public Object getItem(String key, Object defaultValue)
    {
        return items.containsKey(key) ? getItem(key) : defaultValue;
    }

    public ParameterList putItem(String key, Object value)
    {
        items.put(key, value);

        return this;
    }

    /*\**********************************************************************\*/
    /*\                             Constructors                             \*/
    /*\**********************************************************************\*/
    public ParameterList()
    {
        Current = this;
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


}