

package shop.control;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CartController implements Serializable{

    public CartController() {
    }
    public String addProduct(){
        return null;
    }
    public String viewCart(){
        return null;
    }
}
