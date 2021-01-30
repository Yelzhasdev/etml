package com.github.yelzhasdev.etml.reflection;

/*
 *
 *  * Copyright (C) 2020 Suleimenov Yelzhas.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

import com.github.yelzhasdev.etml.EtmlParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class PrimitiveHelper {

    private static boolean DEFAULT_BOOLEAN;
    private static byte DEFAULT_BYTE;
    private static short DEFAULT_SHORT;
    private static int DEFAULT_INT;
    private static long DEFAULT_LONG;
    private static float DEFAULT_FLOAT;
    private static double DEFAULT_DOUBLE;

    public static Object getDefaultValue(Class clazz) {
        if (clazz.equals(boolean.class)) {
            return DEFAULT_BOOLEAN;
        } else if (clazz.equals(byte.class)) {
            return DEFAULT_BYTE;
        } else if (clazz.equals(short.class)) {
            return DEFAULT_SHORT;
        } else if (clazz.equals(int.class)) {
            return DEFAULT_INT;
        } else if (clazz.equals(long.class)) {
            return DEFAULT_LONG;
        } else if (clazz.equals(float.class)) {
            return DEFAULT_FLOAT;
        } else if (clazz.equals(double.class)) {
            return DEFAULT_DOUBLE;
        } else {
            return null;
        }
    }

    public static <T> T extractValueFromElement(Element element, Class<T> type, String valueOfNode) {
        T valueToReturn = null;

        if (type.isAssignableFrom(String.class)) {
            valueToReturn = (T) valueOfNode;
        } else if (type.isAssignableFrom(Integer.TYPE) || type.isAssignableFrom(Integer.class)) {
            valueToReturn = (T) Integer.valueOf(valueOfNode);
        } else if (type.isAssignableFrom(Float.TYPE) || type.isAssignableFrom(Float.class)) {
            valueToReturn = (T) Float.valueOf(valueOfNode);
        } else if (type.isAssignableFrom(Double.TYPE) || type.isAssignableFrom(Double.class)) {
            valueToReturn = (T) Double.valueOf(valueOfNode);
        } else if (type.isAssignableFrom(Long.TYPE) || type.isAssignableFrom(Long.class)) {
            valueToReturn = (T) Long.valueOf(valueOfNode);
        } else if (type.isAssignableFrom(Boolean.TYPE) || type.isAssignableFrom(Boolean.class)) {
            if ("true".equalsIgnoreCase(valueOfNode) || "false".equalsIgnoreCase(valueOfNode)) {
                valueToReturn = (T) Boolean.valueOf(valueOfNode);
            } else {
                throw new EtmlParseException("Boolean must be either \"true\" or \"false\"");
            }
        } else if (type.isAssignableFrom(Short.TYPE) || type.isAssignableFrom(Short.class)) {
            valueToReturn = (T) Short.valueOf(valueOfNode);
        } else {
            EtmlParser<T> parser = new EtmlParser<>();
            valueToReturn = parser.parse(type, Jsoup.parse(element.html()));
        }
        return valueToReturn;
    }
}
