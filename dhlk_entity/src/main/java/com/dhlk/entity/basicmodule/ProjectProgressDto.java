package com.dhlk.entity.basicmodule;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectProgressDto implements Serializable {
    private Integer id;

    private Integer projectId;

    private Integer plan;

    private String projectDescribed;

    private String path;

    private String createTime;
}
