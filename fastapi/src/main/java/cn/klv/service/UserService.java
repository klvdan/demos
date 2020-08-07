package cn.klv.service;

import cn.klv.model.User;
import cn.klv.pojo.UserParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getList(UserParam userParam);

    int getCount(UserParam userParam);

    List<User> getListByName(Map<String, Object> map);

    List<User> getListBySex(@Param("user_sex") String sex);

    User getOne(Long id);

    User insert(User user);

    User update(User user);

    int updateXml(User user);

    int delete(Long id);
}
