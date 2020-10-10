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
import kz.parser.etml.marker.EtmlElement;
import org.jsoup.nodes.Document;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class EtmlParser<T> extends EtmlBaseParser<T> {

    @Override
    public T parse(Class<T> classOf, Document document) throws EtmlParseException {
        T target;
        try {
            target = classOf.newInstance();
            System.out.println("Clazz: " + target.getClass().getName() + " - ");
            for (Field field : target.getClass().getDeclaredFields()) {
                fieldProcessor(field, target, document);
            }
        } catch (InstantiationException e) {
            throw new EtmlParseException(e);
        } catch (IllegalAccessException e) {
            throw new EtmlParseException(e);
        }
        return target;
    }

    private void fieldProcessor(Field fieldToProcess, T target, Document document) {
        System.out.println("Field: " + fieldToProcess.getName() + " - ");
        for (Annotation fieldAn : fieldToProcess.getDeclaredAnnotations()) {
            if (fieldAn instanceof EtmlElement) {
                EtmlElement etmlFieldAnnon = (EtmlElement) fieldAn;
                if (etmlFieldAnnon.selector().isEmpty()) {
                    throw new EtmlParseException("Field selector must not be empty.");
                }
                Type type = fieldToProcess.getGenericType();
                if (type instanceof ParameterizedType) {
                    ParameterizedType pType = (ParameterizedType) type;
                    if (fieldToProcess.getType().isAssignableFrom(List.class) ) {
                        List<?> targetList = SelectorValueGenerator.getValues((Class<T>) pType.getActualTypeArguments()[0],etmlFieldAnnon.selector(),document);
                        setField(target, targetList, fieldToProcess);
                        System.out.print("Raw type: " + pType.getRawType() + " - ");
                        System.out.println("Type args: " + pType.getActualTypeArguments()[0]);
                        System.out.println("List args: " + targetList);
                    }
                } else {
                    System.out.println("Type: " + fieldToProcess.getType());
                    System.out.println("Field selector: " + etmlFieldAnnon.selector());

                    if (SelectorValueGenerator.isPrimitiveOrString(fieldToProcess.getType())) {
                        Object value = SelectorValueGenerator.getValue(fieldToProcess.getType(), etmlFieldAnnon.selector(), document);
                        setField(target, value, fieldToProcess);
                    }
                }
            }
        }
    }
}
