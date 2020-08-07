package cn.klv.mapper;

import cn.klv.constant.SexEnum;
import cn.klv.model.User;
import cn.klv.pojo.UserParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User("test1", "pass", SexEnum.MAN);
        userMapper.insert(user);
        Assert.assertTrue(3L == user.getId());
    }

    @Test
    public void testUpdate() {
        long id = 1;
        String newName = "test2";
        User user = userMapper.getOne(id);
        if (user != null) {
            System.out.println(user.toString());
            user.setUsername(newName);
            userMapper.update(user);
            Assert.assertEquals(newName, userMapper.getOne(id).getUsername());
        } else {
            System.out.println("not found user id=1");
        }
    }

    @Test
    public void testDelete() {
        userMapper.delete(2L);
        Assert.assertEquals(0, userMapper.getCount(new UserParam()));
    }
}
