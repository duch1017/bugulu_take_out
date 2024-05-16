package love.duch.bugulu.dto;

import love.duch.bugulu.entity.Setmeal;
import love.duch.bugulu.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
