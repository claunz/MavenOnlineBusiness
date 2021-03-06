/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shop.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import shop.control.UserController;
import shop.entity.Cart;
import shop.entity.Order;
import shop.entity.OrderLine;
import shop.utils.HibernateUtil;

/**
 *
 * @author coco
 */
@Named
@SessionScoped
public class CartDao implements ICartDao, Serializable{
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Cart cart;
    @Inject
    private CartDao cartDao;
    @Inject
    private UserController userController;
    private Collection<OrderLine> orderLines = new ArrayList<OrderLine>();

    public CartDao() {
    }

    public CartDao(Cart cart) {
        this.cart = cart;
    }
    
    @Override
    public void addOderLine(OrderLine orderLine) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(cart);
            orderLine.setCart(cart);
            session.merge(orderLine);
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
        orderLines.add(orderLine);
    }

    @Override
    public void removeOrderLine(int orderLineId) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            OrderLine orderLine = new OrderLine();
            orderLine.setId(orderLineId);
            session.delete(orderLine);
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
    public Collection<OrderLine> getCartOrderLines() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveCart(Cart cart) {
        System.out.println("---- Saving cart "+cart.getId());
        
        //cart = cart;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.persist(cart);
           // session.merge(cart);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            //e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
               
    }

    @Override
    public void updateCart(Cart cart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cart loadCart() {
        if(cart == null)
        {
            cart = new Cart();
        }
        return cart;
    }

    @Override
    public void deleteCart(Cart cart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public void setOrderLines(Collection<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
    
    public Collection<OrderLine> getOrderLines() { 
        if(!userController.getIsLoggedIn())
            return null;
        System.out.println("--------User Id "+userController.getUser().getId() );
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Query query =  session.createQuery("Select OL from OrderLine OL Join OL.cart C where C.user.id = :userId");
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
    
    public double getCartTotal(){
        double cartTotal = 0;
        for(OrderLine orderLine : orderLines)
        {
            cartTotal += orderLine.getPrice();
        }
        return cartTotal;
    }
    
    public void generateOrderFromCart(){
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Order order = new Order();
            Date today = new Date();
            order.setOrderDate(today);
            order.setTotalAmount(getCartTotal());
            order.setUser(userController.getUser());
            session.save(order);
            for(OrderLine orderLine : orderLines){
                orderLine.setCart(null); 
                orderLine.setOrder(order);
                session.saveOrUpdate(orderLine);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            //e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        
    }
}
