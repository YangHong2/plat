package com.dhlk.light.factoryconstruction.pojo.entity;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.command.CommandParam;
import lombok.Data;

/**
 * 命令对象
 * @author yangfan
 * @since 2021-08-10
 */
@Data
public class Command {

    /**
     * 命令
     */
    CommandTypeEnum commandTypeEnum;

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 命令参数
     */
    private CommandParam commandParam;
}
