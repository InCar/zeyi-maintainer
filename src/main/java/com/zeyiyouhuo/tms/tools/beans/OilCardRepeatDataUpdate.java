package com.zeyiyouhuo.tms.tools.beans;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeyiyouhuo.tms.tools.entity.QOilCard;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对油卡中g7油卡重复的油卡进行删除操作
 * @author xy
 */
@Component
public class OilCardRepeatDataUpdate {
    Logger log = LoggerFactory.getLogger(OilCardRepeatDataUpdate.class);
    @Autowired
    JPAQueryFactory queryFactory;
    @Value("${cmd:}")
    String cmd;
    @Autowired
    EntityManager entityManager;

    @EventListener
    @Transactional
    public void updateOilCardRepeatData(ContextRefreshedEvent event) throws Exception {
        if (!"updateOilCardRepeatData".equals(cmd)) {
            log.info("skipped updateOilCardRepeatData");
            return;
        } else {
            log.warn("[(started task)]: " + cmd);
        }

        //查出所有重复&可下发的g7油卡
        String sql = "select count(*) as x, card_number, group_concat(id) as ids from hyr_oil_card"
                + " where status = 1 and remark like 'G7油卡数据同步,同步时间%%%' and create_time >= '2017-08-31'"
                + "group by card_number having x > 1";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List rows = query.getResultList();
        String[] idsArr;
        String ids;
        List<Long> updateIds = new ArrayList<>();
        for (Object obj : rows) {
            Map row = (Map) obj;
            ids = row.get("ids").toString();
            idsArr = ids.split(",");
            for (int i = 1, len = idsArr.length; i < len; i++) {
                updateIds.add(Long.parseLong(idsArr[i]));
            }
        }
        QOilCard qOilCard = QOilCard.oilCard;
        //long exceuteCount = queryFactory.update(qOilCard).set(qOilCard.deleted, true).where(qOilCard.id.in(updateIds)).execute();
        long exceuteCount = queryFactory.delete(qOilCard).where(qOilCard.id.in(updateIds)).execute();
        log.info("完成重复数据的删除! exceuteCount : " + exceuteCount);
    }
}
