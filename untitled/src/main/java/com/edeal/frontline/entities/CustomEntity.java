package com.edeal.frontline.entities; /**
 *
 * User: Rectus29
 * Date: 24/07/11
 * Time: 07:47
 */

import com.edeal.frontline.entities.sys.CustomField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CustomEntity extends GenericEntity {

	@ManyToMany(targetEntity = CustomField.class, cascade = CascadeType.ALL)
	@JoinColumn( name = "genericEntityiId")
	@MapKey(name="name")
	private Map<String, CustomField> stringCustomFieldMap = new HashMap<>();



	public CustomEntity() {
	}

	public Map<String, CustomField> getStringCustomFieldMap() {
		return stringCustomFieldMap;
	}

	public CustomEntity setStringCustomFieldMap(Map<String, CustomField> stringCustomFieldMap) {
		this.stringCustomFieldMap = stringCustomFieldMap;
		return this;
	}

	public CustomEntity addCustomField(CustomField customField) {
		this.stringCustomFieldMap.put(customField.getName(), customField);
		return this;
	}
}
