package cn.klv.auth.api;

import cn.klv.auth.entity.User;
import cn.klv.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "api v1")
@RestController
@RequestMapping("/api/v1")
public class UserApi {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get User List", notes = "Get User List")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataTypeClass = String.class,name = "Authorization", value = "Access Token", required = true)
    })
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List listUser(){
        return userService.findAll();
    }

    @ApiOperation(value = "Create User", notes = "Create User")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataTypeClass = String.class,name = "Authorization", value = "Access Token", required = true)
    })
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @ApiOperation(value = "Delete User", notes = "Delete User")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataTypeClass = String.class,name = "Authorization", value = "Access Token", required = true)
    })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "success";
    }
}
