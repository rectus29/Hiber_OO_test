package com.edeal.frontline.entities; /**
 *
 * User: Rectus29
 * Date: 24/07/11
 * Time: 07:47
 */

import com.edeal.frontline.entities.sys.CustomField;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.Size;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
public class GenericEntity implements  Serializable {

    @Id
	@GeneratedValue(generator="EdealIdGenerator",strategy = GenerationType.AUTO)
	@GenericGenerator(name="EdealIdGenerator", strategy="com.edeal.frontline.generator.EdealIdGenerator")
    private String id;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date created = new Date();
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated = new Date();

	@OneToMany(targetEntity = CustomField.class, cascade = CascadeType.ALL)
	@JoinColumn( name = "genericEntityiId")
	@MapKey(name="name")
	private Map<String, CustomField> stringCustomFieldMap = new HashMap<>();



	public GenericEntity() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Map<String, CustomField> getStringCustomFieldMap() {
		return stringCustomFieldMap;
	}

	public GenericEntity setStringCustomFieldMap(Map<String, CustomField> stringCustomFieldMap) {
		this.stringCustomFieldMap = stringCustomFieldMap;
		return this;
	}

	public GenericEntity addCustomField(CustomField customField) {
		this.stringCustomFieldMap.put(customField.getName(), customField);
		return this;
	}
}
