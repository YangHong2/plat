package com.dhlk.entity.basicmodule;


import lombok.Data;

@Data
public class ProjectProgressVo {

    private Integer id;

    private Integer projectId;

    private Integer plan;

    private String projectDescribed;

    private String path;

}
