package love.duch.bugulu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import love.duch.bugulu.entity.Category;

public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
