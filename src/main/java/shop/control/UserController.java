
package shop.control;

import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import shop.dao.IUserDao;
import shop.dao.UserDao;

import shop.entity.User;

@Named
@SessionScoped
public class UserController implements Serializable {
    private static final String browser_products = "browseProducts";
    private static final String login = "login";
    //private static final String register = "register";
    private static final String register_user = "register";
    private boolean isLoggedIn;
    private IUserDao userDao;
    private User user;
    private String requestedUrl=null;

    public UserController() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public void setUer(User user) {
        this.user = user;
    }

    public String register() {
        user = new User();

        return register_user;
    }

    public String doRegister() {

        userDao = new UserDao();

        if (!user.getPassword().equalsIgnoreCase(user.getConfPassword())) {
            return register_user;
        }
        
        userDao.saveUser(user);

        return login;
    }

    public String login() {
        user = new User();

        return login;
    }

    public String dologin() {
           //user = new User();

        userDao = new UserDao();
        
        if ((user.getPassword()).equals(userDao.getUserByEmail(user.getEmail()).getPassword())) {
             isLoggedIn = true;
             return browser_products;
        }
        return login;
    }
    public boolean isUserManager(){
        return false;
    }
}