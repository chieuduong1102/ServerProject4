/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.controller;

import com.project4.hobookstore.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.project4.hobookstore.model.User;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Ratingfeedback;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DuongPH
 */
public class RatingfeedbackJpaController implements Serializable {

    public RatingfeedbackJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ratingfeedback ratingfeedback) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User username = ratingfeedback.getUsername();
            if (username != null) {
                username = em.getReference(username.getClass(), username.getUsername());
                ratingfeedback.setUsername(username);
            }
            Book bid = ratingfeedback.getBid();
            if (bid != null) {
                bid = em.getReference(bid.getClass(), bid.getBid());
                ratingfeedback.setBid(bid);
            }
            em.persist(ratingfeedback);
            if (username != null) {
                username.getRatingfeedbackList().add(ratingfeedback);
                username = em.merge(username);
            }
            if (bid != null) {
                bid.getRatingfeedbackList().add(ratingfeedback);
                bid = em.merge(bid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ratingfeedback ratingfeedback) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ratingfeedback persistentRatingfeedback = em.find(Ratingfeedback.class, ratingfeedback.getId());
            User usernameOld = persistentRatingfeedback.getUsername();
            User usernameNew = ratingfeedback.getUsername();
            Book bidOld = persistentRatingfeedback.getBid();
            Book bidNew = ratingfeedback.getBid();
            if (usernameNew != null) {
                usernameNew = em.getReference(usernameNew.getClass(), usernameNew.getUsername());
                ratingfeedback.setUsername(usernameNew);
            }
            if (bidNew != null) {
                bidNew = em.getReference(bidNew.getClass(), bidNew.getBid());
                ratingfeedback.setBid(bidNew);
            }
            ratingfeedback = em.merge(ratingfeedback);
            if (usernameOld != null && !usernameOld.equals(usernameNew)) {
                usernameOld.getRatingfeedbackList().remove(ratingfeedback);
                usernameOld = em.merge(usernameOld);
            }
            if (usernameNew != null && !usernameNew.equals(usernameOld)) {
                usernameNew.getRatingfeedbackList().add(ratingfeedback);
                usernameNew = em.merge(usernameNew);
            }
            if (bidOld != null && !bidOld.equals(bidNew)) {
                bidOld.getRatingfeedbackList().remove(ratingfeedback);
                bidOld = em.merge(bidOld);
            }
            if (bidNew != null && !bidNew.equals(bidOld)) {
                bidNew.getRatingfeedbackList().add(ratingfeedback);
                bidNew = em.merge(bidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ratingfeedback.getId();
                if (findRatingfeedback(id) == null) {
                    throw new NonexistentEntityException("The ratingfeedback with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ratingfeedback ratingfeedback;
            try {
                ratingfeedback = em.getReference(Ratingfeedback.class, id);
                ratingfeedback.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ratingfeedback with id " + id + " no longer exists.", enfe);
            }
            User username = ratingfeedback.getUsername();
            if (username != null) {
                username.getRatingfeedbackList().remove(ratingfeedback);
                username = em.merge(username);
            }
            Book bid = ratingfeedback.getBid();
            if (bid != null) {
                bid.getRatingfeedbackList().remove(ratingfeedback);
                bid = em.merge(bid);
            }
            em.remove(ratingfeedback);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ratingfeedback> findRatingfeedbackEntities() {
        return findRatingfeedbackEntities(true, -1, -1);
    }

    public List<Ratingfeedback> findRatingfeedbackEntities(int maxResults, int firstResult) {
        return findRatingfeedbackEntities(false, maxResults, firstResult);
    }

    private List<Ratingfeedback> findRatingfeedbackEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ratingfeedback.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Ratingfeedback> findRatingFeedbackByBId(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Ratingfeedback.findByBId").setParameter("bid", id).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Ratingfeedback findRatingfeedback(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ratingfeedback.class, id);
        } finally {
            em.close();
        }
    }

    public int getRatingfeedbackCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ratingfeedback> rt = cq.from(Ratingfeedback.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
