package love.duch.bugulu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.duch.bugulu.entity.User;
import love.duch.bugulu.service.UserService;
import love.duch.bugulu.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author duch
* @description 针对表【user(用户信息)】的数据库操作Service实现
* @createDate 2024-05-14 19:42:00
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




