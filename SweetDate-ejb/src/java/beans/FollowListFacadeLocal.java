/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.FollowList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ucan Kartal
 */
@Local
public interface FollowListFacadeLocal
{

    void create(FollowList followList);

    void edit(FollowList followList);

    void remove(FollowList followList);

    FollowList find(Object id);

    List<FollowList> findAll();

    List<FollowList> findRange(int[] range);

    int count();
    
}
