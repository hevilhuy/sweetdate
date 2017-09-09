/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AdvertisementFacadeLocal;
import entities.Advertisement;
import java.io.ByteArrayInputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "editAdAdminManagedBean")
@SessionScoped
public class EditAdAdminManagedBean implements Serializable
{

    @EJB
    private AdvertisementFacadeLocal advertisementFacade;
    private String adId;
    private Advertisement ad;
    private Date dueDate;
    private String price;
    private ArrayList<Integer> ageRange;

    public Date getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }

    public EditAdAdminManagedBean()
    {
    }

    public void saveChanges()
    {
        boolean canChange=true;
        Calendar calendar = Calendar.getInstance();
        Date today=calendar.getTime();
        if (dueDate.before(today))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Warning", "This ad is expired!"));
        }
        try
        {
            ad.setPrice(Integer.parseInt(price));
        }
        catch(NumberFormatException ex)
        {
            canChange=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Please input a valid price."));
        }
        if(ad.getPrice()<0)
        {
            canChange=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Price cannot less than 0."));
        }
        if(ad.getMinAge()>ad.getMaxAge())
        {
            canChange=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Min age connot greater than max age."));
        }
        if(canChange)
        {
            if (adId == null || adId.equals("new") || adId.equals(""))
            {
                advertisementFacade.create(ad);
            }
            else
            {
                String year=(dueDate.getYear() + 1900) + "";
                String month=dueDate.getMonth()+"";
                String date=dueDate.getDate()+"";
                ad.setDueDate(year + "-" + month + "-" + date);
                advertisementFacade.edit(ad);
            }
        }
    }

    public void init()
    {
        Calendar calendar = Calendar.getInstance();
        dueDate = calendar.getTime();
        ageRange = new ArrayList<>();
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try
        {
            if (ad.getDueDate() != null)
            {
                dueDate = formatter.parse(ad.getDueDate());
            }
        }
        catch (ParseException ex)
        {
            System.err.println(ex);
        }
        for (int i = 18; i < 100; i++)
        {
            ageRange.add(i);
        }
        if (adId == null || adId.equals("new") || adId.equals(""))
        {
            ad = new Advertisement();
        }
        else
        {
            try
            {
                ad = advertisementFacade.find(Integer.parseInt(adId));
                if (ad == null)
                {
                    ad = new Advertisement();
                }
            }
            catch (Exception ex)
            {
                ad = new Advertisement();
            }
        }
    }

    public void uploadImage(FileUploadEvent event)
    {
        ad.setImage(event.getFile().getContents());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Image upload completed."));
    }

    public String getAdId()
    {
        return adId;
    }

    public void setAdId(String adId)
    {
        this.adId = adId;
    }

    public Advertisement getAd()
    {
        return ad;
    }

    public void setAd(Advertisement ad)
    {
        this.ad = ad;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public ArrayList<Integer> getAgeRange()
    {
        return ageRange;
    }

    public void setAgeRange(ArrayList<Integer> ageRange)
    {
        this.ageRange = ageRange;
    }
}
