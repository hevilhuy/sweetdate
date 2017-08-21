/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.ProfileFacadeLocal;
import beans.QuestionFacadeLocal;
import entities.Question;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "questionListAdminManagedBean")
@SessionScoped
public class QuestionListAdminManagedBean implements Serializable
{

    @EJB
    private ProfileFacadeLocal profileFacade;

    @EJB
    private QuestionFacadeLocal questionFacade;
    private ArrayList<Question> questionList;
    public QuestionListAdminManagedBean()
    {
        questionList=new ArrayList<>();
    }

    public ArrayList<Question> getQuestionList()
    {
        questionList=new ArrayList<>(questionFacade.findAll());
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList)
    {
        this.questionList = questionList;
    }
}
