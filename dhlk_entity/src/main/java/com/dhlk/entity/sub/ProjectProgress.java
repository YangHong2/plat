package com.dhlk.entity.sub;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProjectProgress implements Serializable {

    private Integer projectId;

    private Integer plan;

    private String projectDescribed;

    private String path;

    private Date createTime;

}
