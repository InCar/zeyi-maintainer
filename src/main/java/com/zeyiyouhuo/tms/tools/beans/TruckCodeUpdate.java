package com.zeyiyouhuo.tms.tools.beans;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeyiyouhuo.tms.tools.entity.QTruck;
import com.zeyiyouhuo.tms.tools.entity.Truck;
import com.zeyiyouhuo.tms.tools.repository.TruckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Created by dave on 17/5/10.
 */
@Component
public class TruckCodeUpdate {
    Logger log = LoggerFactory.getLogger(TruckCodeUpdate.class);
    @Autowired TruckRepository truckRepository;
    @Autowired JPAQueryFactory queryFactory;
    @Value("${cmd:}") String cmd;

    @EventListener
    public void udpateTruckCode(ContextRefreshedEvent event) {
        if (!"udpateTruckCode".equals(cmd)) {
            log.info("skipped udpateTruckCode");
            return;
        } else {
            log.warn("[(started task)]: " + cmd);
        }

        Page<Truck> page = truckRepository.findAll(new PageRequest(0, 500));
        long total = page.getTotalElements();
        int pageNo = 0;
        while (page.getNumberOfElements() > 0) {
            for (int i = 0; i < page.getNumberOfElements(); i++) {
                Truck truck = page.getContent().get(i);
                writeTruckCodeAndSave(truck);
                log.info("车辆{}:{}，已完成{}/{}", truck.getId(), truck.getTruckCode(), 500 * pageNo + i + 1, total);
            }

            page = truckRepository.findAll(new PageRequest(++pageNo, 500));
        }
    }

    private void writeTruckCodeAndSave(Truck truck) {
        QTruck qTruck = QTruck.truck;
        LocalDate date = truck.getCreateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date start = Date.from(date.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(date.with(TemporalAdjusters.firstDayOfNextMonth()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        BooleanExpression exp;
        if (truck.getType() == 1) {
            exp = qTruck.type.eq(1);
        } else {
            exp = qTruck.type.in(0, 2);
        }

        int num = 1;
        Truck last = queryFactory.select(qTruck).from(qTruck)
                .where(qTruck.id.lt(truck.getId()).and(qTruck.createTime.between(start, end)).and(exp))
                .orderBy(qTruck.id.desc())
                .limit(1)
                .fetchOne();
        if (last != null && last.getTruckCode() != null) {
            num = Integer.valueOf(last.getTruckCode().substring(13)) + 1;
        }
        truck.setTruckCode(String.format("ZY-%02d-%s-%04d",
                truck.getType() == 1 ? 2 : 1,
                new SimpleDateFormat("yyyyMM").format(start),
                num));
        truckRepository.saveAndFlush(truck);
    }
}
