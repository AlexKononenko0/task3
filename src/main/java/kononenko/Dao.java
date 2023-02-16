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
 * The {@code Dao} interface contained methods for work with data in tables from database.
 *
 * @param <E> entity database
 * @author Oleksandr Kononenko
 * @version 1.1, 2023-02-01
 */
public interface Dao<E> {

    /**
     * Method for creating entity in table from database.
     *
     * @param entity entity database
     */
    void create(E entity);

    /**
     * Method for updating parameters entity in table from database.
     *
     * @param entity entity database
     */
    void update(E entity);

    /**
     * Method for remove entity in table from database.
     *
     * @param entity entity database
     */
    void remove(E entity);

    /**
     * Method for find all entity in the table from database.
     *
     * @return list entities from table
     */
    List<E> findAll();

    /**
     * Method for find entity by id in the table from database.
     *
     * @param id personal id entity in table
     * @return founded entity from table by id
     */
    E findById(Long id);
}
