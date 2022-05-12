package com.bl.ep;

import com.bl.ep.utils.DateUtil;
import com.bl.ep.utils.TimeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;

@SpringBootTest
class EpidemicPreventionInformationApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void age() throws ParseException {
        int ageByBirth = DateUtil.getAgeByBirth(new Date());
        System.out.println(ageByBirth);
    }

    @Test
    void time(){
        Long nowToNextDaySeconds = TimeUtil.getNowToNextDaySeconds();
        Assertions.assertNotNull(nowToNextDaySeconds);
        System.out.println(nowToNextDaySeconds);
    }
}
