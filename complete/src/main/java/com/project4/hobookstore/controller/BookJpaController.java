/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.controller;

import com.project4.hobookstore.controller.exceptions.IllegalOrphanException;
import com.project4.hobookstore.controller.exceptions.NonexistentEntityException;
import com.project4.hobookstore.model.Book;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.project4.hobookstore.model.Ratingfeedback;
import java.util.ArrayList;
import java.util.List;
import com.project4.hobookstore.model.Image;
import com.project4.hobookstore.model.Bookcategory;
import com.project4.hobookstore.model.Orderdetail;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DuongPH
 */
public class BookJpaController implements Serializable {

    public BookJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createNewBook(Book book) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void create(Book book) {
        if (book.getRatingfeedbackList() == null) {
            book.setRatingfeedbackList(new ArrayList<Ratingfeedback>());
        }
        if (book.getImageList() == null) {
            book.setImageList(new ArrayList<Image>());
        }
        if (book.getBookcategoryList() == null) {
            book.setBookcategoryList(new ArrayList<Bookcategory>());
        }
        if (book.getOrderdetailList() == null) {
            book.setOrderdetailList(new ArrayList<Orderdetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ratingfeedback> attachedRatingfeedbackList = new ArrayList<Ratingfeedback>();
            for (Ratingfeedback ratingfeedbackListRatingfeedbackToAttach : book.getRatingfeedbackList()) {
                ratingfeedbackListRatingfeedbackToAttach = em.getReference(ratingfeedbackListRatingfeedbackToAttach.getClass(), ratingfeedbackListRatingfeedbackToAttach.getId());
                attachedRatingfeedbackList.add(ratingfeedbackListRatingfeedbackToAttach);
            }
            book.setRatingfeedbackList(attachedRatingfeedbackList);
            List<Image> attachedImageList = new ArrayList<Image>();
            for (Image imageListImageToAttach : book.getImageList()) {
                imageListImageToAttach = em.getReference(imageListImageToAttach.getClass(), imageListImageToAttach.getIid());
                attachedImageList.add(imageListImageToAttach);
            }
            book.setImageList(attachedImageList);
            List<Bookcategory> attachedBookcategoryList = new ArrayList<Bookcategory>();
            for (Bookcategory bookcategoryListBookcategoryToAttach : book.getBookcategoryList()) {
                bookcategoryListBookcategoryToAttach = em.getReference(bookcategoryListBookcategoryToAttach.getClass(), bookcategoryListBookcategoryToAttach.getId());
                attachedBookcategoryList.add(bookcategoryListBookcategoryToAttach);
            }
            book.setBookcategoryList(attachedBookcategoryList);
            List<Orderdetail> attachedOrderdetailList = new ArrayList<Orderdetail>();
            for (Orderdetail orderdetailListOrderdetailToAttach : book.getOrderdetailList()) {
                orderdetailListOrderdetailToAttach = em.getReference(orderdetailListOrderdetailToAttach.getClass(), orderdetailListOrderdetailToAttach.getId());
                attachedOrderdetailList.add(orderdetailListOrderdetailToAttach);
            }
            book.setOrderdetailList(attachedOrderdetailList);
            em.persist(book);
            for (Ratingfeedback ratingfeedbackListRatingfeedback : book.getRatingfeedbackList()) {
                Book oldBidOfRatingfeedbackListRatingfeedback = ratingfeedbackListRatingfeedback.getBid();
                ratingfeedbackListRatingfeedback.setBid(book);
                ratingfeedbackListRatingfeedback = em.merge(ratingfeedbackListRatingfeedback);
                if (oldBidOfRatingfeedbackListRatingfeedback != null) {
                    oldBidOfRatingfeedbackListRatingfeedback.getRatingfeedbackList().remove(ratingfeedbackListRatingfeedback);
                    oldBidOfRatingfeedbackListRatingfeedback = em.merge(oldBidOfRatingfeedbackListRatingfeedback);
                }
            }
            for (Image imageListImage : book.getImageList()) {
                Book oldBidOfImageListImage = imageListImage.getBid();
                imageListImage.setBid(book);
                imageListImage = em.merge(imageListImage);
                if (oldBidOfImageListImage != null) {
                    oldBidOfImageListImage.getImageList().remove(imageListImage);
                    oldBidOfImageListImage = em.merge(oldBidOfImageListImage);
                }
            }
            for (Bookcategory bookcategoryListBookcategory : book.getBookcategoryList()) {
                Book oldBidOfBookcategoryListBookcategory = bookcategoryListBookcategory.getBid();
                bookcategoryListBookcategory.setBid(book);
                bookcategoryListBookcategory = em.merge(bookcategoryListBookcategory);
                if (oldBidOfBookcategoryListBookcategory != null) {
                    oldBidOfBookcategoryListBookcategory.getBookcategoryList().remove(bookcategoryListBookcategory);
                    oldBidOfBookcategoryListBookcategory = em.merge(oldBidOfBookcategoryListBookcategory);
                }
            }
            for (Orderdetail orderdetailListOrderdetail : book.getOrderdetailList()) {
                Book oldBidOfOrderdetailListOrderdetail = orderdetailListOrderdetail.getBid();
                orderdetailListOrderdetail.setBid(book);
                orderdetailListOrderdetail = em.merge(orderdetailListOrderdetail);
                if (oldBidOfOrderdetailListOrderdetail != null) {
                    oldBidOfOrderdetailListOrderdetail.getOrderdetailList().remove(orderdetailListOrderdetail);
                    oldBidOfOrderdetailListOrderdetail = em.merge(oldBidOfOrderdetailListOrderdetail);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Book book) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Book persistentBook = em.find(Book.class, book.getBid());
            List<Ratingfeedback> ratingfeedbackListOld = persistentBook.getRatingfeedbackList();
            List<Ratingfeedback> ratingfeedbackListNew = book.getRatingfeedbackList();
            List<Image> imageListOld = persistentBook.getImageList();
            List<Image> imageListNew = book.getImageList();
            List<Bookcategory> bookcategoryListOld = persistentBook.getBookcategoryList();
            List<Bookcategory> bookcategoryListNew = book.getBookcategoryList();
            List<Orderdetail> orderdetailListOld = persistentBook.getOrderdetailList();
            List<Orderdetail> orderdetailListNew = book.getOrderdetailList();
            List<String> illegalOrphanMessages = null;
            for (Ratingfeedback ratingfeedbackListOldRatingfeedback : ratingfeedbackListOld) {
                if (!ratingfeedbackListNew.contains(ratingfeedbackListOldRatingfeedback)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ratingfeedback " + ratingfeedbackListOldRatingfeedback + " since its bid field is not nullable.");
                }
            }
            for (Image imageListOldImage : imageListOld) {
                if (!imageListNew.contains(imageListOldImage)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Image " + imageListOldImage + " since its bid field is not nullable.");
                }
            }
            for (Bookcategory bookcategoryListOldBookcategory : bookcategoryListOld) {
                if (!bookcategoryListNew.contains(bookcategoryListOldBookcategory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bookcategory " + bookcategoryListOldBookcategory + " since its bid field is not nullable.");
                }
            }
            for (Orderdetail orderdetailListOldOrderdetail : orderdetailListOld) {
                if (!orderdetailListNew.contains(orderdetailListOldOrderdetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Orderdetail " + orderdetailListOldOrderdetail + " since its bid field is not nullable.");
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
            book.setRatingfeedbackList(ratingfeedbackListNew);
            List<Image> attachedImageListNew = new ArrayList<Image>();
            for (Image imageListNewImageToAttach : imageListNew) {
                imageListNewImageToAttach = em.getReference(imageListNewImageToAttach.getClass(), imageListNewImageToAttach.getIid());
                attachedImageListNew.add(imageListNewImageToAttach);
            }
            imageListNew = attachedImageListNew;
            book.setImageList(imageListNew);
            List<Bookcategory> attachedBookcategoryListNew = new ArrayList<Bookcategory>();
            for (Bookcategory bookcategoryListNewBookcategoryToAttach : bookcategoryListNew) {
                bookcategoryListNewBookcategoryToAttach = em.getReference(bookcategoryListNewBookcategoryToAttach.getClass(), bookcategoryListNewBookcategoryToAttach.getId());
                attachedBookcategoryListNew.add(bookcategoryListNewBookcategoryToAttach);
            }
            bookcategoryListNew = attachedBookcategoryListNew;
            book.setBookcategoryList(bookcategoryListNew);
            List<Orderdetail> attachedOrderdetailListNew = new ArrayList<Orderdetail>();
            for (Orderdetail orderdetailListNewOrderdetailToAttach : orderdetailListNew) {
                orderdetailListNewOrderdetailToAttach = em.getReference(orderdetailListNewOrderdetailToAttach.getClass(), orderdetailListNewOrderdetailToAttach.getId());
                attachedOrderdetailListNew.add(orderdetailListNewOrderdetailToAttach);
            }
            orderdetailListNew = attachedOrderdetailListNew;
            book.setOrderdetailList(orderdetailListNew);
            book = em.merge(book);
            for (Ratingfeedback ratingfeedbackListNewRatingfeedback : ratingfeedbackListNew) {
                if (!ratingfeedbackListOld.contains(ratingfeedbackListNewRatingfeedback)) {
                    Book oldBidOfRatingfeedbackListNewRatingfeedback = ratingfeedbackListNewRatingfeedback.getBid();
                    ratingfeedbackListNewRatingfeedback.setBid(book);
                    ratingfeedbackListNewRatingfeedback = em.merge(ratingfeedbackListNewRatingfeedback);
                    if (oldBidOfRatingfeedbackListNewRatingfeedback != null && !oldBidOfRatingfeedbackListNewRatingfeedback.equals(book)) {
                        oldBidOfRatingfeedbackListNewRatingfeedback.getRatingfeedbackList().remove(ratingfeedbackListNewRatingfeedback);
                        oldBidOfRatingfeedbackListNewRatingfeedback = em.merge(oldBidOfRatingfeedbackListNewRatingfeedback);
                    }
                }
            }
            for (Image imageListNewImage : imageListNew) {
                if (!imageListOld.contains(imageListNewImage)) {
                    Book oldBidOfImageListNewImage = imageListNewImage.getBid();
                    imageListNewImage.setBid(book);
                    imageListNewImage = em.merge(imageListNewImage);
                    if (oldBidOfImageListNewImage != null && !oldBidOfImageListNewImage.equals(book)) {
                        oldBidOfImageListNewImage.getImageList().remove(imageListNewImage);
                        oldBidOfImageListNewImage = em.merge(oldBidOfImageListNewImage);
                    }
                }
            }
            for (Bookcategory bookcategoryListNewBookcategory : bookcategoryListNew) {
                if (!bookcategoryListOld.contains(bookcategoryListNewBookcategory)) {
                    Book oldBidOfBookcategoryListNewBookcategory = bookcategoryListNewBookcategory.getBid();
                    bookcategoryListNewBookcategory.setBid(book);
                    bookcategoryListNewBookcategory = em.merge(bookcategoryListNewBookcategory);
                    if (oldBidOfBookcategoryListNewBookcategory != null && !oldBidOfBookcategoryListNewBookcategory.equals(book)) {
                        oldBidOfBookcategoryListNewBookcategory.getBookcategoryList().remove(bookcategoryListNewBookcategory);
                        oldBidOfBookcategoryListNewBookcategory = em.merge(oldBidOfBookcategoryListNewBookcategory);
                    }
                }
            }
            for (Orderdetail orderdetailListNewOrderdetail : orderdetailListNew) {
                if (!orderdetailListOld.contains(orderdetailListNewOrderdetail)) {
                    Book oldBidOfOrderdetailListNewOrderdetail = orderdetailListNewOrderdetail.getBid();
                    orderdetailListNewOrderdetail.setBid(book);
                    orderdetailListNewOrderdetail = em.merge(orderdetailListNewOrderdetail);
                    if (oldBidOfOrderdetailListNewOrderdetail != null && !oldBidOfOrderdetailListNewOrderdetail.equals(book)) {
                        oldBidOfOrderdetailListNewOrderdetail.getOrderdetailList().remove(orderdetailListNewOrderdetail);
                        oldBidOfOrderdetailListNewOrderdetail = em.merge(oldBidOfOrderdetailListNewOrderdetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = book.getBid();
                if (findBook(id) == null) {
                    throw new NonexistentEntityException("The book with id " + id + " no longer exists.");
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
            Book book;
            try {
                book = em.getReference(Book.class, id);
                book.getBid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The book with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ratingfeedback> ratingfeedbackListOrphanCheck = book.getRatingfeedbackList();
            for (Ratingfeedback ratingfeedbackListOrphanCheckRatingfeedback : ratingfeedbackListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Book (" + book + ") cannot be destroyed since the Ratingfeedback " + ratingfeedbackListOrphanCheckRatingfeedback + " in its ratingfeedbackList field has a non-nullable bid field.");
            }
            List<Image> imageListOrphanCheck = book.getImageList();
            for (Image imageListOrphanCheckImage : imageListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Book (" + book + ") cannot be destroyed since the Image " + imageListOrphanCheckImage + " in its imageList field has a non-nullable bid field.");
            }
            List<Bookcategory> bookcategoryListOrphanCheck = book.getBookcategoryList();
            for (Bookcategory bookcategoryListOrphanCheckBookcategory : bookcategoryListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Book (" + book + ") cannot be destroyed since the Bookcategory " + bookcategoryListOrphanCheckBookcategory + " in its bookcategoryList field has a non-nullable bid field.");
            }
            List<Orderdetail> orderdetailListOrphanCheck = book.getOrderdetailList();
            for (Orderdetail orderdetailListOrphanCheckOrderdetail : orderdetailListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Book (" + book + ") cannot be destroyed since the Orderdetail " + orderdetailListOrphanCheckOrderdetail + " in its orderdetailList field has a non-nullable bid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(book);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Book> findBookEntities() {
        return findBookEntities(true, -1, -1);
    }

    public List<Book> findBookEntities(int maxResults, int firstResult) {
        return findBookEntities(false, maxResults, firstResult);
    }

    private List<Book> findBookEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Book.class));
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

    public Book findBook(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    public int getBookCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Book> rt = cq.from(Book.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Book> getBookList() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery(Book.class);
            Root<Book> root = cq.from(Book.class);
//            Join<Book,Image> bookImage = root.join(Book_.);
            cq.multiselect(root.get("bid"), root.get("titleBook"), root.get("author"), root.get("manufacture"), root.get("publishingCompany"), root.get("yearPublish"), root.get("dateSale"), root.get("price"), root.get("description"), root.get("status"), root.get("imageList"), root.get("bookcategoryList"));
            Query q = em.createQuery(cq);
            ((org.eclipse.persistence.jpa.JpaQuery) q).getDatabaseQuery().dontMaintainCache();
            return (List<Book>) q.getResultList();
        } finally {
            em.close();
        }
//        
    }

}
