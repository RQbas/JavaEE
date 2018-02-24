/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 2017-10-23 09:08:08
 *
 * @author rkubas // class representing letter
 */
@NamedQueries({
    @NamedQuery(name = "Letter.findAll", query = "SELECT c FROM Letter c")
    ,
@NamedQuery(name = "Letter.findBySenderAddress", query = "SELECT c FROM Letter c WHERE c.senderAddress = :senderAddress")
    ,
@NamedQuery(name = "Letter.findByAddresseeAddress", query = "SELECT c FROM Letter c WHERE c.addresseeAddress = :addresseeAddress"),})
@Entity
@Table(name = "letters")
public class Letter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
    Date of sending
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "sending_date")
    private Date sendingDate;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        if (sendingDate != null) {
            sb.append(" ");
            sb.append(fmt.format(sendingDate));
        }
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
      @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;   
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Letter other = (Letter) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addresseeName, senderName);
    }
}
