package com.sapling.example.springboot;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wei.zhou
 * @date 2019/4/8
 * @since 1.0
 */
@Service
public class TransactionalExample {

    @Transactional
    public void testRollback(){

    }
}
