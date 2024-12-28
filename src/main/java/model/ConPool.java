package model;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConPool {
    private static DataSource datasource;

    public static Connection getConnection() throws SQLException {
        if (datasource == null) {
            PoolProperties p = new PoolProperties();
            p.setUrl("jdbc:mysql://voe2xwwmdq2q95ow:mt9qw738xn5w73x5@jfrpocyduwfg38kq.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/p5uucxui7h661tgv");
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername("voe2xwwmdq2q95ow");
            p.setPassword("mt9qw738xn5w73x5");
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMinIdle(10);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);
            datasource = new DataSource();
            datasource.setPoolProperties(p);
        }
        return datasource.getConnection();
    }
}
