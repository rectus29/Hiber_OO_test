package com.rectusCorp.HiberTest.entities.sys; /**
 *
 * User: Rectus29
 * Date: 24/07/11
 * Time: 07:47
 */

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CustomEntity extends GenericEntity {

	@ManyToMany(targetEntity = CustomField.class, cascade = CascadeType.ALL)
	@JoinColumn( name = "genericEntityiId")
	@MapKey(name="name")
	private Map<String, CustomField> customFieldMap = new HashMap<>();



	public CustomEntity() {
	}

	public Map<String, CustomField> getCustomFieldMap() {
		return customFieldMap;
	}

	public CustomEntity setCustomFieldMap(Map<String, CustomField> customFieldMap) {
		this.customFieldMap = customFieldMap;
		return this;
	}

	public CustomEntity addCustomField(CustomField customField) {
		this.customFieldMap.put(customField.getName(), customField);
		return this;
	}
}
