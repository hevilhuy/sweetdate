/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AddressCompletor;
import beans.PasswordManager;
import beans.ProfileFacadeLocal;
import entities.Profile;
import entities.Role;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "registerManagedBean")
@RequestScoped
public class RegisterManagedBean implements Serializable
{

    @EJB
    private ProfileFacadeLocal profileFacade;
    private String rePassword;
    private Profile profile;
    private String payMethod;

    public RegisterManagedBean()
    {
        profile = new Profile();
    }

    public String getRePassword()
    {
        return rePassword;
    }

    public void setRePassword(String rePassword)
    {
        this.rePassword = rePassword;
    }

    public Profile getProfile()
    {
        return profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }

    public void register()
    {
        Profile profileInDb = profileFacade.find(profile.getUsername());
        if (!profile.getPassword().equals(rePassword))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "The password does not match!"));
        }
        else if (profileInDb != null)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "This username has already existed!"));
        }
        else if (profile.getDisplayName().equals(""))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Display name is empty!"));
        }
        else if(profile.getPassword().length()<4)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Password must have more than 4 characters!"));
        }
        else if(profile.getPassword().length()>12)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Password must have less than 12 characters!"));
        }
        else if(payMethod.equals(""))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Please specify the payment method"));
        }
        else
        {
            profile.setActive(Short.parseShort(0 + ""));
            profile.setRoleId(new Role(2));
            profile.setPassword(PasswordManager.getMD5Hex(profile.getPassword()));
            if(payMethod.equals("year"))
            {
                
            }
            profileFacade.create(profile);
            try
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect(AddressCompletor.complete("index.xhtml"));
            }
            catch (IOException ex)
            {
                Logger.getLogger(RegisterManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getPayMethod()
    {
        return payMethod;
    }

    public void setPayMethod(String payMethod)
    {
        this.payMethod = payMethod;
    }
}
