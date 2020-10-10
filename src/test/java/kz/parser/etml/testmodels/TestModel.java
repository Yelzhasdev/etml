package kz.parser.etml.testmodels;

import kz.parser.etml.marker.EtmlElement;
import kz.parser.etml.marker.EtmlObject;

import java.util.List;

@EtmlObject
public class TestModel{

    @EtmlElement(selector = "head > title")
    private String name;

    @EtmlElement(selector = "body > h1")
    private int age;

    @EtmlElement(selector = "body > ul")
    private List<TestModelWithoutBase> attributes;

    @EtmlElement(selector = "body > p")
    private List<String> elements;

    public List<String> getElements() {
        return elements;
    }

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

    public List<TestModelWithoutBase> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<TestModelWithoutBase> attributes) {
        this.attributes = attributes;
    }
}