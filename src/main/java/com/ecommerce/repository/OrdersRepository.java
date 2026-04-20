package com.ecommerce.repository;

import com.ecommerce.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersRepository {

    @Autowired
    private SessionFactory factory;

    public List<Orders> getProfileOrders(Integer profileId) {
        try(Session session = factory.openSession()){
            Query<Orders> query = session.createQuery("FROM Orders o WHERE o.profileId =:profileId", Orders.class);
            query.setParameter("profileId", profileId);
            return query.getResultList();
        }
    }

    public Orders getOrderByOrderId(int orderId) {
        try(Session session = factory.openSession()){
            Query<Orders> query = session.createQuery("FROM Orders o WHERE o.id =:orderId", Orders.class);
            query.setParameter("orderId", orderId);
            List<Orders> result = query.getResultList();
            return (result.isEmpty() ? null : result.getFirst());
        }
    }
}
