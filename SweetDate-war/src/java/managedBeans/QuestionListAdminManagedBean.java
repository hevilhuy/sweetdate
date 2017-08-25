/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AddressCompletor;
import beans.ProfileFacadeLocal;
import beans.QuestionFacadeLocal;
import entities.Question;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

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
    private ArrayList<Question> selected;
    private String refresh;

    public QuestionListAdminManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        questionList = new ArrayList<>(questionFacade.findAll());
        selected = new ArrayList<>();
    }

    public ArrayList<Question> getQuestionList()
    {
        questionList = new ArrayList<>(questionFacade.findAll());
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList)
    {
        this.questionList = questionList;
    }

    public void delete(ActionEvent event)
    {
        for (Question q : selected)
        {
            System.out.println("SELECTED: " + q.getQuestionContent());
            questionFacade.remove(q);
            questionList = new ArrayList<>(questionFacade.findAll());
            selected = new ArrayList<>();
        }
    }

    public void onSelect(String indexes)
    {
        System.out.println("INDEX ONSELECT " + indexes);
        try
        {
            Question q = questionList.get(Integer.parseInt(indexes));
            selected.add(q);
        }
        catch (Exception ex)
        {

        }
    }

    public void onDeselect(String indexes)
    {
        System.out.println("INDEX DESELECT " + indexes);
        try
        {
            Question q = questionList.get(Integer.parseInt(indexes));
            selected.remove(q);
        }
        catch (Exception ex)
        {

        }
    }

    public ArrayList<Question> getSelected()
    {
        return selected;
    }

    public void setSelected(ArrayList<Question> selected)
    {
        this.selected = selected;
    }

    public String getRefresh()
    {
        return refresh;
    }

    public void setRefresh(String refresh)
    {
        this.refresh = refresh;
    }
    
    public String edit(String id)
    {
        return AddressCompletor.complete("edit_question_admin.xhtml?")+"&questionId="+id;
    }
}
