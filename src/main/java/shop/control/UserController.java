
package shop.control;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import shop.dao.AddressDao;
import shop.dao.BillingAddressDao;
import shop.dao.IAddressDao;
import shop.dao.IUserDao;
import shop.dao.ShippingAddressDao;
import shop.dao.UserDao;
import shop.entity.BillingAddress;
import shop.entity.ShippingAddress;
import shop.entity.User;

@Named
@RequestScoped
public class UserController implements Serializable{
    private static final String login = "login";
    private static final String register = "register";
private IUserDao userDao;
private User user;
private IAddressDao billinAddressDao;
private IAddressDao shippinAddressDao;
private BillingAddress billingAddress;
private ShippingAddress shippingAddress;
    public UserController() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    
    public String register(){
        return null;
    }
    public String doRegister(){
     
        userDao = new UserDao();
        
        billinAddressDao = new BillingAddressDao();
        shippinAddressDao = new ShippingAddressDao();
        if(!user.password.equalsIgnoreCase(user.confPassword)){
       return register;
        }
         userDao.saveUser(user);
        billinAddressDao.saveAddress(billingAddress);
        
        shippinAddressDao.saveAddress(shippingAddress);
        return login;
    }
      
}
