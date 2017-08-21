/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Answer;
import entities.Question;
import entities.QuestionAnswer;
import entities.QuestionType;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ucan Kartal
 */
@Named(value = "addQuestionAdminManagedBean")
@RequestScoped
public class AddQuestionAdminManagedBean
{
    private Question question;
    private ArrayList<Answer> answerList;
    
    public AddQuestionAdminManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        question=new Question();
        question.setQuestionTypeId(new QuestionType());
        answerList=new ArrayList<>();
        Answer a=new Answer();
        a.setAnswerContent("Example");
        answerList.add(a);
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
    
    public void addAnswer(ActionEvent event)
    {
        add();
        //FacesContext.getCurrentInstance().renderResponse();
        System.out.println("LISTTTTTTTTTTTTTTTTTTTTTT: "+answerList.size());
    }
    private void add()
    {
        Answer a=new Answer();
        a.setAnswerContent("new answer");
        answerList.add(a);
    }
}
