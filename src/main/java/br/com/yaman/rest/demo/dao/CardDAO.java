package br.com.yaman.rest.demo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.yaman.rest.demo.entity.CardEntity;
import br.com.yaman.rest.demo.helper.HibernateEntityManagerHelper;

public class CardDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	//private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("web-unit");

	public CardDAO() {
	}

	public List<CardEntity> listMyCards(String userId) {
		try {
			
//			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityManager entityManager = HibernateEntityManagerHelper.getEntityManager();
			
			String transactionQuery = "SELECT * FROM PORTAL.CARDS WHERE USER_UUID = :user_uuid ORDER BY NICKNAME ";
			List<CardEntity> listCards = new ArrayList<>(100);
			
			try {		
				
				listCards = entityManager.createNativeQuery(transactionQuery,CardEntity.class).setParameter("user_uuid", userId).getResultList();				
				
			}catch(NoResultException nre) {
				nre.printStackTrace();
			} catch (Exception e) {				
				e.printStackTrace();
			} finally {				
				HibernateEntityManagerHelper.closeEntityManager();
			}

			return listCards;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public List<CardEntity> listSharedCards(String userId) {
		try {

//			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityManager entityManager = HibernateEntityManagerHelper.getEntityManager();
			
			String transactionQuery = "SELECT * FROM PORTAL.CARDS WHERE USER_UUID = :user_uuid ORDER BY NICKNAME ";
			List<CardEntity> listCards = new ArrayList<>(100);
			
			try {

				listCards = entityManager.createNativeQuery(transactionQuery,CardEntity.class).setParameter("user_uuid", userId).getResultList();			
				
			}catch(NoResultException nre) {
				nre.printStackTrace();
			} catch (Exception e) {				
				e.printStackTrace();
			} finally {				
				HibernateEntityManagerHelper.closeEntityManager();
			}

			return listCards;
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return null;
	}

	public void persistNewCard(CardEntity cardEntity) {
		try {

//			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityManager entityManager = HibernateEntityManagerHelper.getEntityManager();

			try {
				cardEntity.setUuid(UUID.randomUUID().toString());
				cardEntity.setMemberSince(new Date());
				
				HibernateEntityManagerHelper.beginTransaction();
				entityManager.persist(cardEntity);				
				HibernateEntityManagerHelper.commit();
				
			} catch (Exception e) {
				HibernateEntityManagerHelper.rollback();
			} finally {
				HibernateEntityManagerHelper.closeEntityManager();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CardEntity getCardEntity(String uuid) {
		try {

//			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityManager entityManager = HibernateEntityManagerHelper.getEntityManager();
			CardEntity cardEntity = new CardEntity();
			
			String transactionQuery = "SELECT * FROM PORTAL.CARDS WHERE UUID = :uuid ";

			try {
								
				cardEntity = (CardEntity) entityManager.createNativeQuery(transactionQuery,CardEntity.class).setParameter("uuid", uuid).getSingleResult();		
				
				System.out.println("===================================================================== LOG AMADOR : " + cardEntity.toString());
			
				return cardEntity;
				
			}catch(NoResultException nre) {
				nre.printStackTrace();
			} catch (Exception e) {				
				e.printStackTrace();
			} finally {				
				HibernateEntityManagerHelper.closeEntityManager();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}