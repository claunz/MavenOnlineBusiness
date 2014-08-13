/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import shop.utils.HibernateUtil;

/**
 *
 * @author Benybifa
 */
public class BillingAddressDao extends AddressDao{
  private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
   // AddressDeo address = new AddressDeo();
   // @Override
 public void saveAddress(AddressDao address){
        Transaction transaction = null;
        Session session = sessionFactory.getCurrentSession();
        try{
            transaction = session.beginTransaction();
            session.persist(address);
            transaction.commit();
        }catch(RuntimeException e){
            if(transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }
    }
}

