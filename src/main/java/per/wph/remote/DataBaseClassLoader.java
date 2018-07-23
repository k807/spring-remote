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
public class DataBaseClassLoader extends AbstractClassLoader {


    public DataBaseClassLoader(DatabaseStrategy strategy){
        super(strategy);
    }

}
