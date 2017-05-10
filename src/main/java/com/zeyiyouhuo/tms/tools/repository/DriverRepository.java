package com.zeyiyouhuo.tms.tools.repository;

import com.zeyiyouhuo.tms.tools.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dave on 17/5/5.
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
