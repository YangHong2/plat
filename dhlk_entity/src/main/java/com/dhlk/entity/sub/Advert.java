package com.dhlk.entity.sub;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告(Advert)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:20:50
 */
public class Advert implements Serializable {
    private static final long serialVersionUID = 763368482063409745L;
    /**
     * 主键 id
     */
    private Integer id;
    /**
     * 位置编码
     */
    private String addressNo;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片
     */
    private String dataId;
    /**
     * 长 /像素
     */
    private Double pixelLength;
    /**
     * 宽 /像素
     */
    private Double pixelWide;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 伪删除 默认为 0  删除改为 1
     */
    private Integer isDelete;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    private String pname;

    private String path;

    private String suffix;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public Double getPixelLength() {
        return pixelLength;
    }

    public void setPixelLength(Double pixelLength) {
        this.pixelLength = pixelLength;
    }

    public Double getPixelWide() {
        return pixelWide;
    }

    public void setPixelWide(Double pixelWide) {
        this.pixelWide = pixelWide;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}
