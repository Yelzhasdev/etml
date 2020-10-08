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

package kz.parser.etml;

import kz.parser.etml.validator.EtmlValidator;
import org.w3c.dom.Document;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class Etml {

    public <T> T fromHtml(Document docHtml, Class<T> classOf) throws EtmlParseException {
//        if (docHtml == null) {
//            throw new EtmlParseException("Document is null.");
//        }
        if (classOf == null) {
            throw new EtmlParseException("Object can't be null.");
        }
        EtmlValidator<T> validator = new EtmlValidator<>(classOf);
        validator.validate();
        T target = null;
        try {
            target = classOf.newInstance();

            for (Field field : target.getClass().getDeclaredFields()) {
                System.out.println("Field: " + field.getName() + " - ");
                Type type = field.getGenericType();
                if (type instanceof ParameterizedType) {
                    ParameterizedType pType = (ParameterizedType) type;
                    System.out.print("Raw type: " + pType.getRawType() + " - ");
                    System.out.println("Type args: " + pType.getActualTypeArguments()[0]);
                } else {
                    System.out.println("Type: " + field.getType());
                    if (field.getType().isAssignableFrom(String.class)) {
                        System.out.println("It's STRING!!!");
                        setField(target, "ModelName", field);
                    } else if (field.getType().isAssignableFrom(Integer.TYPE)) {
                        System.out.println("It's INT!!!");
                        setField(target, 33, field);
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

    private static void setField(Object obj, Object value, Field field) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}