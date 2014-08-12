
package shop.control;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UserController implements Serializable{

    public UserController() {
    }
    public String register(){
        return null;
    }
    public String doRegister(){
        return null;
    }
    public String browseCustomers(){
        return null;
    }
}
