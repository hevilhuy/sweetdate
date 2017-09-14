/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AddressCompletor;
import beans.ProfileFacadeLocal;
import entities.Profile;
import java.io.ByteArrayInputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "indexManagedBean")
@SessionScoped
public class IndexManagedBean implements Serializable
{

    @EJB
    private ProfileFacadeLocal profileFacade;
    private int gender;
    private ArrayList<Profile> profileList;
    private int currentPosition;
    private Profile viewingProfile;

    public IndexManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        ArrayList<Profile> tempList = new ArrayList<>(profileFacade.findAll());
        profileList = new ArrayList<>();
        Profile loggedProfile = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentProfile");
        for (Profile p : tempList)
        {
            if (p.getActive() == Integer.parseInt("1")&&p.getRoleId().getRoleId()!=1)
            {
                profileList.add(p);
            }
        }
        Collections.shuffle(profileList);
        currentPosition = 0;
        viewingProfile = profileList.get(currentPosition);
    }

    public void nextProfile()
    {
        viewingProfile = profileList.get(++currentPosition);
    }

    public void previousProfile()
    {
        viewingProfile = profileList.get(--currentPosition);
    }

    public String getDisableNext()
    {
        return currentPosition == profileList.size() - 1 ? "true" : "false";
    }

    public String getDisablePrevious()
    {
        return currentPosition == 0 ? "true" : "false";
    }

    public StreamedContent getAvatar()
    {
        StreamedContent avatarImg = new DefaultStreamedContent();
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
        {
            return new DefaultStreamedContent();
        }
        else
        {
            String id = context.getExternalContext().getRequestParameterMap().get("profileId");
            Profile p = profileFacade.find(id);
            if (p.getAvatar() != null)
            {
                ByteArrayInputStream byteArray = new ByteArrayInputStream(p.getAvatar());
                avatarImg = new DefaultStreamedContent(byteArray, "image/png");
            }
        }
        return avatarImg;
    }

    public String viewProfile(Profile p)
    {
        return AddressCompletor.complete("profile.xhtml") + "&username=" + p.getUsername();
    }

    public int getGender()
    {
        return gender;
    }

    public void setGender(int gender)
    {
        this.gender = gender;
    }

    public ArrayList<Profile> getProfileList()
    {
        return profileList;
    }

    public void setProfileList(ArrayList<Profile> profileList)
    {
        this.profileList = profileList;
    }

    public int getCurrentPosition()
    {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition)
    {
        this.currentPosition = currentPosition;
    }

    public Profile getViewingProfile()
    {
        return viewingProfile;
    }

    public void setViewingProfile(Profile viewingProfile)
    {
        this.viewingProfile = viewingProfile;
    }
}
