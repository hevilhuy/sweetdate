/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "Event", catalog = "SweetDate", schema = "dbo")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e")
    , @NamedQuery(name = "Event.findByEventId", query = "SELECT e FROM Event e WHERE e.eventId = :eventId")
    , @NamedQuery(name = "Event.findByStartDate", query = "SELECT e FROM Event e WHERE e.startDate = :startDate")
    , @NamedQuery(name = "Event.findByEventDetail", query = "SELECT e FROM Event e WHERE e.eventDetail = :eventDetail")
    , @NamedQuery(name = "Event.findByName", query = "SELECT e FROM Event e WHERE e.name = :name")
    , @NamedQuery(name = "Event.findByEndDate", query = "SELECT e FROM Event e WHERE e.endDate = :endDate")
    , @NamedQuery(name = "Event.findByMaxParticipant", query = "SELECT e FROM Event e WHERE e.maxParticipant = :maxParticipant")
})
public class Event implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
//    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EventId")
    private Integer eventId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "StartDate")
    private String startDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1073741823)
    @Column(name = "EventDetail")
    private String eventDetail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "EndDate")
    private String endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MaxParticipant")
    private int maxParticipant;
    @JoinTable(name = "Participant", joinColumns =
    {
        @JoinColumn(name = "EventId", referencedColumnName = "EventId")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "Username", referencedColumnName = "Username")
    })
    @ManyToMany
    private Collection<Profile> profileCollection;
    @JoinColumn(name = "EventManager", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Profile eventManager;

    public Event()
    {
    }

    public Event(Integer eventId)
    {
        this.eventId = eventId;
    }

    public Event(Integer eventId, String startDate, String eventDetail, String name, String endDate, int maxParticipant)
    {
        this.eventId = eventId;
        this.startDate = startDate;
        this.eventDetail = eventDetail;
        this.name = name;
        this.endDate = endDate;
        this.maxParticipant = maxParticipant;
    }

    public Integer getEventId()
    {
        return eventId;
    }

    public void setEventId(Integer eventId)
    {
        this.eventId = eventId;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEventDetail()
    {
        return eventDetail;
    }

    public void setEventDetail(String eventDetail)
    {
        this.eventDetail = eventDetail;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public int getMaxParticipant()
    {
        return maxParticipant;
    }

    public void setMaxParticipant(int maxParticipant)
    {
        this.maxParticipant = maxParticipant;
    }

    @XmlTransient
    public Collection<Profile> getProfileCollection()
    {
        return profileCollection;
    }

    public void setProfileCollection(Collection<Profile> profileCollection)
    {
        this.profileCollection = profileCollection;
    }

    public Profile getEventManager()
    {
        return eventManager;
    }

    public void setEventManager(Profile eventManager)
    {
        this.eventManager = eventManager;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (eventId != null ? eventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event))
        {
            return false;
        }
        Event other = (Event) object;
        if ((this.eventId == null && other.eventId != null) || (this.eventId != null && !this.eventId.equals(other.eventId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Event[ eventId=" + eventId + " ]";
    }
    
}
