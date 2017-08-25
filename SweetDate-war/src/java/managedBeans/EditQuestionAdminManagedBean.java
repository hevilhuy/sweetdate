/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AddressCompletor;
import beans.QuestionFacadeLocal;
import beans.QuestionTypeFacadeLocal;
import entities.Answer;
import entities.Question;
import entities.QuestionType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "editQuestionAdminManagedBean")
@SessionScoped
public class EditQuestionAdminManagedBean implements Serializable
{

    @EJB
    private QuestionTypeFacadeLocal questionTypeFacade;
    @EJB
    private QuestionFacadeLocal questionFacade;
    private String error="";
    private Question question;
    private String questionId;
    public EditQuestionAdminManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        if (questionId == null || questionId.equals("new") || questionId.equals(""))
        {
            question = new Question();
            question.setQuestionTypeId(questionTypeFacade.find(1));
        }
        else
        {
            try
            {
                question = questionFacade.find(Integer.parseInt(questionId));
                if (question == null)
                {
                    question = new Question();
                    question.setQuestionTypeId(questionTypeFacade.find(1));
                }
            }
            catch (Exception ex)
            {
                question = new Question();
                question.setQuestionTypeId(questionTypeFacade.find(1));
            }
        }
        FacesContext.getCurrentInstance().renderResponse();
    }

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }

    public String getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(String questionId)
    {
        this.questionId = questionId;
    }
    
    public String saveChanges()
    {
        if (questionId == null || questionId.equals("new") || questionId.equals(""))
        {
            questionFacade.create(question);
        }
        else
        {
            Question questionInDb=questionFacade.find(Integer.parseInt(questionId));
            if(questionInDb!=null)
            {
                questionInDb.setQuestionContent(question.getQuestionContent());
                questionInDb.setQuestionTypeId(questionTypeFacade.find(question.getQuestionTypeId().getQuestionTypeId()));
                questionFacade.edit(questionInDb);
            }
        }
        return AddressCompletor.complete("question_list_admin");
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }
}
