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

import java.util.List;

/**
 * The {@code UserDao} interface contained methods for work with {@code User} in table from database.
 *
 * @author Oleksandr Kononenko
 * @version 1.1, 2023-02-01
 */
public interface UserDao extends Dao<User> {

    /**
     * Method for creating entity {@code User} in table from database.
     *
     * @param user entity from table
     */
    void create(User user);

    /**
     * Method for updating data entity {@code User} in table from database.
     *
     * @param user entity from table
     */
    void update(User user);

    /**
     * Method for removing data entity {@code User} in table from database.
     *
     * @param user entity from table
     */
    void remove(User user);

    /**
     * Method for find all entity {@code User} in the table from database.
     *
     * @return list user/users from table
     */
    List<User> findAll();

    /**
     * Method for find entity {@code User} by name in the table from database.
     *
     * @param name name user in table
     * @return founded entity from table by name
     */
    User findByName(String name);
}
