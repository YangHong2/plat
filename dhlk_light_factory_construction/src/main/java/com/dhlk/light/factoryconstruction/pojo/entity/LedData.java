package com.dhlk.light.factoryconstruction.pojo.entity;


import com.dhlk.light.factoryconstruction.enums.DeviceReadEnum;
import com.dhlk.light.factoryconstruction.enums.DeviceTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.*;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设备对象
 *
 * @author wzx
 * @date 2021/8/9
 */
@Builder
@Data
@ApiModel(value = "led", description = "设备")
@NoArgsConstructor
@AllArgsConstructor
public class LedData {
    /**
     * sn号
     */
    @ApiModelProperty(value = "sn号")
    private String sn;
    /**
     * 人感配置数据
     */
    @ApiModelProperty(value = "人感配置数据")
    private HumanFeelConfigData humanFeelConfigData;
    /**
     * 人感状态数据
     */
    @ApiModelProperty(value = "人感状态数据")
    private HumanFeelData humanFeelData;
    /**
     * 照明状态数据
     */
    @ApiModelProperty(value = "照明状态数据")
    private LightingStatusData lightingStatusData;
    /**
     * wifi配置数据
     */
    @ApiModelProperty(value = "wifi配置数据")
    private WifiConfigData wifiConfigData;
    /**
     * 光感状态数据
     */
    @ApiModelProperty(value = "光感状态")
    private LightFeelData lightFeelData;
    /**
     * 光感配置数据
     */
    @ApiModelProperty(value = "光感配置数据")
    private LightFeelConfigData lightFeelConfigData;
    /**
     * 固件版本
     */
    @ApiModelProperty(value = "固件版本")
    private String version;
    /**
     * 上报时隙
     */
    @ApiModelProperty(value = "上报时隙")
    private String reportTimeSlot;
    /**
     * 设备类型 参考
     *
     * @see DeviceTypeEnum
     */
    @ApiModelProperty(value = "设备类型")
    private String type;
    /**
     * 绑定AP false 未绑定 true 已绑定
     * 默认未绑定
     */
    @ApiModelProperty(value = "绑定AP false 未绑定 true 已绑定")
    private Boolean ap;
    /**
     * 设备整体读取标识 0 准备读取 1 读取中 2 读取完毕
     *
     * @see DeviceReadEnum
     */
    @ApiModelProperty(hidden = true)
    private volatile String readFlag;
    /**
     * wifi读取标识 0 准备读取 1 读取中 2 读取完毕
     *
     * @see DeviceReadEnum
     */
    @ApiModelProperty(hidden = true)
    private volatile String wifiReadFlag;
    /**
     * 人感配置读取标识 0 准备读取 1 读取中 2 读取完毕
     *
     * @see DeviceReadEnum
     */
    @ApiModelProperty(hidden = true)
    private volatile String humanConfigReadFlag;
    /**
     * 光感配置读取标识 0 准备读取 1 读取中 2 读取完毕
     *
     * @see DeviceReadEnum
     */
    @ApiModelProperty(hidden = true)
    private volatile String lightConfigReadFlag;
    /**
     * 固件信息读取 0 准备读取 1 读取中 2 读取完毕
     *
     * @see DeviceReadEnum
     */
    @ApiModelProperty(hidden = true)
    private volatile String versionReadFlag;
    /**
     * 上报时隙读取 0 准备读取 1 读取中 2 读取完毕
     *
     * @see DeviceReadEnum
     */
    @ApiModelProperty(hidden = true)
    private volatile String reportTimeSlotReadFlag;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LedData)) {
            return false;
        }
        LedData ledData = (LedData) o;
        return Objects.equal(getSn(), ledData.getSn());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getSn());
    }
}
