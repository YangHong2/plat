package com.dhlk.basicmodule.service.service.Impl;

import com.dhlk.basicmodule.service.dhlk_basic_module_service.DhlkBasicModuleServiceApplication;
import com.dhlk.basicmodule.service.service.AppMenuService;
import com.dhlk.domain.Result;
import com.dhlk.entity.app.AppTenant;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DhlkBasicModuleServiceApplication.class)
public class AppMenuServiceImplTest {
    @Autowired
    AppMenuService appMenuService;
    @Test
    public void addAppTenant(){
        List<AppTenant> list = new ArrayList<>();
        AppTenant appTenant = new AppTenant();
        appTenant.setAppCode("fanwo");
        appTenant.setAppId(12);
        appTenant.setTenantId(23);

        AppTenant appTenant1 = new AppTenant();
        appTenant1.setAppCode("fanwo1");
        appTenant1.setAppId(13);
        appTenant1.setTenantId(24);

        list.add(appTenant);
        list.add(appTenant1);
        appMenuService.addAppTenant(list);
    }

    @Test
    public void addAppPermissions(){
        appMenuService.addAppPermissions(12,"98,99,100");
    }

    @Test
    public void findListApp(){
        appMenuService.findListApp(1);
    }

    @Test
    public void findListByCode(){
        appMenuService.findListByCode(1);
    }

    @Test
    public void findListAppChecked(){
        appMenuService.findListAppChecked(1);
    }

    @Test
    public void findListRoleChecked(){
        appMenuService.findListRoleChecked(1,1);
    }
}
