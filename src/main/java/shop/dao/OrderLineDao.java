/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.dao;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import shop.entity.Cart;
import shop.entity.OrderLine;
import shop.utils.HibernateUtil;


@Named
@SessionScoped
public class OrderLineDao implements IOrderLineDao, Serializable {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Cart OrderLine;

    public OrderLineDao() {
    }

    
    @Override
    public void saveOrderLine(OrderLine orderLine) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.persist(orderLine);
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
}
