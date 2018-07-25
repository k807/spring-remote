package per.wph;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-25 1:42
 * =============================================
 */
public class Person {
    HelloWorldService service;

    public HelloWorldService getService() {
        return service;
    }

    public void setService(HelloWorldService service) {
        this.service = service;
    }

    public void invoke(){
        this.service.helloWorld();
    }
}
