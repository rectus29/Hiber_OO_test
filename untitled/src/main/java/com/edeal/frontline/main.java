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
 ******************************************************************************/
package com.edeal.frontline;

import com.edeal.frontline.entities.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class main{
	private static final Logger log = LogManager.getLogger(main.class);
	private static SessionFactory sessionFactory;
	private static EntityManager entityManager;

	final StandardServiceRegistry registry =
			new StandardServiceRegistryBuilder().configure() // configures settings from hibernate.cfg.xml
					.build();

	public static void main(String[] args) throws Exception {

		Configuration cfg = new Configuration();
		//load entities
		Reflections reflections = new Reflections("com.edeal.frontline.entities");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Entity.class);
		for (Class<?> annotedClass : annotated) {
			cfg.addAnnotatedClass(annotedClass);

		}
		sessionFactory = cfg.configure().buildSessionFactory();
		entityManager = sessionFactory.createEntityManager();

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

		/*	tx = session.beginTransaction();
			Country country = session.get(Country.class, "000000000015a93826e48");
			City c = session.get(City.class, "000000000015a93826e34");

			for (int i = 0; i < 200; i++) {
				log.debug(i);
				Person p = new Person();
				p.setCivility(Civility.MR);
				p.setFirstName("Pierre" + i);
				p.setFamilyName("Dupont" + i);
				p.setAddress(new Address("Rue qui va bien" + i, c, country));
				p.addCustomField(new CustomField("phone", "02"));
				session.save(p);
				session.flush();
			}
			tx.commit();*/

			//requesting by hql query
			Query query = entityManager.createQuery(
					"select p from Person p where p.firstName like :name"
			,  Person.class);
			query.setParameter("name", "Pier%");
			List<Person> resultQuery = query.getResultList();



			//requesting by criteria

			/*CriteriaBuilder criteriaBuilder =  entityManager.getCriteriaBuilder();

			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
			criteria.add(Restrictions.eq("name","Pierre180"));
			List<Person> result = criteria.list();
			log.debug(result.size());

			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Person.class);
			detachedCriteria.add(Restrictions.eq("name", "Pierre80"));
			List<Person> detachResult = detachedCriteria.getExecutableCriteria(session).list();
			log.debug(detachResult.size());*/

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
