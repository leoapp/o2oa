package com.x.processplatform.assemble.designer.jaxrs;

import javax.servlet.annotation.WebFilter;

import com.x.base.core.application.jaxrs.ManagerUserJaxrsFilter;

@WebFilter(urlPatterns = "/jaxrs/applicationdict/*")
public class ApplicationDictJaxrsFilter extends ManagerUserJaxrsFilter {

}