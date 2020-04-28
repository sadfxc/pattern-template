package org.lcy.pattern.template.dao;

import org.lcy.pattern.template.JdbcTemplate;
import org.lcy.pattern.template.RowMapper;
import org.lcy.pattern.template.entity.Member;

import java.sql.ResultSet;
import java.util.List;

/**
 * 解耦
 */
public class MemberDao{

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(null) ;

    public List<?> query() {
        String sql = "select * from t_member";
        return jdbcTemplate.executeQuery(sql, new RowMapper<Member>() {
            public Member mapRow(ResultSet rs, int rowMap) throws Exception {
                Member member = new Member();
                member.setUsername(rs.getString("username"));
                member.setPasswd(rs.getString("passwd"));
                return member;
            }
        },null);
    }


}
