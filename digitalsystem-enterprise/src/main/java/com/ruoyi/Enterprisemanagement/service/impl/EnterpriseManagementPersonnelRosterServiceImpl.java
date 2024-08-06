package com.ruoyi.Enterprisemanagement.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.Enterprisemanagement.utils.EMExcelUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.utils.GenerateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.Enterprisemanagement.mapper.EnterpriseManagementPersonnelRosterMapper;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementPersonnelRoster;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementPersonnelRosterService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 员工花名单导入Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-06
 */
@Service
public class EnterpriseManagementPersonnelRosterServiceImpl implements IEnterpriseManagementPersonnelRosterService 
{
    @Autowired
    private EnterpriseManagementPersonnelRosterMapper enterpriseManagementPersonnelRosterMapper;

    /**
     * 查询员工花名单导入
     * 
     * @param emprId 员工花名单导入主键
     * @return 员工花名单导入
     */
    @Override
    public EnterpriseManagementPersonnelRoster selectEnterpriseManagementPersonnelRosterByEmprId(Long emprId)
    {
        return enterpriseManagementPersonnelRosterMapper.selectEnterpriseManagementPersonnelRosterByEmprId(emprId);
    }

    /**
     * 查询员工花名单导入列表
     * 
     * @param enterpriseManagementPersonnelRoster 员工花名单导入
     * @return 员工花名单导入
     */
    @Override
    public List<EnterpriseManagementPersonnelRoster> selectEnterpriseManagementPersonnelRosterList(EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster)
    {
        return enterpriseManagementPersonnelRosterMapper.selectEnterpriseManagementPersonnelRosterList(enterpriseManagementPersonnelRoster);
    }

    /**
     * 新增员工花名单导入
     * 
     * @param enterpriseManagementPersonnelRoster 员工花名单导入
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementPersonnelRoster(EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster)
    {
        return enterpriseManagementPersonnelRosterMapper.insertEnterpriseManagementPersonnelRoster(enterpriseManagementPersonnelRoster);
    }

    /**
     * 修改员工花名单导入
     * 
     * @param enterpriseManagementPersonnelRoster 员工花名单导入
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementPersonnelRoster(EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster)
    {
        return enterpriseManagementPersonnelRosterMapper.updateEnterpriseManagementPersonnelRoster(enterpriseManagementPersonnelRoster);
    }

    /**
     * 批量删除员工花名单导入
     * 
     * @param emprIds 需要删除的员工花名单导入主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementPersonnelRosterByEmprIds(Long[] emprIds)
    {
        return enterpriseManagementPersonnelRosterMapper.deleteEnterpriseManagementPersonnelRosterByEmprIds(emprIds);
    }

    /**
     * 删除员工花名单导入信息
     * 
     * @param emprId 员工花名单导入主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementPersonnelRosterByEmprId(Long emprId)
    {
        return enterpriseManagementPersonnelRosterMapper.deleteEnterpriseManagementPersonnelRosterByEmprId(emprId);
    }

    @Override
    public int PRimportInterests(MultipartFile excelFile) throws IOException {
        EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster;
        InputStream is = null;
        try {
            List<EnterpriseManagementPersonnelRoster> enterpriseManagementPersonnelRosters = EMExcelUtils.PRparseExcel(excelFile);
            // 批量获取并删除原来的数据
            List<EnterpriseManagementPersonnelRoster> existingRosters = enterpriseManagementPersonnelRosterMapper.selectEnterpriseManagementPersonnelRosterList(new EnterpriseManagementPersonnelRoster());
            if (!existingRosters.isEmpty()) {
                Long[] emprIds = existingRosters.stream().map(EnterpriseManagementPersonnelRoster::getEmprId).toArray(Long[]::new);
                enterpriseManagementPersonnelRosterMapper.deleteEnterpriseManagementPersonnelRosterByEmprIds(emprIds);
            }
//            int i = 0;
//            while (i < enterpriseManagementPersonnelRosters.size()){
//                enterpriseManagementPersonnelRoster = enterpriseManagementPersonnelRosters.get(i);
//                Long lastid = selectLastId();
//                if(lastid == null){
//                    lastid = 0L;
//                }
//                Long EMPR_id = GenerateId.getNextId(lastid);
//                enterpriseManagementPersonnelRoster.setEmprId(EMPR_id);
//                if (enterpriseManagementPersonnelRoster.getName() == null){
//                    continue;
//                }
//                enterpriseManagementPersonnelRosterMapper.insertEnterpriseManagementPersonnelRoster(enterpriseManagementPersonnelRoster);
//                i++;
//            }
            // 分批处理数据，每批4000条
            int batchSize = 4000;
            int totalSize = enterpriseManagementPersonnelRosters.size();
            for (int i = 0; i < totalSize; i += batchSize) {
                int end = Math.min(i + batchSize, totalSize);
                List<EnterpriseManagementPersonnelRoster> batchRosters = enterpriseManagementPersonnelRosters.subList(i, end);

                // 过滤掉name为null的数据
                batchRosters.removeIf(roster -> roster.getName() == null);

                // 批量插入数据
                if (!batchRosters.isEmpty()) {
                    enterpriseManagementPersonnelRosterMapper.insertBatch(batchRosters);
                }
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
    public List<EnterpriseManagementPersonnelRoster> selectEnterpriseManagementPersonnelRosterList1() {
        return enterpriseManagementPersonnelRosterMapper.selectEnterpriseManagementPersonnelRosterList1();
    }

    private Long selectLastId() {
        return enterpriseManagementPersonnelRosterMapper.selectLastId();
    }
}
