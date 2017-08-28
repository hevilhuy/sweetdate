/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.PasswordManager;
import beans.ProfileFacadeLocal;
import entities.Profile;
import java.io.ByteArrayInputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "editProfileManagedBean")
@SessionScoped
public class EditProfileManagedBean implements Serializable
{

    @EJB
    private ProfileFacadeLocal profileFacade;

    private Profile profile;
    private String oldPassword;
    private String newPassword;
    private String newPassword2;
    private Date birthdate;
    private UploadedFile avatarFile;
    private StreamedContent avatarImg;

    public EditProfileManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        try
        {
            profile = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentProfile");
            DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            birthdate = formatter.parse(profile.getBirthdate());
            ByteArrayInputStream byteArray = new ByteArrayInputStream(profile.getAvatar());
            avatarImg = new DefaultStreamedContent(byteArray, "image/png");
            avatarFile = new DefaultUploadedFile();
        }
        catch (ParseException ex)
        {
            Logger.getLogger(EditProfileManagedBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getOldPassword()
    {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword)
    {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }

    public String getNewPassword2()
    {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2)
    {
        this.newPassword2 = newPassword2;
    }

    public void changePassword()
    {
        if (oldPassword.equals(""))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fill full form", "Your new password can not be empty."));
        }
        else if (!PasswordManager.getMD5Hex(oldPassword).equals(profile.getPassword()))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong password", "Your old password does not correct."));
        }
        else if (!newPassword.equals(newPassword2))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password cofirm is wrong", "Password and re-enter password does not match."));
        }
        else
        {
            profile.setPassword(PasswordManager.getMD5Hex(newPassword));
            profileFacade.edit(profile);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "You password has been changed."));
        }
    }

    public void saveBasic()
    {
        int age = 0;
        try
        {
            age = (Calendar.getInstance().get(Calendar.YEAR)) - (birthdate.getYear() + 1900);
        }
        catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empty field", "Age must not blank."));
        }
        if (profile.getDisplayName().equals(""))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empty field", "You must have a display name."));
        }
        else if (profile.getCountry().equals(""))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empty field", "You must fill where are you come from."));
        }
        else if (age <= 18)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Too young", "You should wait a few years."));
        }
        else
        {
            String year = (birthdate.getYear() + 1900) + "";
            String month = (birthdate.getMonth() + 1) + "";
            String date = birthdate.getDate() + "";
            profile.setBirthdate(year + "-" + month + "-" + date);
            profileFacade.edit(profile);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Your profile is updated."));
        }
    }

    public void saveLookingFor()
    {
        if (profile.getLookingFor().equals(""))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cannot be empty", "Please fill in the looking for."));
        }
        else
        {
            profileFacade.edit(profile);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Your profile is updated."));
        }
    }

    public void saveAbout()
    {
        if (profile.getAbout().equals(""))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cannot be empty", "Please fill in the about me."));
        }
        else
        {
            profileFacade.edit(profile);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Your profile is updated."));
        }
    }

    public void uploadAvatar(FileUploadEvent event)
    {
        ByteArrayInputStream byteArray = new ByteArrayInputStream(event.getFile().getContents());
        avatarImg = new DefaultStreamedContent(byteArray, "image/png");
        profile.setAvatar(event.getFile().getContents());
        profileFacade.edit(profile);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Your profile is updated."));
    }

    public Date getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(Date birthdate)
    {
        this.birthdate = birthdate;
    }

    public UploadedFile getAvatarFile()
    {
        return avatarFile;
    }

    public void setAvatarFile(UploadedFile avatarFile)
    {
        this.avatarFile = avatarFile;
    }

    public StreamedContent getAvatarImg()
    {
        return avatarImg;
    }

    public void setAvatarImg(StreamedContent avatarImg)
    {
        this.avatarImg = avatarImg;
    }
}
