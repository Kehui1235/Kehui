package test.work1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Car {
    @Id
    private int id;
//    private String vid;
    private Long battery_id;
//    private int milage;
//    private int health_status;

}
