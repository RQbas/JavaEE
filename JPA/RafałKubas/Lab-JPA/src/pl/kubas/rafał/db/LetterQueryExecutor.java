/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import pl.kubas.rafał.model.Letter;
import pl.kubas.rafał.model.Postman;

/**
 * 2017-10-24 12:46:55
 *
 * @author rkubas Class representing set of possible methods to perform with
 * letter entity
 */
public class LetterQueryExecutor {

    private EntityManager em;

    LetterQueryExecutor(EntityManager em) {
        this.em = em;
    }
  /**
     *returns list with specified adressee address
     * @param address
     * @return List<Letter>
     */
    public List<Letter> findLetterByAddressee(String address) {
        return em.createNamedQuery("Letter.findByAddresseeAddress").setParameter("addresseeAddress", address).getResultList();
    }
  /**
     *returns list of letters with specified sender
     * @param address
     * @return List<Letter>
     */
    public List<Letter> findLetterBySender(String address) {
        return em.createNamedQuery("Letter.findBySenderAddress").setParameter("senderAddress", address).getResultList();
    }
/**
     *returns list of letters carried by specified postman
     * @param postmanArgument
     * @return List<Letter>
     */
    public List<Letter> findLetterByPostman(Postman postmanArgument) {
        Root<Letter> root;
        Expression<Postman> postman;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Letter> cq = cb.createQuery(Letter.class);

        root = cq.from(Letter.class);
        cq.select(root);
        postman = root.get("postman");
        cq.where(cb.equal(postman, postmanArgument));

        return em.createQuery(cq).getResultList();
    }
  /**
     *finds leter with specidied id
     * @param id
     * @return Letter
     */
    public Letter findLetter(int id) {
        return em.find(Letter.class, id);
    }
  /**
     *finds all stored letters
     * @return List<Letter>
     */
    public List<Letter> findAllLetters() {
        return em.createNamedQuery("Letter.findAll").getResultList(); //getSingleResult();
    }

}
