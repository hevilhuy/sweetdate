/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "helperManagedBean")
@RequestScoped
public class HelperManagedBean
{

    public HelperManagedBean()
    {
    }
    
    public String getAbout(String str)
    {
        return str.substring(0, Math.min(str.length(), 35));
    }
}
