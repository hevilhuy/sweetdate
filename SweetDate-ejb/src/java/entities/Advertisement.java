/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "Advertisement", catalog = "SweetDate", schema = "dbo")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Advertisement.findAll", query = "SELECT a FROM Advertisement a")
    , @NamedQuery(name = "Advertisement.findByAdId", query = "SELECT a FROM Advertisement a WHERE a.adId = :adId")
    , @NamedQuery(name = "Advertisement.findByLink", query = "SELECT a FROM Advertisement a WHERE a.link = :link")
    , @NamedQuery(name = "Advertisement.findByDueDate", query = "SELECT a FROM Advertisement a WHERE a.dueDate = :dueDate")
    , @NamedQuery(name = "Advertisement.findByPrice", query = "SELECT a FROM Advertisement a WHERE a.price = :price")
    , @NamedQuery(name = "Advertisement.findByPosition", query = "SELECT a FROM Advertisement a WHERE a.position = :position")
    , @NamedQuery(name = "Advertisement.findByMinAge", query = "SELECT a FROM Advertisement a WHERE a.minAge = :minAge")
    , @NamedQuery(name = "Advertisement.findByMaxAge", query = "SELECT a FROM Advertisement a WHERE a.maxAge = :maxAge")
    , @NamedQuery(name = "Advertisement.findByDisplay", query = "SELECT a FROM Advertisement a WHERE a.display = :display")
})
public class Advertisement implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
//    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AdId")
    private Integer adId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "Image")
    private byte[] image;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Link")
    private String link;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DueDate")
    private String dueDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Position")
    private int position;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MinAge")
    private int minAge;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MaxAge")
    private int maxAge;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Display")
    private short display;

    public Advertisement()
    {
    }

    public Advertisement(Integer adId)
    {
        this.adId = adId;
    }

    public Advertisement(Integer adId, byte[] image, String link, String dueDate, int price, int position, int minAge, int maxAge, short display)
    {
        this.adId = adId;
        this.image = image;
        this.link = link;
        this.dueDate = dueDate;
        this.price = price;
        this.position = position;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.display = display;
    }

    public Integer getAdId()
    {
        return adId;
    }

    public void setAdId(Integer adId)
    {
        this.adId = adId;
    }

    public byte[] getImage()
    {
        return image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public String getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(String dueDate)
    {
        this.dueDate = dueDate;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public int getMinAge()
    {
        return minAge;
    }

    public void setMinAge(int minAge)
    {
        this.minAge = minAge;
    }

    public int getMaxAge()
    {
        return maxAge;
    }

    public void setMaxAge(int maxAge)
    {
        this.maxAge = maxAge;
    }

    public short getDisplay()
    {
        return display;
    }

    public void setDisplay(short display)
    {
        this.display = display;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (adId != null ? adId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Advertisement))
        {
            return false;
        }
        Advertisement other = (Advertisement) object;
        if ((this.adId == null && other.adId != null) || (this.adId != null && !this.adId.equals(other.adId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Advertisement[ adId=" + adId + " ]";
    }
    
}
