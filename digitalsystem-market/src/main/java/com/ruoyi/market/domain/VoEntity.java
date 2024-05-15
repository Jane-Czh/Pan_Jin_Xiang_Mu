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

