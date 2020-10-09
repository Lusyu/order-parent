package com.is666is.lpl.order.admin.config;

import com.eaphone.jiankang.admin.config.BaseAdminConfiguration;
import com.eaphone.jiankang.admin.spe.BaseDataTablesRepositoryFactoryBean;
import com.eaphone.jiankang.shiro.ShiroFilterChainMapBuilder;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configurable
@EnableMongoRepositories(basePackages ={"com.is666is.lpl.order.core.repo"}
    ,repositoryFactoryBeanClass = BaseDataTablesRepositoryFactoryBean.class)
@ComponentScan(basePackages={"com.is666is.lpl.order.core"})
public class OrderAdminConfiguration extends BaseAdminConfiguration {

    @Override
    protected void configureShiroFilters(ShiroFilterChainMapBuilder builder) {
        builder.and("/actuator/**").anon().andEverything().apiAuth();
    }
}
