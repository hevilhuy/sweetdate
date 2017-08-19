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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable
{

    @EJB
    private ProfileFacadeLocal profileFacade;
    private Profile profile;
    private boolean isLogin;
    private boolean isAdmin;
    private String errors;
    public LoginManagedBean()
    {
        profile=new Profile();
        isLogin=false;
        isAdmin=false;
    }

    public Profile getProfile()
    {
        return profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }
    
    public String login()
    {
        errors="";
        isLogin=false;
        Profile profileInDb=profileFacade.find(profile.getUsername());
        if(profileInDb!=null)
        {
            if(profileInDb.getPassword().equals(PasswordManager.getMD5Hex(profile.getPassword())))
            {
                isLogin=true;
                profile=profileInDb;
                isAdmin=profile.getRoleId().getRoleId()==1;
                return AddressCompletor.complete("index");
            }
        }
        else
        {
            isLogin=false;
            errors="Wrong username or password";
        }
        return "login";
    }

    public String getErrors()
    {
        return errors;
    }

    public void setErrors(String errors)
    {
        this.errors = errors;
    }

    public boolean isIsLogin()
    {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin)
    {
        this.isLogin = isLogin;
    }

    public boolean isIsAdmin()
    {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }
}
