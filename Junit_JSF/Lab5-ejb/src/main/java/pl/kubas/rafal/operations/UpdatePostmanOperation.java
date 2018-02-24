/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafal.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.kubas.rafal.model.Postman;

/**
 * @author rkubas
 * @date 2017-11-21
 * @version 1.0
 */
@Stateless(mappedName = "updatePostman")
public class UpdatePostmanOperation implements UpdatePostmanOperationLocal {
    
    /**
     * Entity manager
     */
    @PersistenceContext
    private EntityManager em;
    /**
     * Bean delivering functionality for finding postman entities
     */
    @EJB
    private ReadPostmanOperationLocal rpo;
    
    
     /**
     * Method for updating postman name
     * @param id ID of postman which will be changed
     * @param name New name of postman
     */
    @Override
    public void updatePostmanName(int id, String name) {
        Postman postman = rpo.findPostman(id);
        postman.setName(name);
        em.merge(postman);
    }

     /**
     * Method for updating postman surname
     * @param id ID of postman which will be changed
     * @param surname New surname of postman
     */
    @Override
    public void updatePostmanSurname(int id, String surname) {
        Postman postman = rpo.findPostman(id);
        postman.setSurname(surname);
        em.merge(postman);
    }

}
