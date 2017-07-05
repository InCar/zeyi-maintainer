package com.zeyiyouhuo.tms.tools.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeyiyouhuo.tms.tools.dto.SiteDto;
import com.zeyiyouhuo.tms.tools.entity.QRouteSiteRelation;
import com.zeyiyouhuo.tms.tools.entity.QWaybill;
import com.zeyiyouhuo.tms.tools.entity.RouteSiteRelation;
import com.zeyiyouhuo.tms.tools.entity.Waybill;
import com.zeyiyouhuo.tms.tools.repository.WaybillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 运单站点信息更新脚本
 * Created by dave on 2017/7/3.
 */
@Component
public class WaybillSitesDataUpdate {
    Logger log = LoggerFactory.getLogger(DriverPasswordUpdate.class);
    @Autowired JPAQueryFactory queryFactory;
    @Autowired WaybillRepository repository;
    @Autowired ObjectMapper objectMapper;
    @Value("${cmd:}") String cmd;

    @EventListener
    public void updateWaybillSitesData(ContextRefreshedEvent event) throws Exception {
        if (!"updateWaybillSitesData".equals(cmd)) {
            log.info("skipped updateWaybillSitesData");
            return;
        } else {
            log.warn("[(started task)]: " + cmd);
        }

        QWaybill root = QWaybill.waybill;
        QRouteSiteRelation qRelation = QRouteSiteRelation.routeSiteRelation;
        JPAQuery<?> query = queryFactory.from(root).where(root.siteDtos.isNull());
        long count = query.fetchCount();
        int pageSize = 500;
        for (int page = 0; page < Math.ceil(count * 1.0 / pageSize); page ++) {
            List<Waybill> list = query.select(root).offset(page * pageSize).limit(pageSize).fetch();
            for (Waybill w: list) {
                List<RouteSiteRelation> relations = queryFactory.select(qRelation).from(qRelation)
                        .where(qRelation.routeId.eq(w.getRouteId()))
                        .orderBy(qRelation.siteOrder.asc())
                        .fetch();

                List<SiteDto> siteDtos = relations.stream().map(SiteDto::new).collect(Collectors.toList());
                String siteData = objectMapper.writeValueAsString(siteDtos);
                w.setSiteDtos(siteData);
                repository.save(w);
                log.info("update waybill {}， 共{} ", w.getId(), count);
            }
        }

    }

}
