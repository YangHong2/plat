package com.dhlk.subcontract.dao.vo;


import lombok.Data;

@Data
public class ProjectProgressVo {

    private Integer id;

    private Integer projectId;

    private Integer plan;

    private String projectDescribed;

    private String path;

}
