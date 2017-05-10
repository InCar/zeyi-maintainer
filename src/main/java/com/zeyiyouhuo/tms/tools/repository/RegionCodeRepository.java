package com.zeyiyouhuo.tms.tools.repository;

import com.zeyiyouhuo.tms.tools.entity.RegionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dave on 17/5/9.
 */
@Repository
public interface RegionCodeRepository extends JpaRepository<RegionCode, Integer> {

    @Query("select r from RegionCode r where r.phoneCode = ?1")
    List<RegionCode> findByPhoneCode(String phoneCode);


}
