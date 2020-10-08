package kz.parser.etml.testmodels;

import kz.parser.etml.marker.EtmlObject;

import java.util.List;

@EtmlObject
public class TestModel {

    private String name;

    private int age;

    private List<TestModel> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<TestModel> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<TestModel> attributes) {
        this.attributes = attributes;
    }
}