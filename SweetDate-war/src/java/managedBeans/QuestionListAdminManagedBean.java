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
import java.util.HashMap;
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

    public QuestionListAdminManagedBean()
    {
        selected=new ArrayList<>();
    }

    public ArrayList<Question> getQuestionList()
    {
        if (questionList == null)
        {
            questionList = new ArrayList<>(questionFacade.findAll());
        }
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList)
    {
        this.questionList = questionList;
    }

    public void delete(ActionEvent event)
    {
        for(Question q : selected)
        {
            System.out.println("SELECTED: "+q.getQuestionContent());
            questionList.remove(q);
            questionFacade.remove(q);
            questionFacade=null;
        }
    }
    
    public void onSelect(String indexes)
    {
        Question q=questionList.get(Integer.parseInt(indexes));
        selected.add(q);
    }
    
    public void onDeselect(String indexes)
    {
        Question q=questionList.get(Integer.parseInt(indexes));
        selected.remove(q);
    }

    public ArrayList<Question> getSelected()
    {
        if(selected==null)
        {
            selected=new ArrayList<>();
        }
        return selected;
    }

    public void setSelected(ArrayList<Question> selected)
    {
        this.selected = selected;
    }
}
