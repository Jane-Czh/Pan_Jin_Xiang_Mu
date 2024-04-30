package com.ruoyi.market.domain;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class MarketIndexResult {


        private List<VoEntity> VoEntity;
        private Map<String,Long> map;
        private  Map<Date, Long> Datemap;
        private Map<String, Map<String, Long>> mapMap;
        private Map<String, Map<Integer, Long>> IntegerMap;
        private List<Map<String, Map<Integer, Long>>> ListMap;
        private Map<String, Map<Date, Long>> DatemapMap;
//        private JSON json;
}
