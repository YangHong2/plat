package com.dhlk.basicmodule.service.dao;

import com.dhlk.entity.basicmodule.ModuleClick;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * <p>
 * 模块点击 Dao 接口
 * </p>
 *
 * @author xkliu
 * @since 2020-08-24
 */
@Repository
public interface ModuleClickDao {

    ModuleClick selectModuleClick(@Param("loginName") String loginName, @Param("moduleName") String moduleName);

    Integer insert(ModuleClick moduleClick);

    Integer update(ModuleClick moduleClick);

    List<ModuleClick> findListByLoginName(@Param("loginName") String loginName);

}
