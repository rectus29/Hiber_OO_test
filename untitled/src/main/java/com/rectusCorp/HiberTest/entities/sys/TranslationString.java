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
package com.rectusCorp.HiberTest.entities.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "translationstring")
public class TranslationString extends GenericEntity{

	@Column(nullable = false)
	private String localCode;

	@Column(nullable = false, columnDefinition = "LONGTEXT")
	private String label;

	public TranslationString() {
	}

	public TranslationString(String localCode, String label) {
		this.localCode = localCode;
		this.label = label;
	}

	public String getLocalCode() {
		return localCode;
	}

	public TranslationString setLocalCode(String localCode) {
		this.localCode = localCode;
		return this;
	}

	public String getLabel() {
		return label;
	}

	public TranslationString setLabel(String label) {
		this.label = label;
		return this;
	}
}
