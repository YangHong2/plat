package com.dhlk.entity.sub;

import lombok.Data;

import java.io.Serializable;


@Data
public class ProjectDelivery implements Serializable {

    private Integer id;

    private Integer projectId;

    private String projectName;

    private String projectRemark;

    private String projectPath;

    private Integer projectStatus;

    private String projectCause;

}
