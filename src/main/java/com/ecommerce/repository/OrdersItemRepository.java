package com.ecommerce.repository;

import com.ecommerce.entity.OrdersItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersItemRepository {

    @Autowired
    private SessionFactory factory;

    public List<OrdersItem> getItemsByOrderId(int orderId) {
        try(Session session = factory.openSession()){
            Query<OrdersItem> query = session.createQuery("FROM OrdersItem ot WHERE ot.orderId =:orderId", OrdersItem.class);
            query.setParameter("orderId", orderId);
            return query.getResultList();
        }
    }

    public void save(OrdersItem ordersItem) {
        Transaction tt;
        try(Session session = factory.openSession()){
            tt = session.beginTransaction();
            try{
                session.save(ordersItem);
                tt.commit();
            }
            catch (Exception ee){
                tt.rollback();
                System.out.println(ee.getMessage());
            }
        }
    }
}
