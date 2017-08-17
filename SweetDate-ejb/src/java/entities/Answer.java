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
import javax.persistence.Id;
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
@Table(name = "Answer", catalog = "SweetDate", schema = "dbo")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a")
    , @NamedQuery(name = "Answer.findByAnswerId", query = "SELECT a FROM Answer a WHERE a.answerId = :answerId")
    , @NamedQuery(name = "Answer.findByAnswerContent", query = "SELECT a FROM Answer a WHERE a.answerContent = :answerContent")
})
public class Answer implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AnswerId")
    private Integer answerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AnswerContent")
    private String answerContent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "answerId")
    private Collection<QuestionAnswer> questionAnswerCollection;

    public Answer()
    {
    }

    public Answer(Integer answerId)
    {
        this.answerId = answerId;
    }

    public Answer(Integer answerId, String answerContent)
    {
        this.answerId = answerId;
        this.answerContent = answerContent;
    }

    public Integer getAnswerId()
    {
        return answerId;
    }

    public void setAnswerId(Integer answerId)
    {
        this.answerId = answerId;
    }

    public String getAnswerContent()
    {
        return answerContent;
    }

    public void setAnswerContent(String answerContent)
    {
        this.answerContent = answerContent;
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
        hash += (answerId != null ? answerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer))
        {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.answerId == null && other.answerId != null) || (this.answerId != null && !this.answerId.equals(other.answerId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Answer[ answerId=" + answerId + " ]";
    }
    
}
