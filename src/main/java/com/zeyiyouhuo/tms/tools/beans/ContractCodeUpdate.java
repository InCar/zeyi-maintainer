package com.zeyiyouhuo.tms.tools.beans;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeyiyouhuo.tms.tools.entity.PayableContract;
import com.zeyiyouhuo.tms.tools.entity.QPayableContract;
import com.zeyiyouhuo.tms.tools.entity.QReceivableContract;
import com.zeyiyouhuo.tms.tools.entity.ReceivableContract;
import com.zeyiyouhuo.tms.tools.repository.PayableContractRepository;
import com.zeyiyouhuo.tms.tools.repository.ReceivableContractRepository;
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
 * Created by dave on 17/5/22.
 */
@Component
public class ContractCodeUpdate {
    Logger log = LoggerFactory.getLogger(ContractCodeUpdate.class);
    @Autowired ReceivableContractRepository receivableContractRepository;
    @Autowired PayableContractRepository payableContractRepository;
    @Autowired JPAQueryFactory queryFactory;
    @Value("${cmd:}") String cmd;

    @EventListener
    public void udpateContractCode(ContextRefreshedEvent event) {
        if (!"udpateContractCode".equals(cmd)) {
            log.info("skipped udpateContractCode");
            return;
        } else {
            log.warn("[(started task)]: " + cmd);
        }

        Page<ReceivableContract> page1 = receivableContractRepository.findAll(new PageRequest(0, 500));
        long total = page1.getTotalElements();
        int pageNo = 0;
        while (page1.getNumberOfElements() > 0) {
            for (int i = 0; i < page1.getNumberOfElements(); i++) {
                ReceivableContract contract = page1.getContent().get(i);
                writeContractCode(contract);
                log.info("应收合同{}:{}，已完成{}/{}", contract.getId(), contract.getContractNumber(), 500 * pageNo + i + 1, total);
            }

            page1 = receivableContractRepository.findAll(new PageRequest(++pageNo, 500));
        }
        log.info("完成应收合同编号更新");

        Page<PayableContract> page2 = payableContractRepository.findAll(new PageRequest(0, 500));
        total = page2.getTotalElements();
        pageNo = 0;
        while (page2.getNumberOfElements() > 0) {
            for (int i = 0; i < page2.getNumberOfElements(); i++) {
                PayableContract contract = page2.getContent().get(i);
                writeContractCode(contract);
                log.info("应付合同{}:{}，已完成{}/{}", contract.getId(), contract.getContractNumber(), 500 * pageNo + i + 1, total);
            }

            page2 = payableContractRepository.findAll(new PageRequest(++pageNo, 500));
        }
        log.info("完成应付合同编号更新");
    }

    private void writeContractCode(ReceivableContract contract) {
        LocalDate date = contract.getCreateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date start = Date.from(date.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(date.with(TemporalAdjusters.firstDayOfNextMonth()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        QReceivableContract qReceivableContract = QReceivableContract.receivableContract;
        long count = queryFactory.from(qReceivableContract)
                .where(qReceivableContract.id.lt(contract.getId())
                        .and(qReceivableContract.contractCategory.eq(contract.getContractCategory()))
                        .and(qReceivableContract.createTime.goe(start))
                        .and(qReceivableContract.createTime.lt(end)))
                .fetchCount();
        contract.setContractNumber(String.format("ZY-AC-%02d-%02d-%s-%07d", contract.getContractType(),
                contract.getContractCategory(), new SimpleDateFormat("yyyyMM").format(contract.getCreateTime()), count + 1));
        receivableContractRepository.save(contract);
    }

    private void writeContractCode(PayableContract contract) {
        LocalDate date = contract.getCreateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date start = Date.from(date.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(date.with(TemporalAdjusters.firstDayOfNextMonth()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        QPayableContract qPayableContract = QPayableContract.payableContract;
        long count = queryFactory.from(qPayableContract)
                .where(qPayableContract.id.lt(contract.getId())
                        .and(qPayableContract.contractCategory.eq(contract.getContractCategory()))
                        .and(qPayableContract.createTime.goe(start))
                        .and(qPayableContract.createTime.lt(end)))
                .fetchCount();
        contract.setContractNumber(String.format("ZY-DC-%02d-%02d-%s-%07d", contract.getContractType(),
                contract.getContractCategory(), new SimpleDateFormat("yyyyMM").format(contract.getCreateTime()), count + 1));
        payableContractRepository.save(contract);
    }
}
