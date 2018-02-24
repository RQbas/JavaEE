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
import pl.kubas.rafał.model.Postman;

/**
 * @author rkubas
 * @date 2017-11-21
 * @version 1.0
 */
@Stateless(mappedName = "delete")
public class DeleteEntityOperation implements DeleteEntityOperationLocal {
   /**
    * Entity manager delivering database operations
    */
    @PersistenceContext
    private EntityManager em;
    /*
    Bean delivering functionallity for finding letters
    */
    @EJB
    ReadLetterOperationLocal rlo;
    /*
    Bean delivering functionallity for finding postmans
    */
    @EJB
    ReadPostmanOperationLocal rpo;
   
    /**
     * Method for deleting leter
     * @param id ID of letter which will be deleted
     */
    @Override
    public void deleteLetter(int id) {
        Letter letter = rlo.findLetter(id);
        em.remove(letter);
    }

     /**
     * Method for deleting postman
     * @param id ID of postman which will be deleted
     */
    @Override
    public void deletePostman(int id) {
        Postman postman = rpo.findPostman(id);
        em.remove(postman);
    }
}
