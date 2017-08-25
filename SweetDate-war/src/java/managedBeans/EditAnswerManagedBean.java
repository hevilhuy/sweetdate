/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AnswerFacadeLocal;
import beans.QuestionAnswerFacadeLocal;
import beans.QuestionFacadeLocal;
import entities.Answer;
import entities.Question;
import entities.QuestionAnswer;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "editAnswerManagedBean")
@SessionScoped
public class EditAnswerManagedBean implements Serializable
{

    @EJB
    private AnswerFacadeLocal answerFacade;

    @EJB
    private QuestionAnswerFacadeLocal questionAnswerFacade;

    @EJB
    private QuestionFacadeLocal questionFacade;
    
    private String questionId;
    private Question question;
    private ArrayList<Answer> answerList;
    private ArrayList<QuestionAnswer> questionAnswerList;
    private ArrayList<Answer> selected;
    public EditAnswerManagedBean()
    {
    }

    public void init()
    {
        System.out.println("QUESTION ID "+questionId);
        question=questionFacade.find(Integer.parseInt(questionId));
        if(question==null)
        {
            try
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index");
            }
            catch (IOException ex)
            {
                Logger.getLogger(EditAnswerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            answerList=new ArrayList<>();
            selected=new ArrayList<>();
            questionAnswerList=new ArrayList<>();
            ArrayList<QuestionAnswer> tempQAList=new ArrayList<>(questionAnswerFacade.findAll());
            for(QuestionAnswer qa:tempQAList)            
            {
                if(qa.getQuestionId().getQuestionID()==Integer.parseInt(questionId))
                {
                    questionAnswerList.add(qa);
                }
            }
            for(QuestionAnswer qa:questionAnswerList)
            {
                answerList.add(qa.getAnswerId());
            }
        }
    }
    
    public void addAnswer(ActionEvent event)
    {
        Answer a=new Answer();
        a.setAnswerContent("New Answer");
        answerList.add(a);
    }
    
    public void deleteAnswer(ActionEvent event)
    {
        for(Answer a:selected)
        {
            System.out.println("DELETE "+a.getAnswerContent());
            answerList.remove(a);
        }
        System.out.println("REMAINING: ");
        for(Answer a:answerList)
        {
            System.out.println(a.getAnswerContent());
        }
        selected=new ArrayList<>();
    }
    
    public void onSelect(String indexes)
    {
        System.out.println("ON SELECT ANSWER "+indexes);
        try
        {
            Answer a=answerList.get(Integer.parseInt(indexes));
            selected.add(a);
            System.out.println("ADDED "+a.getAnswerContent());
        }
        catch(Exception ex)
        {
            
        }
    }
    
    public void onDeselect(String indexes)
    {
        System.out.println("ON DESELECT ANSWER "+indexes);
        try
        {
            Answer a=answerList.get(Integer.parseInt(indexes));
            selected.remove(a);
            System.out.println("REMOVED "+a.getAnswerContent());
        }
        catch(Exception ex)
        {
            
        }
    }
    
    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }

    public ArrayList<Answer> getAnswerList()
    {
        return answerList;
    }

    public void setAnswerList(ArrayList<Answer> answerList)
    {
        this.answerList = answerList;
    }

    public ArrayList<QuestionAnswer> getQuestionAnswerList()
    {
        return questionAnswerList;
    }

    public void setQuestionAnswerList(ArrayList<QuestionAnswer> questionAnswerList)
    {
        this.questionAnswerList = questionAnswerList;
    }

    public String getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(String questionId)
    {
        this.questionId = questionId;
    }

    public ArrayList<Answer> getSelected()
    {
        return selected;
    }

    public void setSelected(ArrayList<Answer> selected)
    {
        this.selected = selected;
    }
}
