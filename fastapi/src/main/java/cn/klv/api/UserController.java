package cn.klv.api;

import cn.klv.model.User;
import cn.klv.pojo.UserParam;
import cn.klv.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "User model api")
@RequestMapping("/api/v1")
public class UserController {

    /**
     * 接口规范
     * GET（SELECT）：从服务器取出资源（一项或多项）。
     * POST（CREATE）：在服务器新建一个资源。
     * PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。全部更新
     * PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。部分更新
     * DELETE（DELETE）：从服务器删除资源。
     *
     * 返回规范
     * GET /collection：返回资源对象的列表（数组）
     * GET /collection/resource：返回单个资源对象
     * POST /collection：返回新生成的资源对象
     * PUT /collection/resource：返回完整的资源对象
     * PATCH /collection/resource：返回完整的资源对象
     * DELETE /collection/resource：返回一个空文档
     */

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get User List", notes = "Get User List")
    @GetMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUserList() {
        return userService.getList(new UserParam());
    }

    @ApiOperation(value = "Create new user", notes = "Create new user")
    @PostMapping(value = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addUser(@RequestBody User user) {
        return userService.insert(user);
    }

    @ApiOperation(value = "Get one user", notes = "Get one user")
    @GetMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getUser(@PathVariable("id") long id) {
        return userService.getOne(id);
    }

    @ApiOperation(value = "Delete one user", notes = "Delete one user")
    @DeleteMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
    }

    @ApiOperation(value = "Update one user", notes = "Update one user")
    @PatchMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Object updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return userService.update(user);
    }

    @ApiOperation(value = "Test", notes = "Test")
    @GetMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public String test() {
        return "hello";
    }
}
