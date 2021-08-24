/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.controller;

import com.example.restservice.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.example.restservice.model.Order1;
import com.example.restservice.model.Book;
import com.example.restservice.model.Orderdetail;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DuongPH
 */
public class OrderdetailJpaController implements Serializable {

    public OrderdetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orderdetail orderdetail) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Order1 oid = orderdetail.getOid();
            if (oid != null) {
                oid = em.getReference(oid.getClass(), oid.getOid());
                orderdetail.setOid(oid);
            }
            Book bid = orderdetail.getBid();
            if (bid != null) {
                bid = em.getReference(bid.getClass(), bid.getBid());
                orderdetail.setBid(bid);
            }
            em.persist(orderdetail);
            if (oid != null) {
                oid.getOrderdetailList().add(orderdetail);
                oid = em.merge(oid);
            }
            if (bid != null) {
                bid.getOrderdetailList().add(orderdetail);
                bid = em.merge(bid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orderdetail orderdetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orderdetail persistentOrderdetail = em.find(Orderdetail.class, orderdetail.getId());
            Order1 oidOld = persistentOrderdetail.getOid();
            Order1 oidNew = orderdetail.getOid();
            Book bidOld = persistentOrderdetail.getBid();
            Book bidNew = orderdetail.getBid();
            if (oidNew != null) {
                oidNew = em.getReference(oidNew.getClass(), oidNew.getOid());
                orderdetail.setOid(oidNew);
            }
            if (bidNew != null) {
                bidNew = em.getReference(bidNew.getClass(), bidNew.getBid());
                orderdetail.setBid(bidNew);
            }
            orderdetail = em.merge(orderdetail);
            if (oidOld != null && !oidOld.equals(oidNew)) {
                oidOld.getOrderdetailList().remove(orderdetail);
                oidOld = em.merge(oidOld);
            }
            if (oidNew != null && !oidNew.equals(oidOld)) {
                oidNew.getOrderdetailList().add(orderdetail);
                oidNew = em.merge(oidNew);
            }
            if (bidOld != null && !bidOld.equals(bidNew)) {
                bidOld.getOrderdetailList().remove(orderdetail);
                bidOld = em.merge(bidOld);
            }
            if (bidNew != null && !bidNew.equals(bidOld)) {
                bidNew.getOrderdetailList().add(orderdetail);
                bidNew = em.merge(bidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orderdetail.getId();
                if (findOrderdetail(id) == null) {
                    throw new NonexistentEntityException("The orderdetail with id " + id + " no longer exists.");
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
            Orderdetail orderdetail;
            try {
                orderdetail = em.getReference(Orderdetail.class, id);
                orderdetail.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderdetail with id " + id + " no longer exists.", enfe);
            }
            Order1 oid = orderdetail.getOid();
            if (oid != null) {
                oid.getOrderdetailList().remove(orderdetail);
                oid = em.merge(oid);
            }
            Book bid = orderdetail.getBid();
            if (bid != null) {
                bid.getOrderdetailList().remove(orderdetail);
                bid = em.merge(bid);
            }
            em.remove(orderdetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orderdetail> findOrderdetailEntities() {
        return findOrderdetailEntities(true, -1, -1);
    }

    public List<Orderdetail> findOrderdetailEntities(int maxResults, int firstResult) {
        return findOrderdetailEntities(false, maxResults, firstResult);
    }

    private List<Orderdetail> findOrderdetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orderdetail.class));
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

    public Orderdetail findOrderdetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orderdetail.class, id);
        } finally {
            em.close();
        }
    }

    public List<Orderdetail> findOrderDetailByBId(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Orderdetail.findByBId").setParameter("bid", id).getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getOrderdetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orderdetail> rt = cq.from(Orderdetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
