package com.zeyiyouhuo.tms.tools.repository;

import com.zeyiyouhuo.tms.tools.entity.PayableContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dave on 17/5/22.
 */
@Repository
public interface PayableContractRepository extends JpaRepository<PayableContract, Long> {

}
