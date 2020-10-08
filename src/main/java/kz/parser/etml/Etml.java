package kz.parser.etml;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static kz.parser.etml.utils.ReflectHelper.setterFromReflect;

public final class Etml {

    public <T> T fromHtml(String html, String configuration, Class<T> classOf) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T target = classOf.newInstance();
        for (Field field : target.getClass().getDeclaredFields()) {
            System.out.println("Field: " + field.getName() + " - ");
            Type type = field.getGenericType();
            if (type instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) type;
                System.out.print("Raw type: " + pType.getRawType() + " - ");
                System.out.println("Type args: " + pType.getActualTypeArguments()[0]);
            } else {
                System.out.println("Type: " + field.getType());
                if (field.getType().isAssignableFrom(String.class)){
                    System.out.println("It's STRING!!!");
                    setterFromReflect(target, "ModelName", field.getName(), String.class);
                } else if (field.getType().isAssignableFrom(Integer.TYPE)) {
                    System.out.println("It's INT!!!");
                    setterFromReflect(target, 33, field.getName(), Integer.TYPE);
                }
            }
        }

        return target;
    }

}