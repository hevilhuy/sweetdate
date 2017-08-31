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
import java.util.Calendar;
import java.util.Date;
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
        else if (profile.getUsername().contains(" "))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "This username must not have blank!"));
        }
        else if (profile.getEmail().equals(""))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Email is must not be empty!"));
        }
        else if (!isValidEmailAddress(profile.getEmail()))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "This email is not valid!"));
        }
        else if (profile.getDisplayName().equals(""))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Display name is empty!"));
        }
        else if (profile.getPassword().length() < 4)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Password must have more than 4 characters!"));
        }
        else if (profile.getPassword().length() > 12)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Password must have less than 12 characters!"));
        }
        else if (payMethod.equals(""))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Please specify the payment method"));
        }
        else
        {
            profile.setActive(Short.parseShort(0 + ""));
            profile.setRoleId(new Role(2));
            profile.setPassword(PasswordManager.getMD5Hex(profile.getPassword()));
            Date today = new Date();
            Calendar c = Calendar.getInstance();
            if (payMethod.equals("year"))
            {
                c.setTime(today);
                c.add(Calendar.YEAR, 1);
                today = c.getTime();
                String year = (today.getYear() + 1900) + "";
                String month = (today.getMonth() + 1) + "";
                String date = (today.getDate()) + "";
                profile.setDueDate(year + "-" + month + "-" + date);
            }
            if (payMethod.equals("month"))
            {
                c.setTime(today);
                c.add(Calendar.MONTH, 1);
                today = c.getTime();
                String year = (today.getYear() + 1900) + "";
                String month = (today.getMonth() + 1) + "";
                String date = (today.getDate()) + "";
                profile.setDueDate(year + "-" + month + "-" + date);
            }
            profileFacade.create(profile);
            try
            {
                //FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + AddressCompletor.complete("index"));
                FacesContext.getCurrentInstance().getExternalContext().redirect(AddressCompletor.complete("register_success.xhtml"));
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

    private boolean isValidEmailAddress(String email)
    {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
