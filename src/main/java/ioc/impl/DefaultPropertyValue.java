package ioc.impl;

import ioc.PropertyValue;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-20 23:23
 * =============================================
 */
public class DefaultPropertyValue implements PropertyValue {
    private String name;
    private String value;

    public DefaultPropertyValue(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
