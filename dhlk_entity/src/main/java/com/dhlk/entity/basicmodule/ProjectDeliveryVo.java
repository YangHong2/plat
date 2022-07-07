package com.dhlk.entity.basicmodule;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectDeliveryVo implements Serializable {

    private Integer projectId;

    private String projectName;

    private String projectRemark;

    private String projectPath;

    private String projectCause;
}
