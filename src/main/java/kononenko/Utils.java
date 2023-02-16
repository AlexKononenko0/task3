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
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The {@code Utils} class contained auxiliary methods.
 *
 * @author Oleksandr Kononenko
 * @version 1.1, 2023-02-01
 */
public class Utils {

    /**
     * Method reading file with the stream and collect with collectors to string.
     *
     * @param file file which you want`s read
     * @return string lines from the read file
     */
    public static String readFile(File file) {
        Path path = Paths.get(String.valueOf(file));
        try (Stream<String> lines = Files.lines(path)) {
            String result = lines.collect(Collectors.joining(" "));
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
