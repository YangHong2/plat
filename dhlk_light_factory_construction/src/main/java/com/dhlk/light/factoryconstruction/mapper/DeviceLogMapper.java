package com.dhlk.light.factoryconstruction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlk.light.factoryconstruction.pojo.dto.CommandLogDTO;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceLog;
import com.dhlk.light.factoryconstruction.pojo.vo.DeviceLogVO;
import org.apache.ibatis.annotations.Param;

/**
 *  命令日志  mapper
 * @author yangfan
 * @since 2021-08-18
 */
public interface DeviceLogMapper extends BaseMapper<DeviceLog> {

    /**
     * 查询命令日志列表
     * @param page  分页条件
     * @param commandLogDTO 请求DTO
     * @return 分页列表
     */
    Page<DeviceLogVO> selectCommandLogList(IPage<DeviceLogVO> page, @Param("dto") CommandLogDTO commandLogDTO);

    /**
     * 删除设备照明状态数据
     * @param clearTime 清理时间
     */
    Integer deleteLightStatusData(String clearTime);

    /**
     * 删除设备数据
     * @param clearTime 清理时间
     */
    Integer deleteData(String clearTime);
}
