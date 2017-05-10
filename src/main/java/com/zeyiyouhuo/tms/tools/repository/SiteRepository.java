package com.zeyiyouhuo.tms.tools.repository;

import com.zeyiyouhuo.tms.tools.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by dave on 17/5/9.
 */
@Repository
public interface SiteRepository extends JpaRepository<Site, Long>, QueryDslPredicateExecutor<Site> {

}
