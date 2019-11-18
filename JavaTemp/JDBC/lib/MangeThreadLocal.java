package DBUtilsTest.utils;

import DBUtilsTest.DBUtil.C3P0Util;

import java.sql.Connection;
import java.sql.SQLException;

public class MangeThreadLocal {

    private static ThreadLocal<Connection> tl = new InheritableThreadLocal<>();

    public static Connection getConnection(){
        Connection conn = tl.get();
        if (conn == null){
            conn = C3P0Util.getConnection();
            tl.set(conn);
        }
        return conn;
    }

    public static void commit(){
        try {
            tl.get().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void begin(){
        try {
            tl.get().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(){
        try {
            tl.get().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void release(){
        try {
            tl.get().close();
            tl.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
