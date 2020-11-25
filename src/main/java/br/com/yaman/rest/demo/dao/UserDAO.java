package br.com.yaman.rest.demo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.yaman.rest.demo.entity.UserEntity;
import br.com.yaman.rest.demo.helper.HibernateEntityManagerHelper;

public class UserDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public UserDAO() {
	}
	
	public List<UserEntity> listAllUsers() {
		try {
			
			EntityManager entityManager = HibernateEntityManagerHelper.getEntityManager();
			
			String transactionQuery = "SELECT * FROM PORTAL.USERS ORDER BY name ";
			List<UserEntity> list = new ArrayList<>(100);
			
			try {		
				
				list = entityManager.createNativeQuery(transactionQuery,UserEntity.class).getResultList();				
				
			}catch(NoResultException nre) {
				nre.printStackTrace();
			} catch (Exception e) {				
				e.printStackTrace();
			} finally {				
				HibernateEntityManagerHelper.closeEntityManager();
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public UserEntity getUserDetails(String userUUID) {
		try {
			
			EntityManager entityManager = HibernateEntityManagerHelper.getEntityManager();
			
			String transactionQuery = "SELECT * FROM PORTAL.USERS WHERE uuid = :uuid";
			UserEntity entity = new UserEntity();
			
			try {		
				
				entity = (UserEntity) entityManager.createNativeQuery(transactionQuery,UserEntity.class).setParameter("uuid", userUUID).getSingleResult();				
				
			}catch(NoResultException nre) {
				nre.printStackTrace();
			} catch (Exception e) {				
				e.printStackTrace();
			} finally {				
				HibernateEntityManagerHelper.closeEntityManager();
			}

			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public void persistNewUser(UserEntity userEntity) {
		try {
			
			EntityManager entityManager = HibernateEntityManagerHelper.getEntityManager();

			try {
				userEntity.setUUID(UUID.randomUUID().toString());				
				userEntity.setTimestamp(new Date());
				
				HibernateEntityManagerHelper.beginTransaction();
				entityManager.persist(userEntity);
				HibernateEntityManagerHelper.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				HibernateEntityManagerHelper.rollback();
			} finally {
				HibernateEntityManagerHelper.closeEntityManager();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeUser(String uuid) {
		try {
			
			EntityManager entityManager = HibernateEntityManagerHelper.getEntityManager();

			try {				
				
				HibernateEntityManagerHelper.beginTransaction();
				entityManager.remove(uuid);
				HibernateEntityManagerHelper.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				HibernateEntityManagerHelper.rollback();
			} finally {
				HibernateEntityManagerHelper.closeEntityManager();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mergeUser(UserEntity userEntity) {
		try {
			
			EntityManager entityManager = HibernateEntityManagerHelper.getEntityManager();

			try {				
				
				HibernateEntityManagerHelper.beginTransaction();
				entityManager.merge(userEntity);
				HibernateEntityManagerHelper.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				HibernateEntityManagerHelper.rollback();
			} finally {
				HibernateEntityManagerHelper.closeEntityManager();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
