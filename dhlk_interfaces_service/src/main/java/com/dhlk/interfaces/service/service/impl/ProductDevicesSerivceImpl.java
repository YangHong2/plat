package com.dhlk.interfaces.service.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.Org;
import com.dhlk.entity.basicmodule.ProductDevices;
import com.dhlk.entity.basicmodule.ProductDevicesTree;
import com.dhlk.entity.basicmodule.User;
import com.dhlk.interfaces.service.dao.OrgDao;
import com.dhlk.interfaces.service.dao.ProductDevicesDao;
import com.dhlk.interfaces.service.dao.UserDao;
import com.dhlk.interfaces.service.service.ProductDevicesService;
import com.dhlk.interfaces.service.util.HeaderUtil;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description 生产设备管理
 * @Author lpsong
 * @Date 2020/3/12
 */
@Service
public class ProductDevicesSerivceImpl implements ProductDevicesService {
    @Autowired
    private ProductDevicesDao productDevicesDao;
    @Value("${attachment.path}")
    private String attachmentPath;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private OrgDao orgDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Result findList(String name,String classifyId) {
        return ResultUtils.success(productDevicesDao.findList(name,headerUtil.tenantId(),classifyId,attachmentPath));
    }

    @Override
    public Result findTreeList(){
        List<Org> orgs=orgDao.treeList(0,headerUtil.tenantId());
        List<ProductDevicesTree> treeList = new ArrayList<>();
        List<ProductDevicesTree> productDevicesList= this.productDevicesToTree(headerUtil.tenantId());
        //遍历机构数，查询机构下人数和绑定的生产设备
        for (Org org:orgs) {
            //查询该机构及下级机构是否有生产设备
            //Integer c = productDevicesDao.findProductDevicesCountByOrgId(org.getId());
            ProductDevicesTree tree = new ProductDevicesTree();
            tree.setId(org.getId().toString());
            tree.setParentId(org.getParentId().toString());
            tree.setTitle(org.getName());
            //当c>0说明该机构或其下级机构绑定了生产设备
//            if(c>0){
//                tree.setComponent("isShow");
//            }
            treeList.add(tree);
            //treeList=this.productDevicesToTree(treeList,tree.getId());
        }
        List<ProductDevicesTree> treeLi = new ArrayList<ProductDevicesTree>();
        for (ProductDevicesTree tree : treeList) {
            if (tree.getParentId().equals("0")) {
                tree.initChildren();
                List<ProductDevicesTree> childList=productDevicesList.stream().filter(s->s.getParentId().equals(tree.getId())).collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(childList)){
                    tree.setChildren(childList);
                    tree.setComponent("isShow");
                }
                treeBuilder(tree, treeList,productDevicesList);
                if(!CheckUtils.isNull(tree.getComponent())&&tree.getComponent().equals("isShow")) {
                    treeLi.add(tree);
                }
            }
        }
        return ResultUtils.success(treeLi);
    }
    //递归组装树机构
    private void treeBuilder(ProductDevicesTree tree, List<ProductDevicesTree> trees,List<ProductDevicesTree> productDevicesList) {
        for (ProductDevicesTree child : trees) {
            if (tree.getId().equals(child.getParentId())) {
                if (child.getChildren() == null){
                    child.initChildren();
                }
                List<ProductDevicesTree> childList=productDevicesList.stream().filter(s->s.getParentId().equals(child.getId())).collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(childList)){
                    child.setChildren(childList);
                    child.setComponent("isShow");
                }
                tree.setHasChildren(true);
                child.setHasParent(true);
                if(!CheckUtils.isNull(child.getComponent())&&child.getComponent().equals("isShow")){
                    treeBuilder(child, trees,productDevicesList);
                    tree.getChildren().add(child);
                }
            }
        }
    }
    @Override
    public Result findDevicesCount(Integer tenantId) {
        return ResultUtils.success(productDevicesDao.findDevicesCount(tenantId));
    }

    /**
     *  查询该机构下生产设备
     * @return
     */
    private List<ProductDevicesTree> productDevicesToTree(Integer tenantId) {
        List<ProductDevicesTree> trees=new ArrayList<>();
        List<ProductDevices> productDevices = productDevicesDao.findProductDevicesByOrgId(tenantId,attachmentPath);
        if(productDevices!=null&&productDevices.size()>0){
            for (ProductDevices pd : productDevices) {
                ProductDevicesTree tree = new ProductDevicesTree();
                tree.setComponent("isShow");
                tree.setId(pd.getId().toString());
                tree.setTbId(pd.getTbId());
                tree.setParentId(pd.getOrgId().toString());
                tree.setTitle(pd.getName());
                tree.setName(pd.getName());
                tree.setClassifyId(pd.getClassifyId());
                tree.setClassifyName(pd.getClassifyName());
                tree.setClassifySet(pd.getClassifySet());
                tree.setNetDevicesList(pd.getNetDevicesList());
                tree.setTenantCode(pd.getTenantCode());
                tree.setCode(pd.getCode());
                tree.setNote(pd.getNote());
                trees.add(tree);
            }
        }
        return trees;
    }
}