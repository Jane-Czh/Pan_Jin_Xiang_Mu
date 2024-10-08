package com.heli.safety.mapper;

import java.util.List;
import com.heli.safety.domain.SafetyEpAssessmentResultsPresentation;

/**
 * 成绩考核展示Mapper接口
 * 
 * @author heli
 * @date 2024-04-21
 */
public interface SafetyEpAssessmentResultsPresentationMapper 
{
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
     * 删除成绩考核展示
     * 
     * @param searpId 成绩考核展示主键
     * @return 结果
     */
    public int deleteSafetyEpAssessmentResultsPresentationBySearpId(Long searpId);

    /**
     * 批量删除成绩考核展示
     * 
     * @param searpIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSafetyEpAssessmentResultsPresentationBySearpIds(Long[] searpIds);

    public void deleteAllData();

    //查询数据库最后一条数据id
    public Long selectLastId();

}
