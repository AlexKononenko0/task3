/*
 * Oleksandr Kononenko
 * <p>
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 * <p>
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package kononenko;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp.datasources.SharedPoolDataSource;

/**
 * The {@code ConnectionPool} class implements all methods for to create connection pool to database.
 *
 * @author Oleksandr Kononenko
 * @version 1.1, 2023-02-01
 */
public class ConnectionPool {

    /**
     * Method for set driver and shared pool properties and returned data source.
     *
     * @return data source with setting properties
     * @throws ClassNotFoundException if JVM has traversed the entire classpath and not found the class you've attempted to reference
     */
    public static DataSource getDataSource() throws ClassNotFoundException {
        DataSource ds;

        DriverAdapterCPDS driver = new DriverAdapterCPDS();

        driver.setDriver("org.h2.Driver");
        driver.setUrl("jdbc:h2:~/test");
        driver.setUser("sa");
        driver.setPassword("");

        SharedPoolDataSource sharedPoolDS = new SharedPoolDataSource();
        sharedPoolDS.setConnectionPoolDataSource(driver);
        sharedPoolDS.setMaxActive(10);
        sharedPoolDS.setMaxWait(50);
        sharedPoolDS.setTestOnBorrow(true);
        sharedPoolDS.setValidationQuery("SELECT 1");
        sharedPoolDS.setTestWhileIdle(true);
        ds = sharedPoolDS;

        return ds;
    }

    /**
     * Method set and return connection.
     *
     * @return connection with param from data source
     * @throws ClassNotFoundException if JVM has traversed the entire classpath and not found the class you've attempted to reference
     * @throws SQLException           if a database access error occurs or this method is called on a closed connection
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        DataSource ds = getDataSource();
        return ds.getConnection();
    }

    /**
     * Method return the prepared statement with connection pool and query in param.
     *
     * @return object that represents a precompiled SQL statement
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    public static PreparedStatement createPreparedStatement(Connection connection, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement;
    }
}
