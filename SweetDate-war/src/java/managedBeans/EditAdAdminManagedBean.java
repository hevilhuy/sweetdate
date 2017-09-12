/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AdvertisementFacadeLocal;
import beans.URLValidator;
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
        boolean canChange = true;
        if(ad.getDescription().equals(""))
        {
            canChange=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Please input some description."));
        }
        if(ad.getImage()==null)
        {
            canChange=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Please upload an image!"));
        }
        if(ad.getLink().equals(""))
        {
            canChange=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Please input a link!"));
        }
        if(dueDate==null)
        {
            canChange=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Please chose an expire date!"));
        }
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        if (dueDate.before(today))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Warning", "This ad is expired!"));
        }
        try
        {
            ad.setPrice(Integer.parseInt(price));
        }
        catch (NumberFormatException ex)
        {
            canChange = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Please input a valid price."));
        }
        if (ad.getPrice() <= 0)
        {
            canChange = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Price cannot less than 0."));
        }
        if (ad.getMinAge() > ad.getMaxAge())
        {
            canChange = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Min age connot greater than max age."));
        }
        if(!URLValidator.validate(ad.getLink()))
        {
            canChange = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "URL is not valid, must begin with http or https"));
        }
        String year = (dueDate.getYear() + 1900) + "";
        String month = (dueDate.getMonth()) + "";
        String date = dueDate.getDate() + "";
        ad.setDueDate(year + "-" + month + "-" + date);
        System.out.println("DATE "+ad.getDueDate());
        if (canChange)
        {
            if (adId == null || adId.equals("new") || adId.equals(""))
            {
                advertisementFacade.create(ad);
            }
            else
            {
                advertisementFacade.edit(ad);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "This advertise has been updated."));
        }
    }

    public void init()
    {
        Calendar calendar = Calendar.getInstance();
        dueDate = calendar.getTime();
        ageRange = new ArrayList<>();
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
                else
                {
                    price=ad.getPrice()+"";
                }
            }
            catch (Exception ex)
            {
                ad = new Advertisement();
            }
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try
        {
            if (ad.getDueDate() != null)
            {
                String[] r=ad.getDueDate().split("-");
                r[1]=(Integer.parseInt(r[1])-1)+"";
                System.out.println("R1 "+r[1]);
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
