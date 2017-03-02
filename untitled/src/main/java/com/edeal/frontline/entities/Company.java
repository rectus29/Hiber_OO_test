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
public class Company extends CustomEntity{

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

	public Company() {
	}

	public String getName() {
		return name;
	}

	public Company setName(String name) {
		this.name = name;
		return this;
	}

	public String getAcronym() {
		return acronym;
	}

	public Company setAcronym(String acronym) {
		this.acronym = acronym;
		return this;
	}

	public List<ContactDetails> getContactDetailsList() {
		return contactDetailsList;
	}

	public Company setContactDetailsList(List<ContactDetails> contactDetailsList) {
		this.contactDetailsList = contactDetailsList;
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public Company setAddress(Address address) {
		this.address = address;
		return this;
	}

	public Actor getCreator() {
		return creator;
	}

	public Company setCreator(Actor creator) {
		this.creator = creator;
		return this;
	}

	public Actor getCouncelor() {
		return councelor;
	}

	public Company setCouncelor(Actor councelor) {
		this.councelor = councelor;
		return this;
	}

	public String getSiret() {
		return siret;
	}

	public Company setSiret(String siret) {
		this.siret = siret;
		return this;
	}

	public String getSiren() {
		return siren;
	}

	public Company setSiren(String siren) {
		this.siren = siren;
		return this;
	}

	public String getComment() {
		return comment;
	}

	public Company setComment(String comment) {
		this.comment = comment;
		return this;
	}

	public List<Person> getEmployees() {
		return employees;
	}

	public Company setEmployees(List<Person> employees) {
		this.employees = employees;
		return this;
	}
}
