/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pl.kubas.rafał.model.Letter;
import pl.kubas.rafał.model.PostOffice;
import pl.kubas.rafał.model.Postman;

/**
 * 2017-10-23 13:36:39
 *
 * @author rkubas //Class representing Database connection, provides API to
 * perform basic operations on entities
 */
public class DatabaseExecutor {

    private EntityManager em;
    private LetterQueryExecutor lqe;
    private PostmanQueryExecutor pqe;

    

    /**
     * Database Executor constructor
     *Setting Entity managers
     */

    public DatabaseExecutor() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabPU");
        em = emf.createEntityManager();
        lqe = new LetterQueryExecutor(em);
        pqe = new PostmanQueryExecutor(em);
    }

    public EntityManager getEm() {
        return em;
    }

    /**
     *updates Entity
     * @param entity
     */
    public void updateObject(Object entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    /**
     * persists entity
     * @param entity
     */
    public void storeObject(Object entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    /**
     * deletes entity from database
     * @param entity
     */
    public void removeObject(Object entity) {   
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    /**
     *finds all stored postmans
     * @return List<Postman>
     */
    public List<Postman> findAllPostmans() {
        return pqe.findAllPostmans();
    }

    /**
     *finds all stored letters
     * @return List<Letter>
     */
    public List<Letter> findAllLetters() {
        return lqe.findAllLetters();
    }

    /**
     finds postman with specified id
     * @param id
     * @return Postman
     */
    public Postman findPostman(int id) {
        return pqe.findPostman(id);
    }

    /**
     *finds leter with specidied id
     * @param id
     * @return Letter
     */
    public Letter findLetter(int id) {
        return lqe.findLetter(id);
    }

    /**
     *returns list of postman found by name
     * @param name
     * @return List<Postman>
     */
    public List<Postman> findPostmanByName(String name) {
        return pqe.findPostmanByName(name);
    }

    /**
     *returns list of postman found by surname
     * @param surname
     * @return List<Postman>
     */
    public List<Postman> findPostmanBySurname(String surname) {
        return pqe.findPostmanBySurname(surname);
    }

    /**
     *returns list of postmans declared in specified office
     * @param postOfficeArgument
     * @return List<Postman>
     */
    public List<Postman> findPostmanByOffice(PostOffice postOfficeArgument) {
        return pqe.findPostmanByOffice(postOfficeArgument);
    }

    /**
     *returns list with specified adressee address
     * @param address
     * @return List<Letter>
     */
    public List<Letter> findLetterByAddressee(String address) {
        return lqe.findLetterByAddressee(address);
    }

    /**
     *returns list of letters with specified sender
     * @param address
     * @return List<Letter>
     */
    public List<Letter> findLetterBySender(String address) {
        return lqe.findLetterBySender(address);
    }

    /**
     *returns list of letters carried by specified postman
     * @param postmanArgument
     * @return List<Letter>
     */
    public List<Letter> findLetterByPostman(Postman postmanArgument) {
        return lqe.findLetterByPostman(postmanArgument);
    }

}
