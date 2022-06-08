package com.dhlk.light.factoryconstruction.annotation;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;

import java.lang.annotation.*;

/**
 * 设备上报类型注解
 *
 * @author wzx
 * @since 2021-08-17
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DeviceReportType {

    CommandTypeEnum type();
}
