package kz.parser.etml.utils;

import java.lang.reflect.InvocationTargetException;

public final class ReflectHelper {

    public static void setterFromReflect(Object obj, Object value, String fieldName, Class<?> classOf) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        obj.getClass().getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1),classOf).invoke(obj, value);
    }
}
