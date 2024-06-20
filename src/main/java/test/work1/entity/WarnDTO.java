package test.work1.entity;


import lombok.Data;

@Data
public class WarnDTO {
    private Long carId;
    private Long warnId;
    private WarnSignal signal;
}

