package test.work1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.util.Signal;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.common.base.Preconditions;
import test.work1.entity.WarnDTO;
import test.work1.entity.WarnSignal;
import test.work1.service.Warningservice;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class Warncontroller {
    @Autowired
    Warningservice warningservice;
    @PostMapping("/warn")
    public Map<String, Object> receiveWarnData(@RequestBody List<Map<String, Object>> inputList) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> listone = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            for (Map<String, Object> item : inputList) {
                Long carId = ((Number) item.get("carId")).longValue();
                Integer warnId = (Integer) item.get("warnId");

                // 将 signal 字段解析为 WarnSignal 对象
                Map<String, Object> signalMap = (Map<String, Object>) item.get("signal");
                WarnSignal signal = objectMapper.convertValue(signalMap, WarnSignal.class);

                Long batteryValue = warningservice.getBatteryValueByCarId(carId);
                List<Map<String, Object>> result = warningservice.result(carId, batteryValue, signal);
                listone.addAll(result);
            }
            map.put("data", listone);
            map.put("status", 200);
            map.put("msg", "ok");
        } catch (Exception e) {
            // 处理异常
            map.put("data", Collections.emptyList()); // 返回空列表
            map.put("status", 500);
            map.put("msg", "error: " + e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

}
