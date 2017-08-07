package com.zeyiyouhuo.tms.tools.repository;

import com.zeyiyouhuo.tms.tools.entity.Org;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dave on 16/11/15.
 */
@Repository
public interface OrgRepository extends JpaRepository<Org, Long>, QueryDslPredicateExecutor<Org> {

    @Query("SELECT o FROM Org o WHERE o.orgCode = ?1")
    Org findByOrgcode(String orgcode);

    @Query("SELECT o FROM Org o WHERE o.name = ?1 AND o.deleted = false")
    Org findByName(String name);

    @Query("SELECT o FROM Org o WHERE (?1 IS NULL OR o.rootId = ?1) "
            + "AND ((?2 IS NULL OR o.parentId = ?2)) "
            + "AND (?3 IS NULL OR o.orgCode = ?3) "
            + "AND (?4 IS NULL OR o.orgType = ?4) "
            + "AND (?5 IS NULL OR o.name LIKE CONCAT('%', ?5, '%')) "
            + "AND (?6 IS NULL OR o.provinceCode = ?6) "
            + "AND (?7 IS NULL OR o.cityCode = ?7) AND o.deleted = false")
    Page<Org> search(Long rootId, Long parentId, String orgCode, Integer orgType, String nameLK,
                     Integer provinceCode, Integer cityCode, Pageable pageable);

    @Query("SELECT o FROM Org o WHERE o.rootId = ?1 AND o.leftOrder <= ?2 AND o.rightOrder >= ?3")
    List<Org> getPath(long rootId, int leftOrder, int rightOrder, Sort sort);

    @Query("SELECT o FROM Org o WHERE o.rootId = ?1 AND o.leftOrder > ?2 AND o.rightOrder < ?3 AND o.deleted = false")
    Page<Org> getSubOrgs(long rootId, int leftOrder, int rightOrder, Pageable pageable);

    @Query("SELECT o FROM Org o WHERE o.rootId = ?1 AND o.leftOrder >= ?2 AND o.rightOrder <= ?3 AND o.deleted = false")
    Page<Org> getSubOrgsIncluded(long rootId, int leftOrder, int rightOrder, Pageable pageable);
}
