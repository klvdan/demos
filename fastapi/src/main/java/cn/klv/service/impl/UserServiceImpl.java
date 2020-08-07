package cn.klv.service.impl;

import cn.klv.mapper.UserMapper;
import cn.klv.model.User;
import cn.klv.pojo.UserParam;
import cn.klv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getList(UserParam userParam) {
        return userMapper.getList(userParam);
    }

    @Override
    public int getCount(UserParam userParam) {
        return userMapper.getCount(userParam);
    }

    @Override
    public List<User> getListByName(Map<String, Object> map) {
        return userMapper.getListByName(map);
    }

    @Override
    public List<User> getListBySex(String sex) {
        return userMapper.getListBySex(sex);
    }

    @Override
    public User getOne(Long id) {
        return userMapper.getOne(id);
    }

    @Override
    public User insert(User user) {
        userMapper.insert(user);
        return user;
    }

    @Override
    public User update(User user) {
        userMapper.update(user);
        return user;
    }

    @Override
    public int updateXml(User user) {
        return userMapper.updateXml(user);
    }

    @Override
    public int delete(Long id) {
        return userMapper.delete(id);
    }
}
