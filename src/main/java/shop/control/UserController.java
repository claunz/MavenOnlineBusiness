package shop.control;

import java.io.Serializable;
import java.util.Map;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import shop.dao.UserDao;
import shop.entity.User;
import shop.rule.MessageProvider;
import shop.rule.MessagesUtil;
import shop.rule.RulesException;
import shop.utils.MessageUtil;

@Named
@SessionScoped
public class UserController implements Serializable {

    private static final String browser_products = "browseProducts";
    private static final String login = "login";
    //private static final String register = "register";
    private static final String register_user = "register";
    private boolean isLoggedIn;
    private FacesContext facesContext;
    
    @Inject
    private UserDao userDao;
    private User user = new User();
    private String requestedUrl = null;
    MessageProvider mp = new MessageProvider();
    private String selectedPlanet;
    private String userDetails;

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
        } else {
            MessageUtil.displayError("Email and Password missmatch");
        }

        return login;
    }

    public boolean isUserManager() {
        if (isLoggedIn) {
            String userType = user.getUserType();
            return userType != null && userType.equalsIgnoreCase("manager");
        }
        return false;
    }

    public void doLogout() {
        isLoggedIn = false;
        setUser(new User());
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

        if (!((user.getCountry().equalsIgnoreCase("USA")) || (user.getCountry().equalsIgnoreCase("America")) || (user.getCountry().equalsIgnoreCase("Canada")))) {

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

    public String getSelectedPlanet() {
        return selectedPlanet;
    }

    public String changePlanet(String newValue) {
        selectedPlanet = newValue;
        return selectedPlanet;
    }

    public String getShowUserDetails() {
        facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        String page = params.get("page");
        
        if ("".equals(page) || page == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(user.getStreet()).append(user.getCity()).append(user.getZip()).append(user.getCountry());
            return "Address : " + sb.toString();
        }
    }
}
