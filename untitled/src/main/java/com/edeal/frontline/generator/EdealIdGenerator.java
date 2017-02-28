package com.edeal.frontline.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IncrementGenerator;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rectus_29 on 10/02/2016.
 */
public class EdealIdGenerator extends IncrementGenerator{

	@Override public synchronized Serializable generate(SharedSessionContractImplementor session,
			Object object) throws HibernateException {

		return "0000000000" + Long.toHexString(new Date().getTime());
	}

}
