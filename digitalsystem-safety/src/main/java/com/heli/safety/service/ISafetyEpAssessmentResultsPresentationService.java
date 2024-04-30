package com.heli.safety.service;

import java.util.List;

import com.heli.safety.domain.SafetyEpAssessEntering;
import com.heli.safety.domain.SafetyEpAssessmentResultsPresentation;

/**
 * 成绩考核展示Service接口
 * 
 * @author heli
 * @date 2024-04-21
 */
public interface ISafetyEpAssessmentResultsPresentationService 
{
    public void Synchronization(List<SafetyEpAssessEntering> list);

    /**
     * 查询成绩考核展示
     * 
     * @param searpId 成绩考核展示主键
     * @return 成绩考核展示
     */
    public SafetyEpAssessmentResultsPresentation selectSafetyEpAssessmentResultsPresentationBySearpId(Long searpId);

    /**
     * 查询成绩考核展示列表
     * 
     * @param safetyEpAssessmentResultsPresentation 成绩考核展示
     * @return 成绩考核展示集合
     */
    public List<SafetyEpAssessmentResultsPresentation> selectSafetyEpAssessmentResultsPresentationList(SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation);

    /**
     * 新增成绩考核展示
     * 
     * @param safetyEpAssessmentResultsPresentation 成绩考核展示
     * @return 结果
     */
    public int insertSafetyEpAssessmentResultsPresentation(SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation);

    /**
     * 修改成绩考核展示
     * 
     * @param safetyEpAssessmentResultsPresentation 成绩考核展示
     * @return 结果
     */
    public int updateSafetyEpAssessmentResultsPresentation(SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation);

    /**
     * 批量删除成绩考核展示
     * 
     * @param searpIds 需要删除的成绩考核展示主键集合
     * @return 结果
     */
    public int deleteSafetyEpAssessmentResultsPresentationBySearpIds(Long[] searpIds);

    /**
     * 删除成绩考核展示信息
     * 
     * @param searpId 成绩考核展示主键
     * @return 结果
     */
    public int deleteSafetyEpAssessmentResultsPresentationBySearpId(Long searpId);
}
