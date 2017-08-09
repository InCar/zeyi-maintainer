package com.zeyiyouhuo.tms.tools.repository;

import com.zeyiyouhuo.tms.tools.entity.TruckOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by dave on 17/1/5.
 */
@Repository
public interface TruckOwnerRepository extends JpaRepository<TruckOwner, Long> {

    @Query("select o from TruckOwner o where o.userId = ?1")
    TruckOwner findByUserId(long userId);

    @Query("select o from TruckOwner o where o.phone = ?1 and o.deleted = false")
    TruckOwner findByPhone(String phone);

    @Query("select o from TruckOwner o where o.idCard = ?1 and o.deleted = false")
    TruckOwner findByIdCard(String idCard);

    @Query("select o from TruckOwner o where o.licenseNo = ?1 and o.deleted = false")
    TruckOwner findByLicenseNo(String licenseNo);
}
