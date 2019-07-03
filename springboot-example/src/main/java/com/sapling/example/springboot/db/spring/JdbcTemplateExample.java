package com.sapling.example.springboot.db.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author wei.zhou
 * @date 2019/4/8
 * @since 1.0
 */
@Service
public class JdbcTemplateExample {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createUser(Integer id, String name, String password) {
        jdbcTemplate.update("insert into user(id,name,password) values(? ,?,?)", id, name, password);
    }

}
