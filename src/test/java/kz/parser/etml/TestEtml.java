package kz.parser.etml;

import kz.parser.etml.testmodels.TestModel;
import org.junit.jupiter.api.Test;

public class TestEtml {

    @Test
    public void testGeneration() {
        Etml etml = new Etml();
        TestModel modelFromHtml = etml.fromHtml(null, TestModel.class);
        System.out.println("Name is: " +  modelFromHtml.getName());
        System.out.println("Age is: " +  modelFromHtml.getAge());
    }
}


