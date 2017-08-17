/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.QuestionAnswer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ucan Kartal
 */
@Local
public interface QuestionAnswerFacadeLocal
{

    void create(QuestionAnswer questionAnswer);

    void edit(QuestionAnswer questionAnswer);

    void remove(QuestionAnswer questionAnswer);

    QuestionAnswer find(Object id);

    List<QuestionAnswer> findAll();

    List<QuestionAnswer> findRange(int[] range);

    int count();
    
}
