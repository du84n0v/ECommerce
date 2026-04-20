package repository;

import entity.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProfileRepository {
    private SessionFactory factory;

    public Profile getProfileByPhone(String phone) {
        try(Session session = factory.openSession()){
            Query<Profile> query = session.createQuery("FROM Profile p where p.phone == phone");
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
            Query<Profile> query = session.createQuery("FROM Profile p WHERE p.phone == phone AND p.password == password");
            List<Profile> profiles = query.getResultList();
            return (profiles.isEmpty() ? null : profiles.getFirst());
        }
    }
}
