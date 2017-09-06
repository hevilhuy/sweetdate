/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AddressCompletor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "indexManagedBean")
@SessionScoped
public class IndexManagedBean implements Serializable
{
    private int gender;
    
    public IndexManagedBean()
    {
    }

    public int getGender()
    {
        return gender;
    }

    public void setGender(int gender)
    {
        this.gender = gender;
    }
}
