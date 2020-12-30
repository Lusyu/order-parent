package com.eaphone.jiankang.demo.admin.config;

import com.eaphone.jiankang.admin.config.BaseAdminConfiguration;
import com.eaphone.jiankang.admin.spe.BaseDataTablesRepositoryFactoryBean;
import com.eaphone.jiankang.shiro.ShiroFilterChainMapBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * 包扫描及权限设置
 */
@Configuration
@EnableMongoRepositories(basePackages ={"com.eaphone.jiankang.demo.core.repo"
                                        ,"com.eaphone.jiankang.demo.admin.repo"}
    ,repositoryFactoryBeanClass = BaseDataTablesRepositoryFactoryBean.class)
@ComponentScan(basePackages={"com.eaphone.jiankang.demo.admin","com.eaphone.jiankang.demo.core"})
public class DemoAdminConfiguration extends BaseAdminConfiguration {

    @Override
    protected void configureShiroFilters(ShiroFilterChainMapBuilder builder) {
        builder.and("/actuator/**").anon().andEverything().apiAuth();
    }
}
