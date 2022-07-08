package com.dhlk.subcontract.dao.vo;

import com.dhlk.entity.sub.ProjectIssue;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目发布(ProjectIssue)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:15
 */
@Data
public class ProjectIssueVo extends ProjectIssue implements Serializable {

  private static final long serialVersionUID = -21991938212329899L;
  private  String ProjectName;

}
