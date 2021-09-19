/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.controller;

import com.project4.hobookstore.controller.exceptions.IllegalOrphanException;
import com.project4.hobookstore.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.project4.hobookstore.model.Bookcategory;
import com.project4.hobookstore.model.Category;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DuongPH
 */
public class CategoryJpaController implements Serializable {

    public CategoryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Category category) {
        if (category.getBookcategoryList() == null) {
            category.setBookcategoryList(new ArrayList<Bookcategory>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Bookcategory> attachedBookcategoryList = new ArrayList<Bookcategory>();
            for (Bookcategory bookcategoryListBookcategoryToAttach : category.getBookcategoryList()) {
                bookcategoryListBookcategoryToAttach = em.getReference(bookcategoryListBookcategoryToAttach.getClass(), bookcategoryListBookcategoryToAttach.getId());
                attachedBookcategoryList.add(bookcategoryListBookcategoryToAttach);
            }
            category.setBookcategoryList(attachedBookcategoryList);
            em.persist(category);
            for (Bookcategory bookcategoryListBookcategory : category.getBookcategoryList()) {
                Category oldCidOfBookcategoryListBookcategory = bookcategoryListBookcategory.getCid();
                bookcategoryListBookcategory.setCid(category);
                bookcategoryListBookcategory = em.merge(bookcategoryListBookcategory);
                if (oldCidOfBookcategoryListBookcategory != null) {
                    oldCidOfBookcategoryListBookcategory.getBookcategoryList().remove(bookcategoryListBookcategory);
                    oldCidOfBookcategoryListBookcategory = em.merge(oldCidOfBookcategoryListBookcategory);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Category category) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category persistentCategory = em.find(Category.class, category.getCid());
            List<Bookcategory> bookcategoryListOld = persistentCategory.getBookcategoryList();
            List<Bookcategory> bookcategoryListNew = category.getBookcategoryList();
            List<String> illegalOrphanMessages = null;
            for (Bookcategory bookcategoryListOldBookcategory : bookcategoryListOld) {
                if (!bookcategoryListNew.contains(bookcategoryListOldBookcategory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bookcategory " + bookcategoryListOldBookcategory + " since its cid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Bookcategory> attachedBookcategoryListNew = new ArrayList<Bookcategory>();
            for (Bookcategory bookcategoryListNewBookcategoryToAttach : bookcategoryListNew) {
                bookcategoryListNewBookcategoryToAttach = em.getReference(bookcategoryListNewBookcategoryToAttach.getClass(), bookcategoryListNewBookcategoryToAttach.getId());
                attachedBookcategoryListNew.add(bookcategoryListNewBookcategoryToAttach);
            }
            bookcategoryListNew = attachedBookcategoryListNew;
            category.setBookcategoryList(bookcategoryListNew);
            category = em.merge(category);
            for (Bookcategory bookcategoryListNewBookcategory : bookcategoryListNew) {
                if (!bookcategoryListOld.contains(bookcategoryListNewBookcategory)) {
                    Category oldCidOfBookcategoryListNewBookcategory = bookcategoryListNewBookcategory.getCid();
                    bookcategoryListNewBookcategory.setCid(category);
                    bookcategoryListNewBookcategory = em.merge(bookcategoryListNewBookcategory);
                    if (oldCidOfBookcategoryListNewBookcategory != null && !oldCidOfBookcategoryListNewBookcategory.equals(category)) {
                        oldCidOfBookcategoryListNewBookcategory.getBookcategoryList().remove(bookcategoryListNewBookcategory);
                        oldCidOfBookcategoryListNewBookcategory = em.merge(oldCidOfBookcategoryListNewBookcategory);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = category.getCid();
                if (findCategory(id) == null) {
                    throw new NonexistentEntityException("The category with id " + id + " no longer exists.");
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
            Category category;
            try {
                category = em.getReference(Category.class, id);
                category.getCid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The category with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Bookcategory> bookcategoryListOrphanCheck = category.getBookcategoryList();
            for (Bookcategory bookcategoryListOrphanCheckBookcategory : bookcategoryListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Category (" + category + ") cannot be destroyed since the Bookcategory " + bookcategoryListOrphanCheckBookcategory + " in its bookcategoryList field has a non-nullable cid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(category);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Category> findCategoryEntities() {
        return findCategoryEntities(true, -1, -1);
    }

    public List<Category> findCategoryEntities(int maxResults, int firstResult) {
        return findCategoryEntities(false, maxResults, firstResult);
    }

    private List<Category> findCategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Category.class));
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

    public Category findCategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }
    
    public Category findIdOfCategoryByName(String name) {
        EntityManager em = getEntityManager();
        try {
            return (Category) em.createNamedQuery("Category.findByCategoryName").setParameter("categoryName", name).getSingleResult();
        } catch (Exception e) {
            return  null;
        } finally {
            em.close();
        }
    }

    public int getCategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Category> rt = cq.from(Category.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Category> getCategoryList() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery(Category.class);
            Root<Category> root = cq.from(Category.class);
            cq.multiselect(root.get("cid"), root.get("categoryName"));

            Query q = em.createQuery(cq);

            return (List<Category>) q.getResultList();
        } finally {
            em.close();
        }
    }

    public Category checkCategoryExist(String name) {
        EntityManager em = getEntityManager();
        try {
            return (Category) em.createNamedQuery("Category.findByCategoryName")
                    .setParameter("categoryName", name)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public int editCategory(Category category) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Category.updateCategory")
                    .setParameter("categoryName", category.getCategoryName())
                    .setParameter("cid", category.getCid())
                    .executeUpdate();
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            em.close();
        }
    }

    public int deleteCategory(Integer categoryId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            int result = em.createNamedQuery("Category.deleteCategory")
                    .setParameter("cid", categoryId)
                    .executeUpdate();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            em.close();
        }
    }
}
