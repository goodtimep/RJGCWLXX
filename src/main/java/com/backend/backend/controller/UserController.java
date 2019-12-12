package com.backend.backend.controller;

import com.backend.backend.common.model.ResponseModel;
import com.backend.backend.jwt.JwtUtil;
import com.backend.backend.model.entity.User;
import com.backend.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户控制器
 *
 * @author goodtimp
 */
@Api("用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "hello world!";
    }

    @GetMapping("/getCurrent")
    @ResponseBody
    @ApiOperation(value = "得到当前登陆用户信息")
    public ResponseModel getCurrent() {
        User user = JwtUtil.getCurrentUser();
        return ResponseModel.success("data", user);
    }

    @ApiOperation(value = "注册", notes = "注册用户 参数示例：{\n" +
            "\t\"username\":\"goodtimp\",\n" +
            "\t\"password\":\"123456\"\n" +
            "}")
    @PostMapping("/login")
    @ResponseBody
    public ResponseModel login(@RequestBody Map<String, Object> map, HttpServletResponse httpServletResponse) {
        String name = (String) map.get("username");
        String password = (String) map.get("password");
        // 查询数据库中的帐号信息
        User user = userServiceImpl.login(name, password);

        // 新增token信息,如果已经有登陆过了下面会刷新refreshToken的时间戳，使得先登录的被t除去
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        String token = JwtUtil.sign(user.getUserId().toString(), user.getUserName(), user.getType().toString(), currentTimeMillis);
        // 添加refreshToken
        JwtUtil.addRefreshToken(user.getUserId().toString(), user.getUserName(), currentTimeMillis);
        // 修改sAuthorization返回AccessToken，时间戳为当前时间戳
        httpServletResponse.setHeader("Authorization", token);
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
        return ResponseModel.success("Login Success.");
    }

    @ApiOperation(value = "注册", notes = "注册用户:参数示例：" +
            "{\n" +
            "\t\"name\":\"goodtimp\",\n" +
            "\t\"userName\":\"goodtimp\",\n" +
            "\t\"userPassword\":\"123456\",\n" +
            "\t\"phone\":\"123\"\n" +
            "\t\"type\":\"1（用户类型1用户，2驿站管理员）\"\n" +
            "}")
    @PostMapping("/signIn")
    @ResponseBody
    public ResponseModel signIn(@RequestBody User user) {
        User sqlUser = userServiceImpl.signIn(user);
        if (sqlUser == null) {
            return ResponseModel.fail("账号已存在");
        }
        return ResponseModel.success("注册成功");
    }
}
