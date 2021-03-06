package com.x.base.core.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.x.base.core.entity.StorageType;
import com.x.base.core.project.gson.XGsonBuilder;

public class x_portal_assemble_designer extends AssembleA {

	public static final String name = "门户设计";
	public static List<String> containerEntities = new ArrayList<>();
	public static List<StorageType> usedStorageTypes = new ArrayList<>();
	public static List<Class<? extends Compilable>> dependents = new ArrayList<>();

	static {
		containerEntities.add("com.x.portal.core.entity.Portal");
		containerEntities.add("com.x.portal.core.entity.Widget");
		containerEntities.add("com.x.portal.core.entity.Page");
		containerEntities.add("com.x.portal.core.entity.Script");
		containerEntities.add("com.x.portal.core.entity.File");
		containerEntities.add("com.x.portal.core.entity.TemplatePage");
		dependents.add(x_base_core_project.class);
		dependents.add(x_organization_core_entity.class);
		dependents.add(x_organization_core_express.class);
		dependents.add(x_portal_core_entity.class);
	}

	protected void custom(File lib, String xLib) throws Exception {
	}

	public static void main(String[] args) {
	}

}
