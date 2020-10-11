package kz.parser.etml.testmodels;

import kz.parser.etml.marker.EtmlElement;

import java.util.List;

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