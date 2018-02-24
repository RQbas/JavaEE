/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.operations;

import java.util.List;
import javax.ejb.EJB;
import javax.persistence.criteria.Expression;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.kubas.rafał.model.Letter;
import pl.kubas.rafał.model.Postman;

/**
 * @author rkubas
 * @date 2017-11-21
 * @version 1.0
 */
@Stateless(mappedName = "readLetter")
public class ReadLetterOperation implements ReadLetterOperationLocal {

    /**
     * Entity manager delivering database operations
     */
    @PersistenceContext
    private EntityManager em;
    /**
     * Bean delivering functionallity for finding postman entities
     */
    @EJB
    private ReadPostmanOperationLocal rpo;

    /**
     * Method for finding all letters persisted
     *
     * @return List of letter objects
     */
    @Override
    public List<Letter> findAllLetters() {
        return em.createNamedQuery("Letter.findAll").getResultList(); //getSingleResult();
    }

    /**
     * Method for finding letter with given id
     *
     * @param id Id of the wanted letter
     * @return Letter object with declared id
     */
    @Override
    public Letter findLetter(int id) {
        return em.find(Letter.class, id);
    }

    /**
     * Method for finding letter with given addressee address
     *
     * @param address Addressee address of wanted letter
     * @return List of letters with addressee address obtained as a parameter
     */
    @Override
    public List<Letter> findLetterByAddresseeAddress(String address) {
        return em.createNamedQuery("Letter.findByAddresseeAddress").setParameter("addresseeAddress", address).getResultList();
    }

    /**
     * Method for finding letter with given sender address
     *
     * @param address Sender address of wanted letter
     * @return List of letters with sender address obtained as a parameter
     */
    @Override
    public List<Letter> findLetterBySender(String address) {
        return em.createNamedQuery("Letter.findBySenderAddress").setParameter("senderAddress", address).getResultList();
    }

    /**
     * Method for finding letters carried by postman
     *
     * @param id Id of the postman which carries letters
     * @return List of leters carried by postman with given id
     */
    @Override
    public List<Letter> findLetterByPostman(int id) {
        Root<Letter> root;
        Expression<Postman> postman;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Letter> cq = cb.createQuery(Letter.class);

        root = cq.from(Letter.class);
        cq.select(root);
        postman = root.get("postman");
        Postman postmanArgument = rpo.findPostman(id);
        cq.where(cb.equal(postman, postmanArgument));

        return em.createQuery(cq).getResultList();
    }

}
