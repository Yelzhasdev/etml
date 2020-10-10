package kz.parser.etml.testmodels;

import kz.parser.etml.marker.EtmlElement;

public class TestModelWithoutBase {

    @EtmlElement(selector = "li")
    private String test;

    @EtmlElement(selector = "h1")
    private int numb;

    public String getTest() {
        return test;
    }

    public int getNumb() {
        return numb;
    }
}