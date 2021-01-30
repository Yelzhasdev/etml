package com.github.yelzhasdev.etml.reflection;

import com.github.yelzhasdev.etml.Etml;
import com.github.yelzhasdev.etml.EtmlParseException;
import com.github.yelzhasdev.etml.marker.EtmlElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


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

    public static <T> List<T> getValues(Class<T> type, EtmlElement elementAnnotation, Document document) throws EtmlParseException {
        List<T> listToReturn = new ArrayList<>();
        try {
            Elements elements = document.select(elementAnnotation.selector());
            elements.forEach(element -> {
                if (isPrimitiveOrWrapper(type)) {
                    listToReturn.add(extractValue(element, elementAnnotation,type));
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

    public static <T> T extractValue(Element element, EtmlElement etmlElement, Class<T> type) {
        T valueToReturn = null;
        String valueOfNode = null;
        if (element == null) {
            if (etmlElement.mandatory()) {
                throw new EtmlParseException("Mandatory object must not be null");
            } else {
                return (T) PrimitiveHelper.getDefaultValue(type.getDeclaringClass());
            }
        }
        if (!etmlElement.attributeValue().isEmpty()){
            valueOfNode = element.attr(etmlElement.attributeValue().trim());
        } else {
            valueOfNode = element.text();
        }
        valueToReturn = PrimitiveHelper.extractValueFromElement(element, type, valueOfNode);
        return valueToReturn;
    }

    public static <T> T getValue(Class<T> type, EtmlElement elementAnnotation, Document document) throws EtmlParseException {
        T valueToReturn;
        try {
            Element element = document.select(elementAnnotation.selector()).first();
            valueToReturn = extractValue(element, elementAnnotation, type);
        } catch (NumberFormatException e) {
            throw new EtmlParseException(e);
        } catch (NullPointerException e) {
            throw new EtmlParseException(e);
        }
        return valueToReturn;
    }

}
