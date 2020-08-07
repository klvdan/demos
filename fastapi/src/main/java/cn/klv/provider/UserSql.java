package cn.klv.provider;

import cn.klv.model.User;
import cn.klv.pojo.UserParam;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class UserSql {

    private static final Logger log = LoggerFactory.getLogger(UserSql.class);

    public String getList(UserParam userParam)
    {
        StringBuffer sql = new StringBuffer("select id, username, password, sex");
        sql.append(" from users where 1=1 ");
        if (userParam != null) {
            if (!StringUtils.isEmpty(userParam.getUsername())){
                sql.append(" and username = #{username}");
            }
            if (!StringUtils.isEmpty(userParam.getSex())){
                sql.append(" and sex = #{sex}");
            }
        }
        sql.append(" order by id desc")
           .append(" limit ").append(userParam.getBeginLine())
           .append(",").append(userParam.getPageSize());
        log.info("getList sql is: " + sql.toString());
        return sql.toString();

    }

    public String getCount(final UserParam userParam){
        String sql = new SQL(){{
            SELECT("count(1)");
            FROM("users");
            if (!StringUtils.isEmpty(userParam.getUsername())){
                WHERE("username = #{username}");
            }
            if (!StringUtils.isEmpty(userParam.getSex())) {
                WHERE("sex = #{sex}");
            }
        }}.toString();

        log.info("getCount sql is: " + sql);
        return sql;
    }

    public String update(final User user) {
        String sql = new SQL(){{
            UPDATE("users");
            if (!StringUtils.isEmpty(user.getUsername())) {
                SET("username = #{username}");
            }
            if (!StringUtils.isEmpty(user.getPassword())) {
                SET("password = #{password}");
            }
            if (!StringUtils.isEmpty(user.getSex())) {
                SET("sex = #{sex}");
            }
            WHERE("id = #{id}");
        }}.toString();
        log.info("update sql is: " + sql);
        return sql;
    }
}
