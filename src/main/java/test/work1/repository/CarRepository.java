package test.work1.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import test.work1.entity.Car;

public interface CarRepository extends JpaRepository<Car,Long>{

}
