package net.xiayule.netbar.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by tan on 15-1-4.
 */
public class JdbcManager {

    private static JdbcTemplate jdbcTemplate;


    public static JdbcTemplate getJdbctemplate() {
        if (jdbcTemplate == null) {
            DriverManagerDataSource dataSource=new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/netbar?useUnicode=true&characterEncoding=utf8");
            dataSource.setUsername("root");
            dataSource.setPassword("0000");

            jdbcTemplate = new JdbcTemplate(dataSource);
        }

        return jdbcTemplate;
    }

    private JdbcManager() {
/*        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/netbar");
        dataSource.setUsername("root");
        dataSource.setPassword("0000");

        jdbcTemplate=new JdbcTemplate(dataSource);*/
//        jdbcTemplate.execute("create table temp(id int primary key,name varchar(32))");
    }
}
