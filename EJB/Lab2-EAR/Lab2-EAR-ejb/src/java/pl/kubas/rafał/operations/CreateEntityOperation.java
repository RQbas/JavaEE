/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.operations;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.kubas.rafał.model.Letter;
import pl.kubas.rafał.model.Postman;

/**
 * @author rkubas
 * @date 2017-11-21
 * @version 1.0
 */
@Stateless(mappedName = "create")
public class CreateEntityOperation implements CreateEntityOperationLocal {

    /*
    Entity manager delivering functionality for database operations
     */
    @PersistenceContext
    private EntityManager em;
    /**
     * Bean delivering functionallity for finding postmans
     */
    @EJB
    ReadPostmanOperationLocal rpo;

    /**
     * Definition of method creating postman instance
     *
     * @param name delivers name of the postman
     * @param surname delivers surname of the postman
     */
    @Override
    public void createPostman(String name, String surname) {
        Postman postman = new Postman();
        postman.setName(name);
        postman.setSurname(surname);
        em.persist(postman);
    }

    /**
     * Definition of method creating letter instance
     *
     * @param letterContent delivers content of the letter
     * @param addresseeName delivers name of the addressee
     * @param addresseeAddress delivers address of the addressee
     * @param senderName delivers name of the sender
     * @param senderAddress delivers address of the sender
     */
    @Override
    public void createLetter(String letterContent, String addresseeName, String addresseeAddress, String senderName, String senderAddress, int postmanID) {
        Letter letter = new Letter();
        letter.setLetterContent(letterContent);
        letter.setAddresseeName(addresseeName);
        letter.setAddresseeAddress(addresseeAddress);
        letter.setSenderName(senderName);
        letter.setSenderAddress(senderAddress);
        Postman postman = rpo.findPostman(postmanID);
        letter.setPostman(postman);
        postman.getLetters().add(letter);

        em.persist(letter);
    }

}
