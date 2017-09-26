package com.back;

/**
 * Created by Rowan on 26/09/17.
 */

public interface IAlertContent {
    /**
     * Triggers the content
     */
    void trigger();

    /**
     * @return the ID of the alerted content
     */
    String getId();

    /**
     * @return the name of the option
     */
    String getOptionName();
}
