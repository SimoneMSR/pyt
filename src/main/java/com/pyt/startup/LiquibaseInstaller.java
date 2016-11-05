package com.pyt.startup;


import java.sql.SQLException;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;
import javax.sql.DataSource;

import liquibase.integration.cdi.CDILiquibaseConfig;
import liquibase.integration.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;


@ApplicationScoped
public class LiquibaseInstaller {

	@Resource(mappedName = "java:jboss/datasources/pyt")
	private DataSource dataSource;

	@Produces
	@LiquibaseType
	public CDILiquibaseConfig createConfig() {
		CDILiquibaseConfig config = new CDILiquibaseConfig();
		config.setChangeLog("database-changelog/master.xml");
		return config;
	}

	@Produces
	@LiquibaseType
	public DataSource createDataSource() throws SQLException, NamingException {
		return dataSource;
	}

	@Produces
	@LiquibaseType
	public ResourceAccessor create() {
		return new ClassLoaderResourceAccessor(getClass().getClassLoader());
	}

}