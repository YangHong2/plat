package com.dhlk.light.factoryconstruction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlk.light.factoryconstruction.pojo.dto.UpdateRecordListDTO;
import com.dhlk.light.factoryconstruction.pojo.entity.UpdateRecord;
import com.dhlk.light.factoryconstruction.pojo.vo.UpdateRecordListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 设备操作修改记录mapper
 * @author yangfan
 * @since 2021-08-16
 */
@Mapper
public interface  UpdateRecordMapper   extends BaseMapper<UpdateRecord> {


    /**
     * 查询更新记录列表
     * @param page  分页条件
     * @param updateRecordListDTO 更新记录列表请求DTO
     * @return 分页列表
     */
    Page<UpdateRecordListVO> selectUpdateRecordList(IPage<UpdateRecordListVO> page, @Param("dto") UpdateRecordListDTO updateRecordListDTO);
}
