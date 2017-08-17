/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ucan Kartal
 */
@Embeddable
public class UserAnswerPK implements Serializable
{

    @Basic(optional = false)
    @NotNull
    @Column(name = "QuestionAnswerId")
    private int questionAnswerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Username")
    private String username;

    public UserAnswerPK()
    {
    }

    public UserAnswerPK(int questionAnswerId, String username)
    {
        this.questionAnswerId = questionAnswerId;
        this.username = username;
    }

    public int getQuestionAnswerId()
    {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(int questionAnswerId)
    {
        this.questionAnswerId = questionAnswerId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) questionAnswerId;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAnswerPK))
        {
            return false;
        }
        UserAnswerPK other = (UserAnswerPK) object;
        if (this.questionAnswerId != other.questionAnswerId)
        {
            return false;
        }
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.UserAnswerPK[ questionAnswerId=" + questionAnswerId + ", username=" + username + " ]";
    }
    
}
