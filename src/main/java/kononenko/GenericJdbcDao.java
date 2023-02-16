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

import org.apache.log4j.Logger;

import java.io.File;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * The {@code GenericJdbcDao} abstract class containing methods for created and initialized database.
 *
 * @author Oleksandr Kononenko
 * @version 1.1, 2023-02-01
 */
public abstract class GenericJdbcDao {
    protected static final Logger logger = Logger.getLogger(GenericJdbcDao.class);

    /**
     * Method connected to database and created all tables and datasets him.
     *
     * @param args args
     * @throws ClassNotFoundException if JVM has traversed the entire classpath and not found the class you've attempted to reference
     */
    public static void main(String[] args) throws ClassNotFoundException {
        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement preparedStatement = ConnectionPool.createPreparedStatement(connection, Utils.readFile(new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\ddl.sql")));
        ) {
            int result = preparedStatement.executeUpdate();
            logger.info("TABLES WAS CREATED --> " + result);

        } catch (SQLException e) {
            logger.trace(e);
        }

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement preparedStatement = ConnectionPool.createPreparedStatement(connection, Utils.readFile(new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\dml.sql")));
        ) {
            int result = preparedStatement.executeUpdate();
            logger.info("DATA INSERTED --> " + result);

        } catch (SQLException e) {
            logger.trace(e);
        }
    }
}
