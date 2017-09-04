/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AddressCompletor;
import beans.FollowListFacadeLocal;
import com.sun.faces.facelets.tag.IterationStatus;
import entities.FollowList;
import entities.Profile;
import java.io.ByteArrayInputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "followManagedBean")
@SessionScoped
public class FollowManagedBean implements Serializable
{

    @EJB
    private FollowListFacadeLocal followListFacade;
    private Profile profile;
    private ArrayList<FollowList> followingList;
    private ArrayList<FollowList> followerList;
    
    public FollowManagedBean()
    {
    }
    
    
    public void init()
    {
        profile = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentProfile");
        followingList=new ArrayList<>();
        followerList=new ArrayList<>();
        if(profile!=null)
        {
            ArrayList<FollowList> tempList=new ArrayList<>(followListFacade.findAll());
            for(FollowList follow:tempList)
            {
                if(follow.getUsername().getUsername().equals(profile.getUsername()))
                {
                    followerList.add(follow);
                }
                if(follow.getFollowerId().getUsername().equals(profile.getUsername()))
                {
                    followingList.add(follow);
                }
            }
        }
        Collections.reverse(followerList);
        Collections.reverse(followingList);
    }
    
    public StreamedContent getAvatar(Profile p)
    {
        StreamedContent avatarImg=new DefaultStreamedContent();
        if (p.getAvatar() != null)
        {
            ByteArrayInputStream byteArray = new ByteArrayInputStream(p.getAvatar());
            avatarImg = new DefaultStreamedContent(byteArray, "image/png");
        }
        return avatarImg;
    }
    
    public String viewProfile(Profile p)
    {
        return AddressCompletor.complete("profile.xhtml")+"&username="+p.getUsername();
    }

    public Profile getProfile()
    {
        return profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }

    public ArrayList<FollowList> getFollowingList()
    {
        return followingList;
    }

    public void setFollowingList(ArrayList<FollowList> followingList)
    {
        this.followingList = followingList;
    }

    public ArrayList<FollowList> getFollowerList()
    {
        return followerList;
    }

    public void setFollowerList(ArrayList<FollowList> followerList)
    {
        this.followerList = followerList;
    }
}
