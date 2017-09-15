/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AdvertisementFacadeLocal;
import entities.Advertisement;
import entities.Profile;
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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "leftMenuManagedBean")
@RequestScoped
public class LeftMenuManagedBean implements Serializable
{

    @EJB
    private AdvertisementFacadeLocal advertisementFacade;
    private Advertisement ad1;
    private Advertisement ad2;
    private Advertisement ad3;
    private StreamedContent ad1Image;
    private StreamedContent ad2Image;
    private StreamedContent ad3Image;

    public LeftMenuManagedBean()
    {
    }

    public String goToAd(String str)
    {
        System.out.println("RUNNING " + str);
        return str;
    }

    @PostConstruct
    public void init()
    {
        Profile loggedProfile = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentProfile");
        Calendar c = Calendar.getInstance();
        Date today = c.getTime();
        ArrayList<Advertisement> adsList = new ArrayList<>(advertisementFacade.findAll());
        ArrayList<Advertisement> adsPos1 = new ArrayList<>();
        ArrayList<Advertisement> adsPos2 = new ArrayList<>();
        ArrayList<Advertisement> adsPos3 = new ArrayList<>();
        for (Advertisement ad : adsList)
        {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date adDate = new Date();
            if (ad.getDueDate() != null)
            {
                String[] r = ad.getDueDate().split("-");
                r[1] = (Integer.parseInt(r[1])) + "";
                String trueDate = r[0] + "-" + r[1] + "-" + r[2];
                try
                {
                    adDate = formatter.parse(trueDate);
                }
                catch (ParseException ex)
                {
                    Logger.getLogger(LeftMenuManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (adDate.before(today))
            {
                ad.setDisplay(Short.parseShort("0"));
                advertisementFacade.edit(ad);
            }

            if (ad.getDisplay() == Short.parseShort(1 + ""))
            {
                try
                {
                    switch (ad.getPosition())
                    {
                        case 1:
                            if (loggedProfile!=null&&loggedProfile.getActive()==1)
                            {
                                String[] r = loggedProfile.getBirthdate().split("-");
                                int currentYear = c.get(Calendar.YEAR);
                                int age = currentYear - Integer.parseInt(r[0]);
                                if (age >= ad.getMinAge() && age <= ad.getMaxAge())
                                {
                                    adsPos1.add(ad);
                                }
                            }
                            else
                            {
                                adsPos1.add(ad);
                            }
                            break;
                        case 2:
                            if (loggedProfile!=null&&loggedProfile.getActive()==1)
                            {
                                String[] r = loggedProfile.getBirthdate().split("-");
                                int currentYear = c.get(Calendar.YEAR);
                                int age = currentYear - Integer.parseInt(r[0]);
                                if (age >= ad.getMinAge() && age <= ad.getMaxAge())
                                {
                                    adsPos2.add(ad);
                                }
                            }
                            else
                            {
                                adsPos2.add(ad);
                            }
                            break;
                        case 3:
                            if (loggedProfile!=null&&loggedProfile.getActive()==1)
                            {
                                String[] r = loggedProfile.getBirthdate().split("-");
                                int currentYear = c.get(Calendar.YEAR);
                                int age = currentYear - Integer.parseInt(r[0]);
                                if (age >= ad.getMinAge() && age <= ad.getMaxAge())
                                {
                                    adsPos3.add(ad);
                                }
                            }
                            else
                            {
                                adsPos3.add(ad);
                            }
                            break;
                        default:
                            break;
                    }
                }
                catch (Exception ex)
                {

                }
            }
        }
        Random random = new Random();
        ad1 = adsPos1.get(random.nextInt(adsPos1.size()));
        ad2 = adsPos2.get(random.nextInt(adsPos2.size()));
        ad3 = adsPos3.get(random.nextInt(adsPos3.size()));

        ad1Image = new DefaultStreamedContent();
        ad2Image = new DefaultStreamedContent();
        ad3Image = new DefaultStreamedContent();

        if (ad1.getImage() != null)
        {
            ByteArrayInputStream byteArray = new ByteArrayInputStream(ad1.getImage());
            ad1Image = new DefaultStreamedContent(byteArray, "image/png");
        }
        if (ad2.getImage() != null)
        {
            ByteArrayInputStream byteArray = new ByteArrayInputStream(ad2.getImage());
            ad2Image = new DefaultStreamedContent(byteArray, "image/png");
        }
        if (ad3.getImage() != null)
        {
            ByteArrayInputStream byteArray = new ByteArrayInputStream(ad3.getImage());
            ad3Image = new DefaultStreamedContent(byteArray, "image/png");
        }
    }

    public Advertisement getAd1()
    {
        return ad1;
    }

    public void setAd1(Advertisement ad1)
    {
        this.ad1 = ad1;
    }

    public Advertisement getAd2()
    {
        return ad2;
    }

    public void setAd2(Advertisement ad2)
    {
        this.ad2 = ad2;
    }

    public Advertisement getAd3()
    {
        return ad3;
    }

    public void setAd3(Advertisement ad3)
    {
        this.ad3 = ad3;
    }

    public StreamedContent getAd1Image()
    {
        return ad1Image;
    }

    public void setAd1Image(StreamedContent ad1Image)
    {
        this.ad1Image = ad1Image;
    }

    public StreamedContent getAd2Image()
    {
        return ad2Image;
    }

    public void setAd2Image(StreamedContent ad2Image)
    {
        this.ad2Image = ad2Image;
    }

    public StreamedContent getAd3Image()
    {
        return ad3Image;
    }

    public void setAd3Image(StreamedContent ad3Image)
    {
        this.ad3Image = ad3Image;
    }
}
