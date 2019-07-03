package com.sapling.example.base.event;

import java.util.EventListener;

/**
 * @author wei.zhou
 * @date 2019/4/7
 * @since 1.0
 */
public interface CustomerEnvetListner extends EventListener {

    void update(CustomerEvent o, Object arg);

}
