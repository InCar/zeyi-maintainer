package com.zeyiyouhuo.tms.tools.repository;

import com.zeyiyouhuo.tms.tools.entity.TruckOwnerReceiptAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dave at 2017/8/8 上午10:26.
 */
@Repository
public interface TruckOwnerReceiptAccountRepository extends JpaRepository<TruckOwnerReceiptAccount, Long> {

    @Query("select a from TruckOwnerReceiptAccount a where a.ownerId = ?1")
    List<TruckOwnerReceiptAccount> findByOwnerId(long ownerId);
}
