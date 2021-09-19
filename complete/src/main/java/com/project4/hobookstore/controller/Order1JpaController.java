/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.controller;

import com.project4.hobookstore.controller.exceptions.IllegalOrphanException;
import com.project4.hobookstore.controller.exceptions.NonexistentEntityException;
import com.project4.hobookstore.model.Order1;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.project4.hobookstore.model.User;
import com.project4.hobookstore.model.Orderdetail;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DuongPH
 */
public class Order1JpaController implements Serializable {

    public Order1JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Order1 order1) {
        if (order1.getOrderdetailList() == null) {
            order1.setOrderdetailList(new ArrayList<Orderdetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User username = order1.getUsername();
            if (username != null) {
                username = em.getReference(username.getClass(), username.getUsername());
                order1.setUsername(username);
            }
            List<Orderdetail> attachedOrderdetailList = new ArrayList<Orderdetail>();
            for (Orderdetail orderdetailListOrderdetailToAttach : order1.getOrderdetailList()) {
                orderdetailListOrderdetailToAttach = em.getReference(orderdetailListOrderdetailToAttach.getClass(), orderdetailListOrderdetailToAttach.getId());
                attachedOrderdetailList.add(orderdetailListOrderdetailToAttach);
            }
            order1.setOrderdetailList(attachedOrderdetailList);
            em.persist(order1);
            if (username != null) {
                username.getOrder1List().add(order1);
                username = em.merge(username);
            }
            for (Orderdetail orderdetailListOrderdetail : order1.getOrderdetailList()) {
                Order1 oldOidOfOrderdetailListOrderdetail = orderdetailListOrderdetail.getOid();
                orderdetailListOrderdetail.setOid(order1);
                orderdetailListOrderdetail = em.merge(orderdetailListOrderdetail);
                if (oldOidOfOrderdetailListOrderdetail != null) {
                    oldOidOfOrderdetailListOrderdetail.getOrderdetailList().remove(orderdetailListOrderdetail);
                    oldOidOfOrderdetailListOrderdetail = em.merge(oldOidOfOrderdetailListOrderdetail);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Order1 order1) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Order1 persistentOrder1 = em.find(Order1.class, order1.getOid());
            User usernameOld = persistentOrder1.getUsername();
            User usernameNew = order1.getUsername();
            List<Orderdetail> orderdetailListOld = persistentOrder1.getOrderdetailList();
            List<Orderdetail> orderdetailListNew = order1.getOrderdetailList();
            List<String> illegalOrphanMessages = null;
            for (Orderdetail orderdetailListOldOrderdetail : orderdetailListOld) {
                if (!orderdetailListNew.contains(orderdetailListOldOrderdetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Orderdetail " + orderdetailListOldOrderdetail + " since its oid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usernameNew != null) {
                usernameNew = em.getReference(usernameNew.getClass(), usernameNew.getUsername());
                order1.setUsername(usernameNew);
            }
            List<Orderdetail> attachedOrderdetailListNew = new ArrayList<Orderdetail>();
            for (Orderdetail orderdetailListNewOrderdetailToAttach : orderdetailListNew) {
                orderdetailListNewOrderdetailToAttach = em.getReference(orderdetailListNewOrderdetailToAttach.getClass(), orderdetailListNewOrderdetailToAttach.getId());
                attachedOrderdetailListNew.add(orderdetailListNewOrderdetailToAttach);
            }
            orderdetailListNew = attachedOrderdetailListNew;
            order1.setOrderdetailList(orderdetailListNew);
            order1 = em.merge(order1);
            if (usernameOld != null && !usernameOld.equals(usernameNew)) {
                usernameOld.getOrder1List().remove(order1);
                usernameOld = em.merge(usernameOld);
            }
            if (usernameNew != null && !usernameNew.equals(usernameOld)) {
                usernameNew.getOrder1List().add(order1);
                usernameNew = em.merge(usernameNew);
            }
            for (Orderdetail orderdetailListNewOrderdetail : orderdetailListNew) {
                if (!orderdetailListOld.contains(orderdetailListNewOrderdetail)) {
                    Order1 oldOidOfOrderdetailListNewOrderdetail = orderdetailListNewOrderdetail.getOid();
                    orderdetailListNewOrderdetail.setOid(order1);
                    orderdetailListNewOrderdetail = em.merge(orderdetailListNewOrderdetail);
                    if (oldOidOfOrderdetailListNewOrderdetail != null && !oldOidOfOrderdetailListNewOrderdetail.equals(order1)) {
                        oldOidOfOrderdetailListNewOrderdetail.getOrderdetailList().remove(orderdetailListNewOrderdetail);
                        oldOidOfOrderdetailListNewOrderdetail = em.merge(oldOidOfOrderdetailListNewOrderdetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = order1.getOid();
                if (findOrder1(id) == null) {
                    throw new NonexistentEntityException("The order1 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Order1 order1;
            try {
                order1 = em.getReference(Order1.class, id);
                order1.getOid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The order1 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Orderdetail> orderdetailListOrphanCheck = order1.getOrderdetailList();
            for (Orderdetail orderdetailListOrphanCheckOrderdetail : orderdetailListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Order1 (" + order1 + ") cannot be destroyed since the Orderdetail " + orderdetailListOrphanCheckOrderdetail + " in its orderdetailList field has a non-nullable oid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            User username = order1.getUsername();
            if (username != null) {
                username.getOrder1List().remove(order1);
                username = em.merge(username);
            }
            em.remove(order1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Order1> findOrder1Entities() {
        return findOrder1Entities(true, -1, -1);
    }

    public List<Order1> findOrder1Entities(int maxResults, int firstResult) {
        return findOrder1Entities(false, maxResults, firstResult);
    }

    private List<Order1> findOrder1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Order1.class));
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

    public Order1 findOrder1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Order1.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrder1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Order1> rt = cq.from(Order1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void createNewOrder(Order1 order) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public int updateOrderStatus(Integer oid, Integer status) {
        EntityManager em = null;
        int rowsUpdated;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("UPDATE Order1 o SET o.status = :status WHERE o.oid = :oid");
            query.setParameter("status", status);
            query.setParameter("oid", oid);
            rowsUpdated = query.executeUpdate();
            em.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            rowsUpdated = 0;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return rowsUpdated;
    }

    public List<Order1> findOrderByUserName(User userName) {
        if (userName == null) {
            return null;
        }
        EntityManager em = null;
        List<Order1> list = null;
        try {
            em = getEntityManager();
            Query query = em.createQuery("SELECT o FROM Order1 o WHERE o.username = :username");
            query.setParameter("username", userName);
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return list;
    }
}
