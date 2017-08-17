/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ucan Kartal
 */
@Entity
@Table(name = "UserAnswer", catalog = "SweetDate", schema = "dbo")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "UserAnswer.findAll", query = "SELECT u FROM UserAnswer u")
    , @NamedQuery(name = "UserAnswer.findByQuestionAnswerId", query = "SELECT u FROM UserAnswer u WHERE u.userAnswerPK.questionAnswerId = :questionAnswerId")
    , @NamedQuery(name = "UserAnswer.findByUsername", query = "SELECT u FROM UserAnswer u WHERE u.userAnswerPK.username = :username")
    , @NamedQuery(name = "UserAnswer.findByDetail", query = "SELECT u FROM UserAnswer u WHERE u.detail = :detail")
})
public class UserAnswer implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserAnswerPK userAnswerPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Detail")
    private String detail;
    @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Profile profile;
    @JoinColumn(name = "QuestionAnswerId", referencedColumnName = "QuestionAnswerId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private QuestionAnswer questionAnswer;

    public UserAnswer()
    {
    }

    public UserAnswer(UserAnswerPK userAnswerPK)
    {
        this.userAnswerPK = userAnswerPK;
    }

    public UserAnswer(UserAnswerPK userAnswerPK, String detail)
    {
        this.userAnswerPK = userAnswerPK;
        this.detail = detail;
    }

    public UserAnswer(int questionAnswerId, String username)
    {
        this.userAnswerPK = new UserAnswerPK(questionAnswerId, username);
    }

    public UserAnswerPK getUserAnswerPK()
    {
        return userAnswerPK;
    }

    public void setUserAnswerPK(UserAnswerPK userAnswerPK)
    {
        this.userAnswerPK = userAnswerPK;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public Profile getProfile()
    {
        return profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }

    public QuestionAnswer getQuestionAnswer()
    {
        return questionAnswer;
    }

    public void setQuestionAnswer(QuestionAnswer questionAnswer)
    {
        this.questionAnswer = questionAnswer;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (userAnswerPK != null ? userAnswerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAnswer))
        {
            return false;
        }
        UserAnswer other = (UserAnswer) object;
        if ((this.userAnswerPK == null && other.userAnswerPK != null) || (this.userAnswerPK != null && !this.userAnswerPK.equals(other.userAnswerPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.UserAnswer[ userAnswerPK=" + userAnswerPK + " ]";
    }
    
}
