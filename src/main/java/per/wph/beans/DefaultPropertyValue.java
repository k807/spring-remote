package per.wph.beans;

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
    private Object object;

    public DefaultPropertyValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getValue() {
        return this.value;
    }

}
