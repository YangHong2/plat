package com.dhlk.subcontract.dao.vo;

import com.dhlk.entity.sub.ProjectIssue;
import lombok.Data;

import java.io.Serializable;
@Data
public class ProjectIssueVO extends ProjectIssue implements Serializable {
    private static final long serialVersionUID = -21991939456732193L;
    private String companyName;
}
