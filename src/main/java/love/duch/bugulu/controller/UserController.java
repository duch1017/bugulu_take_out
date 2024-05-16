package love.duch.bugulu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import love.duch.bugulu.common.Result;
import love.duch.bugulu.entity.User;
import love.duch.bugulu.service.UserService;
import love.duch.bugulu.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * 发送验证码
     *
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public Result<String> sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)) {
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
//            SMSUtils.sendMessage("", "", phone, code);
            log.info("验证码={}", code);
            session.setAttribute(phone, code);
            return Result.success("短信发送成功");
        }
        return Result.error("短信发生失败");
    }

    /**
     * 移动端用户登录
     *
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody Map<String, String> map, HttpSession session) {
        log.info("map:{}", map);
        String phone = map.get("phone");
        String code = map.get("code");
        Object codeInSeesion = session.getAttribute(phone);
        if (codeInSeesion != null && codeInSeesion.equals(code)) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());
            return Result.success(user);
        }
        return Result.error("登陆失败");
    }

    /**
     * 用户退出
     *
     * @param request
     * @return
     */
    @PostMapping("/loginout")
    public Result<String> loginout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return Result.success("退出成功");
    }
}
