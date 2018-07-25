package per.wph;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-25 2:10
 * =============================================
 */
public class Father {
    protected String name;

    protected void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("i am father : " + name);
    }
}
