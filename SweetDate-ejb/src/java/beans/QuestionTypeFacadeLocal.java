/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.QuestionType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ucan Kartal
 */
@Local
public interface QuestionTypeFacadeLocal
{

    void create(QuestionType questionType);

    void edit(QuestionType questionType);

    void remove(QuestionType questionType);

    QuestionType find(Object id);

    List<QuestionType> findAll();

    List<QuestionType> findRange(int[] range);

    int count();
    
}
