package per.wph.beans.io;

import java.io.InputStream;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-20 23:02
 * =============================================
 */
public class ClassPathResource implements Resource {
    private String name;

    public ClassPathResource(String name) {
        this.name = name;
    }

    @Override
    public InputStream getInputStream() {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
    }
}
