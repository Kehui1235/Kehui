package test.work1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.work1.entity.Warningrule;

import java.util.List;

public interface WarningruleRepository extends JpaRepository<Warningrule, Integer> {
    List<Warningrule> findByAlarmnameAndBattery(Integer alarmname, Long battery);
}
