package com.zeyiyouhuo.tms.tools.beans;

import com.zeyiyouhuo.tms.tools.entity.User;
import com.zeyiyouhuo.tms.tools.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * Created by dave on 17/5/5.
 */
@Component
public class DriverPasswordUpdate {
    Logger log = LoggerFactory.getLogger(DriverPasswordUpdate.class);
    @Autowired UserRepository repository;
    @Value("${cmd:}") String cmd;

    @EventListener
    public void updateDriverPassword(ContextRefreshedEvent event) {
        if (!"updateDriverPassword".equals(cmd)) {
            log.info("skipped updateDriverPassword");
            return;
        } else {
            log.warn("[(started task)]: " + cmd);
        }

        Page<User> page = repository.findDriver(new PageRequest(0, 500));
        long total = page.getTotalElements();
        int pageNo = 0;
        while (page.getNumberOfElements() > 0) {
            for (int i = 0; i < page.getNumberOfElements(); i++) {
                User u = page.getContent().get(i);
                String password = u.getPhone().substring(5);
                String encryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                u.setPassword(encryptPassword);
                repository.saveAndFlush(u);
                log.info("已经完成{}/{}，当前：{}/{}", pageNo * 500 + i + 1, total, u.getRealName(), u.getPhone());
            }

            pageNo += 1;
            page = repository.findAll(new PageRequest(pageNo, 500));
        }
        log.info("完成任务：" + cmd);
    }
}
