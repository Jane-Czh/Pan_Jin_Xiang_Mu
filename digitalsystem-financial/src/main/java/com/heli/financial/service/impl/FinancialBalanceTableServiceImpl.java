package com.heli.financial.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.heli.financial.domain.FinancialInterestsTable;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.excel.ReadExcelCellUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.financial.mapper.FinancialBalanceTableMapper;
import com.heli.financial.domain.FinancialBalanceTable;
import com.heli.financial.service.IFinancialBalanceTableService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 财务-资产负债Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-31
 */
@Service
public class FinancialBalanceTableServiceImpl implements IFinancialBalanceTableService 
{
    @Autowired
    private FinancialBalanceTableMapper financialBalanceTableMapper;

    /**
     * @description: 资产负债表导入
     * @author: hong
     * @date: 2024/4/1
     * @param: excel文件
     * @return: 影响的行数
     **/
    @Override
    public int importInterestsTable(MultipartFile excelFile) throws IOException {
        FinancialBalanceTable financialBalanceTable;
        InputStream is = null;
        try {
            System.out.println(excelFile);
            is = excelFile.getInputStream();
            financialBalanceTable = (FinancialBalanceTable) ReadExcelCellUtils.parseExcelToModel("com.heli.financial.domain.FinancialBalanceTable", is, null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return financialBalanceTableMapper.insertFinancialBalanceTable(financialBalanceTable);
    }


    /**
     * 查询财务-资产负债
     * 
     * @param fbId 财务-资产负债主键
     * @return 财务-资产负债
     */
    @Override
    public FinancialBalanceTable selectFinancialBalanceTableByFbId(Long fbId)
    {
        return financialBalanceTableMapper.selectFinancialBalanceTableByFbId(fbId);
    }

    /**
     * 查询财务-资产负债列表
     * 
     * @param financialBalanceTable 财务-资产负债
     * @return 财务-资产负债
     */
    @Override
    public List<FinancialBalanceTable> selectFinancialBalanceTableList(FinancialBalanceTable financialBalanceTable)
    {
        return financialBalanceTableMapper.selectFinancialBalanceTableList(financialBalanceTable);
    }

    /**
     * 新增财务-资产负债
     * 
     * @param financialBalanceTable 财务-资产负债
     * @return 结果
     */
    @Override
    public int insertFinancialBalanceTable(FinancialBalanceTable financialBalanceTable)
    {
        return financialBalanceTableMapper.insertFinancialBalanceTable(financialBalanceTable);
    }

    /**
     * 修改财务-资产负债
     * 
     * @param financialBalanceTable 财务-资产负债
     * @return 结果
     */
    @Override
    public int updateFinancialBalanceTable(FinancialBalanceTable financialBalanceTable)
    {
        return financialBalanceTableMapper.updateFinancialBalanceTable(financialBalanceTable);
    }

    /**
     * 批量删除财务-资产负债
     * 
     * @param fbIds 需要删除的财务-资产负债主键
     * @return 结果
     */
    @Override
    public int deleteFinancialBalanceTableByFbIds(Long[] fbIds)
    {
        return financialBalanceTableMapper.deleteFinancialBalanceTableByFbIds(fbIds);
    }

    /**
     * 删除财务-资产负债信息
     * 
     * @param fbId 财务-资产负债主键
     * @return 结果
     */
    @Override
    public int deleteFinancialBalanceTableByFbId(Long fbId)
    {
        return financialBalanceTableMapper.deleteFinancialBalanceTableByFbId(fbId);
    }
}
