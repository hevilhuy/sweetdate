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
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "profileListAdminManagedBean")
@SessionScoped
public class ProfileListAdminManagedBean implements Serializable
{

    @EJB
    private ProfileFacadeLocal profileFacade;
    private ArrayList<Profile> profileList;
    private ArrayList<Profile> selected;
    
    public ProfileListAdminManagedBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
        profileList=new ArrayList<>(profileFacade.findAll());
        selected=new ArrayList<>();
    }
    
    public void onSelect(String indexes)
    {
        
    }
    
    public void onDeselect(String indexes)
    {
        
    }

    public ArrayList<Profile> getProfileList()
    {
        return profileList;
    }

    public void setProfileList(ArrayList<Profile> profileList)
    {
        this.profileList = profileList;
    }

    public ArrayList<Profile> getSelected()
    {
        return selected;
    }

    public void setSelected(ArrayList<Profile> selected)
    {
        this.selected = selected;
    }
    
}
