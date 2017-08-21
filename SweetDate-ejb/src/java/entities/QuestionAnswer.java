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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ucan Kartal
 */
@Entity
@Table(name = "QuestionAnswer", catalog = "SweetDate", schema = "dbo")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "QuestionAnswer.findAll", query = "SELECT q FROM QuestionAnswer q")
    , @NamedQuery(name = "QuestionAnswer.findByQuestionAnswerId", query = "SELECT q FROM QuestionAnswer q WHERE q.questionAnswerId = :questionAnswerId")
})
public class QuestionAnswer implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
//    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionAnswerId")
    private Integer questionAnswerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionAnswer")
    private Collection<UserAnswer> userAnswerCollection;
    @JoinColumn(name = "AnswerId", referencedColumnName = "AnswerId")
    @ManyToOne(optional = false)
    private Answer answerId;
    @JoinColumn(name = "QuestionId", referencedColumnName = "QuestionID")
    @ManyToOne(optional = false)
    private Question questionId;

    public QuestionAnswer()
    {
    }

    public QuestionAnswer(Integer questionAnswerId)
    {
        this.questionAnswerId = questionAnswerId;
    }

    public Integer getQuestionAnswerId()
    {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(Integer questionAnswerId)
    {
        this.questionAnswerId = questionAnswerId;
    }

    @XmlTransient
    public Collection<UserAnswer> getUserAnswerCollection()
    {
        return userAnswerCollection;
    }

    public void setUserAnswerCollection(Collection<UserAnswer> userAnswerCollection)
    {
        this.userAnswerCollection = userAnswerCollection;
    }

    public Answer getAnswerId()
    {
        return answerId;
    }

    public void setAnswerId(Answer answerId)
    {
        this.answerId = answerId;
    }

    public Question getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(Question questionId)
    {
        this.questionId = questionId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (questionAnswerId != null ? questionAnswerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionAnswer))
        {
            return false;
        }
        QuestionAnswer other = (QuestionAnswer) object;
        if ((this.questionAnswerId == null && other.questionAnswerId != null) || (this.questionAnswerId != null && !this.questionAnswerId.equals(other.questionAnswerId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.QuestionAnswer[ questionAnswerId=" + questionAnswerId + " ]";
    }
    
}
