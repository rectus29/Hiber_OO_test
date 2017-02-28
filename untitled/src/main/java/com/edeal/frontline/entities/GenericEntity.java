package com.edeal.frontline.entities; /**
 *
 * User: Rectus29
 * Date: 24/07/11
 * Time: 07:47
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class GenericEntity implements  Serializable {

    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date created = new Date();
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated = new Date();


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
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
}
