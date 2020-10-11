package kz.parser.etml.testmodels;

import kz.parser.etml.marker.EtmlElement;
import kz.parser.etml.marker.EtmlObject;

import java.util.List;

@EtmlObject
public class TestModel{

    @EtmlElement(selector = "head > title")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
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