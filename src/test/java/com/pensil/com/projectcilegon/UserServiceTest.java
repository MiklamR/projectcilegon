package com.pensil.com.projectcilegon;

import com.pensil.com.projectcilegon.model.TestSchedule;
import com.pensil.com.projectcilegon.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    
    @Test
    void testUserServiceWith2Results() {
        List<TestSchedule> futureList = new ArrayList<>();
        List<TestSchedule> pastList = new ArrayList<>();
        
        userService.listTestScheduleForUserId(1002, pastList, futureList);
        assertEquals(1, futureList.size());
        assertEquals(1, pastList.size());
    }

    @Test
    void deleteTestScheduleById(Long id) {
        testScheduleRepository.deleteById(id);
    }

}
