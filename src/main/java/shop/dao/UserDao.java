/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.dao;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import shop.entity.User;
import shop.utils.HibernateUtil;

@Named
@SessionScoped
public class UserDao implements IUserDao, Serializable {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    //Collection<User> userlist = new ArrayList<User>();

    
    @Override
    public void saveUser(User user) {
        Transaction transaction = null;
        Session session = sessionFactory.getCurrentSession();
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.flush();
            //session.close();
        }
    }

    @Override
    public User loadUser(int userId) {
        User user = null;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, userId);
            Hibernate.initialize(user);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return user;
    }
    @Override
    public User getUserByEmail(String email) {
        User user = null;
        Transaction transaction = null;
        Session session = sessionFactory.getCurrentSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT a FROM User a WHERE a.email = :email");
            query.setParameter("email", email);
            List<User> userlist = query.list();
            if (!userlist.isEmpty()) {
                user = userlist.get(0);
            }

            transaction.commit();

        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
        return user;
    }
}
