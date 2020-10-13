package com.github.yelzhasdev.etml;

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

public class EtmlParseException extends RuntimeException {

    public EtmlParseException(String message) {
        super(message);
    }

    public EtmlParseException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public EtmlParseException(Throwable throwable) {
        super(throwable);
    }
}
