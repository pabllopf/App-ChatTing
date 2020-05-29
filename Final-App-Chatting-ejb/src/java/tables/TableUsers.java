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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 42250209
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableUsers.findAll", query = "SELECT t FROM TableUsers t"),
    @NamedQuery(name = "TableUsers.findById", query = "SELECT t FROM TableUsers t WHERE t.id = :id"),
    @NamedQuery(name = "TableUsers.findByName", query = "SELECT t FROM TableUsers t WHERE t.name = :name"),
    @NamedQuery(name = "TableUsers.findByPassword", query = "SELECT t FROM TableUsers t WHERE t.password = :password"),
    @NamedQuery(name = "TableUsers.findByChat", query = "SELECT t FROM TableUsers t WHERE t.chat = :chat"),
    @NamedQuery(name = "TableUsers.findByChatlist", query = "SELECT t FROM TableUsers t WHERE t.chatlist = :chatlist")})
public class TableUsers implements Serializable {

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
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 255)
    @Column(name = "CHAT")
    private String chat;
    @Size(max = 255)
    @Column(name = "CHATLIST")
    private String chatlist;

    public TableUsers() {
    }

    public TableUsers(Integer id) {
        this.id = id;
    }

    public TableUsers(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getChatlist() {
        return chatlist;
    }

    public void setChatlist(String chatlist) {
        this.chatlist = chatlist;
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
        if (!(object instanceof TableUsers)) {
            return false;
        }
        TableUsers other = (TableUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tables.TableUsers[ id=" + id + " ]";
    }
    
}
