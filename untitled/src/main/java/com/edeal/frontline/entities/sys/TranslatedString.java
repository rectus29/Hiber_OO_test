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
package com.edeal.frontline.entities.sys;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "translatedstring")
public class TranslatedString extends GenericEntity{

	@Column(nullable = false)
	private String code;

	@OneToMany(targetEntity = TranslationString.class, cascade = CascadeType.ALL)
	@JoinColumn( name = "translatedstring_id")
	@MapKey(name="localCode")
	private Map<String, TranslationString> translationStringList = new HashMap<>();

	public TranslatedString() {
	}

	public TranslatedString(String code) {
		this.code = code;
	}

	public String getTranslation(@NotNull String localeCode){
		return null;
	}

	public void addTranslation(TranslationString translationString){
		if(StringUtils.isNotEmpty(translationString.getLocalCode())){
			this.translationStringList.put(translationString.getLocalCode(), translationString);
		}
	}

}
