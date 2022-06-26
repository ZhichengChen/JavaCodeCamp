package com.example.demo;

import com.example.demo.bean.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Hikari {
    private JdbcTemplate jdbcTemplate;

    Hikari(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int create(Integer userId, Integer productId) {
        return jdbcTemplate.update("insert into Order(userId, productId) values(?, ?)", userId, productId);
    }

    public Order getById(Integer id) {
        Order result = jdbcTemplate.queryForObject("select id,userId,productId,create_time,update_time from Order where id = ?", (resultSet, i) -> {
            Order order = new Order();
            order.setId(resultSet.getString("id"));
            order.setUserId(resultSet.getInt("useId"));
            order.setProductId(resultSet.getInt("productId"));
            order.setCreate_time(resultSet.getTime("create_time"));
            order.setUpdate_time(resultSet.getTime("update_time"));
            return order;
        }, id);
        return result;
    }

    public int deleteById(Integer id) {
        return jdbcTemplate.update("delete from Order where id = ?", id);
    }

    public int updateOrder(Integer id, Integer productId) {
        return jdbcTemplate.update("update from Order set productId = ? where id = ?", productId, id);
    }
}
