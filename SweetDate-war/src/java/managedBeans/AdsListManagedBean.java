/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AddressCompletor;
import beans.AdvertisementFacadeLocal;
import entities.Advertisement;
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
@Named(value = "adsListManagedBean")
@SessionScoped
public class AdsListManagedBean implements Serializable
{

    @EJB
    private AdvertisementFacadeLocal advertisementFacade;
    private ArrayList<Advertisement> adsList;
    private ArrayList<Advertisement> selected;

    public AdsListManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        adsList = new ArrayList<>(advertisementFacade.findAll());
        selected=new ArrayList<>();
    }

    public void onSelect(String indexes)
    {
        System.out.println("INDEX ONSELECT " + indexes);
        try
        {
            Advertisement ad = adsList.get(Integer.parseInt(indexes));
            selected.add(ad);
        }
        catch (Exception ex)
        {

        }
    }

    public String displayTransfer(short display)
    {
        return display == Short.parseShort("0") ? "No" : "Yes";
    }

    public void onDeselect(String indexes)
    {
        System.out.println("INDEX DESELECT " + indexes);
        try
        {
            Advertisement ad = adsList.get(Integer.parseInt(indexes));
            selected.remove(ad);
        }
        catch (Exception ex)
        {

        }
    }
    
    public void delete()
    {
        for(Advertisement ad:selected)
        {
            advertisementFacade.remove(ad);
        }
        init();
    }

    public String edit(String id)
    {
        return AddressCompletor.complete("edit_ads_admin.xhtml?") + "&adId=" + id;
    }

    public ArrayList<Advertisement> getAdsList()
    {
        return adsList;
    }

    public void setAdsList(ArrayList<Advertisement> adsList)
    {
        this.adsList = adsList;
    }

    public ArrayList<Advertisement> getSelected()
    {
        return selected;
    }

    public void setSelected(ArrayList<Advertisement> selected)
    {
        this.selected = selected;
    }
}
