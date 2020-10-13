package com.github.yelzhasdev.etml.testmodels;

import com.github.yelzhasdev.etml.marker.EtmlElement;

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

public class TestModelWithoutBase {
    @EtmlElement(selector = "li")
    private String test;

    @EtmlElement(selector = "h1")
    private int numb;

    @EtmlElement(selector = "ol")
    private List<String> testText;

    public List<String> getTestText() {
        return testText;
    }

    public String getTest() {
        return test;
    }

    public int getNumb() {
        return numb;
    }
}