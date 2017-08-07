package com.zeyiyouhuo.tms.tools.repository;

import com.zeyiyouhuo.tms.tools.entity.WaybillMonthStat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * created by dave 2017/7/21
 */
@Repository
public interface WaybillMonthStatRepository extends JpaRepository<WaybillMonthStat, Long>,
        QueryDslPredicateExecutor<WaybillMonthStat> {

    @Query("select s from WaybillMonthStat s where s.orgId = ?1")
    Page<WaybillMonthStat> findByOrgId(long orgId, Pageable pageble);
}
