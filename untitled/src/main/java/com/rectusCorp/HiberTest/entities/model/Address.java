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

import com.rectusCorp.HiberTest.enums.AddressStatus;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Address{
	@Column
	private String address;
	@Column
	private String addressComplement ;
	@ManyToOne
	private City city ;
	@ManyToOne()
	private Country country ;
	@Column
	private AddressStatus addressStatus = AddressStatus.PENDING;

	public Address() {
	}

	public Address(String address, City city, Country country) {
		this.address = address;
		this.city = city;
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public Address setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getAddressComplement() {
		return addressComplement;
	}

	public Address setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
		return this;
	}

	public City getCity() {
		return city;
	}

	public Address setCity(City city) {
		this.city = city;
		return this;
	}

	public Country getCountry() {
		return country;
	}

	public Address setCountry(Country country) {
		this.country = country;
		return this;
	}

	public AddressStatus getAddressStatus() {
		return addressStatus;
	}

	public Address setAddressStatus(AddressStatus addressStatus) {
		this.addressStatus = addressStatus;
		return this;
	}
}
