package com.ruoyi.market.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Data
public class VoEntity {

    private String yearMonth;
    private List<MinEntity>  minEntity;
    private Long number;
    private String branch;


    public static List<VoEntity> convertCpdToVoEntities(Map<String, Map<String, Long>> cpd) {
            List<VoEntity> voEntities = new ArrayList<>();

            for (Map.Entry<String, Map<String, Long>> entry : cpd.entrySet()) {
                String yearMonth = entry.getKey();
                Map<String, Long> branchMap = entry.getValue();

                VoEntity voEntity = new VoEntity();
                voEntity.setYearMonth(yearMonth);

                List<MinEntity> minEntities = new ArrayList<>();
                for (Map.Entry<String, Long> branchEntry : branchMap.entrySet()) {
                    String branch = branchEntry.getKey();
                    Long number = branchEntry.getValue();

                    MinEntity minEntity = new MinEntity();
                    minEntity.setBranch(branch);
                    minEntity.setNumber(number);

                    minEntities.add(minEntity);
                }

                voEntity.setMinEntity(minEntities);
                voEntities.add(voEntity);
            }
        if (voEntities.isEmpty()) {
            // 创建一个默认的 VoEntity 对象，所有属性设置为0
            System.out.println("voEntities为空"+voEntities);
            VoEntity defaultVoEntity = new VoEntity();
            defaultVoEntity.setYearMonth("0"); // 假设年月可以设置为"0"

            List<MinEntity> defaultMinEntities = new ArrayList<>();
            MinEntity defaultMinEntity = new MinEntity();
            defaultMinEntity.setBranch("0"); // 假设分支可以设置为"0"
            defaultMinEntity.setNumber(0L); // 设置数量为0
            defaultMinEntities.add(defaultMinEntity);

            defaultVoEntity.setMinEntity(defaultMinEntities);
            voEntities.add(defaultVoEntity);
        }
        System.out.println("赋值后的voEntities"+voEntities);
        return voEntities;
        }

    public static List<VoEntity> convertCpdToVoEntitiesDouble(Map<String, Map<String, Double>> cpd) {
        List<VoEntity> voEntities = new ArrayList<>();

        for (Map.Entry<String, Map<String, Double>> entry : cpd.entrySet()) {
            String yearMonth = entry.getKey();
            Map<String, Double> branchMap = entry.getValue();

            VoEntity voEntity = new VoEntity();
            voEntity.setYearMonth(yearMonth);

            List<MinEntity> minEntities = new ArrayList<>();
            for (Map.Entry<String, Double> branchEntry : branchMap.entrySet()) {
                String branch = branchEntry.getKey();
                Double proportion = branchEntry.getValue();

                MinEntity minEntity = new MinEntity();
                minEntity.setBranch(branch);
                minEntity.setProportion(proportion);

                minEntities.add(minEntity);
            }

            voEntity.setMinEntity(minEntities);
            voEntities.add(voEntity);
        }

        if (voEntities.isEmpty()) {
            // 创建一个默认的 VoEntity 对象，所有属性设置为0
            System.out.println("voEntities为空"+voEntities);
            VoEntity defaultVoEntity = new VoEntity();
            defaultVoEntity.setYearMonth("0"); // 假设年月可以设置为"0"

            List<MinEntity> defaultMinEntities = new ArrayList<>();
            MinEntity defaultMinEntity = new MinEntity();
            defaultMinEntity.setBranch("0"); // 假设分支可以设置为"0"
            defaultMinEntity.setNumber(0L); // 设置数量为0
            defaultMinEntities.add(defaultMinEntity);

            defaultVoEntity.setMinEntity(defaultMinEntities);
            voEntities.add(defaultVoEntity);
        }
        System.out.println("赋值后的voEntities"+voEntities);
        return voEntities;
    }



    public static List<VoEntity> convert(Map<String, Map<String, Long>> mapMap) {
        List<VoEntity> entityList = new ArrayList<>();

        for (Map.Entry<String, Map<String, Long>> entry : mapMap.entrySet()) {
            String branch = entry.getKey();
            Map<String, Long> dateMap = entry.getValue();

            for (Map.Entry<String, Long> dateEntry : dateMap.entrySet()) {
                String yearMonth = dateEntry.getKey();
                Long number = dateEntry.getValue();

                VoEntity entity = new VoEntity();
                entity.setBranch(branch);
                entity.setYearMonth(yearMonth);
                entity.setNumber(number);

                entityList.add(entity);
            }
        }

        return entityList;
    }
}

