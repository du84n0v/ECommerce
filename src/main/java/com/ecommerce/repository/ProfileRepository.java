package com.ecommerce.repository;

import com.ecommerce.entity.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProfileRepository {
    @Autowired
    private SessionFactory factory;

    public Profile getProfileByPhone(String phone) {
        try(Session session = factory.openSession()){
            Query<Profile> query = session.createQuery("FROM Profile p where p.phone =: phone", Profile.class);
            query.setParameter("phone", phone);
            List<Profile> response = query.getResultList();
            return (response.isEmpty() ? null : response.getFirst());
        }
    }

    public int save(Profile profile) {
        try(Session session = factory.openSession()){
            Transaction tt = session.beginTransaction();
            try{
                session.save(profile);
                tt.commit();
                return 1;
            }
            catch (Exception ee){
                tt.rollback();
                System.out.println(ee.getMessage());
                return 0;
            }
        }
    }

    public Profile getProfileByPhoneAndPassword(String phone, String password) {
        try(Session session = factory.openSession()){
            Query<Profile> query = session.createQuery("FROM Profile p WHERE p.phone =: phone AND p.password =: password", Profile.class);
            query.setParameter("phone", phone);
            query.setParameter("password", password);
            List<Profile> profiles = query.getResultList();
            return (profiles.isEmpty() ? null : profiles.getFirst());
        }
    }

    public boolean fillBalance(Integer profileId, double amount) {
        try(Session session = factory.openSession()){
            Transaction tt = session.beginTransaction();
            try{
                Query query = session.createQuery("UPDATE Profile p SET p.balance = p.balance + :amount WHERE p.id =: profileId");
                query.setParameter("amount", amount);
                query.setParameter("profileId", profileId);
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

    public Profile getProfileById(Integer profileId) {
        try(Session session = factory.openSession()){
            Query<Profile> query = session.createQuery("FROM Profile p WHERE p.id = :profileId", Profile.class);
            query.setParameter("profileId", profileId);
            List<Profile> profiles = query.getResultList();
            return (profiles.isEmpty() ? null : profiles.getFirst());
        }
    }

    public int withdrawBalance(Integer profileId, double totalSum) {
        Transaction tt;
        try(Session session = factory.openSession()){
            tt = session.beginTransaction();
            try{
                Query query = session.createQuery("UPDATE Profile p SET p.balance = p.balance - :total WHERE p.id = :profileId");
                query.setParameter("total", totalSum);
                query.setParameter("profileId", profileId);
                int upd = query.executeUpdate();
                tt.commit();
                return upd;
            }
            catch (Exception ee){
                tt.rollback();
                System.out.println(ee.getMessage());
                return 0;
            }
        }
    }
}

























