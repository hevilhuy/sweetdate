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

@Named(value = "profileManagedBean")
@SessionScoped
public class ProfileManagedBean implements Serializable
{

    @EJB
    private ProfileFacadeLocal profileFacade;
    private Profile profile;
    private String username;
    private boolean isOwner;

    public ProfileManagedBean()
    {
    }

    public void init()
    {
        if (username == null || username.equals(""))
        {
            profile = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentProfile");
        }
        else
        {
            profile = profileFacade.find(username);
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

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public boolean isIsOwner()
    {
        Profile p = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentProfile");
        if (p != null)
        {
            if (username.equals(p.getUsername()))
            {
                isOwner=true;
            }
            else
            {
                isOwner=false;
            }
        }
        else
        {
            isOwner=false;
        }
        return isOwner;
    }

    public void setIsOwner(boolean isOwner)
    {
        this.isOwner = isOwner;
    }
}
