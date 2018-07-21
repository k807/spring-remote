package test;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-21 20:08
 * =============================================
 */

/**
 <bean id="car" class="bean.Car">
 <property name="name" value="Mercedes Benz G 500"/>
 <property name="length" value="4717mm"/>
 <property name="width" value="1855mm"/>
 <property name="height" value="1949mm"/>
 <property name="wheel" ref="wheel"/>
 </bean>
 */
public class Car {
    private String name;
    private String length;
    private String width;
    private String height;
    private Wheel wheel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }
}
