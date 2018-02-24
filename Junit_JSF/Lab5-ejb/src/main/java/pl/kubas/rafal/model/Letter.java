/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.kubas.rafal.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author rkubas
 * @date 2017-11-21
 * @version 1.0
 */

@NamedQueries({
    @NamedQuery(name = "Letter.findAll", query = "SELECT c FROM Letter c"),
    @NamedQuery(name = "Letter.findBySenderAddress", query = "SELECT c FROM Letter c WHERE c.senderAddress = :senderAddress"),
    @NamedQuery(name = "Letter.findByAddresseeAddress", query = "SELECT c FROM Letter c WHERE c.addresseeAddress = :addresseeAddress")})
@Entity
@Table(name = "letters")
public class Letter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /*
    Name of the addressee
     */
    @Column(nullable = false, length = 25, name = "addressee_name")
    private String addresseeName;

    /*
    Address of the addressee
     */
    @Column(nullable = false, length = 25, name = "addressee_address")
    private String addresseeAddress;

    /*
    Name of the sender
     */
    @Column(nullable = false, length = 25, name = "sender_name")
    private String senderName;

    /*
    Address of the sender
     */
    @Column(nullable = false, length = 25, name = "sender_address")
    private String senderAddress;

    /*
    Content of the letter
     */
    @Column(nullable = false, length = 500, name = "letter_content")
    private String letterContent;

    /*
    Letter can be carried by postman
     */
    @ManyToOne(targetEntity = Postman.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "postman_id")
    private Postman postman;
    
    
      public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public void setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName;
    }

    public String getAddresseeAddress() {
        return addresseeAddress;
    }

    public void setAddresseeAddress(String addresseeAddress) {
        this.addresseeAddress = addresseeAddress;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public Postman getPostman() {
        return postman;
    }

    public void setPostman(Postman postman) {
        this.postman = postman;
    }
    
    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;   
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        final Letter other = (Letter) object;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
     
        sb.append(addresseeName);
        sb.append("-");
        sb.append(addresseeAddress);
        sb.append(" ");
        sb.append(senderName);
        sb.append("-");
        sb.append(addresseeAddress);
        sb.append(" ");
        sb.append(letterContent);

        return sb.toString();
    }

}
