/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.UserAnswer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ucan Kartal
 */
@Local
public interface UserAnswerFacadeLocal
{

    void create(UserAnswer userAnswer);

    void edit(UserAnswer userAnswer);

    void remove(UserAnswer userAnswer);

    UserAnswer find(Object id);

    List<UserAnswer> findAll();

    List<UserAnswer> findRange(int[] range);

    int count();
    
}
