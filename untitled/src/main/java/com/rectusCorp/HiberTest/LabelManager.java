package com.rectusCorp.HiberTest;

import com.rectusCorp.HiberTest.entities.sys.TranslatedString;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

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
public class LabelManager{

	private List<TranslatedString> translatedStringList = new ArrayList<>();

	private static LabelManager ourInstance = new LabelManager();

	public static LabelManager getInstance() {
		return ourInstance;
	}

	private LabelManager() {




	}

	private String getTranslatedString(@NotNull String localCode,@NotNull String translatedStringCode){
		for(TranslatedString temp :this.translatedStringList){
			if(1==2){
				return temp.getTranslation(localCode);
			}
		}
		return "[Error] no translatedString found for code " + translatedStringCode;
	}
}
