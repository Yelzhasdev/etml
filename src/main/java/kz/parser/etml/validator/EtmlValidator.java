package kz.parser.etml.validator;

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

import kz.parser.etml.EtmlParseException;
import kz.parser.etml.marker.EtmlObject;

import java.lang.annotation.Annotation;

public final class EtmlValidator<T> {

    private static Class<?> target;

    public EtmlValidator(Class<T> target) {
        this.target = target;
    }

    public void validate() throws EtmlParseException {
        if (target == null) {
            throw new EtmlParseException("Target class can not be null.");
        }

        if (!containsAnnotation(target.getAnnotations(), EtmlObject.class)) {
            throw new EtmlParseException("Target class should be annotated with @EtmlObject.");
        }

    }

    private boolean containsAnnotation(Annotation[] annotations, Class<?> targetAnnotation) {
        Annotation[] classAnnotations = target.getAnnotations();
        for (Annotation annotation : classAnnotations) {
            if (targetAnnotation.isInstance(annotation)) {
                return true;
            }
        }
        return false;
    }
}
