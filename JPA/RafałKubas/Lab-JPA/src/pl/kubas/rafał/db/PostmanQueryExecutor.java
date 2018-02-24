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
import pl.kubas.rafał.model.PostOffice;
import pl.kubas.rafał.model.Postman;

/**
 * 2017-10-24 12:46:43
 *
 * @author rkubas Class representing set of possible methods to perform with
 * letter entity
 */
public class PostmanQueryExecutor {

    private EntityManager em;

    PostmanQueryExecutor(EntityManager em) {
        this.em = em;
    }
/**
     *returns list of postman found by name
     * @param name
     * @return List<Postman>
     */
    public List<Postman> findPostmanByName(String name) {
        return em.createNamedQuery("Postman.findByName").setParameter("name", name).getResultList();
    }
 /**
     *returns list of postman found by surname
     * @param surname
     * @return List<Postman>
     */
    public List<Postman> findPostmanBySurname(String surname) {
        return em.createNamedQuery("Postman.findBySurname").setParameter("surname", surname).getResultList();
    }
  /**
     *returns list of postmans declared in specified office
     * @param postOfficeArgument
     * @return List<Postman>
     */
    public List<Postman> findPostmanByOffice(PostOffice postOfficeArgument) {
        Root<Postman> root;
        Expression<PostOffice> postOffice;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Postman> cq = cb.createQuery(Postman.class);

        root = cq.from(Postman.class);
        cq.select(root);
        postOffice = root.get("postOffice");
        cq.where(cb.equal(postOffice, postOfficeArgument));

        return em.createQuery(cq).getResultList();
    }
/**
     finds postman with specified id
     * @param id
     * @return Postman
     */
    public Postman findPostman(int id) {
        return em.find(Postman.class, id);
    }
 /**
     *finds all stored postmans
     * @return List<Postman>
     */
    public List<Postman> findAllPostmans() {
        return em.createNamedQuery("Postman.findAll").getResultList(); //getSingleResult();
    }

}
