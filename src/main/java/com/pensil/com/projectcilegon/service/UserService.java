package com.pensil.com.projectcilegon.service;

import com.pensil.com.projectcilegon.model.TestSchedule;
import com.pensil.com.projectcilegon.model.User;
import com.pensil.com.projectcilegon.reporsitory.TestScheduleRepository;
import com.pensil.com.projectcilegon.reporsitory.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final TestScheduleRepository testScheduleRepository;

@Autowired
    public UserService(UserRepository userRepository, TestScheduleRepository testScheduleRepository) {
        this.userRepository = userRepository;
        this.testScheduleRepository = testScheduleRepository;
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public User getUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User " + id + " is not found");
        }
        User foundUser = userOptional.get();
        return foundUser;
    }

    public User addUser(User user) {
        return  userRepository.save(user);
    }

    public List<TestSchedule> listTestScheduleForUserId(long userId) {

        List<TestSchedule> scheduleListFromDb = testScheduleRepository.findByUserId(userId);
        return scheduleListFromDb;
    }

    public void listTestScheduleForUserId(long userId, List<TestSchedule> pastScheduleList, List<TestSchedule> futureScheduleList) {

        LocalDateTime currentTime = LocalDateTime.now();

        List<TestSchedule> scheduleListFromDb = testScheduleRepository.findByUserId(userId);
        for (TestSchedule schedule : scheduleListFromDb) {
            LocalDateTime scheduleTime = schedule.getTestDateTime();

            if (scheduleTime.isAfter(currentTime)) {
                System.out.println("This " + scheduleTime + " is in the future");
                futureScheduleList.add(schedule);

            } else {
                System.out.println("This " + scheduleTime + " is in the past, you can't change it");
                pastScheduleList.add(schedule);
            }
        }
    }



    public TestSchedule addTestSchedule(TestSchedule testSchedule) {

        return testScheduleRepository.save(testSchedule);
    }


    @Transactional
    public void deleteTestScheduleById(Long id) {
        testScheduleRepository.deleteById(id);
    }
}
