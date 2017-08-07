package com.zeyiyouhuo.tms.tools.service;

import com.querydsl.core.types.Predicate;
import com.zeyiyouhuo.tms.tools.entity.Org;
import com.zeyiyouhuo.tms.tools.repository.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dave on 16/11/14.
 */
@Service
public class OrgService {
    @Autowired OrgRepository repository;

    public Org save(Org org) {
        return repository.save(org);
    }

    public Org saveAndFlush(Org org) {
        return repository.saveAndFlush(org);
    }

    public Org findById(long id) {
        return repository.findOne(id);
    }

    public Org findByOrgcode(String orgcode) {
        return repository.findByOrgcode(orgcode);
    }

    public Org findByName(String name) {
        return repository.findByName(name);
    }

    public Page<Org> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    public Page<Org> search(Long rootId, Long parentId, String orgCode, Integer orgType, String nameLk,
                            Integer provinceCode, Integer cityCode, Pageable pageable) {
        return repository.search(rootId, parentId, orgCode, orgType, nameLk, provinceCode, cityCode, pageable);
    }

    public List<Org> getPath(long rootId, int leftOrder, int rightOrder) {
        return repository.getPath(rootId, leftOrder, rightOrder, new Sort(Sort.Direction.ASC, "leftOrder"));
    }

    public Page<Org> getSubOrgs(long rootId, int leftOrder, int rightOrder, int pageNo, int pageSize) {
        return repository.getSubOrgs(rootId, leftOrder, rightOrder,
                new PageRequest(pageNo - 1, pageSize, new Sort(Sort.Direction.ASC, "parentId")));
    }

    public Page<Org> getSubOrgsIncluded(long rootId, int leftOrder, int rightOrder, int pageNo, int pageSize) {
        return repository.getSubOrgsIncluded(rootId, leftOrder, rightOrder,
                new PageRequest(pageNo - 1, pageSize, new Sort(Sort.Direction.ASC, "parentId")));
    }

    public List<Long> getSubOrgIdsIncluded(long orgId) {
        Org org = findById(orgId);
        if (org == null) {
            return Collections.emptyList();
        }
        List<Org> orgList = getSubOrgsIncluded(org.getRootId(), org.getLeftOrder(), org.getRightOrder(), 1, 1000).getContent();
        return orgList.stream().map(Org::getId).collect(Collectors.toList());
    }

    public void delete(Org org) {
        if (org != null) {
            org.setDeleted(true);
            repository.save(org);
        }
    }

}
