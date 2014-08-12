/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shop.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author coco
 */
public class MessageUtil {
    public static void displayError(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage errMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
        context.addMessage(null, errMsg);
    }
}
