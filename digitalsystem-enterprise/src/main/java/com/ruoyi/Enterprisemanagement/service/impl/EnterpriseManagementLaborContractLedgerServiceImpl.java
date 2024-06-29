package com.ruoyi.Enterprisemanagement.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionLaborReminder;
import com.ruoyi.Enterprisemanagement.utils.EMExcelUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.utils.GenerateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.Enterprisemanagement.mapper.EnterpriseManagementLaborContractLedgerMapper;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementLaborContractLedgerService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 劳动合同台账导入Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-24
 */
@Service
public class EnterpriseManagementLaborContractLedgerServiceImpl implements IEnterpriseManagementLaborContractLedgerService 
{
    @Autowired
    private EnterpriseManagementLaborContractLedgerMapper enterpriseManagementLaborContractLedgerMapper;

    /**
     * 查询劳动合同台账导入
     * 
     * @param emlcId 劳动合同台账导入主键
     * @return 劳动合同台账导入
     */
    @Override
    public EnterpriseManagementLaborContractLedger selectEnterpriseManagementLaborContractLedgerByEmlcId(Long emlcId)
    {
        return enterpriseManagementLaborContractLedgerMapper.selectEnterpriseManagementLaborContractLedgerByEmlcId(emlcId);
    }

    /**
     * 查询劳动合同台账导入列表
     * 
     * @param enterpriseManagementLaborContractLedger 劳动合同台账导入
     * @return 劳动合同台账导入
     */
    @Override
    public List<EnterpriseManagementLaborContractLedger> selectEnterpriseManagementLaborContractLedgerList(EnterpriseManagementLaborContractLedger enterpriseManagementLaborContractLedger)
    {
        return enterpriseManagementLaborContractLedgerMapper.selectEnterpriseManagementLaborContractLedgerList(enterpriseManagementLaborContractLedger);
    }

    /**
     * 新增劳动合同台账导入
     * 
     * @param enterpriseManagementLaborContractLedger 劳动合同台账导入
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementLaborContractLedger(EnterpriseManagementLaborContractLedger enterpriseManagementLaborContractLedger)
    {
        return enterpriseManagementLaborContractLedgerMapper.insertEnterpriseManagementLaborContractLedger(enterpriseManagementLaborContractLedger);
    }

    /**
     * 修改劳动合同台账导入
     * 
     * @param enterpriseManagementLaborContractLedger 劳动合同台账导入
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementLaborContractLedger(EnterpriseManagementLaborContractLedger enterpriseManagementLaborContractLedger)
    {
        return enterpriseManagementLaborContractLedgerMapper.updateEnterpriseManagementLaborContractLedger(enterpriseManagementLaborContractLedger);
    }

    /**
     * 批量删除劳动合同台账导入
     * 
     * @param emlcIds 需要删除的劳动合同台账导入主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementLaborContractLedgerByEmlcIds(Long[] emlcIds)
    {
        return enterpriseManagementLaborContractLedgerMapper.deleteEnterpriseManagementLaborContractLedgerByEmlcIds(emlcIds);
    }

    /**
     * 删除劳动合同台账导入信息
     * 
     * @param emlcId 劳动合同台账导入主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementLaborContractLedgerByEmlcId(Long emlcId)
    {
        return enterpriseManagementLaborContractLedgerMapper.deleteEnterpriseManagementLaborContractLedgerByEmlcId(emlcId);
    }

    @Override
    public int LRimportInterests(MultipartFile excelFile) throws IOException {
        EnterpriseManagementLaborContractLedger enterpriseManagementLaborContractLedger;
        InputStream is = null;
        try {
            List<EnterpriseManagementLaborContractLedger> enterpriseManagementLaborContractLedgers = EMExcelUtils.LRparseExcel(excelFile);
            int i = 0;
            while (i < enterpriseManagementLaborContractLedgers.size()){
                enterpriseManagementLaborContractLedger = enterpriseManagementLaborContractLedgers.get(i);
                Long lastid = selectLastId();
                if(lastid == null){
                    lastid = 0L;
                }
                Long EMLC_id = GenerateId.getNextId(lastid);
                enterpriseManagementLaborContractLedger.setEmlcId(EMLC_id);
                if (enterpriseManagementLaborContractLedger.getName() == null){
                    continue;
                }
                enterpriseManagementLaborContractLedgerMapper.insertEnterpriseManagementLaborContractLedger(enterpriseManagementLaborContractLedger);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return 0;
    }

    @Override
    public List<EnterpriseManagementLaborContractLedger> selectEnterpriseManagementLaborContractLedgerList1() {
        return enterpriseManagementLaborContractLedgerMapper.selectEnterpriseManagementLaborContractLedgerList1();
    }

    private Long selectLastId() {
        return enterpriseManagementLaborContractLedgerMapper.selectLastId();
    }
}
