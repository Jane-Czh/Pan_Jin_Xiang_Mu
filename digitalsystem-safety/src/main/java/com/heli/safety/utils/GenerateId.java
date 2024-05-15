package com.heli.safety.utils;

import java.util.UUID;

public class GenerateId {
    public static Long getUUid(){
        // 生成一个随机的 UUID
        UUID uuid = UUID.randomUUID();

        // 获取 UUID 的 mostSignificantBits 和 leastSignificantBits
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getLeastSignificantBits();

        // 将两部分 bits 合并成一个 long 值
        long combined = mostSigBits ^ leastSigBits;

        Long positiveValue = Math.abs(combined);
        return positiveValue;
    }

    public static Long getNextId(Long currentId) {
        if (currentId == null) {
            // 如果当前 ID 为空，返回 null 或者抛出异常，具体根据需求而定
            return null;
        }

        // 将给定的 ID 加 1
        return currentId + 1;
    }

}
