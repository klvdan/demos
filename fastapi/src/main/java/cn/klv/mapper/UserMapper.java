package cn.klv.mapper;

import cn.klv.constant.SexEnum;
import cn.klv.model.User;
import cn.klv.pojo.UserParam;
import cn.klv.provider.UserSql;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "username", column = "username", javaType = String.class),
            @Result(property = "sex", column = "sex", javaType = SexEnum.class)
    })
    List<User> getAll();

    @SelectProvider(type = UserSql.class, method = "getList")
    List<User> getList(UserParam userParam);

    @SelectProvider(type = UserSql.class, method = "getCount")
    int getCount(UserParam userParam);

    @Select("SELECT * FROM users WHERE username = #{username}")
    List<User> getListByName(Map<String, Object> map);

    @Select("SELECT * FROM users WHERE sex = #{user_sex}")
    List<User> getListBySex(@Param("user_sex") String sex);

    @Select("SELECT * FROM users WHERE ID = #{id}")
    @Results({
            @Result(property = "username", column = "username", javaType = String.class),
            @Result(property = "sex", column = "sex", javaType = SexEnum.class)
    })
    User getOne(Long id);

    @Insert("INSERT INTO users (username, password, sex) VALUES(#{username}, #{password}, #{sex})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @UpdateProvider(type = UserSql.class, method = "update")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int update(User user);

    @Update({
            "<script>",
            "UPDATE users ",
            "<set>",
            " <if test='username != null'>username=#{username}</if>",
            " </set> ",
            "WHERE id=#{id} ",
            "</script>"
    })
    int updateXml(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    int delete(Long id);
}
