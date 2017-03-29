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
package com.edeal.frontline.entities.model;

import com.edeal.frontline.entities.CustomEntity;
import com.edeal.frontline.enums.Civility;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "person")
public class Person extends CustomEntity{
	private static final Log logger = LogFactory.getLog(Person.class);
	@Column
	private Civility civility;
	@Column
	private String firstName;
	@Column
	private String familyName;
	@ManyToOne(targetEntity = Company.class)
	private Company company;
	@Column
	private Position mainPosition;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private List<Position> positionList = new ArrayList<>() ;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
	private List<ContactDetails> contactDetails = new ArrayList<>();

	@Embedded
	private Address address;

	@Column
	private String comment;
	@Column
	private Locale locale;

	public Person() {
	}

	public static Log getLogger() {
		return logger;
	}

	public Civility getCivility() {
		return civility;
	}

	public Person setCivility(Civility civility) {
		this.civility = civility;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public Person setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getFamilyName() {
		return familyName;
	}

	public Person setFamilyName(String familyName) {
		this.familyName = familyName;
		return this;
	}

	public Company getCompany() {
		return company;
	}

	public Person setCompany(Company company) {
		this.company = company;
		return this;
	}

	public Position getMainPosition() {
		return mainPosition;
	}

	public Person setMainPosition(Position mainPosition) {
		this.mainPosition = mainPosition;
		return this;
	}

	public List<Position> getPositionList() {
		return positionList;
	}

	public Person setPositionList(List<Position> positionList) {
		this.positionList = positionList;
		return this;
	}

	public List<ContactDetails> getContactDetails() {
		return contactDetails;
	}

	public Person setContactDetails(List<ContactDetails> contactDetails) {
		this.contactDetails = contactDetails;
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public Person setAddress(Address address) {
		this.address = address;
		return this;
	}

	public String getComment() {
		return comment;
	}

	public Person setComment(String comment) {
		this.comment = comment;
		return this;
	}

	public Locale getLocale() {
		return locale;
	}

	public Person setLocale(Locale locale) {
		this.locale = locale;
		return this;
	}
}
