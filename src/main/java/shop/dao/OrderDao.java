/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shop.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import shop.control.UserController;
import shop.entity.Order;
import shop.entity.OrderLine;
import shop.utils.HibernateUtil;

@Named
@SessionScoped
public class OrderDao implements IOrderDao, Serializable{
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Inject
    private UserController userController;
    private Collection<OrderLine> orderLines = new ArrayList<OrderLine>();
    private Collection<Order> userOrders = new ArrayList<Order>();

    public OrderDao() {
    }
    
    @Override
    public Collection<OrderLine> getOrderItems() {
        
        if(!userController.getIsLoggedIn())
            return null;
        
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Query query =  session.createQuery("Select OL from OrderLine OL Join OL.order O where O.user.id = :userId");
            query.setParameter("userId", userController.getUser().getId() );
            orderLines = (List<OrderLine>) query.list(); 
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
        return orderLines;
    }

    @Override
    public Collection<Order> getUserOrders() {
        if(!userController.getIsLoggedIn())
            return null;
        System.out.println("----------inside get user orders");
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Query query =  session.createQuery("Select O from Order O Join where O.user.id = :userId");
            query.setParameter("userId", userController.getUser().getId() );
            userOrders = (List<Order>) query.list(); 
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
        return userOrders;
        
    }
    
}
