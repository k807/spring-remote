package per.wph.remote;

import per.wph.beans.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-23 23:39
 * =============================================
 */
public class DatabaseStrategy implements ByteLoaderStrategy {

    private RemoteDBConfiguer dbConfiguer;

    public DatabaseStrategy(RemoteDBConfiguer dbConfiguer) {
        this.dbConfiguer = dbConfiguer;
    }



    @Override
    public byte[] load(String name) {
        InputStream is = null;
        Connection conn = ConnectionManger.getConn();
        try {
            String sql = "SELECT * FROM class WHERE name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "1");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                is = resultSet.getBlob(3).getBinaryStream();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return doLoadByte(is);
    }



    private byte[] doLoadByte(InputStream is){
        ByteArrayOutputStream baos = null;
        baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int length = 0;
        try {
            while ((length = is.read(bytes)) != -1) {
                baos.write(bytes,0,length);
            }
            FileOutputStream fios = new FileOutputStream("temp.txt");
            fios.write(baos.toByteArray());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return baos.toByteArray();
    }
}
