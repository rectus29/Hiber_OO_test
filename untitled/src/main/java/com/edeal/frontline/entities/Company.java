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

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company extends GenericEntity{

	@Column
	private String name;
	@Column
	private String acronym;
	@OneToMany
	private List<ContactDetails> contactDetailsList = new ArrayList<>();
	@Embedded
	private Address address;
	@ManyToOne
	private Actor creator;
	@ManyToOne
	private Actor councelor;
	@Column
	private String siret;
	@Column
	private String siren;
	@Column(columnDefinition = "LONGTEXT")
	private String comment;
	@OneToMany(mappedBy = "company")
	private List<Person> employees = new ArrayList<>();

}
