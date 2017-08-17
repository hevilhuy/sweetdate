/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Advertisement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ucan Kartal
 */
@Local
public interface AdvertisementFacadeLocal
{

    void create(Advertisement advertisement);

    void edit(Advertisement advertisement);

    void remove(Advertisement advertisement);

    Advertisement find(Object id);

    List<Advertisement> findAll();

    List<Advertisement> findRange(int[] range);

    int count();
    
}
