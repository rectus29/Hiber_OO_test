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
package com.rectusCorp.HiberTest;

import com.rectusCorp.HiberTest.dao.impl.CityDaoImpl;
import com.rectusCorp.HiberTest.entities.sys.TranslatedString;
import com.rectusCorp.HiberTest.entities.sys.TranslationString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
