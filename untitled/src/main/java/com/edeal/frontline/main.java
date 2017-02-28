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

import com.edeal.frontline.entities.Address;
import com.edeal.frontline.entities.City;
import com.edeal.frontline.entities.Country;
import com.edeal.frontline.entities.Person;
import com.edeal.frontline.enums.Civility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import javax.persistence.Entity;
import java.util.Set;

public class main{

	private static SessionFactory sessionFactory;
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure() // configures settings from hibernate.cfg.xml
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
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			//insert data
			Country country = new Country("France");
			  session.save(country);

			City c = new City("Toulouse", "31000", country);
			 session.save(c);







			Person p = new Person();
			p.setCivility(Civility.MR);
			p.setFirstName("Pierre");
			p.setFamilyName("Dupont");
			p.setAddress(new Address("Rue qui va bien", c, country));

			session.save(p);

			tx.commit();
		}
		catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
