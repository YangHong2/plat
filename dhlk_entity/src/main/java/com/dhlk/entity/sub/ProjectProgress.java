package com.dhlk.entity.sub;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectProgress implements Serializable {

    private Integer projectId;

    private Integer plan;

    private String projectDescribed;

    private String path;

    private String createTime;

}
