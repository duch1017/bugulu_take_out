package love.duch.bugulu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.duch.bugulu.entity.AddressBook;
import love.duch.bugulu.service.AddressBookService;
import love.duch.bugulu.mapper.AddressBookMapper;
import org.springframework.stereotype.Service;

/**
* @author duch
* @description 针对表【address_book(地址管理)】的数据库操作Service实现
* @createDate 2024-05-14 20:33:33
*/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
    implements AddressBookService {

}




