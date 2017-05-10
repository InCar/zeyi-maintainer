package com.zeyiyouhuo.tms.tools.repository;

import com.zeyiyouhuo.tms.tools.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by dave on 17/5/10.
 */
@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>, QueryDslPredicateExecutor<Partner> {
}
