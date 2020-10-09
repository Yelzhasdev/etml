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
import kz.parser.etml.validator.EtmlValidator;
import org.jsoup.nodes.Document;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

public class EtmlParser<T> extends EtmlBaseParser<T> {

    @Override
    public T parse(Class<T> classOf, Document document) throws EtmlParseException {
        EtmlValidator<T> validator = new EtmlValidator<>(classOf);
        validator.validate();
        T target;
        try {
            target = classOf.newInstance();
            if (Collection.class.isAssignableFrom(classOf)) {
                System.out.println("Found a collection");
                Type collectionType = ((ParameterizedType) target).getActualTypeArguments()[0];
            }
            for (Field field : target.getClass().getDeclaredFields()) {
                System.out.println("Field: " + field.getName() + " - ");
                for (Annotation fieldAn : field.getDeclaredAnnotations()) {
                    if (fieldAn instanceof EtmlElement) {
                        EtmlElement  etmlFieldAnnon = (EtmlElement) fieldAn;
                        if (etmlFieldAnnon.selector().isEmpty()) {
                            throw new EtmlParseException("Field xPath must not be empty.");
                        }
                        Type type = field.getGenericType();
                        if (type instanceof ParameterizedType) {
                            ParameterizedType pType = (ParameterizedType) type;
                            System.out.print("Raw type: " + pType.getRawType() + " - ");
                            System.out.println("Type args: " + pType.getActualTypeArguments()[0]);
                        } else {
                            System.out.println("Type: " + field.getType());
                            if (SelectorValueGenerator.isPrimitiveOrString(field.getType())) {
//                                if (field.getType().isAssignableFrom(String.class)) {
//                                    System.out.println("It's STRING!!!");
//                                    setField(target, "ModelName", field);
//                                } else
                                Object value = SelectorValueGenerator.getValue(field.getType(),etmlFieldAnnon.selector(), document);
                                setField(target, value, field);
//                                if (field.getType().isAssignableFrom(Integer.TYPE)) {
//                                    System.out.println("It's INT!!!");
//                                    setField(target, 33, field);
//                                }
                            }
                        }
                    }
                }
            }
        } catch (InstantiationException e) {
            throw new EtmlParseException(e);
        } catch (IllegalAccessException e) {
            throw new EtmlParseException(e);
        }
        return target;
    }

}
