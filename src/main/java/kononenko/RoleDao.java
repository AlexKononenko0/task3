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

/**
 * The {@code RoleDao} interface contained methods for work with {@code Role} in table from database.
 *
 * @author Oleksandr Kononenko
 * @version 1.1, 2023-02-01
 */
public interface RoleDao extends Dao<Role> {

    /**
     * Method for creating entity {@code Role} in table from database.
     *
     * @param role entity from table
     */
    void create(Role role);

    /**
     * Method for updating data entity {@code Role} in table from database.
     *
     * @param role entity from table
     */
    void update(Role role);

    /**
     * Method for removing data entity {@code Role} in table from database.
     *
     * @param role entity from table
     */
    void remove(Role role);

    /**
     * Method for find entity {@code Role} by name in the table from database.
     *
     * @param name role name in table
     * @return founded entity from table by name
     */
    Role findByName(String name);
}
