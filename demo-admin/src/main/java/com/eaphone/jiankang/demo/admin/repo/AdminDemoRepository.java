package com.eaphone.jiankang.demo.admin.repo;

import com.eaphone.jiankang.admin.spe.BaseDataTablesRepository;
import com.eaphone.jiankang.demo.core.document.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDemoRepository extends BaseDataTablesRepository<Order,String> {
}
