/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import shop.entity.Product;
import shop.entity.User;
import shop.utils.HibernateUtil;

/**
 *
 * @author Benybifa
 */
public class UserDao implements IUserDao {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private User user;
    
    @Override
    public void saveUser(User user) {
        Transaction transaction = null;
        Session session = sessionFactory.getCurrentSession();
        try {
            transaction = session.beginTransaction();
            session.persist(user);
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
}
