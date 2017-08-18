/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.PasswordManager;
import beans.ProfileFacadeLocal;
import entities.Profile;
import entities.Role;
import javax.inject.Named;
import java.io.Serializable;
import java.security.MessageDigest;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

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
    private String errors;

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

    public String register()
    {
        Profile profileInDb = profileFacade.find(profile.getUsername());
        errors="";
        if (!profile.getPassword().equals(rePassword))
        {
            errors += "The password does not match!";
        }
        else if (profileInDb != null)
        {
            errors += "This username has already existed!";
        }
        else if (profile.getDisplayName().equals(""))
        {
            errors += "Please fill in the display name!";
        }
        else if(profile.getPassword().length()<4)
        {
            errors+="Password must more than 4 characters!";
        }
        else if(profile.getPassword().length()>12)
        {
            errors+="Password must less than 12 characters";
        }
        else
        {
            profile.setActive(Short.parseShort(0 + ""));
            profile.setRoleId(new Role(2));
            profile.setPassword(PasswordManager.getMD5Hex(profile.getPassword()));
            profileFacade.create(profile);
        }
        return errors.equals("")?"profile":"register";
    }

    public String getErrors()
    {
        return errors;
    }

    public void setErrors(String errors)
    {
        this.errors = errors;
    }
}
