package com.zeyiyouhuo.tms.tools.biz;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeyiyouhuo.tms.tools.entity.QWaybill;
import com.zeyiyouhuo.tms.tools.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by dave on 2017/7/18.
 */
@Component
public class DashboardWaybillStatBiz {
    @Autowired OrgService orgService;
    @Autowired JPAQueryFactory queryFactory;

    /**
     * 统计发车运单数量
     * @param orgId 运单机构ID，自动加入子机构
     * @param start 统计起始时间, 包含起始时刻
     * @param end 统计结束时间，包含结束时刻
     * @return
     */
    public long statDeparturedWaybill(long orgId, Date start, Date end) {
        QWaybill root = QWaybill.waybill;
        BooleanBuilder builder = new BooleanBuilder(root.departureTime.goe(start).and(root.departureTime.loe(end)).and(root.revoked.eq(false)));

        if (orgId != 1) { // 顶级机构不用过滤
            builder.and(root.orgId.in(orgService.getSubOrgIdsIncluded(orgId)));
        }

        return queryFactory.from(root).where(builder.getValue()).fetchCount();
    }

    public long statArrivedWaybill(long orgId, Date start, Date end) {
        QWaybill root = QWaybill.waybill;
        BooleanBuilder builder = new BooleanBuilder(root.actualCompletionTime.goe(start).and(root.actualCompletionTime.loe(end)).and(root.revoked.eq(false)));

        if (orgId != 1) { // 顶级机构不用过滤
            builder.and(root.orgId.in(orgService.getSubOrgIdsIncluded(orgId)));
        }

        return queryFactory.from(root).where(builder.getValue()).fetchCount();
    }
}
