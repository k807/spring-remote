package per.wph.remote;

import org.junit.Test;
import per.wph.HelloWorldService;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-23 23:01
 * =============================================
 */

public class EntityResourceTest {


    @Test
    public void testDBConnect() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        RemoteClassLoader classLoader = new RemoteClassLoader(new DatabaseStrategy());
        classLoader.loadClass("per.wph.HelloWorldServiceImpl");
        Class clazz = classLoader.getClass("per.wph.HelloWorldServiceImpl");
        HelloWorldService service = (HelloWorldService) clazz.newInstance();
        service.helloWorld();
    }
}
