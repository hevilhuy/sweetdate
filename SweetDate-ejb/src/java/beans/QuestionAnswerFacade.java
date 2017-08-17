/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.QuestionAnswer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ucan Kartal
 */
@Stateless
public class QuestionAnswerFacade extends AbstractFacade<QuestionAnswer> implements QuestionAnswerFacadeLocal
{

    @PersistenceContext(unitName = "SweetDate-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public QuestionAnswerFacade()
    {
        super(QuestionAnswer.class);
    }
    
}
