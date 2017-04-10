package com.edeal.frontline.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/******************************************************************************
 * Copyright (c) 2000-2017 E-DEAL
 *
 * E-DEAL
 * 41 Rue Périer
 * 92120 Montrouge
 * France
 *
 * T: +33 (0)1 73 03 29 80
 * F: +33 (0)1 73 01 69 77
 * http://www.e-deal.com
 *
 * La diffusion de ce code source sous quelque forme que ce soit sans
 * l'autorisation de E-DEAL est interdite.
 *
 * Vous êtes autorisé à modifier ce code source uniquement pour votre usage
 * propre et sous réserve que les mentions de copyright demeurent intactes.
 *
 * Ce code est fourni en l'état. Aucune garantie d'aucune sorte, explicite ou
 * implicite n'est donnée. En aucun cas E-DEAL ne pourra être tenu pour
 * responsable des dommages pouvant résulter de l'utilisation de ce code
 * source.
 */
public class EdealApplication implements Application{
	public static final String MODEL = "model";
	public static final String SYS = "sys";
	private static final Log logger = LogFactory.getLog(EdealApplication.class);
	private Map<String, PersistanceUnit> persistanceUnitMap = new HashMap<>();


	private static class SingletonHolder{
		private final static EdealApplication instance = new EdealApplication();
	}

	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static EdealApplication getInstance() {
		return SingletonHolder.instance;
	}

	public Map<String, PersistanceUnit> getPersistanceUnitMap() {
		return persistanceUnitMap;
	}

	public EdealApplication() {
		//TEST purpose only
		this.persistanceUnitMap.put(MODEL, new PersistanceUnit(new ModelUnitConfiguration()));
		this.persistanceUnitMap.put(SYS, new PersistanceUnit(new SysUnitConfiguration()));

	}



	public Session getSysSession(){
		return this.getPersistanceUnitMap().get(SYS).getSession();
	}

	public Session getModelSession(){
		return this.getPersistanceUnitMap().get(MODEL).getSession();
	}



	/**
	 * unload the current application
	 */
	public void unload(){
		for(PersistanceUnit temp : this.getPersistanceUnitMap().values()){
			temp.unload();
			temp = null;
		}
	}

	private abstract class PersistanceUnitConfiguration{
		public String entitesPackage ;
		public String hbConfigFile ;
		public String mode ;

		public PersistanceUnitConfiguration() {
		}
	}

	private class ModelUnitConfiguration extends PersistanceUnitConfiguration {
		public ModelUnitConfiguration() {
			this.entitesPackage = "com.edeal.frontline.entities.model";
			this.hbConfigFile = "model.hbm.xml";
			this.mode = MODEL;
		}
	}

	private class SysUnitConfiguration extends PersistanceUnitConfiguration {
		public SysUnitConfiguration() {
			this.entitesPackage = "com.edeal.frontline.entities.sys";
			this.hbConfigFile = "sys.hbm.xml";
			this.mode = SYS;
		}
	}

	private class PersistanceUnit{
		private final EntityManager entityManager;
		private final SessionFactory sessionFactory;
		private final Session session;

		public PersistanceUnit(PersistanceUnitConfiguration configuration) {
			Configuration cfg = new Configuration().configure(configuration.hbConfigFile);
			//load entities from classes
			Reflections reflections = new Reflections(configuration.entitesPackage);
			Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Entity.class);
			for (Class<?> annotedClass : annotated) {
				cfg.addAnnotatedClass(annotedClass);
			}
			this.sessionFactory = cfg.buildSessionFactory();
			this.entityManager = sessionFactory.createEntityManager();
			this.session  = sessionFactory.openSession();
		}

		public EntityManager getEntityManager() {
			return entityManager;
		}

		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		public Session getSession() {
			return session;
		}

		public void unload(){
			this.session.close();
			this.sessionFactory.close();
			this.entityManager.close();
		}


	}

}
