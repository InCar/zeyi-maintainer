package com.zeyiyouhuo.tms.tools.beans;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeyiyouhuo.tms.tools.entity.QExceptionEvent;
import com.zeyiyouhuo.tms.tools.entity.QWaybill;
import com.zeyiyouhuo.tms.tools.entity.Waybill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 更新所有运单的check_status值
 * @author xy
 */
@Component
public class WaybillCheckStausUpdate {
    Logger log = LoggerFactory.getLogger(WaybillCheckStausUpdate.class);
    @Autowired
    JPAQueryFactory queryFactory;
    @Value("${cmd:}") String cmd;

    @EventListener
    @Transactional
    public void updateWaybillCheckStatus(ContextRefreshedEvent event) throws Exception {
        if (!"updateWaybillCheckStatus".equals(cmd)) {
            log.info("skipped updateWaybillCheckStatus");
            return;
        } else {
            log.warn("[(started task)]: " + cmd);
        }
        //查出所有运单信息
        int checkStatus = 0;
        Waybill waybill ;
        QWaybill qWaybill = QWaybill.waybill;
        List<Tuple> allWaybillList = queryFactory.select(qWaybill.id, qWaybill.exceptionStatus, qWaybill.isLate).from(qWaybill).fetch();
        QExceptionEvent qExceptionEvent = QExceptionEvent.exceptionEvent;
        for (int x = 0, len2 = allWaybillList.size(); x < len2; x++) {
            waybill = new Waybill();
            waybill.setId(allWaybillList.get(x).get(0, Long.class));
            waybill.setExceptionStatus(allWaybillList.get(x).get(1, Integer.class));
            waybill.setIsLate(allWaybillList.get(x).get(2, Integer.class));
            if (waybill.getExceptionStatus() == 3) {
                checkStatus = 7;    //已完成
            } else if (waybill.getIsLate() == 2) {
                checkStatus = 4;   //车管审批
            } else if (waybill.getIsLate() == 3) {
                checkStatus = 5;   //区域审批
            } else if (waybill.getIsLate() == 4) {
                checkStatus = 6;  //总部审批
            } else {
                int yshCount = 0; //已审核通过
                int yqrCount = 0; //已确认
                int yclCount = 0; //已处理
                int wclCount = 0; //未处理
                Tuple temp;
                //根据运单id查出所有的运单事件 (不查出审核未通过的数据)
                List<Tuple> list = queryFactory.select(qExceptionEvent.processStatus, qExceptionEvent.completeDocument)
                        .from(qExceptionEvent).where(qExceptionEvent.waybillId.eq(waybill.getId()), qExceptionEvent.processStatus.ne(3)).fetch();
                int len = list.size();
                for (int i = 0; i < len; i++) {
                    temp = list.get(i);
                    if (temp.get(0, Integer.class) == 0) {
                        wclCount++;  //未处理
                    } else if (temp.get(0, Integer.class) == 1) {
                        yclCount++; //已处理
                    } else if (temp.get(0, Integer.class) == 2) {
                        yshCount++; //已审核
                    }
                    if (temp.get(1, Integer.class) == 1 || temp.get(1, Integer.class) == 2) {
                        yqrCount++;  //已确认
                    }
                }


                if (yclCount + yshCount == len && yclCount != 0) {
                    checkStatus = 1;    //已处理
                }
                if (yshCount == len) {
                    checkStatus = 3;    //已审核
                }
                if (yqrCount == len && yshCount != len) {
                    checkStatus = 2;    //已确认
                }
                if (len == 0 || wclCount != 0) {
                    checkStatus = 0;    //未处理
                }
            }
            queryFactory.update(qWaybill).set(qWaybill.checkStatus, checkStatus).where(qWaybill.id.eq(waybill.getId())).execute();
        }
        log.info("完成所有运单checkStatus值的更新!");
    }

}
