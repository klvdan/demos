package cn.klv.auth.repo;

import cn.klv.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("from User where username = :username")
    User findByUsername(String username);
}
