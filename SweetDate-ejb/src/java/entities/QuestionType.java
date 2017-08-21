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
@Table(name = "QuestionType", catalog = "SweetDate", schema = "dbo")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "QuestionType.findAll", query = "SELECT q FROM QuestionType q")
    , @NamedQuery(name = "QuestionType.findByQuestionTypeId", query = "SELECT q FROM QuestionType q WHERE q.questionTypeId = :questionTypeId")
    , @NamedQuery(name = "QuestionType.findByTypeName", query = "SELECT q FROM QuestionType q WHERE q.typeName = :typeName")
})
public class QuestionType implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
//    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionTypeId")
    private Integer questionTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TypeName")
    private String typeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionTypeId")
    private Collection<Question> questionCollection;

    public QuestionType()
    {
    }

    public QuestionType(Integer questionTypeId)
    {
        this.questionTypeId = questionTypeId;
    }

    public QuestionType(Integer questionTypeId, String typeName)
    {
        this.questionTypeId = questionTypeId;
        this.typeName = typeName;
    }

    public Integer getQuestionTypeId()
    {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId)
    {
        this.questionTypeId = questionTypeId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    @XmlTransient
    public Collection<Question> getQuestionCollection()
    {
        return questionCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection)
    {
        this.questionCollection = questionCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (questionTypeId != null ? questionTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionType))
        {
            return false;
        }
        QuestionType other = (QuestionType) object;
        if ((this.questionTypeId == null && other.questionTypeId != null) || (this.questionTypeId != null && !this.questionTypeId.equals(other.questionTypeId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.QuestionType[ questionTypeId=" + questionTypeId + " ]";
    }
    
}
