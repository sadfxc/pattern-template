package org.lcy.pattern.template;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    private PreparedStatement createPreparedStatement(Connection con,String sql) throws SQLException {
        return con.prepareStatement(sql);
    }

    private ResultSet executQuery(PreparedStatement pstmt,Object[] value) throws SQLException {
        for(int i = 0; i<value.length;i++) {
            pstmt.setObject(i,value[i]);
        }
        return pstmt.executeQuery();
    }


    private void closeStatement(Statement stmt) throws Exception {
        stmt.close();
    }
    private void closeConnection(Connection conn) throws Exception {
        //把它放到连接池中
//        conn.close();
    }
    private void closeResultSet(ResultSet rs) throws Exception {
        rs.close();
    }

    private List<?> parseResultSet(ResultSet rs,RowMapper rowMapper) throws Exception {
        int rowNum = 1;
        List<Object> result = new ArrayList<Object>();
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs,rowNum ++));
        }
        return result;
    }

    public  List<?> executeQuery(String sql,RowMapper<?> rowMapper ,Object[] values) {
        try {
            //1、获取连接
            Connection conn = this.getConnection();
            //2、创建语句集
            PreparedStatement pstms = this.createPreparedStatement(conn,sql);

            //3、执行语句集，并获取结果集
//            pstms.set
            ResultSet rs = this.executQuery(pstms,values);

            //4、解析语句集
            List<?> result = this.parseResultSet(rs, rowMapper);
            //5、关闭结果集
            this.closeResultSet(rs);
            //6、关闭语句集
            this.closeStatement(pstms);
            //7、关闭连接
            this.closeConnection(conn);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

   // public Object processResult(ResultSet rs,int rowNum) throws SQLException;

}
