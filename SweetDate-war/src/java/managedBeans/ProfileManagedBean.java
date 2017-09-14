/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.FollowListFacadeLocal;
import beans.ProfileFacadeLocal;
import entities.FollowList;
import entities.Profile;
import java.io.ByteArrayInputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named(value = "profileManagedBean")
@SessionScoped
public class ProfileManagedBean implements Serializable
{

    @EJB
    private FollowListFacadeLocal followListFacade;

    @EJB
    private ProfileFacadeLocal profileFacade;
    private Profile profile;
    private String username;
    private boolean isOwner;
    private boolean canSeeContact;
    private boolean isSentRequest;

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
            isOwner = username.equals(p.getUsername());
        }
        else
        {
            isOwner = false;
        }
        return isOwner;
    }

    public void setIsOwner(boolean isOwner)
    {
        this.isOwner = isOwner;
    }

    public boolean isCanSeeContact()
    {
        followCheck();
        return canSeeContact;
    }

    public void setCanSeeContact(boolean canSeeContact)
    {
        this.canSeeContact = canSeeContact;
    }

    public boolean isIsSentRequest()
    {
        followCheck();
        return isSentRequest;
    }

    public void setIsSentRequest(boolean isSentRequest)
    {
        this.isSentRequest = isSentRequest;
    }

    private void followCheck()
    {
        Profile loggedProfile = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentProfile");
        if (loggedProfile == null)
        {
            canSeeContact = false;
            isSentRequest = false;
        }
        else
        {
            isSentRequest = false;
            canSeeContact = false;
            ArrayList<FollowList> tempList = new ArrayList<>(followListFacade.findAll());
            //is sent request
            ArrayList<FollowList> requestList = new ArrayList<>();
            for (FollowList followList : tempList)
            {
                if (followList.getFollowerId().getUsername().equals(loggedProfile.getUsername()))
                {
                    requestList.add(followList);
                }
            }
            for (FollowList followList : requestList)
            {
                if (followList.getUsername().getUsername().equals(profile.getUsername()))
                {
                    isSentRequest = true;
                }
            }
            //can see contact
            ArrayList<FollowList> responseList = new ArrayList<>();
            if (isSentRequest)
            {
                for (FollowList followList : tempList)
                {
                    if (followList.getUsername().getUsername().equals(loggedProfile.getUsername()))
                    {
                        responseList.add(followList);
                    }
                }
                for (FollowList followList : responseList)
                {
                    if (followList.getFollowerId().getUsername().equals(profile.getUsername()))
                    {
                        canSeeContact = true;
                    }
                }
            }
        }
    }

    public void unfollow()
    {
        Profile loggedProfile = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentProfile");
        ArrayList<FollowList> followList = new ArrayList<>(followListFacade.findAll());
        for(FollowList follow:followList)
        {
            if(follow.getUsername().getUsername().equals(profile.getUsername())
                    &&follow.getFollowerId().getUsername().equals(loggedProfile.getUsername()))
            {
                followListFacade.remove(follow);
            }
        }
    }

    public void follow()
    {
        Profile loggedProfile = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentProfile");
        FollowList followList = new FollowList();
        followList.setUsername(profile);
        followList.setFollowerId(loggedProfile);
        followListFacade.create(followList);
    }
}
