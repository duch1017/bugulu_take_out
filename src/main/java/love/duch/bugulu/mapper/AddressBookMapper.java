package love.duch.bugulu.mapper;

import love.duch.bugulu.entity.AddressBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author duch
 * @description 针对表【address_book(地址管理)】的数据库操作Mapper
 * @createDate 2024-05-14 20:33:33
 * @Entity love.duch.bugulu.entity.AddressBook
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}




