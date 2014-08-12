
package shop.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import shop.entity.Product;
import shop.utils.HibernateUtil;


public class ProductDao implements IProductDao {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Collection<Product> productlist = new ArrayList<Product>();

    public void saveProduct(Product product) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.merge(product);
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

    @Override
    public void updateProduct(Product product) {
        Product productExist = loadProduct(product.getId());
        if(productExist != null){
            productlist.remove(productExist); //remove old product
            productlist.add(product); //add the new 
        }
    }

    @Override
    public Product loadProduct(int productId) {
        for(Product product : productlist){
            if(product.getId() == productId)
                return product;
        }
        return null;
    }

    @Override
    public Collection<Product> getProducts(){ 
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            productlist = (List<Product>) session.createQuery("from Product p").list();
            
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
        return productlist;
    }
}
