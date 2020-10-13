package com.github.yelzhasdev.etml.reflection;

import com.github.yelzhasdev.etml.EtmlParseException;
import org.jsoup.nodes.Document;

import java.lang.reflect.Field;

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

abstract class EtmlBaseParser<T> {

    abstract public T parse(Class<T> target, Document document);

    static void setField(Object obj, Object value, Field field) throws EtmlParseException {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            throw new EtmlParseException(e);
        }
    }
}
