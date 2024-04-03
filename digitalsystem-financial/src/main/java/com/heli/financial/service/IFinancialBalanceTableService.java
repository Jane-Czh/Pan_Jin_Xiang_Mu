package com.heli.financial.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.heli.financial.domain.FinancialBalanceTable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 财务-资产负债Service接口
 *
 * @author ruoyi
 * @date 2024-04-03
 */
public interface IFinancialBalanceTableService {

    /**
     * @description: 资产负债表导入
     * @author: hong
     * @date: 2024/4/3 10:59
     * @param: [excelFile]
     * @return: int
     **/
    public int importBalanceTable(String createdBy, Date createdTime, Date yearAndMonth, BigDecimal reserveCarAmount, MultipartFile excelFile) throws IOException;

    public int importBalance(MultipartFile excelFile) throws IOException;

    /**
     * 查询财务-资产负债
     *
     * @param fbId 财务-资产负债主键
     * @return 财务-资产负债
     */
    public FinancialBalanceTable selectFinancialBalanceTableByFbId(Long fbId);

    /**
     * 查询财务-资产负债列表
     *
     * @param financialBalanceTable 财务-资产负债
     * @return 财务-资产负债集合
     */
    public List<FinancialBalanceTable> selectFinancialBalanceTableList(FinancialBalanceTable financialBalanceTable);

    /**
     * 新增财务-资产负债
     *
     * @param financialBalanceTable 财务-资产负债
     * @return 结果
     */
    public int insertFinancialBalanceTable(FinancialBalanceTable financialBalanceTable);

    /**
     * 修改财务-资产负债
     *
     * @param financialBalanceTable 财务-资产负债
     * @return 结果
     */
    public int updateFinancialBalanceTable(FinancialBalanceTable financialBalanceTable);

    /**
     * 批量删除财务-资产负债
     *
     * @param fbIds 需要删除的财务-资产负债主键集合
     * @return 结果
     */
    public int deleteFinancialBalanceTableByFbIds(Long[] fbIds);

    /**
     * 删除财务-资产负债信息
     *
     * @param fbId 财务-资产负债主键
     * @return 结果
     */
    public int deleteFinancialBalanceTableByFbId(Long fbId);
}
