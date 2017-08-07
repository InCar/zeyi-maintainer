package com.zeyiyouhuo.tms.tools.service;

import com.querydsl.core.types.Predicate;
import com.zeyiyouhuo.tms.tools.entity.WaybillMonthStat;
import com.zeyiyouhuo.tms.tools.repository.WaybillMonthStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * created by dave 2017/7/21
 */
@Service
public class WaybillMonthStatService {
    @Autowired WaybillMonthStatRepository repository;

    public WaybillMonthStat save(WaybillMonthStat stat) {
        return repository.save(stat);
    }

    public Page<WaybillMonthStat> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    public Page<WaybillMonthStat> findByOrgId(long orgId, Pageable pageable) {
        return repository.findByOrgId(orgId, pageable);
    }
}
