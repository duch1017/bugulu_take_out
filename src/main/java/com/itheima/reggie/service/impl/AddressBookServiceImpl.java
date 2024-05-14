package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.AddressBook;
import com.itheima.reggie.service.AddressBookService;
import com.itheima.reggie.mapper.AddressBookMapper;
import org.springframework.stereotype.Service;

/**
* @author duch
* @description 针对表【address_book(地址管理)】的数据库操作Service实现
* @createDate 2024-05-14 20:33:33
*/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
    implements AddressBookService{

}




