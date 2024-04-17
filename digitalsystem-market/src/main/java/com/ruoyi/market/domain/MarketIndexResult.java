package com.ruoyi.market.domain;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.util.Map;

@Data
public class MarketIndexResult {
//        private  String Branch;
//        private  Long Number;
        private Map<String,Long> map;

        private Map<String, Map<String, Long>> mapMap;
//        private JSON json;
}
