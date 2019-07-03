package com.sapling.example.base.event;

import java.util.EventObject;

/**
 * @author wei.zhou
 * @date 2019/4/7
 * @since 1.0
 */
public class CustomerEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomerEvent(Object source) {
        super(source);
    }
    
}
