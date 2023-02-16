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
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The {@code JdbcUserDao} class implements all methods from {@code UserDao}.
 *
 * @author Oleksandr Kononenko
 * @version 1.1, 2023-02-01
 */
public class JdbcUserDao extends BaseDao<User> implements UserDao {

    /**
     * Method for creating entity {@code User} in table from database.
     *
     * @param user entity from table
     */
    @Override
    public void create(User user) {
        File file = new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\create_user.sql");
        String query = Utils.readFile(file);
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setLong(3, user.getRoleId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for updating data entity {@code User} in table from database.
     *
     * @param user entity from table
     */
    @Override
    public void update(User user) {
        File file = new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\update_user.sql");
        String query = Utils.readFile(file);
        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(query);
        ) {
            prepareStatement.setLong(1, user.getId());
            prepareStatement.setString(2, user.getName());
            prepareStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for removing data entity {@code User} in table from database.
     *
     * @param user entity from table
     */
    @Override
    public void remove(User user) {
        File file = new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\remove_user.sql");
        String query = Utils.readFile(file);
        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(query);
        ) {
            prepareStatement.setLong(1, user.getId());
            prepareStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Method for find all user in the table from database.
     *
     * @return list all user from table
     */
    @Override
    public List<User> findAll() {
        File file = new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\find_all_user.sql");
        String query = Utils.readFile(file);
        List<User> resultList = new LinkedList<>();
        try (
                Connection connection = ConnectionPool.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.executeQuery(query);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                resultList.add(new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getLong(3)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    /**
     * Method for find entity {@code User} by name in the table from database.
     *
     * @param name name user in table
     * @return founded user from table by name
     */
    @Override
    public User findByName(String name) {
        File file = new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\find_user_by_name.sql");
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

    /**
     * Method for find user by id in the table from database.
     *
     * @param id personal id user in table
     * @return founded user from table by id
     */
    @Override
    public User findById(Long id) {
        File file = new File("D:\\nix_study\\bookstore\\task3\\src\\main\\java\\kononenko\\db\\find_user_by_id.sql");
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
}
