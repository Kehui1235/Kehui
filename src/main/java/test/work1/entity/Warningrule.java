package test.work1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Warningrule {
    @Id
    private int id;
    private int ruleid;
    private Integer alarmname;
    private Long battery;
    private int rule;
    private Double min;
    private Double max;

}
