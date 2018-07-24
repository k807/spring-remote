package per.wph.remote;

import per.wph.beans.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-23 22:42
 * =============================================
 */
public class RemoteClassLoader extends AbstractClassLoader {


    /**
     * 使用策略模式，使用strategy获取class文件的byte[]
     * @param strategy
     */
    public RemoteClassLoader(DatabaseStrategy strategy){
        super(strategy);
    }

}
