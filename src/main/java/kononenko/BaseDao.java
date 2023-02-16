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
import java.sql.SQLException;

/**
 * The {@code BaseDao} abstract class implements all methods for to create connection pool to database.
 *
 * @param <E> entity database
 * @author Oleksandr Kononenko
 * @version 1.1, 2023-02-01
 */
public abstract class BaseDao<E> implements Dao<E> {

    /**
     * Method returned connection to database with {@code ConnectionPool}.
     *
     * @return data source with setting properties
     */
    protected Connection getConnection() {
        try {
            return ConnectionPool.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
