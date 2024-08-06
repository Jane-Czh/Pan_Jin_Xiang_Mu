package com.ruoyi.Enterprisemanagement.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionSpecialOperationWarning;
import com.ruoyi.Enterprisemanagement.utils.EMExcelUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.Enterprisemanagement.mapper.EnterpriseManagementSpecialOperationsManagementLedgerMapper;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementSpecialOperationsManagementLedger;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementSpecialOperationsManagementLedgerService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 特种作业台账导入Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-24
 */
@Service
public class EnterpriseManagementSpecialOperationsManagementLedgerServiceImpl implements IEnterpriseManagementSpecialOperationsManagementLedgerService 
{
    @Autowired
    private EnterpriseManagementSpecialOperationsManagementLedgerMapper enterpriseManagementSpecialOperationsManagementLedgerMapper;

    /**
     * 查询特种作业台账导入
     * 
     * @param emsiId 特种作业台账导入主键
     * @return 特种作业台账导入
     */
    @Override
    public EnterpriseManagementSpecialOperationsManagementLedger selectEnterpriseManagementSpecialOperationsManagementLedgerByEmsiId(Long emsiId)
    {
        return enterpriseManagementSpecialOperationsManagementLedgerMapper.selectEnterpriseManagementSpecialOperationsManagementLedgerByEmsiId(emsiId);
    }

    /**
     * 查询特种作业台账导入列表
     * 
     * @param enterpriseManagementSpecialOperationsManagementLedger 特种作业台账导入
     * @return 特种作业台账导入
     */
    @Override
    public List<EnterpriseManagementSpecialOperationsManagementLedger> selectEnterpriseManagementSpecialOperationsManagementLedgerList(EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementSpecialOperationsManagementLedger)
    {
        return enterpriseManagementSpecialOperationsManagementLedgerMapper.selectEnterpriseManagementSpecialOperationsManagementLedgerList(enterpriseManagementSpecialOperationsManagementLedger);
    }

    /**
     * 新增特种作业台账导入
     * 
     * @param enterpriseManagementSpecialOperationsManagementLedger 特种作业台账导入
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementSpecialOperationsManagementLedger(EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementSpecialOperationsManagementLedger)
    {
        return enterpriseManagementSpecialOperationsManagementLedgerMapper.insertEnterpriseManagementSpecialOperationsManagementLedger(enterpriseManagementSpecialOperationsManagementLedger);
    }

    /**
     * 修改特种作业台账导入
     * 
     * @param enterpriseManagementSpecialOperationsManagementLedger 特种作业台账导入
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementSpecialOperationsManagementLedger(EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementSpecialOperationsManagementLedger)
    {
        return enterpriseManagementSpecialOperationsManagementLedgerMapper.updateEnterpriseManagementSpecialOperationsManagementLedger(enterpriseManagementSpecialOperationsManagementLedger);
    }

    /**
     * 批量删除特种作业台账导入
     * 
     * @param emsiIds 需要删除的特种作业台账导入主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementSpecialOperationsManagementLedgerByEmsiIds(Long[] emsiIds)
    {
        return enterpriseManagementSpecialOperationsManagementLedgerMapper.deleteEnterpriseManagementSpecialOperationsManagementLedgerByEmsiIds(emsiIds);
    }

    /**
     * 删除特种作业台账导入信息
     * 
     * @param emsiId 特种作业台账导入主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementSpecialOperationsManagementLedgerByEmsiId(Long emsiId)
    {
        return enterpriseManagementSpecialOperationsManagementLedgerMapper.deleteEnterpriseManagementSpecialOperationsManagementLedgerByEmsiId(emsiId);
    }

    @Override
    public int SOimportInterests(MultipartFile excelFile) throws IOException {
        {
            EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementSpecialOperationsManagementLedger;
            InputStream is = null;
            try {
                List<EnterpriseManagementSpecialOperationsManagementLedger> enterpriseManagementSpecialOperationsManagementLedgers = EMExcelUtils.SOparseExcel(excelFile);
                // 批量获取并删除原来的数据
                List<EnterpriseManagementSpecialOperationsManagementLedger> existingRosters = enterpriseManagementSpecialOperationsManagementLedgerMapper.selectEnterpriseManagementSpecialOperationsManagementLedgerList(new EnterpriseManagementSpecialOperationsManagementLedger());
                for (EnterpriseManagementSpecialOperationsManagementLedger existingRoster : existingRosters) {
                    enterpriseManagementSpecialOperationsManagementLedgerMapper.deleteEnterpriseManagementSpecialOperationsManagementLedgerByEmsiId(existingRoster.getEmsiId());
                }
                int i = 0;
                while (i < enterpriseManagementSpecialOperationsManagementLedgers.size()){
                    enterpriseManagementSpecialOperationsManagementLedger = enterpriseManagementSpecialOperationsManagementLedgers.get(i);
                    Long lastid = selectLastId();
                    if(lastid == null){
                        lastid = 0L;
                    }
                    Long EMSI_id = GenerateId.getNextId(lastid);
                    enterpriseManagementSpecialOperationsManagementLedger.setEmsiId(EMSI_id);
                    enterpriseManagementSpecialOperationsManagementLedger.setCreatedTime(getTime.getCurrentDate());
                    if (enterpriseManagementSpecialOperationsManagementLedger.getName() == null){
                        continue;
                    }
                    enterpriseManagementSpecialOperationsManagementLedgerMapper.insertEnterpriseManagementSpecialOperationsManagementLedger(enterpriseManagementSpecialOperationsManagementLedger);
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

    }

    @Override
    public List<EnterpriseManagementSpecialOperationsManagementLedger> selectEnterpriseManagementSpecialOperationsManagementLedgerList1() {
        return enterpriseManagementSpecialOperationsManagementLedgerMapper.selectEnterpriseManagementSpecialOperationsManagementLedgerList1();
    }

    private Long selectLastId() {
        return enterpriseManagementSpecialOperationsManagementLedgerMapper.selectLastId();
    }
}

