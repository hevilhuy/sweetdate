/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.ProfileFacadeLocal;
import entities.Profile;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "profileManagedBean")
@SessionScoped
public class ProfileManagedBean implements Serializable
{

    @EJB
    private ProfileFacadeLocal profileFacade;
    private Profile profile;
    public ProfileManagedBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
        String username=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username").toString();
        profile=username.equals("")?null:profileFacade.find(username);
        if(profile==null)
        {
            profile=new Profile();
        }
    }

    public Profile getProfile()
    {
        return profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }
}
