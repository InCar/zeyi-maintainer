package com.zeyiyouhuo.tms.tools.repository;

import com.zeyiyouhuo.tms.tools.entity.Waybill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by wh on 2017/1/11.
 */
@Repository
public interface WaybillRepository extends JpaRepository<Waybill, Long> {


}
