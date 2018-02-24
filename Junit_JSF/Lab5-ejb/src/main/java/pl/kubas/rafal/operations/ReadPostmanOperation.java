/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafal.operations;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.kubas.rafal.model.Letter;
import pl.kubas.rafal.model.Postman;

/**
 * @author rkubas
 * @date 2017-11-21
 * @version 1.0
 */
@Stateless(mappedName = "readPostman")
public class ReadPostmanOperation implements ReadPostmanOperationLocal {

    /**
     * Entity manager delivering transactions
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Method for finding postman with given id
     *
     * @param id Id of the wanted postman
     * @return Postman object with declared id
     */
    @Override
    public Postman findPostman(int id) {
        return em.find(Postman.class, id);
    }

    /**
     * Method for finding all postmans persisted
     *
     * @return List of postman objects
     */
    @Override
    public List<Postman> findAllPostmans() {
        return em.createNamedQuery("Postman.findAll").getResultList();
    }

    /**
     * Method for finding postman with given name
     *
     * @param name Name of wanted postman
     * @return List of postmans with name obtained as a parameter
     */
    @Override
    public List<Postman> findPostmanByName(String name) {
        return em.createNamedQuery("Postman.findByName").setParameter("name", name).getResultList();
    }

    /**
     * Method for finding postman with given surname
     *
     * @param surname surname of wanted postman
     * @return List of postmans with surname obtained as a parameter
     */
    @Override
    public List<Postman> findPostmanBySurname(String surname) {
        return em.createNamedQuery("Postman.findBySurname").setParameter("surname", surname).getResultList();
    }

}
