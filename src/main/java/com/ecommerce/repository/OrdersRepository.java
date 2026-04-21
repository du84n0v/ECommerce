package com.ecommerce.repository;

import com.ecommerce.entity.Orders;
import com.ecommerce.enums.OrderStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    public int save(Orders order) {
        Transaction tt;
        try(Session session = factory.openSession()){
            tt = session.beginTransaction();
            try{
                Integer orderId = (Integer) session.save(order);
                tt.commit();
                return orderId;
            }
            catch (Exception ee){
                tt.rollback();
                System.out.println(ee.getMessage());
                return 0;
            }
        }
    }

    public int orderState(int orderId, Integer profileId) {
        try(Session session = factory.openSession()){
            Query<Orders> query = session.createQuery("FROM Orders o WHERE o.id =:orderId AND o.profileId =:profileId", Orders.class);
            query.setParameter("orderId", orderId);
            query.setParameter("profileId", profileId);
            List<Orders> result = query.getResultList();
            if(result.isEmpty()) return 0;
            if(result.getFirst().getStatus().equals(OrderStatus.PAID)) return 1;
            if(result.getFirst().getStatus().equals(OrderStatus.CANCELLED)) return 2;
            return 3;
        }
    }

    public boolean updateOrderStatus(int orderId, OrderStatus newStatus) {
        Transaction tt;
        try(Session session = factory.openSession()){
            tt = session.beginTransaction();
            try {
                Query query = session.createQuery("UPDATE Orders o SET o.status = :newStatus WHERE o.id = :orderId");
                query.setParameter("newStatus", newStatus);
                query.setParameter("orderId", orderId);
                int result = query.executeUpdate();
                tt.commit();
                return result > 0;
            }
            catch (Exception ee){
                tt.rollback();
                System.out.println(ee.getMessage());
                return false;
            }

        }
    }
}
