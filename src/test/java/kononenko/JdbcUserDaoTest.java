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

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.sql.DataSource;

import java.io.FileInputStream;


/**
 * The {@code JdbcUserDaoTest} class tested methods from JdbcUserDao.java class.
 *
 * @author Oleksandr Kononenko
 * @version 1.1, 2023-02-01
 */
@RunWith(JUnit4.class)
public class JdbcUserDaoTest extends DataSourceBasedDBTestCase {

    @Override
    protected DataSource getDataSource() {
        try {
            return ConnectionPool.getDataSource();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\user.xml"));
//      return new FlatXmlDataSetBuilder().build(new FileInputStream("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\remove_user_dataset.xml"));
//      return new FlatXmlDataSetBuilder().build(new FileInputStream("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\user_update_dataset.xml"));

    }

    @Test
    public void create() throws Exception {
        User user = new User(1L, "Alex", 1L);
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        jdbcUserDao.create(user);
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("user");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("PUBLIC.user");

        assertEquals(expectedTable.getTableMetaData(), actualTable.getTableMetaData());
    }

    @Test
    public void update() throws Exception {
        User user = new User(1L, "Avex", 1L);
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        jdbcUserDao.update(user);
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("user");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("PUBLIC.user");

        assertEquals(expectedTable.getValue(0, "name"), actualTable.getValue(0, "name"));
    }

    @Test
    public void remove() throws Exception {
        User user = new User(1L, "Alex", 1L);
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        jdbcUserDao.create(user);
        jdbcUserDao.remove(user);
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("user");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("PUBLIC.user");

        assertEquals(expectedTable.getRowCount(), actualTable.getRowCount());
    }

    @Test
    public void findAll() throws Exception {
        User user = new User(1L, "Alex", 1L);
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        jdbcUserDao.create(user);
        jdbcUserDao.findAll();
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("user");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("TEST.PUBLIC.\"user\"");

        assertEquals(expectedTable.getTableMetaData().getTableName(), actualTable.getTableMetaData().getTableName());
        assertEquals(expectedTable.getTableMetaData().getColumns()[0].getColumnName(), actualTable.getTableMetaData().getColumns()[0].getColumnName());
        assertEquals(expectedTable.getTableMetaData().getColumns()[1].getColumnName(), actualTable.getTableMetaData().getColumns()[1].getColumnName());
    }

    @Test
    public void findByName() throws Exception {
        User user = new User(1L, "Alex", 1L);
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        jdbcUserDao.create(user);
        jdbcUserDao.findByName("Alex");
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("user");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("TEST.PUBLIC.\"user\"");

        assertEquals(expectedTable.getValue(0, "name"), actualTable.getValue(0, "name"));
    }

    @Test
    public void findById() throws Exception {
        User user = new User(1L, "Alex", 1L);
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        jdbcUserDao.create(user);
        jdbcUserDao.findById(1L);
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("user");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("TEST.PUBLIC.\"user\"");

        assertEquals(expectedTable.getValue(0, "name"), actualTable.getValue(0, "name"));

    }
}