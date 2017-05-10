package com.zeyiyouhuo.tms.tools.repository;

import com.zeyiyouhuo.tms.tools.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by dave on 17/5/10.
 */
public interface TruckRepository extends JpaRepository<Truck, Long>, QueryDslPredicateExecutor<Truck> {
}
