package com.admin;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

public class PrimefacesShowcaseUtil {

    private PrimefacesShowcaseUtil() {

    }


    public static final Object getPropertyValueViaReflection(Object o, String field)
            throws ReflectiveOperationException, IllegalArgumentException, IntrospectionException {
        return new PropertyDescriptor(field, o.getClass()).getReadMethod().invoke(o);
    }
}
