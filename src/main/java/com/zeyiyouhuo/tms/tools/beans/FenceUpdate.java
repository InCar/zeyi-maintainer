package com.zeyiyouhuo.tms.tools.beans;

import com.zeyiyouhuo.tms.tools.GeoUtil;
import com.zeyiyouhuo.tms.tools.entity.Site;
import com.zeyiyouhuo.tms.tools.repository.SiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by dave on 17/5/26.
 */
@Component
public class FenceUpdate {
    Logger log = LoggerFactory.getLogger(FenceUpdate.class);
    @Autowired SiteRepository siteRepository;
    @Value("${cmd:}") String cmd;

    @EventListener
    public void updateFence(ContextRefreshedEvent event) {
        if (!"updateFence".equals(cmd)) {
            log.info("skipped updateFence");
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
                if (site.getFence() != null) continue;
                double lng = site.getLongitude().doubleValue();
                double lat = site.getLatitude().doubleValue();
                double lngDelta = GeoUtil.getDeltaLng(site.getLatitude().doubleValue(), 1000);
                double latDelta = GeoUtil.getDeltaLat(1000);
                double lngMin = lng - lngDelta;
                double lngMax = lng + lngDelta;
                double latMin = lat - latDelta;
                double latMax = lat + latDelta;
                site.setLngMin(new BigDecimal(lngMin));
                site.setLngMax(new BigDecimal(lngMax));
                site.setLatMin(new BigDecimal(latMin));
                site.setLatMax(new BigDecimal(latMax));

                site.setFence(String.format("%.7f,%.7f|%.7f,%.7f|%.7f,%.7f|%.7f,%.7f", lngMin, latMin, lngMin, latMax, lngMax, latMax, lngMax, latMin));
                site.setFenceRounded(false);
                siteRepository.saveAndFlush(site);
                log.info("站点围栏{}:{}，已完成{}/{}", site.getId(), site.getName(), 500 * pageNo + i + 1, total);
            }

            page = siteRepository.findAll(new PageRequest(++pageNo, 500));
        }
        log.info("完成任务：" + cmd);
    }
}
