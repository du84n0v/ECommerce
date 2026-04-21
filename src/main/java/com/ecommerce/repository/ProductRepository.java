package com.ecommerce.repository;

import com.ecommerce.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class ProductRepository {

    @Autowired
    private SessionFactory factory;

    public List<Product> getAllProduct() {
        try(Session session = factory.openSession()){
            Query<Product> query = session.createQuery("FROM Product", Product.class);
            return query.getResultList();
        }
    }

    public List<Product> getProductsByIds(Set<Integer> ids) {
        try(Session session = factory.openSession()){
            Query<Product> query = session.createQuery("FROM Product WHERE id IN (:ids)", Product.class);
            query.setParameter("ids", ids);
            return query.getResultList();
        }
    }

    public boolean hasEnoughProduct(Integer productId, Integer quantity) {
        try(Session session = factory.openSession()){
            Query<Product> query = session.createQuery("FROM Product p WHERE p.id = :productId AND p.quantity >=:quantity", Product.class);
            query.setParameter("productId", productId);
            query.setParameter("quantity", quantity);
            List<Product> result = query.getResultList();
            return !result.isEmpty();
        }
    }
}
