package com.zeyiyouhuo.tms.tools.repository;

import com.zeyiyouhuo.tms.tools.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by dave on 16/10/19.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("select u from User u where u.userType = 0")
    Page<User> findDriver(Pageable pageable);
}
