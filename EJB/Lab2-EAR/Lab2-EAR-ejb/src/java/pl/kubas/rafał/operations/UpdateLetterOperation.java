/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.kubas.rafał.model.Letter;

/**
 * @author rkubas
 * @date 2017-11-21
 * @version 1.0
 */
@Stateless(mappedName = "updateLetter")
public class UpdateLetterOperation implements UpdateLetterOperationLocal {

    /**
     * Entity manager devilering transactions
     */
    @PersistenceContext
    private EntityManager em;
    /**
     * Bean delivering functionallity for finding letters
     */
    @EJB
    private ReadLetterOperationLocal rlo;

    /**
     * Method updating content of the letter
     *
     * @param id ID of the letter which will be changed
     * @param letterContent New content of the letter
     */
    @Override
    public void updateLetterContent(int id, String letterContent) {
        Letter letter = rlo.findLetter(id);
        letter.setLetterContent(letterContent);
        em.merge(letter);
    }

    /**
     * Method updating addressee name
     *
     * @param id ID of the letter which will be changed
     * @param name New addressee name
     */
    @Override
    public void updateAddresseeName(int id, String name) {
        Letter letter = rlo.findLetter(id);
        letter.setAddresseeName(name);
        em.merge(letter);
    }

    /**
     * Method updating addressee address
     *
     * @param id ID of the letter which will be changed
     * @param address New addressee address
     */
    @Override
    public void updateAddresseAddress(int id, String address) {
        Letter letter = rlo.findLetter(id);
        letter.setAddresseeAddress(address);
        em.merge(letter);
    }

    /**
     * Method updating addressee name
     *
     * @param id ID of the letter which will be changed
     * @param name New sender name
     */
    @Override
    public void updateSenderName(int id, String name) {
        Letter letter = rlo.findLetter(id);
        letter.setSenderName(name);
        em.merge(letter);
    }

    /**
     * Method updating sender address
     *
     * @param id ID of the letter which will be changed
     * @param address New sendeer address
     */
    @Override
    public void updateSenderAddress(int id, String address) {
        Letter letter = rlo.findLetter(id);
        letter.setSenderAddress(address);
        em.merge(letter);
    }

}
