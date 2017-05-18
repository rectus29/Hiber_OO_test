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

import com.edeal.frontline.core.EdealApplication;
import com.edeal.frontline.dao.impl.CityDaoImpl;
import com.edeal.frontline.dao.impl.TranslatedStringDaoImpl;
import com.edeal.frontline.entities.model.City;
import com.edeal.frontline.entities.model.Country;
import com.edeal.frontline.entities.model.Person;
import com.edeal.frontline.entities.sys.TranslatedString;
import com.edeal.frontline.entities.sys.TranslationString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.reflections.Reflections;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Set;

public class main{
	private static final Logger log = LogManager.getLogger(main.class);


	public static void main(String[] args) throws Exception {
		CityDaoImpl cityDao = new CityDaoImpl();


		TranslatedString translatedString = new TranslatedString("LF839");
		translatedString.addTranslation(new TranslationString("fr", "France"));
		translatedString.addTranslation(new TranslationString("de", "Frankreich"));

		/*TranslatedString translatedString1 = translatedStringDao.merge(translatedString);
		translatedString1.addTranslation(new TranslationString("tu", "yolo"));
		 translatedStringDao.persist(translatedString1);
		 //save dirty
		TranslatedString translatedString15 = new TranslatedString("LF839");
		translatedString15.setId(translatedString1.getId());
		translatedStringDao.save(translatedString15);

		List<City> cities = cityDao.getAll();
		log.debug("plop");

			//requesting by hql query
			Query query = EdealApplication.getInstance().getEntityManager().createQuery(
					"select p from Person p where p.firstName like :name"
			,  Person.class);
			query.setParameter("name", "Pier%");
			List<Person> resultQuery = query.getResultList();



			//requesting by criteria

			CriteriaBuilder criteriaBuilder =  entityManager.getCriteriaBuilder();

			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(City.class);
			criteria.add(Restrictions.eq("country","papouazy"));
			List<City> result = criteria.list();
			for(City temp:result){
				temp.setCountry(new Country(""))
			}

			log.debug(result.size());

			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Person.class);
			detachedCriteria.add(Restrictions.eq("name", "Pierre80"));
			List<Person> detachResult = detachedCriteria.getExecutableCriteria(session).list();
			log.debug(detachResult.size());

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}*/

	}
}
