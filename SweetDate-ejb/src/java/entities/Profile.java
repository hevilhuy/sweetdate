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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "Profile", catalog = "SweetDate", schema = "dbo")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p")
    , @NamedQuery(name = "Profile.findByUsername", query = "SELECT p FROM Profile p WHERE p.username = :username")
    , @NamedQuery(name = "Profile.findByPassword", query = "SELECT p FROM Profile p WHERE p.password = :password")
    , @NamedQuery(name = "Profile.findByAbout", query = "SELECT p FROM Profile p WHERE p.about = :about")
    , @NamedQuery(name = "Profile.findByActive", query = "SELECT p FROM Profile p WHERE p.active = :active")
    , @NamedQuery(name = "Profile.findByDisplayName", query = "SELECT p FROM Profile p WHERE p.displayName = :displayName")
    , @NamedQuery(name = "Profile.findByCountry", query = "SELECT p FROM Profile p WHERE p.country = :country")
    , @NamedQuery(name = "Profile.findByGender", query = "SELECT p FROM Profile p WHERE p.gender = :gender")
    , @NamedQuery(name = "Profile.findByBirthdate", query = "SELECT p FROM Profile p WHERE p.birthdate = :birthdate")
    , @NamedQuery(name = "Profile.findByLookingFor", query = "SELECT p FROM Profile p WHERE p.lookingFor = :lookingFor")
    , @NamedQuery(name = "Profile.findByDueDate", query = "SELECT p FROM Profile p WHERE p.dueDate = :dueDate")
    , @NamedQuery(name = "Profile.findByEmail", query = "SELECT p FROM Profile p WHERE p.email = :email")
    , @NamedQuery(name = "Profile.findByContact", query = "SELECT p FROM Profile p WHERE p.contact = :contact")
    , @NamedQuery(name = "Profile.findByMaritalStatus", query = "SELECT p FROM Profile p WHERE p.maritalStatus = :maritalStatus")
    , @NamedQuery(name = "Profile.findByCaste", query = "SELECT p FROM Profile p WHERE p.caste = :caste")
})
public class Profile implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Password")
    private String password;
    @Size(max = 1073741823)
    @Column(name = "About")
    private String about;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Active")
    private short active;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DisplayName")
    private String displayName;
    @Size(max = 50)
    @Column(name = "Country")
    private String country;
    @Lob
    @Column(name = "Avatar")
    private byte[] avatar;
    @Column(name = "Gender")
    private Short gender;
    @Size(max = 10)
    @Column(name = "Birthdate")
    private String birthdate;
    @Size(max = 1073741823)
    @Column(name = "LookingFor")
    private String lookingFor;
    @Size(max = 10)
    @Column(name = "DueDate")
    private String dueDate;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Email")
    private String email;
    @Size(max = 1073741823)
    @Column(name = "Contact")
    private String contact;
    @Size(max = 50)
    @Column(name = "MaritalStatus")
    private String maritalStatus;
    @Size(max = 50)
    @Column(name = "Caste")
    private String caste;
    @ManyToMany(mappedBy = "profileCollection")
    private Collection<Event> eventCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<FollowList> followListCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "followerId")
    private Collection<FollowList> followListCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    private Collection<UserAnswer> userAnswerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventManager")
    private Collection<Event> eventCollection1;
    @JoinColumn(name = "RoleId", referencedColumnName = "RoleId")
    @ManyToOne(optional = false)
    private Role roleId;

    public Profile()
    {
    }

    public Profile(String username)
    {
        this.username = username;
    }

    public Profile(String username, String password, short active, String displayName, String email)
    {
        this.username = username;
        this.password = password;
        this.active = active;
        this.displayName = displayName;
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAbout()
    {
        return about;
    }

    public void setAbout(String about)
    {
        this.about = about;
    }

    public short getActive()
    {
        return active;
    }

    public void setActive(short active)
    {
        this.active = active;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public byte[] getAvatar()
    {
        return avatar;
    }

    public void setAvatar(byte[] avatar)
    {
        this.avatar = avatar;
    }

    public Short getGender()
    {
        return gender;
    }

    public void setGender(Short gender)
    {
        this.gender = gender;
    }

    public String getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(String birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getLookingFor()
    {
        return lookingFor;
    }

    public void setLookingFor(String lookingFor)
    {
        this.lookingFor = lookingFor;
    }

    public String getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(String dueDate)
    {
        this.dueDate = dueDate;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getMaritalStatus()
    {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus)
    {
        this.maritalStatus = maritalStatus;
    }

    public String getCaste()
    {
        return caste;
    }

    public void setCaste(String caste)
    {
        this.caste = caste;
    }

    @XmlTransient
    public Collection<Event> getEventCollection()
    {
        return eventCollection;
    }

    public void setEventCollection(Collection<Event> eventCollection)
    {
        this.eventCollection = eventCollection;
    }

    @XmlTransient
    public Collection<FollowList> getFollowListCollection()
    {
        return followListCollection;
    }

    public void setFollowListCollection(Collection<FollowList> followListCollection)
    {
        this.followListCollection = followListCollection;
    }

    @XmlTransient
    public Collection<FollowList> getFollowListCollection1()
    {
        return followListCollection1;
    }

    public void setFollowListCollection1(Collection<FollowList> followListCollection1)
    {
        this.followListCollection1 = followListCollection1;
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

    @XmlTransient
    public Collection<Event> getEventCollection1()
    {
        return eventCollection1;
    }

    public void setEventCollection1(Collection<Event> eventCollection1)
    {
        this.eventCollection1 = eventCollection1;
    }

    public Role getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Role roleId)
    {
        this.roleId = roleId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profile))
        {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Profile[ username=" + username + " ]";
    }
    
}
