package com.x.face.assemble.control.jaxrs.unit;

import com.x.base.core.project.exception.PromptException;

class ExceptionIdentityNotExist extends PromptException {

	private static final long serialVersionUID = 4132300948670472899L;

	ExceptionIdentityNotExist(String flag) {
		super("身份:{}, 不存在.", flag);
	}
}
