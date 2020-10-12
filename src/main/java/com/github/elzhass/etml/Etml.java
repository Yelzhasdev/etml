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

import kz.parser.etml.reflection.EtmlParser;
import kz.parser.etml.validator.EtmlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public final class Etml {

    public <T> T fromHtml(String html, Class<T> classOf) throws EtmlParseException {
        if (html.isEmpty()) {
            throw new EtmlParseException("HTML string can not be empty.");
        }
        T target = fromHtml(Jsoup.parse(html), classOf);
        return target;
    }

    public <T> T fromHtml(Document document, Class<T> classOf) throws EtmlParseException {
        if (document == null) {
            throw new EtmlParseException("Document is null.");
        }
        EtmlValidator<T> validator = new EtmlValidator<>(classOf);
        validator.validate();
        if (classOf == null) {
            throw new EtmlParseException("Object can't be null.");
        }
        EtmlParser<T> parser = new EtmlParser<>();
        T target = parser.parse(classOf, document);
        return target;
    }

}