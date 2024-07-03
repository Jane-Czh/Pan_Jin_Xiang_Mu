package com.heli.safety.service.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.heli.safety.domain.SafetyEpAssessEntering;
import com.heli.safety.utils.GenerateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.safety.mapper.SafetyEpAssessmentResultsPresentationMapper;
import com.heli.safety.domain.SafetyEpAssessmentResultsPresentation;
import com.heli.safety.service.ISafetyEpAssessmentResultsPresentationService;

/**
 * 成绩考核展示Service业务层处理
 * 
 * @author heli
 * @date 2024-04-21
 */
@Service
public class SafetyEpAssessmentResultsPresentationServiceImpl implements ISafetyEpAssessmentResultsPresentationService 
{
    @Autowired
    private SafetyEpAssessmentResultsPresentationMapper safetyEpAssessmentResultsPresentationMapper;

    @Override
    public void Synchronization(List<SafetyEpAssessEntering> list){

        //获取每月份的各班组得分
        Map<String, Double> averageAssessmentScoresByTeam1 = list.stream().filter(SafetyEpAssessEntering -> {
            // 从 SafetyEpAssessEntering 中获取 Date 类型的 CheckTime
            Date checkTime = SafetyEpAssessEntering.getCheckTime();
            // 使用 Calendar 获取月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkTime);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH 返回的是0-11，所以要加1
            // 选出时间为当年1月份的数据
            return month == 1;
        }).filter(entry -> entry.getAssessmentScore() != null).collect(Collectors.groupingBy(SafetyEpAssessEntering::getCheckTeam, Collectors.averagingDouble(SafetyEpAssessEntering::getAssessmentScore)));

        Map<String, Double> averageAssessmentScoresByTeam2 = list.stream().filter(SafetyEpAssessEntering -> {
            // 从 SafetyEpAssessEntering 中获取 Date 类型的 CheckTime
            Date checkTime = SafetyEpAssessEntering.getCheckTime();
            // 使用 Calendar 获取月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkTime);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH 返回的是0-11，所以要加1
            // 选出时间为当年2月份的数据
            return month == 2;
        }).filter(entry -> entry.getAssessmentScore() != null).collect(Collectors.groupingBy(SafetyEpAssessEntering::getCheckTeam, Collectors.averagingDouble(SafetyEpAssessEntering::getAssessmentScore)));

        Map<String, Double> averageAssessmentScoresByTeam3 = list.stream().filter(SafetyEpAssessEntering -> {
            // 从 SafetyEpAssessEntering 中获取 Date 类型的 CheckTime
            Date checkTime = SafetyEpAssessEntering.getCheckTime();
            // 使用 Calendar 获取月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkTime);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH 返回的是0-11，所以要加1
            // 选出时间为当年3月份的数据
            return month == 3;
        }).filter(entry -> entry.getAssessmentScore() != null).collect(Collectors.groupingBy(SafetyEpAssessEntering::getCheckTeam, Collectors.averagingDouble(SafetyEpAssessEntering::getAssessmentScore)));

        Map<String, Double> averageAssessmentScoresByTeam4 = list.stream().filter(SafetyEpAssessEntering -> {
            // 从 SafetyEpAssessEntering 中获取 Date 类型的 CheckTime
            Date checkTime = SafetyEpAssessEntering.getCheckTime();
            // 使用 Calendar 获取月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkTime);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH 返回的是0-11，所以要加1
            // 选出时间为当年4月份的数据
            return month == 4;
        }).filter(entry -> entry.getAssessmentScore() != null).collect(Collectors.groupingBy(SafetyEpAssessEntering::getCheckTeam, Collectors.averagingDouble(SafetyEpAssessEntering::getAssessmentScore)));

        Map<String, Double> averageAssessmentScoresByTeam5 = list.stream().filter(SafetyEpAssessEntering -> {
            // 从 SafetyEpAssessEntering 中获取 Date 类型的 CheckTime
            Date checkTime = SafetyEpAssessEntering.getCheckTime();
            // 使用 Calendar 获取月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkTime);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH 返回的是0-11，所以要加1
            // 选出时间为当年5月份的数据
            return month == 5;
        }).filter(entry -> entry.getAssessmentScore() != null).collect(Collectors.groupingBy(SafetyEpAssessEntering::getCheckTeam, Collectors.averagingDouble(SafetyEpAssessEntering::getAssessmentScore)));

        Map<String, Double> averageAssessmentScoresByTeam6 = list.stream().filter(SafetyEpAssessEntering -> {
            // 从 SafetyEpAssessEntering 中获取 Date 类型的 CheckTime
            Date checkTime = SafetyEpAssessEntering.getCheckTime();
            // 使用 Calendar 获取月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkTime);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH 返回的是0-11，所以要加1
            // 选出时间为当年6月份的数据
            return month == 6;
        }).filter(entry -> entry.getAssessmentScore() != null).collect(Collectors.groupingBy(SafetyEpAssessEntering::getCheckTeam, Collectors.averagingDouble(SafetyEpAssessEntering::getAssessmentScore)));

        Map<String, Double> averageAssessmentScoresByTeam7 = list.stream().filter(SafetyEpAssessEntering -> {
            // 从 SafetyEpAssessEntering 中获取 Date 类型的 CheckTime
            Date checkTime = SafetyEpAssessEntering.getCheckTime();
            // 使用 Calendar 获取月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkTime);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH 返回的是0-11，所以要加1
            // 选出时间为当年7月份的数据
            return month == 7;
        }).filter(entry -> entry.getAssessmentScore() != null).collect(Collectors.groupingBy(SafetyEpAssessEntering::getCheckTeam, Collectors.averagingDouble(SafetyEpAssessEntering::getAssessmentScore)));

        Map<String, Double> averageAssessmentScoresByTeam8 = list.stream().filter(SafetyEpAssessEntering -> {
            // 从 SafetyEpAssessEntering 中获取 Date 类型的 CheckTime
            Date checkTime = SafetyEpAssessEntering.getCheckTime();
            // 使用 Calendar 获取月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkTime);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH 返回的是0-11，所以要加1
            // 选出时间为当年8月份的数据
            return month == 8;
        }).filter(entry -> entry.getAssessmentScore() != null).collect(Collectors.groupingBy(SafetyEpAssessEntering::getCheckTeam, Collectors.averagingDouble(SafetyEpAssessEntering::getAssessmentScore)));

        Map<String, Double> averageAssessmentScoresByTeam9 = list.stream().filter(SafetyEpAssessEntering -> {
            // 从 SafetyEpAssessEntering 中获取 Date 类型的 CheckTime
            Date checkTime = SafetyEpAssessEntering.getCheckTime();
            // 使用 Calendar 获取月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkTime);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH 返回的是0-11，所以要加1
            // 选出时间为当年9月份的数据
            return month == 9;
        }).filter(entry -> entry.getAssessmentScore() != null).collect(Collectors.groupingBy(SafetyEpAssessEntering::getCheckTeam, Collectors.averagingDouble(SafetyEpAssessEntering::getAssessmentScore)));

        Map<String, Double> averageAssessmentScoresByTeam10 = list.stream().filter(SafetyEpAssessEntering -> {
            // 从 SafetyEpAssessEntering 中获取 Date 类型的 CheckTime
            Date checkTime = SafetyEpAssessEntering.getCheckTime();
            // 使用 Calendar 获取月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkTime);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH 返回的是0-11，所以要加1
            // 选出时间为当年10月份的数据
            return month == 10;
        }).filter(entry -> entry.getAssessmentScore() != null).collect(Collectors.groupingBy(SafetyEpAssessEntering::getCheckTeam, Collectors.averagingDouble(SafetyEpAssessEntering::getAssessmentScore)));

        Map<String, Double> averageAssessmentScoresByTeam11 = list.stream().filter(SafetyEpAssessEntering -> {
            // 从 SafetyEpAssessEntering 中获取 Date 类型的 CheckTime
            Date checkTime = SafetyEpAssessEntering.getCheckTime();
            // 使用 Calendar 获取月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkTime);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH 返回的是0-11，所以要加1
            // 选出时间为当年11月份的数据
            return month == 11;
        }).filter(entry -> entry.getAssessmentScore() != null).collect(Collectors.groupingBy(SafetyEpAssessEntering::getCheckTeam, Collectors.averagingDouble(SafetyEpAssessEntering::getAssessmentScore)));

        Map<String, Double> averageAssessmentScoresByTeam12 = list.stream().filter(SafetyEpAssessEntering -> {
            // 从 SafetyEpAssessEntering 中获取 Date 类型的 CheckTime
            Date checkTime = SafetyEpAssessEntering.getCheckTime();
            // 使用 Calendar 获取月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkTime);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH 返回的是0-11，所以要加1
            // 选出时间为当年12月份的数据
            return month == 12;
        }).filter(entry -> entry.getAssessmentScore() != null).collect(Collectors.groupingBy(SafetyEpAssessEntering::getCheckTeam, Collectors.averagingDouble(SafetyEpAssessEntering::getAssessmentScore)));


        safetyEpAssessmentResultsPresentationMapper.deleteAllData();

        HashMap<String, SafetyEpAssessmentResultsPresentation> SafetyEpAssessmentResultsPresentationHashMaps = new HashMap<>();

        //添加每个月份的得分数
        for (Map.Entry<String, Double> entry : averageAssessmentScoresByTeam1.entrySet()) {
            String group = entry.getKey();
            if (!SafetyEpAssessmentResultsPresentationHashMaps.containsKey(group)){
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = new SafetyEpAssessmentResultsPresentation();
                safetyEpAssessmentResultsPresentation.setCheckTeam(group);
                safetyEpAssessmentResultsPresentation.setJanuaryResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.put(group, safetyEpAssessmentResultsPresentation);
            }else {
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = SafetyEpAssessmentResultsPresentationHashMaps.get(group);
                safetyEpAssessmentResultsPresentation.setJanuaryResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.replace(group, safetyEpAssessmentResultsPresentation);
            }
        }
        for (Map.Entry<String, Double> entry : averageAssessmentScoresByTeam2.entrySet()) {
            String group = entry.getKey();
            if (!SafetyEpAssessmentResultsPresentationHashMaps.containsKey(group)){
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = new SafetyEpAssessmentResultsPresentation();
                safetyEpAssessmentResultsPresentation.setCheckTeam(group);
                safetyEpAssessmentResultsPresentation.setFebruaryResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.put(group, safetyEpAssessmentResultsPresentation);
            }else {
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = SafetyEpAssessmentResultsPresentationHashMaps.get(group);
                safetyEpAssessmentResultsPresentation.setFebruaryResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.replace(group, safetyEpAssessmentResultsPresentation);
            }
        }
        for (Map.Entry<String, Double> entry : averageAssessmentScoresByTeam3.entrySet()) {
            String group = entry.getKey();
            if (!SafetyEpAssessmentResultsPresentationHashMaps.containsKey(group)){
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = new SafetyEpAssessmentResultsPresentation();
                safetyEpAssessmentResultsPresentation.setCheckTeam(group);
                safetyEpAssessmentResultsPresentation.setMarchResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.put(group, safetyEpAssessmentResultsPresentation);
            }else {
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = SafetyEpAssessmentResultsPresentationHashMaps.get(group);
                safetyEpAssessmentResultsPresentation.setMarchResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.replace(group, safetyEpAssessmentResultsPresentation);
            }
        }
        for (Map.Entry<String, Double> entry : averageAssessmentScoresByTeam4.entrySet()) {
            String group = entry.getKey();
            if (!SafetyEpAssessmentResultsPresentationHashMaps.containsKey(group)){
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = new SafetyEpAssessmentResultsPresentation();
                safetyEpAssessmentResultsPresentation.setCheckTeam(group);
                safetyEpAssessmentResultsPresentation.setAprilResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.put(group, safetyEpAssessmentResultsPresentation);
            }else {
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = SafetyEpAssessmentResultsPresentationHashMaps.get(group);
                safetyEpAssessmentResultsPresentation.setAprilResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.replace(group, safetyEpAssessmentResultsPresentation);
            }
        }
        for (Map.Entry<String, Double> entry : averageAssessmentScoresByTeam5.entrySet()) {
            String group = entry.getKey();
            if (!SafetyEpAssessmentResultsPresentationHashMaps.containsKey(group)){
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = new SafetyEpAssessmentResultsPresentation();
                safetyEpAssessmentResultsPresentation.setCheckTeam(group);
                safetyEpAssessmentResultsPresentation.setMayResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.put(group, safetyEpAssessmentResultsPresentation);
            }else {
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = SafetyEpAssessmentResultsPresentationHashMaps.get(group);
                safetyEpAssessmentResultsPresentation.setMayResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.replace(group, safetyEpAssessmentResultsPresentation);
            }
        }
        for (Map.Entry<String, Double> entry : averageAssessmentScoresByTeam6.entrySet()) {
            String group = entry.getKey();
            if (!SafetyEpAssessmentResultsPresentationHashMaps.containsKey(group)){
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = new SafetyEpAssessmentResultsPresentation();
                safetyEpAssessmentResultsPresentation.setCheckTeam(group);
                safetyEpAssessmentResultsPresentation.setJuneResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.put(group, safetyEpAssessmentResultsPresentation);
            }else {
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = SafetyEpAssessmentResultsPresentationHashMaps.get(group);
                safetyEpAssessmentResultsPresentation.setJuneResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.replace(group, safetyEpAssessmentResultsPresentation);
            }
        }
        for (Map.Entry<String, Double> entry : averageAssessmentScoresByTeam7.entrySet()) {
            String group = entry.getKey();
            if (!SafetyEpAssessmentResultsPresentationHashMaps.containsKey(group)){
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = new SafetyEpAssessmentResultsPresentation();
                safetyEpAssessmentResultsPresentation.setCheckTeam(group);
                safetyEpAssessmentResultsPresentation.setJulyResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.put(group, safetyEpAssessmentResultsPresentation);
            }else {
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = SafetyEpAssessmentResultsPresentationHashMaps.get(group);
                safetyEpAssessmentResultsPresentation.setJulyResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.replace(group, safetyEpAssessmentResultsPresentation);
            }
        }
        for (Map.Entry<String, Double> entry : averageAssessmentScoresByTeam8.entrySet()) {
            String group = entry.getKey();
            if (!SafetyEpAssessmentResultsPresentationHashMaps.containsKey(group)){
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = new SafetyEpAssessmentResultsPresentation();
                safetyEpAssessmentResultsPresentation.setCheckTeam(group);
                safetyEpAssessmentResultsPresentation.setAugustResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.put(group, safetyEpAssessmentResultsPresentation);
            }else {
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = SafetyEpAssessmentResultsPresentationHashMaps.get(group);
                safetyEpAssessmentResultsPresentation.setAugustResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.replace(group, safetyEpAssessmentResultsPresentation);
            }
        }
        for (Map.Entry<String, Double> entry : averageAssessmentScoresByTeam9.entrySet()) {
            String group = entry.getKey();
            if (!SafetyEpAssessmentResultsPresentationHashMaps.containsKey(group)){
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = new SafetyEpAssessmentResultsPresentation();
                safetyEpAssessmentResultsPresentation.setCheckTeam(group);
                safetyEpAssessmentResultsPresentation.setSeptemberResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.put(group, safetyEpAssessmentResultsPresentation);
            }else {
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = SafetyEpAssessmentResultsPresentationHashMaps.get(group);
                safetyEpAssessmentResultsPresentation.setSeptemberResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.replace(group, safetyEpAssessmentResultsPresentation);
            }
        }
        for (Map.Entry<String, Double> entry : averageAssessmentScoresByTeam10.entrySet()) {
            String group = entry.getKey();
            if (!SafetyEpAssessmentResultsPresentationHashMaps.containsKey(group)){
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = new SafetyEpAssessmentResultsPresentation();
                safetyEpAssessmentResultsPresentation.setCheckTeam(group);
                safetyEpAssessmentResultsPresentation.setOctoberResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.put(group, safetyEpAssessmentResultsPresentation);
            }else {
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = SafetyEpAssessmentResultsPresentationHashMaps.get(group);
                safetyEpAssessmentResultsPresentation.setOctoberResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.replace(group, safetyEpAssessmentResultsPresentation);
            }
        }
        for (Map.Entry<String, Double> entry : averageAssessmentScoresByTeam11.entrySet()) {
            String group = entry.getKey();
            if (!SafetyEpAssessmentResultsPresentationHashMaps.containsKey(group)){
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = new SafetyEpAssessmentResultsPresentation();
                safetyEpAssessmentResultsPresentation.setCheckTeam(group);
                safetyEpAssessmentResultsPresentation.setNovemberResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.put(group, safetyEpAssessmentResultsPresentation);
            }else {
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = SafetyEpAssessmentResultsPresentationHashMaps.get(group);
                safetyEpAssessmentResultsPresentation.setNovemberResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.replace(group, safetyEpAssessmentResultsPresentation);
            }
        }
        for (Map.Entry<String, Double> entry : averageAssessmentScoresByTeam12.entrySet()) {
            String group = entry.getKey();
            if (!SafetyEpAssessmentResultsPresentationHashMaps.containsKey(group)){
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = new SafetyEpAssessmentResultsPresentation();
                safetyEpAssessmentResultsPresentation.setCheckTeam(group);
                safetyEpAssessmentResultsPresentation.setDecemberResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.put(group, safetyEpAssessmentResultsPresentation);
            }else {
                SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = SafetyEpAssessmentResultsPresentationHashMaps.get(group);
                safetyEpAssessmentResultsPresentation.setDecemberResults(BigDecimal.valueOf(entry.getValue()));
                SafetyEpAssessmentResultsPresentationHashMaps.replace(group, safetyEpAssessmentResultsPresentation);
            }
        }

        //添加每个季度和全年得分数
        for (Map.Entry<String, SafetyEpAssessmentResultsPresentation> entry : SafetyEpAssessmentResultsPresentationHashMaps.entrySet()){
            SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = entry.getValue();
            BigDecimal First_Quarter_Results = null;
            BigDecimal Second_Quarter_Results = null;
            BigDecimal Third_Quarter_Results = null;
            BigDecimal Fourth_Quarter_Results = null;
            BigDecimal Annual_Results = null;

            //计算第一季度分数
            BigDecimal JanuaryResults = safetyEpAssessmentResultsPresentation.getJanuaryResults();
            BigDecimal FebruaryResults = safetyEpAssessmentResultsPresentation.getFebruaryResults();
            BigDecimal MarchResults = safetyEpAssessmentResultsPresentation.getMarchResults();
            if (JanuaryResults != null && FebruaryResults != null && MarchResults != null ){
                First_Quarter_Results = JanuaryResults.add(FebruaryResults).add(MarchResults).divide(BigDecimal.valueOf(3), BigDecimal.ROUND_CEILING);
            }
            //计算第二季度分数
            BigDecimal AprilResults = safetyEpAssessmentResultsPresentation.getAprilResults();
            BigDecimal MayResults = safetyEpAssessmentResultsPresentation.getMayResults();
            BigDecimal JuneResults = safetyEpAssessmentResultsPresentation.getJuneResults();
            if (AprilResults != null && MayResults != null && JuneResults != null ){
                Second_Quarter_Results = AprilResults.add(MayResults).add(JuneResults).divide(BigDecimal.valueOf(3), BigDecimal.ROUND_CEILING);
            }
            //计算第三季度分数
            BigDecimal JulyResults = safetyEpAssessmentResultsPresentation.getJulyResults();
            BigDecimal AugustResults = safetyEpAssessmentResultsPresentation.getAugustResults();
            BigDecimal SeptemberResults = safetyEpAssessmentResultsPresentation.getSeptemberResults();
            if (JulyResults != null && AugustResults != null && SeptemberResults != null ){
                Third_Quarter_Results = JulyResults.add(AugustResults).add(SeptemberResults).divide(BigDecimal.valueOf(3), BigDecimal.ROUND_CEILING);
            }
            //计算第四季度分数
            BigDecimal OctoberResults = safetyEpAssessmentResultsPresentation.getOctoberResults();
            BigDecimal NovemberResults = safetyEpAssessmentResultsPresentation.getNovemberResults();
            BigDecimal DecemberResults = safetyEpAssessmentResultsPresentation.getDecemberResults();
            if (OctoberResults != null && NovemberResults != null && DecemberResults != null ){
                Fourth_Quarter_Results = OctoberResults.add(NovemberResults).add(DecemberResults).divide(BigDecimal.valueOf(3), BigDecimal.ROUND_CEILING);
            }
            //全年得分
            if (First_Quarter_Results != null && Second_Quarter_Results != null && Third_Quarter_Results != null && Fourth_Quarter_Results != null ){
                Annual_Results = First_Quarter_Results.add(Second_Quarter_Results).add(Third_Quarter_Results).add(Fourth_Quarter_Results).divide(BigDecimal.valueOf(4), BigDecimal.ROUND_CEILING);
            }

            if (First_Quarter_Results != null){
                safetyEpAssessmentResultsPresentation.setFirstQuarterResults(First_Quarter_Results);
            }
            if (Second_Quarter_Results != null){
                safetyEpAssessmentResultsPresentation.setSecondQuarterResults(Second_Quarter_Results);
            }
            if (Third_Quarter_Results != null){
                safetyEpAssessmentResultsPresentation.setThirdQuarterResults(Third_Quarter_Results);
            }
            if (Fourth_Quarter_Results != null){
                safetyEpAssessmentResultsPresentation.setFourthQuarterResults(Fourth_Quarter_Results);
            }
            if (Annual_Results != null){
                safetyEpAssessmentResultsPresentation.setAnnualResults(Annual_Results);
            }

        }
        Long id = 0L;
        for (Map.Entry<String, SafetyEpAssessmentResultsPresentation> entry : SafetyEpAssessmentResultsPresentationHashMaps.entrySet()){
            SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation = entry.getValue();
            safetyEpAssessmentResultsPresentation.setSearpId(GenerateId.getNextId(id++));
            System.out.println(safetyEpAssessmentResultsPresentation);
            safetyEpAssessmentResultsPresentationMapper.insertSafetyEpAssessmentResultsPresentation(safetyEpAssessmentResultsPresentation);
        }
    }

    /**
     * 查询成绩考核展示
     * 
     * @param searpId 成绩考核展示主键
     * @return 成绩考核展示
     */
    @Override
    public SafetyEpAssessmentResultsPresentation selectSafetyEpAssessmentResultsPresentationBySearpId(Long searpId)
    {
        return safetyEpAssessmentResultsPresentationMapper.selectSafetyEpAssessmentResultsPresentationBySearpId(searpId);
    }

    /**
     * 查询成绩考核展示列表
     * 
     * @param safetyEpAssessmentResultsPresentation 成绩考核展示
     * @return 成绩考核展示
     */
    @Override
    public List<SafetyEpAssessmentResultsPresentation> selectSafetyEpAssessmentResultsPresentationList(SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation)
    {
        return safetyEpAssessmentResultsPresentationMapper.selectSafetyEpAssessmentResultsPresentationList(safetyEpAssessmentResultsPresentation);
    }

    /**
     * 新增成绩考核展示
     * 
     * @param safetyEpAssessmentResultsPresentation 成绩考核展示
     * @return 结果
     */
    @Override
    public int insertSafetyEpAssessmentResultsPresentation(SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation)
    {
        return safetyEpAssessmentResultsPresentationMapper.insertSafetyEpAssessmentResultsPresentation(safetyEpAssessmentResultsPresentation);
    }

    /**
     * 修改成绩考核展示
     * 
     * @param safetyEpAssessmentResultsPresentation 成绩考核展示
     * @return 结果
     */
    @Override
    public int updateSafetyEpAssessmentResultsPresentation(SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation)
    {
        return safetyEpAssessmentResultsPresentationMapper.updateSafetyEpAssessmentResultsPresentation(safetyEpAssessmentResultsPresentation);
    }

    /**
     * 批量删除成绩考核展示
     * 
     * @param searpIds 需要删除的成绩考核展示主键
     * @return 结果
     */
    @Override
    public int deleteSafetyEpAssessmentResultsPresentationBySearpIds(Long[] searpIds)
    {
        return safetyEpAssessmentResultsPresentationMapper.deleteSafetyEpAssessmentResultsPresentationBySearpIds(searpIds);
    }

    /**
     * 删除成绩考核展示信息
     * 
     * @param searpId 成绩考核展示主键
     * @return 结果
     */
    @Override
    public int deleteSafetyEpAssessmentResultsPresentationBySearpId(Long searpId)
    {
        return safetyEpAssessmentResultsPresentationMapper.deleteSafetyEpAssessmentResultsPresentationBySearpId(searpId);
    }
}
