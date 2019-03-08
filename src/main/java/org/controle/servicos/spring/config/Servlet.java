package org.controle.servicos.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Servlet extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {		
		return new Class[] {  };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {		
		return new Class[] { AppWebConfiguration.class, JpaConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {		
		return new String[] {"/"};
	}

}
