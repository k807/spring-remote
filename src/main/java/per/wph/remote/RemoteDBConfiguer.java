package per.wph.remote;

import java.sql.Driver;

public interface RemoteDBConfiguer {
    void configureDriver(String driver);
    void configureHost(String host);
    void configureUsername(String username);
    void configurePassowrd(String password);
}
