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
package com.edeal.frontline.entities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "person")
public class Person extends GenericEntity{
	private static final Log logger = LogFactory.getLog(Person.class);
	@Column
	private Civility civility;
	@Column
	private String firstName;
	@Column
	private String familiyName;
	@ManyToOne
	private Company company;
	@Column
	private Position mainPosition;
	@OneToMany
	private List<Position> positionList = new ArrayList<>() ;
	@OneToMany
	private List<ContactDetails> contactDetails = new ArrayList<>();
	@Embedded
	private Address address;
	@Column
	private String comment;
	@Column
	private Locale locale;

	public Person() {
	}


}
