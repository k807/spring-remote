package per.wph.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-20 23:21
 * =============================================
 */
public class PropertyValues{
    private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    public PropertyValues() {
    }

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    public List<PropertyValue> getPropertyValues() {
        return this.propertyValueList;
    }
}
