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


import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


import java.util.LinkedList;
import java.util.List;

/**
 * The {@code JdbcRoleDao} class implements all methods from {@code RoleDao}.
 *
 * @author Oleksandr Kononenko
 * @version 1.1, 2023-02-01
 */
public class JdbcRoleDao extends BaseDao<Role> implements RoleDao {

    /**
     * Method for find all role in the table from database.
     *
     * @return list all role from table
     */
    @Override
    public List<Role> findAll() {
        File file = new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\find_all_role.sql");
        String query = Utils.readFile(file);
        List<Role> resultList = new LinkedList<>();
        try (
                Connection connection = ConnectionPool.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.executeQuery(query);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                resultList.add(new Role(resultSet.getLong(1), resultSet.getString(2)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    /**
     * Method for find role by id in the table from database.
     *
     * @param id personal id role in table
     * @return founded role from table by id
     */
    @Override
    public Role findById(Long id) {
        File file = new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\find_role_by_id.sql");
        String query = Utils.readFile(file);
        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(query);
        ) {
            prepareStatement.setLong(1, id);
            prepareStatement.execute();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Method for creating entity {@code Role} in table from database.
     *
     * @param role entity from table
     */
    @Override
    public void create(Role role) {
        File file = new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\create_role.sql");
        String query = Utils.readFile(file);
        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for updating data entity {@code Role} in table from database.
     *
     * @param role entity from table
     */
    @Override
    public void update(Role role) {
        File file = new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\update_role.sql");
        String query = Utils.readFile(file);
        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(query);
        ) {
            prepareStatement.setString(1, role.getName());
            prepareStatement.setLong(2, role.getId());
            prepareStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for removing data entity {@code Role} in table from database.
     *
     * @param role entity from table
     */
    @Override
    public void remove(Role role) {
        File file = new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\remove_role.sql");
        String query = Utils.readFile(file);
        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(query);
        ) {
            prepareStatement.setLong(1, role.getId());
            prepareStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for find entity {@code Role} by name in the table from database.
     *
     * @param name name role in table
     * @return founded role from table by name
     */
    @Override
    public Role findByName(String name) {
        File file = new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\find_role_by_name.sql");
        String query = Utils.readFile(file);
        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(query);
        ) {
            prepareStatement.setString(1, name);
            prepareStatement.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
