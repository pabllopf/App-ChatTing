/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wwwam
 */
@Entity
@Table(name = "EXAMPLECHAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableExamplechat.findAll", query = "SELECT t FROM TableExamplechat t"),
    @NamedQuery(name = "TableExamplechat.findById", query = "SELECT t FROM TableExamplechat t WHERE t.id = :id"),
    @NamedQuery(name = "TableExamplechat.findByName", query = "SELECT t FROM TableExamplechat t WHERE t.name = :name"),
    @NamedQuery(name = "TableExamplechat.findByMessage", query = "SELECT t FROM TableExamplechat t WHERE t.message = :message"),
    @NamedQuery(name = "TableExamplechat.findByCreatedAt", query = "SELECT t FROM TableExamplechat t WHERE t.createdAt = :createdAt")})
public class TableExamplechat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MESSAGE")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    private int createdAt;

    public TableExamplechat() {
    }

    public TableExamplechat(Integer id) {
        this.id = id;
    }

    public TableExamplechat(Integer id, String name, String message, int createdAt) {
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

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
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
        if (!(object instanceof TableExamplechat)) {
            return false;
        }
        TableExamplechat other = (TableExamplechat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tables.TableExamplechat[ id=" + id + " ]";
    }
    
}
