package com.eaphone.jiankang.demo.admin.repo;

import com.eaphone.jiankang.admin.spe.BaseDataTablesRepository;
import com.eaphone.jiankang.demo.core.document.Demo;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDemoRepository extends BaseDataTablesRepository<Demo,String> {
}
