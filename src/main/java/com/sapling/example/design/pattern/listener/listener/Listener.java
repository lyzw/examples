package com.sapling.example.design.pattern.listener.listener;

import com.sapling.example.design.pattern.listener.event.Event;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/26
 * @since v1.0
 */
public interface Listener {



    void fireEvent(Event event);
}
