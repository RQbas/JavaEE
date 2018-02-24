/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.kubas.rafał.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * @author rkubas
 * @date 2017-11-21
 * @version 1.0
 */
@NamedQueries({
    @NamedQuery(name = "Postman.findByName", query = "SELECT c FROM Postman c WHERE c.name = :name"),
    @NamedQuery(name = "Postman.findBySurname", query = "SELECT c FROM Postman c WHERE c.surname = :surname"),
    @NamedQuery(name = "Postman.findAll", query = "SELECT c FROM Postman c")})
@Entity
@Table(name = "postmans")
public class Postman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 15, name = "postman_name")
    private String name;

    @Column(nullable = false, length = 20, name = "postman_surname")
    private String surname;

    /*
    Postman can carry list of letters
     */
    @OneToMany(mappedBy = "postman", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Letter> letters = new ArrayList<>();
    
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public List<Letter> getLetters() {
        return letters;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

  @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        sb.append(name);
        sb.append(" ");
        sb.append(surname);

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
        final Postman other = (Postman) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
