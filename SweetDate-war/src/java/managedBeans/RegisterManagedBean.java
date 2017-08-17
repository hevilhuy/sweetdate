/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Profile;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "registerManagedBean")
@RequestScoped
public class RegisterManagedBean implements Serializable
{
    private String rePassword;
    private Profile profile;
    private String errors;  
    public RegisterManagedBean()
    {
        profile=new Profile();
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
        return "index";
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
