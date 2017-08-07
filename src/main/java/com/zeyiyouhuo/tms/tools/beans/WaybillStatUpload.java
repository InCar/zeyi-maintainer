package com.zeyiyouhuo.tms.tools.beans;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeyiyouhuo.tms.tools.biz.DashboardWaybillStatBiz;
import com.zeyiyouhuo.tms.tools.entity.Org;
import com.zeyiyouhuo.tms.tools.entity.QOrg;
import com.zeyiyouhuo.tms.tools.entity.WaybillDayStat;
import com.zeyiyouhuo.tms.tools.repository.PayableContractRepository;
import com.zeyiyouhuo.tms.tools.repository.ReceivableContractRepository;
import com.zeyiyouhuo.tms.tools.service.OrgService;
import com.zeyiyouhuo.tms.tools.service.WaybillDayStatService;
import com.zeyiyouhuo.tms.tools.service.WaybillMonthStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by dave at 2017/8/4 下午5:11.
 */
@Component
public class WaybillStatUpload {
    Logger log = LoggerFactory.getLogger(ContractCodeUpdate.class);
    @Autowired ReceivableContractRepository receivableContractRepository;
    @Autowired PayableContractRepository payableContractRepository;
    @Autowired JPAQueryFactory queryFactory;
    @Autowired DashboardWaybillStatBiz statBiz;
    @Autowired WaybillDayStatService dayStatService;
    @Autowired WaybillMonthStatService monthStatService;
    @Autowired OrgService orgService;
    @Value("${cmd:}") String cmd;

    @EventListener
    public void udpateWaybillStat(ContextRefreshedEvent event) {
        if (!"udpateWaybillStat".equals(cmd)) {
            log.info("skipped udpateWaybillStat");
            return;
        } else {
            log.warn("[(started task)]: " + cmd);
        }

        for (int i = 30; i > 0; i--) {
            Date start = Date.from(LocalDate.now().minusDays(1 + i).atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date end = Date.from(LocalDate.now().minusDays(i).atStartOfDay().minusSeconds(1).atZone(ZoneId.systemDefault()).toInstant());
            QOrg root = QOrg.org;
            List<Org> orgs = orgService.findAll(root.orgType.eq(Org.OrgType.ZEYI_ORG.getTypeCode()), new PageRequest(0, 500)).getContent();
            orgs.forEach(o -> {
                long startedCount = statBiz.statDeparturedWaybill(o.getId(), start, end);
                long arrivedCount = statBiz.statArrivedWaybill(o.getId(), start, end);
                WaybillDayStat stat = new WaybillDayStat();
                stat.setOrgId(o.getId());
                stat.setStartedCount(startedCount);
                stat.setArrivedCount(arrivedCount);
                stat.setStatTime(start);
                dayStatService.save(stat);
            });
            log.info("每日运单统计任务已完成: {}", new SimpleDateFormat("yyyy-MM-dd").format(start));
        }
    }

}
