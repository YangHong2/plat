delete from dhlk_app_menu where app_code like 'fw%';
/**泛沃设备管理**/
INSERT INTO dhlk_app_menu VALUES (20, '/devMaintain', '设备保养管理', '/devMaintain', 0, 0, 'devMaintain:view', 0,'fwDevice');

INSERT INTO dhlk_app_menu VALUES (21, '/devMaintain/maintainProgram', '设备保养项目', '/devMaintain/maintainProgram', 0, 20, 'devMaintainItem:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (22, 'maintainProgram/add', '新增', 'maintainProgram/add', 0, 21, 'devicesKeepItem:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (23, 'maintainProgram/edit', '修改', 'maintainProgram/edit', 0, 21, 'devicesKeepItem:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (24, 'maintainProgram/delete', '删除', 'maintainProgram/delete', 0, 21, 'devicesKeepItem:delete', 1,'fwDevice');

INSERT INTO dhlk_app_menu VALUES (26, '/devMaintain/maintainProgramGroup', '设备保养表单', '/devMaintain/maintainProgramGroup', 0, 20, 'devMaintainTeam:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (27, 'maintainProgramGroup/add', '新增', 'maintainProgramGroup/add', 0, 26, 'devicesKeepTeam:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (28, 'maintainProgramGroup/edit', '修改', 'maintainProgramGroup/edit', 0, 26, 'devicesKeepTeam:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (29, 'maintainProgramGroup/delete', '删除', 'maintainProgramGroup/delete', 0, 26, 'devicesKeepTeam:delete', 1,'fwDevice');

INSERT INTO dhlk_app_menu VALUES (30, '/devMaintain/maintainPlan', '设备保养计划制定', '/devMaintain/maintainPlan', 0, 20, 'devMaintainPlan:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (31, 'maintainPlan/add', '新增', 'maintainPlan/add', 0, 30, 'devicesKeepPlan:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (32, 'maintainPlan/edit', '修改', 'maintainPlan/edit', 0, 30, 'devicesKeepPlan:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (33, 'maintainPlan/delete', '删除', 'maintainPlan/delete', 0, 30, 'devicesKeepPlan:delete', 1,'fwDevice');

INSERT INTO dhlk_app_menu VALUES (34, '/devMaintain/maintainPlanImplement', '设备保养计划执行', '/devMaintain/maintainPlanImplement', 0, 20, 'devMaintainTask:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (35, 'maintainPlanImplement/delay', '延后', 'maintainPlanImplement/delay', 0, 34, 'devicesKeepTask:postPoned', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (36, 'maintainPlanImplement/close', '关闭', 'maintainPlanImplement/close', 0, 34, 'devicesKeepTask:postPoned', 1,'fwDevice');




INSERT INTO dhlk_app_menu VALUES (50, '/devSpotCheck', '设备点检管理', '/devSpotCheck', 0, 0, 'devSpotCheck:view', 0,'fwDevice');

INSERT INTO dhlk_app_menu VALUES (51, '/devSpotCheck/spotChecks', '设备点检项目', '/devSpotCheck/spotChecks', 0, 50, 'devicesCheckItem:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (52, 'spotChecks/add', '新增', '/devicesCheckItem/save', 0, 51, 'devicesCheckItem:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (53, 'spotChecks/edit', '修改', '/devicesCheckItem/save', 0, 51, 'devicesCheckItem:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (54, 'spotChecks/delete', '删除', '/devicesCheckItem/delete', 0, 51, 'devicesCheckItem:delete', 1,'fwDevice');

INSERT INTO dhlk_app_menu VALUES (55, '/devSpotCheck/spotCheckGroup', '设备点检表单', '/devicesCheckTeam', 0, 50, 'devicesCheckTeam:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (56, 'spotCheckGroup/add', '新增', '/devicesCheckTeam/save', 0, 55, 'devicesCheckTeam:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (57, 'spotCheckGroup/edit', '修改', '/devicesCheckTeam/save', 0, 55, 'devicesCheckTeam:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (58, 'spotCheckGroup/delete', '删除', '/devicesCheckTeam/delete', 0, 55, 'devicesCheckTeam:delete', 1,'fwDevice');

INSERT INTO dhlk_app_menu VALUES (59, '/devSpotCheck/spotCheckLog', '设备点检记录', '/devicesCheck', 0, 50, 'devicesCheck:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (60, 'spotCheckLog/find', '查看', '/devicesCheck', 0, 59, 'devicesCheck:view', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (146, 'spotCheckLog/save', '执行', '/devicesCheck', 0, 59, 'devicesCheck:save', 1,'fwDevice');
-- 设备备品备件
INSERT INTO dhlk_app_menu VALUES (61, '/devSpareParts', '设备备品备件管理', '/devSpareParts', 0, 0, 'devSpareParts:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (62, '/devSpareParts/stockInfo', '库存信息', '/devSpareParts/stockInfo', 0, 61, 'devSparePartsInfo:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (63, 'stockInfo/add', '新增', 'stockInfo/add', 0, 62, 'productDevicesSpare:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (64, 'stockInfo/edit', '修改', 'stockInfo/edit', 0, 62, 'productDevicesSpare:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (65, 'stockInfo/delete', '删除', 'stockInfo/delete', 0, 62, 'productDevicesSpare:delete', 1,'fwDevice');

INSERT INTO dhlk_app_menu VALUES (66, '/devSpareParts/warehouseOut', '出入库管理', '/devSpareParts/warehouseOut', 0, 61, 'jbpmDeployment:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (67, 'warehouseOut/in', '新增入库', 'warehouseOut/in', 0, 66, 'devicesSpareIn:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (68, 'warehouseOut/out', '新增出库', 'warehouseOut/out', 0, 66, 'devicesSpareOut:save', 1,'fwDevice');

-- 设备维修项目管理
INSERT INTO dhlk_app_menu VALUES (1018, '/devRepair', '设备维修管理', '/devRepair', 0, 0, 'devRepair:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (1019, '/devRepair/programme', '设备维修项目', '/devRepair/programme', 0, 1018, 'devRepair:programme', 0,'fwDevice');

INSERT INTO dhlk_app_menu VALUES (1020, 'programme/add', '新增', 'programme/add', 0, 1019, 'devicesRepairItem:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (1021, 'programme/edit', '修改', 'programme/edit', 0, 1019, 'devicesRepairItem:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (1022, 'programme/delete', '删除', 'programme/delete', 0, 1019, 'devicesRepairItem:delete', 1,'fwDevice');

-- 设备维修执行
INSERT INTO dhlk_app_menu VALUES (1023, '/devRepair/programmeImplement', '设备维修任务执行', '/devRepair/programmeImplement', 0, 1018, '/devRepair:programmeImplement', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (1024, 'programmeImplement/add', '申请', 'programmeImplement/add', 0, 1023, 'devicesRepair:save', 1,'fwDevice');

INSERT INTO dhlk_app_menu VALUES (1025, 'programmeImplement/edit', '制定', 'programmeImplement/edit', 0, 1023, 'devicesRepair:makeProject', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (1026, 'programmeImplement/delete', '删除', 'programmeImplement/delete', 0, 1023, 'devicesRepair:delete', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (1027, 'programmeImplement/get', '领取', 'programmeImplement/get', 0, 1023, 'devicesRepair:getTask', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (1028, 'programmeImplement/go', '执行', 'programmeImplement/go', 0, 1023, 'devicesRepair:repairTaskExecute', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (1029, 'programmeImplement/find', '查看', 'programmeImplement/find', 0, 1023, 'devicesRepair:findList', 1,'fwDevice');


INSERT INTO dhlk_app_menu VALUES (70, '/devResume', '设备档案履历', '/devResume', 0, 0, 'jbpmDeployment:view', 0,'fwDevice');

INSERT INTO dhlk_app_menu VALUES (71, '/devResume/resume', '设备履历概况', '/devicesExtension', 0, 70, 'devicesExtension:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (72, 'resume/find', '查看', '/devicesExtension/findList', 0, 71, 'devicesExtension:view', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (73, 'resume/edit', '编辑', '/devicesExtension/save', 0, 71, 'devicesExtension:save', 1,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (74, 'resume/scrap', '报废', '/devicesScrap/save', 0, 71, 'devicesScrap:save', 1,'fwDevice');

INSERT INTO dhlk_app_menu VALUES (75, '/devResume/scraps', '设备报废记录', '/devicesScrap/findList', 0, 70, 'devicesScrap:view', 0,'fwDevice');
INSERT INTO dhlk_app_menu VALUES (76, 'scraps/find', '查看', '/devicesScrap/findByDeviceExtensionId', 0, 75, 'devicesScrap:view', 1,'fwDevice');

/**泛沃流程审核**/

INSERT INTO dhlk_app_menu VALUES (77, '/approvalTask', '待审批任务', '/approvalTask', 0, 0, 'approvalTask', 0,'fwJbpm');
INSERT INTO dhlk_app_menu VALUES (78, '/approvalTask/approvalTask', '待审批任务', '/approvalTask/approvalTask', 0, 77, 'approvalTask:view', 0,'fwJbpm');
INSERT INTO dhlk_app_menu VALUES (79, 'approvalTask/audit', '审核', 'approvalTask/audit', 0, 78, 'jbpmExecution:doTask', 1,'fwJbpm');

INSERT INTO dhlk_app_menu VALUES (80, '/examineLog', '审批结果记录', '/examineLog', 0, 0, 'devicesExtension', 0,'fwJbpm');
INSERT INTO dhlk_app_menu VALUES (81, '/examineLog/examineLog', '审批结果记录', '/examineLog/examineLog', 0, 80, 'devicesExtension:view', 0,'fwJbpm');
INSERT INTO dhlk_app_menu VALUES (82, 'examineLog/find', '查看', 'examineLog/find', 0, 81, 'devicesScrap:view', 1,'fwJbpm');

INSERT INTO dhlk_app_menu VALUES (83, '/flowSetting', '审核流程设置', '/flowSetting', 0, 0, 'flowSetting', 0,'fwJbpm');
INSERT INTO dhlk_app_menu VALUES (84, '/flowSetting/flowSetting', '审核流程设置', '/flowSetting/flowSetting', 0, 83, 'flowSetting:view', 0,'fwJbpm');
INSERT INTO dhlk_app_menu VALUES (85, 'flowSetting/add', '新增', 'flowSetting/add', 0, 84, 'jbpmDeployment:save', 1,'fwJbpm');
INSERT INTO dhlk_app_menu VALUES (86, 'flowSetting/edit', '修改', 'flowSetting/edit', 0, 84, 'jbpmDeployment:save', 1,'fwJbpm');
INSERT INTO dhlk_app_menu VALUES (87, 'flowSetting/delete', '删除', 'flowSetting/delete', 0, 84, 'jbpmDeployment:delete', 1,'fwJbpm');
INSERT INTO dhlk_app_menu VALUES (88, 'flowSetting/find', '查看', 'flowSetting/find', 0, 84, 'devicesScrap:view', 1,'fwJbpm');

/**泛沃模具管理**/
INSERT INTO dhlk_app_menu VALUES (89, '/mouldMaintain', '模具保养管理', '/mouldMaintain', 0, 0, 'mouldMaintain:view', 0,'fwMould');

INSERT INTO dhlk_app_menu VALUES (90, '/mouldMaintain/maintainProgramMould', '模具保养项目', '/mouldMaintain/maintainProgramMould', 0, 89, 'mouldMaintainItem:view', 0,'fwMould');
INSERT INTO dhlk_app_menu VALUES (91, 'maintainProgramMould/add', '新增', 'maintainProgramMould/add', 0, 90, 'mouldKeepItem:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (92, 'maintainProgramMould/edit', '修改', 'maintainProgramMould/edit', 0, 90, 'mouldKeepItem:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (93, 'maintainProgramMould/delete', '删除', 'maintainProgramMould/delete', 0, 90, 'mouldKeepItem:delete', 1,'fwMould');

INSERT INTO dhlk_app_menu VALUES (94, '/mouldMaintain/maintainProgramGroupMould', '模具保养表单', '/mouldMaintain/maintainProgramGroupMould', 0, 89, 'mouldMaintainTeam:view', 0,'fwMould');
INSERT INTO dhlk_app_menu VALUES (95, 'maintainProgramGroupMould/add', '新增', 'maintainProgramGroupMould/add', 0, 94, 'mouldKeepTeam:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (96, 'maintainProgramGroupMould/edit', '修改', 'maintainProgramGroupMould/edit', 0, 94, 'mouldKeepTeam:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (97, 'maintainProgramGroupMould/delete', '删除', 'maintainProgramGroupMould/delete', 0, 94, 'mouldKeepTeam:delete', 1,'fwMould');

INSERT INTO dhlk_app_menu VALUES (98, '/mouldMaintain/maintainPlanMould', '模具保养计划制定', '/mouldMaintain/maintainPlanMould', 0, 89, 'mouldMaintainPlan:view', 0,'fwMould');
INSERT INTO dhlk_app_menu VALUES (99, 'maintainPlanMould/add', '新增', 'maintainPlanMould/add', 0, 98, 'mouldKeepPlan:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (100, 'maintainPlanMould/edit', '修改', 'maintainPlanMould/edit', 0, 98, 'mouldKeepPlan:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (101, 'maintainPlanMould/delete', '删除', 'maintainPlanMould/delete', 0, 98, 'mouldKeepPlan:delete', 1,'fwMould');

INSERT INTO dhlk_app_menu VALUES (102, '/mouldMaintain/maintainPlanImplementMould', '模具保养计划执行', '/mouldMaintain/maintainPlanImplementMould', 0, 89, 'mouldMaintainTask:view', 0,'fwMould');
INSERT INTO dhlk_app_menu VALUES (103, 'maintainPlanImplementMould/delay', '延后', 'maintainPlanImplementMould/delay', 0, 102, 'mouldKeepTask:postPoned', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (104, 'maintainPlanImplementMould/close', '关闭', 'maintainPlanImplementMould/close', 0, 102, 'mouldKeepTask:postPoned', 1,'fwMould');




INSERT INTO dhlk_app_menu VALUES (115, '/mouldStorageLocation', '模具库位管理', '/mouldStorageLocation', 0, 0, 'mouldStorageLocation:view', 0,'fwMould');

INSERT INTO dhlk_app_menu VALUES (116, '/mouldStorageLocation/storageLocationMould', '模具库位管理', '/mouldStorageLocation/storageLocationMould', 0, 115, 'storageLocationMould:view', 0,'fwMould');
INSERT INTO dhlk_app_menu VALUES (117, 'storageLocationMould/addStore', '新建仓库', 'storageLocationMould/addStore', 0, 116, 'mouldStorageHouse:saveStorage', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (118, 'storageLocationMould/editStore', '编辑仓库', 'storageLocationMould/editStore', 0, 116, 'mouldStorageHouse:saveStorage', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (119, 'storageLocationMould/addHouse', '新建库位', 'storageLocationMould/addHouse', 0, 116, 'mouldStorageHouse:saveLocation', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (120, 'storageLocationMould/editHouse', '编辑库位', 'storageLocationMould/editHouse', 0, 116, 'mouldStorageHouse:saveLocation', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (121, 'storageLocationMould/deleteHouse', '删除库位', 'storageLocationMould/deleteHouse', 0, 116, 'mouldStorageHouse:deleteLocation', 1,'fwMould');

INSERT INTO dhlk_app_menu VALUES (122, '/mouldStorageLocation/setStorageLocationMould', '模具库位设置', '/mouldStorageLocation/setStorageLocationMould', 0, 115, 'setStorageLocationMould:view', 0,'fwMould');
INSERT INTO dhlk_app_menu VALUES (123, 'setStorageLocationMould/bind', '绑定', 'setStorageLocationMould/bind', 0, 122, 'mouldStorageHouse:boundMould', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (124, 'setStorageLocationMould/unbind', '解绑', 'setStorageLocationMould/unbind', 0, 122, 'mouldStorageHouse:unbundMoule', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (125, 'setStorageLocationMould/in', '入库', 'setStorageLocationMould/in', 0, 122, 'mouldHouse:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (126, 'setStorageLocationMould/out', '出库', 'setStorageLocationMould/out', 0, 122, 'mouldHouse:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (127, 'setStorageLocationMould/move', '移库', 'setStorageLocationMould/move', 0, 122, 'mouldHouse:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (128, 'setStorageLocationMould/link', '关联', 'setStorageLocationMould/link', 0, 122, 'mouldStorageHouse:boundMould', 1,'fwMould');

INSERT INTO dhlk_app_menu VALUES (129, '/mouldStorageLocation/operatingTaskMould', '模具操作任务', '/mouldStorageLocation/operatingTaskMould', 0, 115, 'operatingTaskMould:view', 0,'fwMould');


INSERT INTO dhlk_app_menu VALUES (139, '/mouldResume', '模具档案履历', '/mouldResume', 0, 0, 'devicesExtension:view', 0,'fwMould');

INSERT INTO dhlk_app_menu VALUES (140, '/mouldResume/resumeMould', '模具履历概况', '/mouldResume/resumeMould', 0, 139, 'devicesExtension:view', 0,'fwMould');
INSERT INTO dhlk_app_menu VALUES (141, 'resumeMould/add', '新增', 'resumeMould/add', 0, 140, 'mouldDevices:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (143, 'resumeMould/find', '查看', 'resumeMould/find', 0, 140, 'mouldDevices:view', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (144, 'resumeMould/edit', '编辑', 'resumeMould/edit', 0, 140, 'mouldDevices:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (145, 'resumeMould/transition', '转段', 'resumeMould/transition', 0, 140, 'mouldDevices:saveTurn', 1,'fwMould');






-- 模具维修项目
INSERT INTO dhlk_app_menu VALUES (1043, '/mouldRepair', '模具维修管理', '/mouldRepair', 0, 0, 'dhlk:view', 0,'fwMould');

INSERT INTO dhlk_app_menu VALUES (1044, '/mouldRepair/programmeMould', '模具维修项目', '/mouldRepair/programmeMould', 0, 1043, 'dhlk:view', 0,'fwMould');
INSERT INTO dhlk_app_menu VALUES (1045, 'programmeMould/add', '新增', 'programmeMould/add', 0, 1044, 'mouldRepairItem:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (1046, 'programmeMould/edit', '修改', 'programmeMould/edit', 0, 1044, 'mouldRepairItem:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (1047, 'programmeMould/delete', '删除', 'programmeMould/delete', 0, 1044, 'mouldRepairItem:delete', 1,'fwMould');

-- 模具维修任务执行
INSERT INTO dhlk_app_menu VALUES (1048, '/mouldRepair/programmeImplementMould', '模具维修任务执行', '/mouldRepair/programmeImplementMould', 0, 1043, 'dhlk:view', 0,'fwMould');

INSERT INTO dhlk_app_menu VALUES (1049, 'programmeImplementMould/add', '申请', 'programmeImplementMould/add', 0, 1048, 'mouldRepair:save', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (1050, 'programmeImplementMould/edit', '制定', 'programmeImplementMould/edit', 0, 1048, 'mouldRepair:makeProject', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (1051, 'programmeImplementMould/get', '领取', 'programmeImplementMould/get', 0, 1048, 'mouldRepair:getTask', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (1052, 'programmeImplementMould/go', '执行', 'programmeImplementMould/go', 0, 1048, 'mouldRepair:repairTaskExecute', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (1053, 'programmeImplementMould/delete', '删除', 'programmeImplementMould/delete', 0, 1048, 'mouldRepair:delete', 1,'fwMould');

-- 模具备品备件
INSERT INTO dhlk_app_menu VALUES (1054, '/mouldSpareParts', '备品备件管理', '/mouldSpareParts', 0, 0, 'dhlk:view', 0,'fwMould');

INSERT INTO dhlk_app_menu VALUES (1055, '/mouldSpareParts/stockInfoMould', '库存信息', '/mouldSpareParts/stockInfoMould', 0, 1054, 'dhlk:view', 0,'fwMould');
INSERT INTO dhlk_app_menu VALUES (1056, 'stockInfoMould/add', '新增', 'stockInfoMould/add', 0, 1055, 'mouldStorage:insertSpareInfo', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (1057, 'stockInfoMould/edit', '修改', 'stockInfoMould/edit', 0, 1055, 'mouldStorage:insertSpareInfo', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (1058, 'stockInfoMould/delete', '删除', 'stockInfoMould/delete', 0, 1055, 'mouldStorage:deleteSpareInfo', 1,'fwMould');

-- 模具出入库

INSERT INTO dhlk_app_menu VALUES (1059, '/mouldSpareParts/warehouseOutMould', '出入库管理', '/mouldSpareParts/warehouseOutMould', 0, 1054, 'dhlk:view', 0,'fwMould');

INSERT INTO dhlk_app_menu VALUES (1160, 'warehouseOutMould/in', '新增入库', 'warehouseOutMould/in', 0, 1059, 'mouldStorage:mouldIn', 1,'fwMould');
INSERT INTO dhlk_app_menu VALUES (1161, 'warehouseOutMould/out', '新增出库', 'warehouseOutMould/out', 0, 1059, 'mouldStorage:mouldOut', 1,'fwMould');





/**分层审核管理**/
-- 审核项目
INSERT INTO dhlk_app_menu VALUES (1000, '/auditProgram', '审核项目管理', '/auditProgram', 0, 0, 'dhlk:view', 0,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1001, '/auditProgram/auditProgram', '审核项目管理', '/auditProgram/auditProgram', 0, 1000, 'dhlk:view', 0,'fwAudit');

INSERT INTO dhlk_app_menu VALUES (1002, 'auditProgram/add', '新增', 'auditProgram/add', 0, 1001, 'auditItem:save', 1,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1003, 'auditProgram/edit', '修改', 'auditProgram/edit', 0, 1001, 'auditItem:save', 1,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1004, 'auditProgram/delete', '删除', 'auditProgram/delete', 0, 1001, 'auditItem:delete', 1,'fwAudit');

-- 审核表单管理
INSERT INTO dhlk_app_menu VALUES (1005, '/auditProgramGroup', '审核表单管理', '/auditProgramGroup', 0, 0, 'dhlk:view', 0,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1006, '/auditProgramGroup/auditProgramGroup', '审核表单管理', '/auditProgramGroup/auditProgramGroup', 0, 1005, 'devicesExtension:view', 0,'fwAudit');

INSERT INTO dhlk_app_menu VALUES (1007, 'auditProgramGroup/add', '新增', 'auditProgramGroup/add', 0, 1006, 'auditTeam:save', 1,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1008, 'auditProgramGroup/edit', '修改', 'auditProgramGroup/edit', 0, 1006, 'auditTeam:save', 1,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1009, 'auditProgramGroup/delete', '删除', 'auditProgramGroup/delete', 0, 1006, 'auditTeam:delete', 1,'fwAudit');

-- 审核计划管理
INSERT INTO dhlk_app_menu VALUES (1010, '/auditPlan', '审核计划制定', '/auditPlan', 0, 0, 'jbpmDeployment:view', 0,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1011, '/auditPlan/auditPlan', '审核计划制定', '/auditPlan/auditPlan', 0, 1010, 'devicesExtension:view', 0,'fwAudit');

INSERT INTO dhlk_app_menu VALUES (1012, 'auditPlan/add', '新增', 'auditPlan/add', 0, 1011, 'auditPlan:save', 1,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1013, 'auditPlan/edit', '修改', 'auditPlan/edit', 0, 1011, 'auditPlan:save', 1,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1014, 'auditPlan/delete', '删除', 'auditPlan/delete', 0, 1011, 'auditPlan:delete', 1,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1093, 'auditPlan/start', '禁用启用', 'auditPlan/start', 0, 1010, 'auditPlan:save', 1,'fwAudit');


-- 审核计划任务执行
INSERT INTO dhlk_app_menu VALUES (1015, '/auditPlanImplement', '审核计划执行', '/auditPlanImplement', 0, 0, 'jbpmDeployment:view', 0,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1016, '/auditPlanImplement/auditPlanImplement', '审核计划执行', '/auditPlanImplement/auditPlanImplement', 0, 1015, 'devicesExtension:view', 0,'fwAudit');

INSERT INTO dhlk_app_menu VALUES (1017, 'auditPlanImplement/find', '查看', 'auditPlanImplement/find', 0, 1016, 'auditTask:exeTask', 1,'fwAudit');
-- 问题管理
INSERT INTO dhlk_app_menu VALUES (1910, '/questionManage', '问题管理', '/questionManage', 0, 0, 'questionManage:view', 0,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1911, '/questionManage/questionManage', '问题管理', '/questionManage/questionManage', 0, 1910, 'questionManage:view', 0,'fwAudit');

INSERT INTO dhlk_app_menu VALUES (1912, 'questionManage/add', '新增', 'questionManage/add', 0, 1911, 'auditQuestion:save', 1,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1913, 'questionManage/task', '执行', 'questionManage/task', 0, 1911, 'auditQuestion:save', 1,'fwAudit');
INSERT INTO dhlk_app_menu VALUES (1914, 'questionManage/people', '指派', 'questionManage/people', 0, 1911, 'auditQuestion:save', 1,'fwAudit');

/**物流管理**/
-- 物流移库
INSERT INTO dhlk_app_menu VALUES (1030, '/movesManage', '移库管理', '/movesManage', 0, 0, '/dhlk:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1031, '/movesManage/moves', '申请', '/movesManage/moves', 0, 1030, 'dhlk:view', 0,'fwLogistics');

INSERT INTO dhlk_app_menu VALUES (1032, 'moves/add', '新增', 'moves/add', 0, 1031, 'logisticsMoveHouse:save', 1,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1034, 'moves/delete', '删除', 'moves/delete', 0, 1031, 'logisticsMoveHouse:delete', 1,'fwLogistics');

-- 收货管理
INSERT INTO dhlk_app_menu VALUES (1035, '/confirmHarvest', '收货管理', '/confirmHarvest', 0, 0, '/dhlk:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1036, '/confirmHarvest/confirmHarvest', '申请', '/confirmHarvest/confirmHarvest', 0, 1035, 'dhlk:view', 0,'fwLogistics');

INSERT INTO dhlk_app_menu VALUES (1037, 'confirmHarvest/update', '更新收货明细', 'confirmHarvest/update', 0, 1036, 'logisticsTakeOrder:update', 1,'fwLogistics');

-- 库存管理
INSERT INTO dhlk_app_menu VALUES (500, '/stockManage', '库存管理', '/stockManage', 0, 0, 'stockManage:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (501, '/stockManage/materielManage', '物料管理', '/stockManage/materielManage', 0, 500, 'stockManageProduct:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (502, '/stockManage/materielDetailed', '物料明细', '/stockManage/materielDetailed', 0, 500, 'stockManageDetail:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (503, '/stockManage/preloadingInfo', '预警信息', '/stockManage/preloadingInfo', 0, 500, 'stockManageWarn:view', 0,'fwLogistics');
-- 上下架记录
INSERT INTO dhlk_app_menu VALUES (504, '/upLowManage', '上下架记录', '/upLowManage', 0, 0, 'upLowManage:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (505, '/upLowManage/upLowLog', '上下架记录', '/upLowManage/upLowLog', 0, 504,'upLowManageRecord:view', 0,'fwLogistics');
-- 退料管理
INSERT INTO dhlk_app_menu VALUES (508, '/materialReturnManage', '退料管理', '/materialReturnManage', 0, 0, '/materialReturnManage:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (509, '/materialReturnManage/materialReturn', '退料管理', '/materialReturnManage/materialReturn', 0, 508,'materialReturnManageBack:view', 0,'fwLogistics');




-- 盘库管理
INSERT INTO dhlk_app_menu VALUES (1504, '/inventoryManage', '盘库管理', '/inventoryManage', 0, 0, 'inventoryManage:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1505, '/inventoryManage/inventory', '盘库管理', '/inventoryManage/inventory', 0, 1504, 'inventory:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1506, 'inventory/add', '新建', 'inventory/add', 0, 1505, 'logisticsCheckHouse:insert', 1,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1507, 'inventory/edit', '盘库', 'inventory/edit', 0, 1505, 'logisticsCheckHouse:update', 1,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1508, 'inventory/close', '关闭', 'inventory/updateStatus', 0, 1505, 'logisticsCheckHouse:updateStatus', 1,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1509, 'inventory/find', '查看', 'inventory/find', 0, 1505, 'logisticsCheckHouse:find', 1,'fwLogistics');




-- 发货计划
INSERT INTO dhlk_app_menu VALUES (1660, '/deliveryPlan', '发货计划', '/deliveryPlan', 0, 0, 'deliveryPlan:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1661, '/deliveryPlan/deliveryPlan', '发货计划', '/deliveryPlan/deliveryPlan', 0, 1660, 'deliveryPlan:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1662, 'deliveryPlan/add', '新增', 'deliveryPlan/add', 0, 1661, 'logisticsDeliveryPlan:insert', 1,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1663, 'deliveryPlan/edit', '修改', 'deliveryPlan/edit', 0, 1661, 'logisticsDeliveryPlan:update', 1,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1664, 'deliveryPlan/delete', '删除', 'deliveryPlan/delete', 0, 1661, 'logisticsDeliveryPlan:delete', 1,'fwLogistics');

-- 入库管理 
INSERT INTO dhlk_app_menu VALUES (1665, '/warehousingManage', '入库管理', '/warehousingManage', 0, 0, 'warehousingManage:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1666, '/warehousingManage/warehousing', '入库管理', '/warehousingManage/warehousing', 0, 1665, 'warehousingManage:view', 0,'fwLogistics');

-- 出库管理
INSERT INTO dhlk_app_menu VALUES (1667, '/warehouseOutManage', '出库管理', '/warehouseOutManage', 0, 0, 'warehouseOutManage:view', 0,'fwLogistics');
INSERT INTO dhlk_app_menu VALUES (1668, '/warehouseOutManage/warehouseOut', '出库管理', '/warehouseOutManage/warehouseOut', 0, 1667, 'warehousingManage:view', 0,'fwLogistics');

/**质量管理**/

-- 首末件管理
INSERT INTO dhlk_app_menu VALUES (1060, '/qualityTask', '质量管理', '/qualityTask', 0, 0, 'dhlk:view', 0,'fwQuality');
-- 质量首末检管理
INSERT INTO dhlk_app_menu VALUES (1061, '/qualityTask/firstLastTest', '质量首末检管理', '/qualityTask/firstLastTest', 0, 1060, 'dhlk:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1062, 'firstLastTest/submit', '提交质检结果', '/firstLastTest/submit', 0, 1061, 'qualityFirstendCheck:submit', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1063, 'firstLastTest/update', '更新质检结果', '/firstLastTest/update', 0, 1061, 'qualityFirstendCheck:update', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1064, 'firstLastTest/coerceClose', '强制关闭', '/firstLastTest/coerceClose', 0, 1061, 'qualityFirstendCheck:coerceClose', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1065, 'firstLastTest/postponeExe', '延期执行', '/firstLastTest/postponeExe', 0, 1061, 'qualityFirstendCheck:postponeExe', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1078, 'firstLastTest/sizeCheck', '首末检尺寸检验', '/firstLastTest/sizeCheck', 0, 1061, 'qualityFirstendCheck:update', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1079, 'firstLastTest/facadeCheck', '首末检外观检验', '/firstLastTest/facadeCheck', 0, 1061, 'qualityFirstendCheck:update', 1,'fwQuality');
-- 来料检验管理
INSERT INTO dhlk_app_menu VALUES (1066, '/qualityTask/incomingTest', '来料检验管理', '/qualityTask/incomingTest', 0, 1060, 'dhlk:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1067, 'incomingTest/submit', '提交质检结果', '/incomingTest/submit', 0, 1066, 'qualityMaterialCheck:submit', 1,'fwQuality');
-- 出库检验管理
INSERT INTO dhlk_app_menu VALUES (1068, '/qualityTask/outhousingTest', '出库检验管理', '/qualityTask/outhousingTest', 0, 1060, 'dhlk:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1069, 'outhousingTest/submit', '提交质检结果', '/outhousingTest/submit', 0, 1068, 'qualityOutCheck:submit', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1070, 'outhousingTest/update', '更新质检结果', '/outhousingTest/update', 0, 1068, 'qualityOutCheck:update', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1076, 'outhousingTest/sizeCheck', '出库尺寸检验', '/outhousingTest/sizeCheck', 0, 1068, 'qualityOutCheck:update', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1077, 'outhousingTest/facadeCheck', '出库外观检验', '/outhousingTest/facadeCheck', 0, 1068, 'qualityOutCheck:update', 1,'fwQuality');
-- 入库检验管理
INSERT INTO dhlk_app_menu VALUES (1071, '/qualityTask/warehousingTest', '入库检验管理', '/qualityTask/warehousingTest', 0, 1060, 'dhlk:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1072, 'warehousingTest/submit', '提交质检结果', '/outhousingTest/submit', 0, 1071, 'qualityStoreCheck:submit', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1073, 'warehousingTest/update', '更新质检结果', '/outhousingTest/update', 0, 1071, 'qualityStoreCheck:update', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1074, 'warehousingTest/sizeCheck', '入库尺寸检验', '/outhousingTest/sizeCheck', 0, 1071, 'qualityStoreCheck:update', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1075, 'warehousingTest/facadeCheck', '入库外观检验', '/outhousingTest/facadeCheck', 0, 1071, 'qualityStoreCheck:update', 1,'fwQuality');

-- 检验记录
INSERT INTO dhlk_app_menu VALUES (1080, '/qualityLog', '检验记录', '/qualityTask', 0, 0, 'dhlk:view', 0,'fwQuality');
-- 质量首末检记录
INSERT INTO dhlk_app_menu VALUES (1081, '/qualityLog/firstLastLog', '质量首末检记录', '/qualityLog/firstLastLog', 0, 1080, 'dhlk:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1082, 'firstLastLog/fakeLog', '虚假记录', '/qualityLog/firstLastLog', 0, 1081, 'dhlk:view', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1083, 'firstLastLog/realLog', '真实记录', '/qualityLog/realLog', 0, 1081, 'dhlk:view', 1,'fwQuality');
-- 来料检验记录
INSERT INTO dhlk_app_menu VALUES (1084, '/qualityLog/incomingLog', '来料检验记录', '/qualityLog/incomingLog', 0, 1080, 'dhlk:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1085, 'incomingLog/fakeLog', '虚假记录', '/incomingLog/firstLastLog', 0, 1084, 'dhlk:view', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1086, 'incomingLog/realLog', '真实记录', '/incomingLog/realLog', 0, 1084, 'dhlk:view', 1,'fwQuality');
-- 入库检验记录
INSERT INTO dhlk_app_menu VALUES (1087, '/qualityLog/warehousingLog', '入库检验记录', '/qualityLog/warehousingLog', 0, 1080, 'dhlk:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1088, 'warehousingLog/fakeLog', '虚假记录', '/warehousingLog/firstLastLog', 0, 1087, 'dhlk:view', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1089, 'warehousingLog/realLog', '真实记录', '/warehousingLog/realLog', 0, 1087, 'dhlk:view', 1,'fwQuality');
-- 出库检验记录
INSERT INTO dhlk_app_menu VALUES (1090, '/qualityLog/outhousingLog', '出库检验记录', '/qualityLog/outhousingLog', 0, 1080, 'dhlk:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1091, 'outhousingLog/fakeLog', '虚假记录', '/outhousingLog/firstLastLog', 0, 1090, 'dhlk:view', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (1092, 'outhousingLog/realLog', '真实记录', '/outhousingLog/realLog', 0, 1090, 'dhlk:view', 1,'fwQuality');





-- 首末检检验规范
INSERT INTO dhlk_app_menu VALUES (514, '/standardManage', '检验规范管理', '/standardManage', 0, 0, '/standardManage:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (515, '/standardManage/firstLastStandard', '质量首末检规范', '/standardManage/firstLastStandard', 0, 514, 'standardManageFirstLast:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (516, 'firstLastStandard/add', '新增', 'firstLastStandard/add', 0, 515,'qualityInspection:save', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (517, 'firstLastStandard/change', '变更', 'firstLastStandard/change', 0, 515,'qualityInspection:change', 1,'fwQuality');
-- 来料检验规范
INSERT INTO dhlk_app_menu VALUES (518, '/standardManage/incomingStandard', '来料检验规范', '/standardManage/incomingStandard', 0, 514, 'standardManageIncoming:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (519, 'incomingStandard/add', '新增', 'incomingStandard/add', 0, 518,'qualityInspection:save', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (520, 'incomingStandard/change', '变更', 'incomingStandard/change', 0, 518,'qualityInspection:change', 1,'fwQuality');
-- 入库检验规范
INSERT INTO dhlk_app_menu VALUES (521, '/standardManage/warehousingStandard', '入库检验规范', '/standardManage/warehousingStandard', 0, 514, 'standardManageWarehousing:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (522, 'warehousingStandard/add', '新增', 'warehousingStandard/add', 0, 521,'qualityInspection:save', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (523, 'warehousingStandard/change', '变更', 'warehousingStandard/change', 0, 521,'qualityInspection:change', 1,'fwQuality');
-- 出库检验规范
INSERT INTO dhlk_app_menu VALUES (524, '/standardManage/outhousingStandard', '出库检验规范', '/standardManage/outhousingStandard', 0, 514, 'standardManageOuthousing:view', 0,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (525, 'outhousingStandard/add', '新增', 'outhousingStandard/add', 0, 524,'qualityInspection:save', 1,'fwQuality');
INSERT INTO dhlk_app_menu VALUES (526, 'outhousingStandard/change', '变更', 'outhousingStandard/change', 0, 524,'qualityInspection:change', 1,'fwQuality');





/**工艺管理**/

-- 工艺模型管理

INSERT INTO dhlk_app_menu VALUES (1474, '/processManage', '工艺管理', '/processManage', 0, 0, 'dhlk:view', 0,'fwCraft');
INSERT INTO dhlk_app_menu VALUES (1475, '/processManage/processManage', '工艺模型管理', '/processManage/processManage', 0, 1474, 'dhlk:view', 0,'fwCraft');

INSERT INTO dhlk_app_menu VALUES (1476, 'processManage/add', '新增', 'processManage/add', 0, 1475, 'craftModel:save', 1,'fwCraft');
INSERT INTO dhlk_app_menu VALUES (1477, 'processManage/edit', '修改', 'processManage/edit', 0, 1475, 'craftModel:save', 1,'fwCraft');
INSERT INTO dhlk_app_menu VALUES (1478, 'processManage/delete', '删除', 'processManage/delete', 0, 1475, 'craftModel:delete', 1,'fwCraft');


-- 工艺卡管理
INSERT INTO dhlk_app_menu VALUES (1584, '/processCard', '工艺卡管理', '/processCard', 0, 0, 'processCard:view', 0,'fwCraft');
INSERT INTO dhlk_app_menu VALUES (1585, '/processCard/processCard', '工艺卡管理', '/processCard/processCard', 0, 1584, 'processCard:view', 0,'fwCraft');
INSERT INTO dhlk_app_menu VALUES (1586, 'processCard/add', '新增',  'processCard/add', 0, 1585, 'craftCard:save', 1,'fwCraft');
INSERT INTO dhlk_app_menu VALUES (1587, 'processCard/edit', '变更', 'processCard/edit', 0, 1585, 'craftCard:saveCardLog', 1,'fwCraft');
INSERT INTO dhlk_app_menu VALUES (1588, 'processCard/find', '查看', 'processCard/find', 0, 1585, 'craftCard:view', 1,'fwCraft');

INSERT INTO dhlk_app_menu VALUES (1589, '/processData', '工艺数据监测', '/processData', 0, 0, 'processData:view', 0,'fwCraft');
INSERT INTO dhlk_app_menu VALUES (1590, '/processData/processData', '工艺数据监测', '/processData/processData', 0, 1589, 'processData:view', 0,'fwCraft');



/**生产管理**/
-- 退料管理
INSERT INTO dhlk_app_menu VALUES (511, '/backMaterial', '退料', '/backMaterial', 0, 0, '/backMaterial:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (512, '/backMaterial/backMaterial', '退料', '/backMaterial/backMaterial', 0, 511, 'backMaterialBack:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (513, 'backMaterial/add', '新增', 'backMaterial/add', 0, 512,'logisticsBackHouse:save', 1,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (533, 'backMaterial/delete', '删除', 'backMaterial/delete', 0, 512,'logisticsBackHouse:delete', 1,'fwProduce');


-- 报废管理
INSERT INTO dhlk_app_menu VALUES (1038, '/scrapPro', '报废管理', '/scrapPro', 0, 0, '/dhlk:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1039, '/scrapPro/scrapPro', '报废管理', '/scrapPro/scrapPro', 0, 1038, 'dhlk:view', 0,'fwProduce');


INSERT INTO dhlk_app_menu VALUES (1040, 'scrapPro/add', '新增', 'scrapPro/add', 0, 1039, 'logisticsScrap:save', 1,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1041, 'scrapPro/update', '更新', 'scrapPro/update', 0, 1039, 'logisticsScrap:save', 1,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1042, 'scrapPro/delete', '删除', 'scrapPro/delete', 0, 1039, 'logisticsScrap:delete', 1,'fwProduce');

-- 生产入库
INSERT INTO dhlk_app_menu VALUES (1669, '/warehousingPro', '生产入库', '/warehousingPro', 0, 0, 'warehousingPro:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1670, '/warehousingPro/warehousingPro', '生产入库', '/warehousingPro/warehousingPro', 0, 1669, 'warehousingPro:view', 0,'fwProduce');

INSERT INTO dhlk_app_menu VALUES (1671, 'logisticsStoreHouse/save', '新增', 'logisticsStoreHouse/save', 0, 1670, 'logisticsStoreHouse:save', 1,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1672, 'logisticsStoreHouse/delete', '删除', 'logisticsStoreHouse/delete', 0, 1670, 'logisticsStoreHouse:delete', 1,'fwProduce');


-- 生产实时监测
INSERT INTO dhlk_app_menu VALUES (1973, '/deviceStatusInfo', '生产实时监测', '/deviceStatusInfo', 0, 0, 'deviceStatusInfo:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1974, '/productTimesInfo/deviceStatusInfo', '生产实时监测', '/productTimesInfo/deviceStatusInfo', 0, 1973, 'deviceStatusInfo:view', 0,'fwProduce');

-- 生产操作
INSERT INTO dhlk_app_menu VALUES (1975, '/produceIndex', '生产操作', '/produceIndex', 0, 0, 'produceIndex:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1976, '/produce/receiveMaterials', '领料', '/produce/receiveMaterials', 0, 1975, 'produce:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1977, '/produce/putInMaterials', '投料', '/produce/putInMaterials', 0, 1975, 'produce:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1978, '/produce/reprocessing', '二次加工', '/produce/reprocessing', 0, 1975, 'produce:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1979, '/produce/arrangeStaff', '安排员工', '/produce/arrangeStaff', 0, 1975, 'produce:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1980, '/produce/shiftHandover', '换班交接', '/produce/shiftHandover', 0, 1975, 'produce:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1981, '/produce/dryingTime', '烘料时长', '/produce/dryingTime', 0, 1975, 'produce:view', 0,'fwProduce');

-- 生产信息
INSERT INTO dhlk_app_menu VALUES (1982, '/productInfo', '生产信息', '/productInfo', 0, 0, 'produce:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1983, '/productInfo/productInfo', '生产信息', '/productInfo/productInfo', 0, 1982, 'produce:view', 0,'fwProduce');
INSERT INTO dhlk_app_menu VALUES (1984, 'productInfo/edit', '编辑', '/produceMessage/save', 0, 1983, 'produceMessage', 1,'fwProduce');
/**排产计划管理**/

-- 客户管理
INSERT INTO dhlk_app_menu VALUES (1500, '/customers', '客户管理', '/customers', 0, 0, 'customers:view', 0,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1501, '/customers/customers', '客户管理', '/customers/customers', 0, 1500, 'customers:view', 0,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1502, 'customers/add', '新建客户', 'customers/add', 0, 1501, 'customer:save', 1,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1503, 'customers/edit', '编辑客户', 'customers/edit', 0, 1501, 'customer:save', 1,'fwPlan');


-- 注塑计划管理
INSERT INTO dhlk_app_menu VALUES (1565, '/schedulingTools', '预排产工具', '/schedulingTools', 0, 0, 'schedulingTools:view', 0,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1566, '/schedulingTools/schedulingTools', '预排产工具', '/schedulingTools/schedulingTools', 0, 1565, 'schedulingTools:view', 0,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1567, 'schedulingTools/add', '新增', 'schedulingTools/add', 0, 1566, 'injectionMolding:insert', 1,'fwPlan');

INSERT INTO dhlk_app_menu VALUES (1568, '/instructionManage', '生产指令管理', '/instructionManage', 0, 0, 'instructionManage:view', 0,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1569, '/instructionManage/instructionManage', '生产指令管理', '/instructionManage/instructionManage', 0, 1568, 'instructionManage:view', 0,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1570, 'instructionManage/edit', '编辑', 'instructionManage/edit', 0, 1569, 'injectionMolding:update', 1,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1571, 'instructionManage/cancel', '取消', 'instructionManage/cancel', 0, 1569, 'injectionMolding:moveUpDownCancel', 1,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1572, 'instructionManage/stop', '暂停', 'instructionManage/stop', 0, 1569, 'injectionMolding:stop', 1,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1573, 'instructionManage/moveUp', '上移', 'instructionManage/moveUp', 0, 1569, 'injectionMolding:moveUpDownCancel', 1,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1574, 'instructionManage/moveDown', '下移', 'instructionManage/moveDown', 0, 1569, 'injectionMolding:moveUpDownCancel', 1,'fwPlan');



-- 二次加工计划管理

INSERT INTO dhlk_app_menu VALUES (1674, '/doubleSchedulingTools', '二次加工预排产', '/doubleSchedulingTools', 0, 0, 'doubleSchedulingTools:view', 0,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1575, '/doubleSchedulingTools/doubleSchedulingTools', '二次加工预排产', '/doubleSchedulingTools/doubleSchedulingTools', 0, 1674, 'doubleSchedulingTools:view', 0,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1576, 'doubleSchedulingTools/add', '新增', 'doubleSchedulingTools/add', 0, 1575, 'reworkInjection:insert', 1,'fwPlan');

INSERT INTO dhlk_app_menu VALUES (1577, '/doubleInstructionManage', '二次加工指令管理', '/doubleInstructionManage', 0, 0, 'doubleInstructionManage:view', 0,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1578, '/doubleInstructionManage/doubleInstructionManage', '二次加工指令管理', '/doubleInstructionManage/doubleInstructionManage', 0, 1577, 'doubleInstructionManage:view', 0,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1579, 'doubleInstructionManage/edit', '编辑', 'doubleInstructionManage/edit', 0, 1578, 'reworkInjection:update', 1,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1580, 'doubleInstructionManage/cancel', '取消', 'doubleInstructionManage/cancel', 0, 1578, 'reworkInjection:cancel', 1,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1581, 'doubleInstructionManage/stop', '暂停', 'doubleInstructionManage/stop', 0, 1578, 'reworkInjection:stop', 1,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1582, 'doubleInstructionManage/moveUp', '上移', 'doubleInstructionManage/moveUp', 0, 1578, 'reworkInjection:moveUp', 1,'fwPlan');
INSERT INTO dhlk_app_menu VALUES (1583, 'doubleInstructionManage/moveDown', '下移', 'doubleInstructionManage/moveDown', 0, 1578, 'reworkInjection:moveDown', 1,'fwPlan');


/**条码追溯**/

INSERT INTO dhlk_app_menu VALUES (1800, '/productionOrder', '条码追溯', '/productionOrder', 0, 0, 'productionOrder:view', 0,'fwBar');
INSERT INTO dhlk_app_menu VALUES (1801, '/productionOrder/productionOrder', '条码追溯', '/productionOrder/productionOrder', 0, 1800, 'productionOrder:view', 0,'fwBar');


/**安灯管理**/
-- 异常记录
INSERT INTO dhlk_app_menu VALUES (1802, '/abnormalLog', '异常记录', '/abnormalLog', 0, 0, 'abnormalLog:view', 0,'fwWarn');
INSERT INTO dhlk_app_menu VALUES (1803, '/abnormalLog/abnormalLog', '异常记录', '/abnormalLog/abnormalLog', 0, 1802,'abnormalLog:view', 0,'fwWarn');
INSERT INTO dhlk_app_menu VALUES (1804, 'abnormalLog/start', '开始处理', 'abnormalLog/start', 0, 1803,'abnormalLog:start', 1,'fwWarn');
INSERT INTO dhlk_app_menu VALUES (1805, 'abnormalLog/end', '完成处理', 'abnormalLog/end', 0, 1803,'abnormalLog:end', 1,'fwWarn');
-- PAD安灯
INSERT INTO dhlk_app_menu VALUES (1806, '/andon', '安灯', '/andon', 0, 0, 'andon:view', 0,'fwWarn');
INSERT INTO dhlk_app_menu VALUES (1807, '/andon/andonLog', '安灯记录', '/andon/andonLog', 0, 1806,'andonLog:view', 0,'fwWarn');
INSERT INTO dhlk_app_menu VALUES (1808, '/andon/andonManage', '安灯', '/andon/andonManage', 0, 1806,'andonManage:view', 0,'fwWarn');

-- 故障升级流程定义
INSERT INTO dhlk_app_menu VALUES (1809, '/abnormalSetting', '故障升级流程定义', '/abnormalSetting', 0, 0, 'abnormalSetting:view', 0,'fwWarn');
INSERT INTO dhlk_app_menu VALUES (1810, '/abnormalSetting/abnormalSetting', '故障升级流程定义', '/abnormalSetting/abnormalSetting', 0, 1809,'abnormalSetting:view', 0,'fwWarn');
INSERT INTO dhlk_app_menu VALUES (1811, 'abnormalSetting/edit', '编辑', 'abnormalSetting/edit', 0, 1810,'abnormalSetting:add', 1,'fwWarn');
INSERT INTO dhlk_app_menu VALUES (1812, 'abnormalSetting/delete', '删除', 'abnormalSetting/delete', 0, 1810,'abnormalSetting:delete', 1,'fwWarn');
INSERT INTO dhlk_app_menu VALUES (1813, 'abnormalSetting/add', '新增', 'abnormalSetting/add', 0, 1810,'abnormalSetting:add', 1,'fwWarn');




/**看板管理**/

INSERT INTO dhlk_app_menu VALUES (2000, '/deviceBoard', '看板管理', '/deviceBoard', 0, 0, 'deviceBoard:view', 0,'fwBoard');
INSERT INTO dhlk_app_menu VALUES (2001, '/deviceBoard/workshopSite', '车间现场看板', '/deviceBoard/workshopSite', 0, 2000, 'workshopSite:view', 0,'fwBoard');
INSERT INTO dhlk_app_menu VALUES (2002, '/deviceBoard/deviceBoard', '设备生产监控', '/deviceBoard/deviceBoard', 0, 2000, 'deviceBoard:view', 0,'fwBoard');
INSERT INTO dhlk_app_menu VALUES (2003, '/deviceBoard/finishedProduct', '成品仓库看板', '/deviceBoard/finishedProduct', 0, 2000, 'finishedProduct:view', 0,'fwBoard');
INSERT INTO dhlk_app_menu VALUES (2004, '/deviceBoard/rawMaterial', '设备看板', '/deviceBoard/rawMaterial', 0, 2000, 'rawMaterial:view', 0,'fwBoard');
INSERT INTO dhlk_app_menu VALUES (2005, '/deviceBoard/logistics', '物流办公室看板', '/deviceBoard/logistics', 0, 2000, 'logistics:view', 0,'fwBoard');
INSERT INTO dhlk_app_menu VALUES (2006, '/deviceBoard/quality', '质量办公室看板', '/deviceBoard/quality', 0, 2000, 'quality:view', 0,'fwBoard');
INSERT INTO dhlk_app_menu VALUES (2007, '/deviceBoard/moulds', '模具仓库看板', '/deviceBoard/moulds', 0, 2000, 'moulds:view', 0,'fwBoard');
