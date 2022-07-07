package com.dhlk.entity.basicmodule;

import lombok.Data;

import java.io.Serializable;


@Data
public class ProjectCheck implements Serializable {

    private Integer projectId;

    private Integer projectStatus;

    private String  projectCause;
}
