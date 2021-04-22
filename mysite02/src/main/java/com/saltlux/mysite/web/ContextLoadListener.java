package com.saltlux.mysite.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext context= servletContextEvent.getServletContext();
		String contextconfigLoacation =context.getInitParameter("contextConfigLocation");
		
		System.out.println("Application Starts....."+ contextconfigLoacation);
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

}
