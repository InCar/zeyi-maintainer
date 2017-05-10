package com.zeyiyouhuo.tms.tools.beans;

import com.querydsl.core.BooleanBuilder;
import com.zeyiyouhuo.tms.tools.entity.*;
import com.zeyiyouhuo.tms.tools.repository.PartnerRepository;
import com.zeyiyouhuo.tms.tools.repository.RegionCodeRepository;
import com.zeyiyouhuo.tms.tools.repository.SiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dave on 17/5/9.
 */
@Component
public class SiteCodeUpdate {
    Logger log = LoggerFactory.getLogger(SiteCodeUpdate.class);
    @Autowired SiteRepository siteRepository;
    @Autowired RegionCodeRepository regionCodeRepository;
    @Autowired PartnerRepository partnerRepository;
    @Value("${cmd:}") String cmd;

    @EventListener
    public void updateSiteCode(ContextRefreshedEvent event) {
        if (!"updateSiteCode".equals(cmd)) {
            log.info("skipped updateSiteCode");
            return;
        } else {
            log.warn("[(started task)]: " + cmd);
        }

        Page<Site> page = siteRepository.findAll(new PageRequest(0, 500));
        long total = page.getTotalElements();
        int pageNo = 0;
        while (page.getNumberOfElements() > 0) {
            for (int i = 0; i < page.getNumberOfElements(); i++) {
                Site site = page.getContent().get(i);
                writeSiteCodeAndSave(site);
                log.info("站点{}:{}，已完成{}/{}", site.getId(), site.getSiteCode(), 500 * pageNo + i + 1, total);
            }

            pageNo += 1;
            page = siteRepository.findAll(new PageRequest(pageNo, 500));
        }
    }

    private void writeSiteCodeAndSave(Site site) {
        RegionCode region = regionCodeRepository.findOne(site.getCityCode());
        String phoneCode = region.getPhoneCode();
        if (phoneCode == null || "".equals(phoneCode)) {
            throw new RuntimeException("站点所在城市没有区号信息");
        }

        QPartner qPartner = QPartner.partner;
        Partner root = partnerRepository.findOne(site.getPartnerId());
        if (root.getParentPartnerId() > 0) {
            root = partnerRepository.findOne(root.getRootPartnerId());
        }
        List<Long> parnterIds = partnerRepository.findAll(new BooleanBuilder().and(qPartner.rootPartnerId.eq(root.getId())), new PageRequest(0, Integer.MAX_VALUE))
                .getContent().stream().map(Partner::getId).collect(Collectors.toList());
        List<Integer> regionIds = regionCodeRepository.findByPhoneCode(phoneCode).stream().map(RegionCode::getCode).collect(Collectors.toList());

        int num = 1;
        QSite qSite = QSite.site;
        Page<Site> sites = siteRepository.findAll(new BooleanBuilder()
                .and(qSite.id.lt(site.getId())) // 只读取比当前站点序号小的
                .and(qSite.partnerId.in(parnterIds))
                .and(qSite.cityCode.in(regionIds))
                .getValue(), new PageRequest(0, 1, Sort.Direction.DESC, "orderNum"));
        if (sites.getNumberOfElements() > 0) {
            num = sites.getContent().get(0).getOrderNum() + 1;
        }
        site.setOrderNum(num);
        site.setSiteCode("ZY-" + root.getPartnerOrgCode() + "-" + phoneCode + "-" + String.format("%04d", num));
        siteRepository.saveAndFlush(site);
    }
}
