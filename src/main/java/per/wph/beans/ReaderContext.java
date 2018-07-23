package per.wph.beans;

import java.util.concurrent.ConcurrentHashMap;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-23 23:45
 * =============================================
 */
public class ReaderContext {
    private ConcurrentHashMap<String,String> properyMap = new ConcurrentHashMap();

    public String getProperty(String key){
        return properyMap.get(key);
    }

    public void setProperty(String key, String value){
        this.properyMap.put(key,value);
    }
}
