package cn.klv.auth.service;

import cn.klv.auth.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User save(User user);
    void delete(Long id);
}
