package xyz.yhsj.yhutils.tools;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 获取泛型
 * Created by LOVE on 2015/12/25.
 */
public class ClassUtils {

    public static <T> Class<T> getGenericClass(Class<?> klass) {
        Type type = klass.getGenericSuperclass();
        if (type == null || !(type instanceof ParameterizedType)) return null;
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        if (types == null || types.length == 0) return null;
        return (Class<T>) types[0];
    }
}
