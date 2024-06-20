package test.work1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import test.work1.entity.Car;
import test.work1.entity.WarnDTO;
import test.work1.entity.WarnSignal;
import test.work1.entity.Warningrule;
import test.work1.repository.CarRepository;
import test.work1.repository.WarningruleRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

@Service
public class Warningservice {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private WarningruleRepository ruleRepository;

    public Double getInfo(WarnDTO warnDTO){
        Long carId = warnDTO.getCarId();
        Long warnId = warnDTO.getWarnId();
        // 计算Mx和Mi的差值
        Double Mx = warnDTO.getSignal().getMx();
        Double Mi = warnDTO.getSignal().getMi();
        Double Ix = warnDTO.getSignal().getIx();
        Double Ii = warnDTO.getSignal().getMi();
        Double difference = abs(Mx - Mi);
        Double difference2 = abs(Ix - Ii);
        Map<String, Object> response = new HashMap<>();
//      response.put("ruleId", ruleId);
        response.put("carId", carId);
        response.put("warnId", warnId);
        response.put("Mx", Mx);
        response.put("Mi", Mi);
        response.put("signalDifference", difference);
        return difference;
    }

    public Long getBatteryValueByCarId(Long carId){
        Car car = carRepository.findById(carId).orElse(null);
        if (car != null) {
            // 返回查询到的battery值
            return car.getBattery_id();
        } else {
            // 如果找不到对应的carId，可以抛出异常或返回null/默认值等
            // 这里简单返回null作为示例
            return null;
        }
    }

    public String resolveBatteryType(Long batteryValue) {
        if (batteryValue == 0) {
            return "三元电池";
        } else {
            return "铁锂电池";
        }
    }

//    public Map<String, Object> result(Long car, Long batteryId, WarnSignal signal) {
//        Map<String, Object> resultobj = new HashMap<>();
//        boolean hasMxMi=signal.getMx() != null && signal.getMi() != null;
//        boolean hasIxIi=signal.getIx() != null && signal.getIi() != null;
//
//        resultobj.put("车架编号", car);
//        resultobj.put("电池类型", resolveBatteryType(batteryId));
//
//        if(hasMxMi){
//            int alarmname=0;
//            Double difference = abs(signal.getMx() - signal.getMi());
//            resultobj.put("warnName","电压差报警");
//            Integer rulevalue = getRules(alarmname, batteryId, difference);
//            resultobj.put("warnLevel", rulevalue);
//        }
//        if(hasIxIi){
//            int alarmname=1;
//            Double difference = abs(signal.getIx() - signal.getMi());
//            resultobj.put("warnName","电流差报警");
//            Integer rulevalue = getRules(alarmname, batteryId, difference);
//            resultobj.put("warnLevel", rulevalue);
//        }
//
//        return resultobj;
//
//    }
    public List<Map<String, Object>> result(Long car, Long batteryId, WarnSignal signal) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        boolean hasMxMi = signal.getMx() != null && signal.getMi() != null;
        boolean hasIxIi = signal.getIx() != null && signal.getIi() != null;

        Map<String, Object> commonData = new HashMap<>();
        commonData.put("车架编号", car);
        commonData.put("电池类型", resolveBatteryType(batteryId));//"三元"or“铁锂”

        if (hasMxMi) {
            int alarmname = 0;
            Double difference = abs(signal.getMx() - signal.getMi());
            Map<String, Object> voltageWarning = new HashMap<>(commonData);
            voltageWarning.put("warnName", "电压差报警");
            Integer rulevalue = getRules(alarmname, batteryId, difference);//0-5
            if (rulevalue ==  5) {
                voltageWarning.put("warnLevel", "不报警");
            }
            voltageWarning.put("warnLevel", rulevalue);
            resultList.add(voltageWarning);
        }

        if (hasIxIi) {
            int alarmname = 1;
            Double difference = abs(signal.getIx() - signal.getIi());
            Map<String, Object> currentWarning = new HashMap<>(commonData);
            currentWarning.put("warnName", "电流差报警");
            Integer rulevalue = getRules(alarmname, batteryId, difference);
            if (rulevalue ==  3) {
                currentWarning.put("warnLevel", "不报警");
            }
            currentWarning.put("warnLevel", rulevalue);
            resultList.add(currentWarning);
        }

        return resultList;
    }

    public Integer getRules(Integer alarmName, Long battery, Double difference) {
        List<Warningrule> rules = ruleRepository.findByAlarmnameAndBattery(alarmName, battery);

        Optional<Integer> lastRule =rules.stream()
                .filter(rule -> rule.getMin() < difference && (rule.getMax() == null || difference < rule.getMax()))
                .map(Warningrule::getRule)
                .reduce((first, second) -> second);
        return lastRule.orElse(null);
    }
}
