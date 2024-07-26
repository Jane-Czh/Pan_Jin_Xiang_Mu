package com.heli.financial.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.heli.financial.domain.FinancialInterestsTable;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

/**
 * 财务-利润Service接口
 *
 * @author hong
 * @date 2024-03-31
 */
public interface IFinancialInterestsTableService {

    boolean checkDataExists();

    Date selectMaxYearAndMonth();

    FinancialInterestsTable selectInterestsSumInfoByYear(Date yearAndMonth);


    /**
     * @description: 利润表导入
     * @author: hong
     * @date: 2024/3/31 13:48
     */
    int importInterestsTable(String createdBy, Date createdTime, Date yearAndMonth, MultipartFile excelFile) throws IOException;

    int importInterests(MultipartFile excelFile) throws IOException;

    FinancialInterestsTable selectFinancialInterestsTableByYearAndMonth(Date yearAndMonth);


    List<FinancialInterestsTable> selectFinancialInterestsTableByYear(String year);

    List<FinancialInterestsTable> selectFinancialInterestsTableByTime(Date beginTime, Date endTime);

    boolean checkInterestsDataIsExisted(Date date);

    List<Date> selectAllInterestsYearAndMonth();

    /**
     * @description: 查询营业收入
     * @author: hong
     * @date: 2024/4/9 10:14
     * @param: [date]
     **/
    public BigDecimal selectOperatingRevenueByMonth(Date date);

    void deleteFinancialInterestsTableByYearAndMonth(Date yearAndMonth);


    /**
     * 查询财务-利润
     *
     * @param fiId 财务-利润主键
     * @return 财务-利润
     */
    public FinancialInterestsTable selectFinancialInterestsTableByFiId(Long fiId);

    /**
     * 查询财务-利润列表
     *
     * @param financialInterestsTable 财务-利润
     * @return 财务-利润集合
     */
    public List<FinancialInterestsTable> selectFinancialInterestsTableList(FinancialInterestsTable financialInterestsTable);

    /**
     * 新增财务-利润
     *
     * @param financialInterestsTable 财务-利润
     * @return 结果
     */
    public int insertFinancialInterestsTable(FinancialInterestsTable financialInterestsTable);

    /**
     * 修改财务-利润
     *
     * @param financialInterestsTable 财务-利润
     * @return 结果
     */
    public int updateFinancialInterestsTable(FinancialInterestsTable financialInterestsTable);

    /**
     * 批量删除财务-利润
     *
     * @param fiIds 需要删除的财务-利润主键集合
     * @return 结果
     */
    public int deleteFinancialInterestsTableByFiIds(Long[] fiIds);

    /**
     * 删除财务-利润信息
     *
     * @param fiId 财务-利润主键
     * @return 结果
     */
    public int deleteFinancialInterestsTableByFiId(Long fiId);
}

