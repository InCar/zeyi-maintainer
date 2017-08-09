package com.zeyiyouhuo.tms.tools.beans;

import com.zeyiyouhuo.tms.tools.entity.TruckOwner;
import com.zeyiyouhuo.tms.tools.entity.TruckOwnerReceiptAccount;
import com.zeyiyouhuo.tms.tools.repository.TruckOwnerReceiptAccountRepository;
import com.zeyiyouhuo.tms.tools.repository.TruckOwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * Created by dave at 2017/8/9 下午1:56.
 */
@Component
public class TruckOwnerUpdate {
    Logger log = LoggerFactory.getLogger(TruckOwnerUpdate.class);
    @Autowired TruckOwnerRepository ownerRepository;
    @Autowired TruckOwnerReceiptAccountRepository receiptAccountRepository;
    @Value("${cmd:}") String cmd;

    @EventListener
    public void udpateTruckOwner(ContextRefreshedEvent event) {
        if (!"updateTruckOwner".equals(cmd)) {
            log.info("skipped udpateTruckOwner");
            return;
        } else {
            log.warn("[(started task)]: " + cmd);
        }

        int pageNo = 0;
        int pageSize = 500;
        int totalPages;

        do {
            Page<TruckOwner> owners = ownerRepository.findAll(new PageRequest(pageNo, pageSize));
            totalPages = owners.getTotalPages();

            owners.getContent().stream().forEach(owner -> {
                TruckOwnerReceiptAccount account = new TruckOwnerReceiptAccount(owner);
                receiptAccountRepository.saveAndFlush(account);
            });

            pageNo += 1;
            log.info("更新车主： {}/{}", pageNo, totalPages);
        } while (pageNo < totalPages);

    }
}
