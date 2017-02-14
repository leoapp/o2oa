package com.x.organization.assemble.control.jaxrs.person;

import com.x.base.core.bean.BeanCopyTools;
import com.x.base.core.bean.BeanCopyToolsBuilder;
import com.x.base.core.project.x_collect_service_transmit;
import com.x.organization.assemble.control.ThisApplication;
import com.x.organization.assemble.control.wrapin.WrapInPerson;
import com.x.organization.assemble.control.wrapout.WrapOutPerson;
import com.x.organization.core.entity.Person;

public class ActionBase {
	protected static BeanCopyTools<Person, WrapOutPerson> outCopier = BeanCopyToolsBuilder.create(Person.class,
			WrapOutPerson.class, null, WrapOutPerson.Excludes);

	protected static BeanCopyTools<WrapInPerson, Person> inCopier = BeanCopyToolsBuilder.create(WrapInPerson.class,
			Person.class);

	protected void collectTransmit() throws Exception {
		/* 通知x_collect_service_transmit同步数据到collect */
		ThisApplication.applications.getQuery(x_collect_service_transmit.class, "transmit/execute");
	}

}