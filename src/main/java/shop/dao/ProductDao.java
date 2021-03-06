package shop.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import shop.entity.Product;
import shop.utils.HibernateUtil;

@Named
@SessionScoped
public class ProductDao implements IProductDao, Serializable {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Collection<Product> productlist = new ArrayList<Product>();

    public void saveProduct(Product product) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.merge(product);
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
    public void updateProduct(Product product) {
        Product productExist = loadProduct(product.getId());
        if (productExist != null) {
            productlist.remove(productExist); //remove old product
            productlist.add(product); //add the new 
        }
    }

    @Override
    public Product loadProduct(int productId) {
        if (productId > 0) {
            if (productlist != null && productlist.size() > 0 ) {
                for (Product product : productlist) {
                    if (product.getId() == productId) {
                        return product;
                    }
                }
            } else {
                 System.out.println("-------IN getProduct by id null");
                 return getProductById(productId);
            }
        }
                 
        return null;
    }

    public Product getProductById(int productId) {
        Product product = null;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            System.out.println("-------IN getProduct by id");
            product = (Product) session.get(Product.class, productId);
            Hibernate.initialize(product);
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
        return product;
    }

    @Override
    public Collection<Product> getProducts() {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            productlist = (List<Product>) session.createQuery("from Product p").list();

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
        return productlist;
    }

    @Override
    public void deleteProduct(Product product) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(product);
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
