package cn.klv.model;


import cn.klv.constant.SexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class User implements Serializable {
    private static final long serailVersionUID = 1L;
    @ApiModelProperty(value = "User ID")
    private Long id;
    @ApiModelProperty(value = "Username")
    private String username;
    @ApiModelProperty(value = "Password")
    private String password;
    @ApiModelProperty(value = "Sex")
    private SexEnum sex;

    public User()
    {
        super();
    }

    public User(String username, String password, SexEnum sex) {
        super();
        this.username = username;
        this.password = password;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                '}';
    }
}
