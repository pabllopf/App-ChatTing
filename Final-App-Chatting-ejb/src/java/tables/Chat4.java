/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wwwam
 */
@Entity
@Table(name = "CHAT4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chat4.findAll", query = "SELECT c FROM Chat4 c"),
    @NamedQuery(name = "Chat4.findById", query = "SELECT c FROM Chat4 c WHERE c.id = :id"),
    @NamedQuery(name = "Chat4.findByName", query = "SELECT c FROM Chat4 c WHERE c.name = :name"),
    @NamedQuery(name = "Chat4.findByMessage", query = "SELECT c FROM Chat4 c WHERE c.message = :message"),
    @NamedQuery(name = "Chat4.findByCreatedAt", query = "SELECT c FROM Chat4 c WHERE c.createdAt = :createdAt")})
public class Chat4 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MESSAGE")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Chat4() {
    }

    public Chat4(Integer id) {
        this.id = id;
    }

    public Chat4(Integer id, String name, String message, Date createdAt) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chat4)) {
            return false;
        }
        Chat4 other = (Chat4) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tables.Chat4[ id=" + id + " ]";
    }
    
}
