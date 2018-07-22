package per.wph;

import java.util.List;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-21 21:06
 * =============================================
 */
public class Bus {
    private int age;
    private String name;

    private List<String> wheels;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getWheels() {
        return wheels;
    }

    public void setWheels(List<String> wheels) {
        this.wheels = wheels;
    }
}
