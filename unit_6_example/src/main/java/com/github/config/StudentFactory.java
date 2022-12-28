package com.github.config;

import com.github.dao.StudentDao;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StudentFactory {

    private final Reflections reflections = new Reflections("com.github");
    private static final Map<Class<?>, Object> map = new HashMap<>();

    public void configure() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Set<Class<? extends StudentDao>> subTypesOf = reflections.getSubTypesOf(StudentDao.class);
        //System.out.println("subTypesOf = " + subTypesOf);
        for (Class<? extends StudentDao> aClass : subTypesOf) {
            if (aClass.isAnnotationPresent(Release.class)) {
                System.out.println("aClass = " + aClass);
                map.put(StudentDao.class, aClass.getDeclaredConstructor().newInstance());
            }
        }
    }

    public static Object getImplementationByClass(Class<?> gClass) {
        return map.get(gClass);
    }
}
