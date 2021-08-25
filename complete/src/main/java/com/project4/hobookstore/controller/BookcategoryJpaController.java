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
import com.project4.hobookstore.model.Category;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Bookcategory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DuongPH
 */
public class BookcategoryJpaController implements Serializable {

    public BookcategoryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bookcategory bookcategory) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category cid = bookcategory.getCid();
            if (cid != null) {
                cid = em.getReference(cid.getClass(), cid.getCid());
                bookcategory.setCid(cid);
            }
            Book bid = bookcategory.getBid();
            if (bid != null) {
                bid = em.getReference(bid.getClass(), bid.getBid());
                bookcategory.setBid(bid);
            }
            em.persist(bookcategory);
            if (cid != null) {
                cid.getBookcategoryList().add(bookcategory);
                cid = em.merge(cid);
            }
            if (bid != null) {
                bid.getBookcategoryList().add(bookcategory);
                bid = em.merge(bid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bookcategory bookcategory) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bookcategory persistentBookcategory = em.find(Bookcategory.class, bookcategory.getId());
            Category cidOld = persistentBookcategory.getCid();
            Category cidNew = bookcategory.getCid();
            Book bidOld = persistentBookcategory.getBid();
            Book bidNew = bookcategory.getBid();
            if (cidNew != null) {
                cidNew = em.getReference(cidNew.getClass(), cidNew.getCid());
                bookcategory.setCid(cidNew);
            }
            if (bidNew != null) {
                bidNew = em.getReference(bidNew.getClass(), bidNew.getBid());
                bookcategory.setBid(bidNew);
            }
            bookcategory = em.merge(bookcategory);
            if (cidOld != null && !cidOld.equals(cidNew)) {
                cidOld.getBookcategoryList().remove(bookcategory);
                cidOld = em.merge(cidOld);
            }
            if (cidNew != null && !cidNew.equals(cidOld)) {
                cidNew.getBookcategoryList().add(bookcategory);
                cidNew = em.merge(cidNew);
            }
            if (bidOld != null && !bidOld.equals(bidNew)) {
                bidOld.getBookcategoryList().remove(bookcategory);
                bidOld = em.merge(bidOld);
            }
            if (bidNew != null && !bidNew.equals(bidOld)) {
                bidNew.getBookcategoryList().add(bookcategory);
                bidNew = em.merge(bidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bookcategory.getId();
                if (findBookcategory(id) == null) {
                    throw new NonexistentEntityException("The bookcategory with id " + id + " no longer exists.");
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
            Bookcategory bookcategory;
            try {
                bookcategory = em.getReference(Bookcategory.class, id);
                bookcategory.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bookcategory with id " + id + " no longer exists.", enfe);
            }
            Category cid = bookcategory.getCid();
            if (cid != null) {
                cid.getBookcategoryList().remove(bookcategory);
                cid = em.merge(cid);
            }
            Book bid = bookcategory.getBid();
            if (bid != null) {
                bid.getBookcategoryList().remove(bookcategory);
                bid = em.merge(bid);
            }
            em.remove(bookcategory);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bookcategory> findBookcategoryEntities() {
        return findBookcategoryEntities(true, -1, -1);
    }

    public List<Bookcategory> findBookcategoryEntities(int maxResults, int firstResult) {
        return findBookcategoryEntities(false, maxResults, firstResult);
    }

    private List<Bookcategory> findBookcategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bookcategory.class));
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

    public Bookcategory findBookcategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bookcategory.class, id);
        } finally {
            em.close();
        }
    }

    
    public List<Bookcategory> findBookCategoryByBId(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Bookcategory.findByBId").setParameter("bid", id).getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getBookcategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bookcategory> rt = cq.from(Bookcategory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
