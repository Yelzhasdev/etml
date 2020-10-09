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
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public final class SelectorValueGenerator {

    private static Document document;

    public SelectorValueGenerator(Document document) {
        this.document = document;
    }

    public static boolean isPrimitiveOrString(Class<?> type) {
        return type.isAssignableFrom(String.class)
                || type.isAssignableFrom(Integer.TYPE)
                || type.isAssignableFrom(Float.TYPE)
                || type.isAssignableFrom(Double.TYPE)
                || type.isAssignableFrom(Long.TYPE)
                || type.isAssignableFrom(Boolean.TYPE)
                || type.isAssignableFrom(Short.TYPE);
    }

    public static <T> T getValue(Class<T> type, String selector) throws EtmlParseException {
        T valueToReturn = null;
        try {
            Element element = document.select(selector).first();
            String valueOfNode = element.text();
            if (type.isAssignableFrom(String.class)) {
                valueToReturn = (T) valueOfNode;
            }
            if (type.isAssignableFrom(Integer.TYPE)) {
                valueToReturn = (T) Integer.valueOf(valueOfNode);
            }
            if (type.isAssignableFrom(Float.TYPE)) {
                valueToReturn = (T) Float.valueOf(valueOfNode);
            }
            if (type.isAssignableFrom(Double.TYPE)) {
                valueToReturn = (T) Double.valueOf(valueOfNode);
            }
            if (type.isAssignableFrom(Long.TYPE)) {
                valueToReturn = (T) Long.valueOf(valueOfNode);
            }
            if (type.isAssignableFrom(Boolean.TYPE)) {
                if ("true".equalsIgnoreCase(valueOfNode) || "false".equalsIgnoreCase(valueOfNode)) {
                    valueToReturn = (T) Boolean.valueOf(valueOfNode);
                } else {
                    throw new EtmlParseException("Boolean must be either \"true\" or \"false\"");
                }
            }
            if (type.isAssignableFrom(Short.TYPE)) {
                valueToReturn = (T) Short.valueOf(valueOfNode);
            }
        } catch (NumberFormatException e) {
            throw new EtmlParseException(e);
        }

        return valueToReturn;
    }

//    public static <T> List<T> getValue(List<T> type, String xPathExpr) throws EtmlParseException {
//        try {
//            NodeList nodeList = (NodeList) xPath.compile(xPathExpr).evaluate(document, XPathConstants.NODESET);
//
//        } catch (XPathExpressionException e) {
//            throw new EtmlParseException(e);
//        }
//    }

}
