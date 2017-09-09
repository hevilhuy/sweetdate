/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AdvertisementFacadeLocal;
import entities.Advertisement;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    public void init()
    {
        adsList=new ArrayList<>(advertisementFacade.findAll());
    }
    
    public void onSelect(String indexes)
    {
        System.out.println("INDEX ONSELECT " + indexes);
        try
        {
            Advertisement ad=adsList.get(Integer.parseInt(indexes));
            selected.add(ad);
        }
        catch (Exception ex)
        {

        }
    }

    public void onDeselect(String indexes)
    {
        System.out.println("INDEX DESELECT " + indexes);
        try
        {
            Advertisement ad=adsList.get(Integer.parseInt(indexes));
            selected.remove(ad);
        }
        catch (Exception ex)
        {

        }
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
