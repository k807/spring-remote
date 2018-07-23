package per.wph.remote;

import per.wph.beans.io.Resource;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-23 22:39
 * =============================================
 */
public abstract class AbstractClassLoader extends ClassLoader{


    protected ByteLoaderStrategy strategy;

    protected AbstractClassLoader(ByteLoaderStrategy strategy) {
        super(Thread.currentThread().getContextClassLoader());
        this.strategy = strategy;
    }

    public Class<?> getClass(String name) throws ClassNotFoundException {
        return loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.defineClass(name, strategy.load(name), 0 , strategy.load(name).length);
    }
}
