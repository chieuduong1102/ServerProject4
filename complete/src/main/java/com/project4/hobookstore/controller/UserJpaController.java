/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.controller;

import com.project4.hobookstore.controller.exceptions.IllegalOrphanException;
import com.project4.hobookstore.controller.exceptions.NonexistentEntityException;
import com.project4.hobookstore.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.project4.hobookstore.model.Ratingfeedback;
import java.util.ArrayList;
import java.util.List;
import com.project4.hobookstore.model.Order1;
import com.project4.hobookstore.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DuongPH
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) throws PreexistingEntityException, Exception {
        if (user.getRatingfeedbackList() == null) {
            user.setRatingfeedbackList(new ArrayList<Ratingfeedback>());
        }
        if (user.getOrder1List() == null) {
            user.setOrder1List(new ArrayList<Order1>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ratingfeedback> attachedRatingfeedbackList = new ArrayList<Ratingfeedback>();
            for (Ratingfeedback ratingfeedbackListRatingfeedbackToAttach : user.getRatingfeedbackList()) {
                ratingfeedbackListRatingfeedbackToAttach = em.getReference(ratingfeedbackListRatingfeedbackToAttach.getClass(), ratingfeedbackListRatingfeedbackToAttach.getId());
                attachedRatingfeedbackList.add(ratingfeedbackListRatingfeedbackToAttach);
            }
            user.setRatingfeedbackList(attachedRatingfeedbackList);
            List<Order1> attachedOrder1List = new ArrayList<Order1>();
            for (Order1 order1ListOrder1ToAttach : user.getOrder1List()) {
                order1ListOrder1ToAttach = em.getReference(order1ListOrder1ToAttach.getClass(), order1ListOrder1ToAttach.getOid());
                attachedOrder1List.add(order1ListOrder1ToAttach);
            }
            user.setOrder1List(attachedOrder1List);
            em.persist(user);
            for (Ratingfeedback ratingfeedbackListRatingfeedback : user.getRatingfeedbackList()) {
                User oldUsernameOfRatingfeedbackListRatingfeedback = ratingfeedbackListRatingfeedback.getUsername();
                ratingfeedbackListRatingfeedback.setUsername(user);
                ratingfeedbackListRatingfeedback = em.merge(ratingfeedbackListRatingfeedback);
                if (oldUsernameOfRatingfeedbackListRatingfeedback != null) {
                    oldUsernameOfRatingfeedbackListRatingfeedback.getRatingfeedbackList().remove(ratingfeedbackListRatingfeedback);
                    oldUsernameOfRatingfeedbackListRatingfeedback = em.merge(oldUsernameOfRatingfeedbackListRatingfeedback);
                }
            }
            for (Order1 order1ListOrder1 : user.getOrder1List()) {
                User oldUsernameOfOrder1ListOrder1 = order1ListOrder1.getUsername();
                order1ListOrder1.setUsername(user);
                order1ListOrder1 = em.merge(order1ListOrder1);
                if (oldUsernameOfOrder1ListOrder1 != null) {
                    oldUsernameOfOrder1ListOrder1.getOrder1List().remove(order1ListOrder1);
                    oldUsernameOfOrder1ListOrder1 = em.merge(oldUsernameOfOrder1ListOrder1);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUser(user.getUsername()) != null) {
                throw new PreexistingEntityException("User " + user + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getUsername());
            List<Ratingfeedback> ratingfeedbackListOld = persistentUser.getRatingfeedbackList();
            List<Ratingfeedback> ratingfeedbackListNew = user.getRatingfeedbackList();
            List<Order1> order1ListOld = persistentUser.getOrder1List();
            List<Order1> order1ListNew = user.getOrder1List();
            List<String> illegalOrphanMessages = null;
            for (Ratingfeedback ratingfeedbackListOldRatingfeedback : ratingfeedbackListOld) {
                if (!ratingfeedbackListNew.contains(ratingfeedbackListOldRatingfeedback)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ratingfeedback " + ratingfeedbackListOldRatingfeedback + " since its username field is not nullable.");
                }
            }
            for (Order1 order1ListOldOrder1 : order1ListOld) {
                if (!order1ListNew.contains(order1ListOldOrder1)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Order1 " + order1ListOldOrder1 + " since its username field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Ratingfeedback> attachedRatingfeedbackListNew = new ArrayList<Ratingfeedback>();
            for (Ratingfeedback ratingfeedbackListNewRatingfeedbackToAttach : ratingfeedbackListNew) {
                ratingfeedbackListNewRatingfeedbackToAttach = em.getReference(ratingfeedbackListNewRatingfeedbackToAttach.getClass(), ratingfeedbackListNewRatingfeedbackToAttach.getId());
                attachedRatingfeedbackListNew.add(ratingfeedbackListNewRatingfeedbackToAttach);
            }
            ratingfeedbackListNew = attachedRatingfeedbackListNew;
            user.setRatingfeedbackList(ratingfeedbackListNew);
            List<Order1> attachedOrder1ListNew = new ArrayList<Order1>();
            for (Order1 order1ListNewOrder1ToAttach : order1ListNew) {
                order1ListNewOrder1ToAttach = em.getReference(order1ListNewOrder1ToAttach.getClass(), order1ListNewOrder1ToAttach.getOid());
                attachedOrder1ListNew.add(order1ListNewOrder1ToAttach);
            }
            order1ListNew = attachedOrder1ListNew;
            user.setOrder1List(order1ListNew);
            user = em.merge(user);
            for (Ratingfeedback ratingfeedbackListNewRatingfeedback : ratingfeedbackListNew) {
                if (!ratingfeedbackListOld.contains(ratingfeedbackListNewRatingfeedback)) {
                    User oldUsernameOfRatingfeedbackListNewRatingfeedback = ratingfeedbackListNewRatingfeedback.getUsername();
                    ratingfeedbackListNewRatingfeedback.setUsername(user);
                    ratingfeedbackListNewRatingfeedback = em.merge(ratingfeedbackListNewRatingfeedback);
                    if (oldUsernameOfRatingfeedbackListNewRatingfeedback != null && !oldUsernameOfRatingfeedbackListNewRatingfeedback.equals(user)) {
                        oldUsernameOfRatingfeedbackListNewRatingfeedback.getRatingfeedbackList().remove(ratingfeedbackListNewRatingfeedback);
                        oldUsernameOfRatingfeedbackListNewRatingfeedback = em.merge(oldUsernameOfRatingfeedbackListNewRatingfeedback);
                    }
                }
            }
            for (Order1 order1ListNewOrder1 : order1ListNew) {
                if (!order1ListOld.contains(order1ListNewOrder1)) {
                    User oldUsernameOfOrder1ListNewOrder1 = order1ListNewOrder1.getUsername();
                    order1ListNewOrder1.setUsername(user);
                    order1ListNewOrder1 = em.merge(order1ListNewOrder1);
                    if (oldUsernameOfOrder1ListNewOrder1 != null && !oldUsernameOfOrder1ListNewOrder1.equals(user)) {
                        oldUsernameOfOrder1ListNewOrder1.getOrder1List().remove(order1ListNewOrder1);
                        oldUsernameOfOrder1ListNewOrder1 = em.merge(oldUsernameOfOrder1ListNewOrder1);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = user.getUsername();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getUsername();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ratingfeedback> ratingfeedbackListOrphanCheck = user.getRatingfeedbackList();
            for (Ratingfeedback ratingfeedbackListOrphanCheckRatingfeedback : ratingfeedbackListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Ratingfeedback " + ratingfeedbackListOrphanCheckRatingfeedback + " in its ratingfeedbackList field has a non-nullable username field.");
            }
            List<Order1> order1ListOrphanCheck = user.getOrder1List();
            for (Order1 order1ListOrphanCheckOrder1 : order1ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Order1 " + order1ListOrphanCheckOrder1 + " in its order1List field has a non-nullable username field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public User findUserByName(String username) {
        EntityManager em = null;
        User user = null;
        try {
            em = getEntityManager();
            Query query = em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username ");
            query.setParameter("username", username);
            user = (User) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return user;
    }
}
