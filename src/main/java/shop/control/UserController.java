package shop.control;

import java.io.Serializable;
import java.util.Map;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import shop.dao.IUserDao;
import shop.dao.UserDao;
import shop.entity.User;
import shop.rule.MessageProvider;
import shop.rule.MessagesUtil;
import shop.rule.RulesException;

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
    private String requestedUrl = null;
    MessageProvider mp = new MessageProvider();
      private String selectedPlanet;

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

    public boolean isUserManager() {
        return false;
    }

    public void verifyEmail() throws RulesException {
        //String pattern = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+.(com|net|org)";
        String pattern = "[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
        Pattern r = Pattern.compile(pattern);
        String str = user.getEmail();
        if (!str.matches(pattern)) {
            throw new RulesException(mp.getValue("errorEmail"));
        }
    }

    public void verifyCountry() throws RulesException {

        if (!((user.getCountry().equalsIgnoreCase("USA") )||(user.getCountry().equalsIgnoreCase("America") )||(user.getCountry().equalsIgnoreCase("Canada")))) {

            throw new RulesException(mp.getValue("countExp"));
        }

    }
    public void verifyLogin() throws RulesException {

      if (!user.getPassword().equalsIgnoreCase(user.getConfPassword())) {

            throw new RulesException(mp.getValue("passmis"));
        }

    }
    public String doRegisterWvalid() {

        String url = null;

        try {
            verifyCountry();
            verifyEmail();
           verifyLogin();
           userDao = new UserDao();
            userDao.saveUser(user);
            url = login;
        } catch (RulesException e) {
            // TODO Auto-generated catch block
            MessagesUtil.displayError(e.getMessage());
            url = register_user;

        }

        return url;

    }

    public void setSelectedPlanet(String selectedPlanet) {
        this.selectedPlanet = selectedPlanet;
    }

  
  public String getSelectedPlanet() { return selectedPlanet; }
  
  public String changePlanet(String newValue) {
     selectedPlanet = newValue;
     return selectedPlanet;
  }
}
