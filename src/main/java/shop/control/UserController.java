
package shop.control;

import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import shop.dao.IUserDao;
import shop.dao.UserDao;

import shop.entity.User;
import shop.utils.MessageUtil;

@Named
@SessionScoped
public class UserController implements Serializable {
    private static final String browser_products = "browseProducts";
    private static final String login = "login";
    //private static final String register = "register";
    private static final String register_user = "register";
    private boolean isLoggedIn;
    @Inject
    private UserDao userDao;
    private User user = new User();
    private String requestedUrl=null;

    public UserController() {
       // user = new User();
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

    public void setUser(User user) {
        this.user = user;
    }

    public String register() {
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
        return login;
    }

    public String dologin() {
        User registeredUser = userDao.getUserByEmail(user.getEmail());
        if (registeredUser != null && user.getPassword().equals(registeredUser.getPassword())) {
             user = registeredUser;
             isLoggedIn = true;
             return browser_products;
        }else
            MessageUtil.displayError("Email and Password missmatch");
        
        return login;
    }
    public boolean isUserManager(){
        if(isLoggedIn){
            String userType = user.getUserType();
            return userType != null && userType.equalsIgnoreCase("manager");
        }
        return false;
    }
    
    public void doLogout(){
        isLoggedIn = false;
        setUser(new User());
    }
}