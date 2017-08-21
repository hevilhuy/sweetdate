/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ucan Kartal
 */
@Entity
@Table(name = "Question", catalog = "SweetDate", schema = "dbo")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
    , @NamedQuery(name = "Question.findByQuestionID", query = "SELECT q FROM Question q WHERE q.questionID = :questionID")
    , @NamedQuery(name = "Question.findByQuestionContent", query = "SELECT q FROM Question q WHERE q.questionContent = :questionContent")
})
public class Question implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
//    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionID")
    private Integer questionID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "QuestionContent")
    private String questionContent;
    @JoinColumn(name = "QuestionTypeId", referencedColumnName = "QuestionTypeId")
    @ManyToOne(optional = false)
    private QuestionType questionTypeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
    private Collection<QuestionAnswer> questionAnswerCollection;

    public Question()
    {
    }

    public Question(Integer questionID)
    {
        this.questionID = questionID;
    }

    public Question(Integer questionID, String questionContent)
    {
        this.questionID = questionID;
        this.questionContent = questionContent;
    }

    public Integer getQuestionID()
    {
        return questionID;
    }

    public void setQuestionID(Integer questionID)
    {
        this.questionID = questionID;
    }

    public String getQuestionContent()
    {
        return questionContent;
    }

    public void setQuestionContent(String questionContent)
    {
        this.questionContent = questionContent;
    }

    public QuestionType getQuestionTypeId()
    {
        return questionTypeId;
    }

    public void setQuestionTypeId(QuestionType questionTypeId)
    {
        this.questionTypeId = questionTypeId;
    }

    @XmlTransient
    public Collection<QuestionAnswer> getQuestionAnswerCollection()
    {
        return questionAnswerCollection;
    }

    public void setQuestionAnswerCollection(Collection<QuestionAnswer> questionAnswerCollection)
    {
        this.questionAnswerCollection = questionAnswerCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (questionID != null ? questionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question))
        {
            return false;
        }
        Question other = (Question) object;
        if ((this.questionID == null && other.questionID != null) || (this.questionID != null && !this.questionID.equals(other.questionID)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Question[ questionID=" + questionID + " ]";
    }
    
}
