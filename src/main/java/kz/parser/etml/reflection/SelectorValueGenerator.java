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

package kz.parser.etml.reflection;

import kz.parser.etml.EtmlParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public final class SelectorValueGenerator {

    public static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isAssignableFrom(String.class)
                || type.isAssignableFrom(Integer.TYPE) || type.isAssignableFrom(Integer.class)
                || type.isAssignableFrom(Float.TYPE) || type.isAssignableFrom(Float.class)
                || type.isAssignableFrom(Double.TYPE) || type.isAssignableFrom(Double.class)
                || type.isAssignableFrom(Long.TYPE) || type.isAssignableFrom(Long.class)
                || type.isAssignableFrom(Boolean.TYPE) || type.isAssignableFrom(Boolean.class)
                || type.isAssignableFrom(Short.TYPE) || type.isAssignableFrom(Short.class);
    }

    public static <T> List<T> getValues(Class<T> type, String selector, Document document) throws EtmlParseException {
        List<T> listToReturn = new ArrayList<>();
        try {
            Elements elements = document.select(selector);
            elements.forEach(element -> {
                if (isPrimitiveOrWrapper(type)) {
                    listToReturn.add(extractValue(element, type));
                } else {
                    EtmlParser<T> parser = new EtmlParser<>();
                    listToReturn.add(parser.parse(type, Jsoup.parse(element.html())));
                }
            });
        } catch (NumberFormatException e) {
            throw new EtmlParseException(e);
        } catch (NullPointerException e) {
            throw new EtmlParseException(e);
        }
        return listToReturn;
    }

    public static <T> T extractValue(Element element, Class<T> type) {
        T valueToReturn = null;
        if (element == null) {
            throw new EtmlParseException("Element is empty! Selector is wrong or page is not valid.");
        }
        String valueOfNode = element.text();
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

    public static <T> T getValue(Class<T> type, String selector, Document document) throws EtmlParseException {
        T valueToReturn;
        try {
            Element element = document.select(selector).first();
            valueToReturn = extractValue(element, type);
        } catch (NumberFormatException e) {
            throw new EtmlParseException(e);
        } catch (NullPointerException e) {
            throw new EtmlParseException(e);
        }
        return valueToReturn;
    }

}
