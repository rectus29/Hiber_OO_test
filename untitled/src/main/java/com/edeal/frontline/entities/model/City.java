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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City extends CustomEntity{

	@Column
	private String name;
	@Column
	private String zipCode;
	@Column(nullable = true)
	private Long lng;
	@Column(nullable = true)
	private Long lat;
	@ManyToOne
	private Country country;

	public City() {
	}

	public City(String name, String zipCode, Country country) {
		this.name = name;
		this.zipCode = zipCode;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public City setName(String name) {
		this.name = name;
		return this;
	}

	public String getZipCode() {
		return zipCode;
	}

	public City setZipCode(String zipCode) {
		this.zipCode = zipCode;
		return this;
	}

	public long getLng() {
		return lng;
	}

	public City setLng(long lng) {
		this.lng = lng;
		return this;
	}

	public long getLat() {
		return lat;
	}

	public City setLat(long lat) {
		this.lat = lat;
		return this;
	}

	public Country getCountry() {
		return country;
	}

	public City setCountry(Country country) {
		this.country = country;
		return this;
	}
}
