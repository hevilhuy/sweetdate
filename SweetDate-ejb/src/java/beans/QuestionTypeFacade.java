/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.QuestionType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ucan Kartal
 */
@Stateless
public class QuestionTypeFacade extends AbstractFacade<QuestionType> implements QuestionTypeFacadeLocal
{

    @PersistenceContext(unitName = "SweetDate-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public QuestionTypeFacade()
    {
        super(QuestionType.class);
    }
    
}
