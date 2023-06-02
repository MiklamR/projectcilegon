package com.pensil.com.projectcilegon.reporsitory;

import com.pensil.com.projectcilegon.model.TestSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestScheduleRepository extends JpaRepository <TestSchedule, Long> {
    List<TestSchedule> findByUserId(long userId);
}
