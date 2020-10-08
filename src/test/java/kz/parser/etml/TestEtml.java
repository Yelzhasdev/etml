package kz.parser.etml;

import kz.parser.etml.testmodels.TestModel;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEtml {

    @Test
    public void testGeneration() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Etml etml = new Etml();
        TestModel modelFromHtml = etml.fromHtml("","", TestModel.class);
        System.out.println("Name is: " +  modelFromHtml.getName());
        System.out.println("Age is: " +  modelFromHtml.getAge());
    }
}


