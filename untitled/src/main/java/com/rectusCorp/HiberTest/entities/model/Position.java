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
package com.rectusCorp.HiberTest.entities.model;

import com.rectusCorp.HiberTest.entities.sys.CustomEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class Position extends CustomEntity{

	@Column
	private boolean isMainPosition= true;

	@ManyToOne(targetEntity = Person.class)
	private Person person;

	public Position() {
	}

	public boolean isMainPosition() {
		return isMainPosition;
	}

	public Position setMainPosition(boolean mainPosition) {
		isMainPosition = mainPosition;
		return this;
	}

	public Person getPerson() {
		return person;
	}

	public Position setPerson(Person person) {
		this.person = person;
		return this;
	}
}
