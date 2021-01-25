package com.github.yelzhasdev.etml.testmodels;

import com.github.yelzhasdev.etml.marker.EtmlElement;
import com.github.yelzhasdev.etml.marker.EtmlObject;

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

@EtmlObject
public class TestModel{

    @EtmlElement(selector = "head > title", mandatory = true)
    private long name;

    @EtmlElement(selector = "body > h1")
    private Long age;

    @EtmlElement(selector = "body > h2")
    private boolean isHuman;

    @EtmlElement(selector = "body > ul")
    private List<TestModelWithoutBase> attributes;

    @EtmlElement(selector = "body > p")
    private List<String> elements;

    @EtmlElement(selector = "body")
    private SubModel subModel;

    public SubModel getSubModel() {
        return subModel;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public List<String> getElements() {
        return elements;
    }

    public long getName() {
        return name;
    }

    public void setName(long name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public List<TestModelWithoutBase> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<TestModelWithoutBase> attributes) {
        this.attributes = attributes;
    }
}