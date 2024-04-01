//package com.heli.financial.service.impl;
//
//import java.io.IOException;
//import java.util.List;
//
//import com.ruoyi.common.exception.ServiceException;
//import com.ruoyi.common.utils.xlsx.ReadExcellCellUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.heli.financial.mapper.FinancialInterestsTableMapper;
//import com.heli.financial.domain.FinancialInterestsTable;
//import com.heli.financial.service.IFinancialInterestsTableService;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * 财务-利润Service业务层处理
// *
// * @author hong
// * @date 2024-03-31
// */
//@Service
//public class FinancialInterestsTableServiceImpl implements IFinancialInterestsTableService {
//    @Autowired
//    private FinancialInterestsTableMapper financialInterestsTableMapper;
//
//
//    /**
//     * @description: 利润表导入
//     * @author: hong
//     * @date: 2024/3/31 13:50
//     * @param: [excelFile]
//     * @return: com.heli.financial.domain.FinancialInterestsTable
//     **/
//    @Override
//    public FinancialInterestsTable importInterestsTable(MultipartFile excelFile) {
//        FinancialInterestsTable financialInterestsTable;
//        try {
//            financialInterestsTable = (FinancialInterestsTable) ReadExcellCellUtils.parseExcelToModel("com.heli.financial.domain.FinancialInterestsTable",excelFile.getInputStream(),null);
//        } catch (IOException e){
//            e.printStackTrace();
//            throw new ServiceException("excel解析失败");
//        }
//        return financialInterestsTable;
//    }
//
//    /**
//     * 查询财务-利润
//     *
//     * @param fiId 财务-利润主键
//     * @return 财务-利润
//     */
//    @Override
//    public FinancialInterestsTable selectFinancialInterestsTableByFiId(Long fiId) {
//        return financialInterestsTableMapper.selectFinancialInterestsTableByFiId(fiId);
//    }
//
//    /**
//     * 查询财务-利润列表
//     *
//     * @param financialInterestsTable 财务-利润
//     * @return 财务-利润
//     */
//    @Override
//    public List<FinancialInterestsTable> selectFinancialInterestsTableList(FinancialInterestsTable financialInterestsTable) {
//        return financialInterestsTableMapper.selectFinancialInterestsTableList(financialInterestsTable);
//    }
//
//    /**
//     * 新增财务-利润
//     *
//     * @param financialInterestsTable 财务-利润
//     * @return 结果
//     */
//    @Override
//    public int insertFinancialInterestsTable(FinancialInterestsTable financialInterestsTable) {
//        return financialInterestsTableMapper.insertFinancialInterestsTable(financialInterestsTable);
//    }
//
//    /**
//     * 修改财务-利润
//     *
//     * @param financialInterestsTable 财务-利润
//     * @return 结果
//     */
//    @Override
//    public int updateFinancialInterestsTable(FinancialInterestsTable financialInterestsTable) {
//        return financialInterestsTableMapper.updateFinancialInterestsTable(financialInterestsTable);
//    }
//
//    /**
//     * 批量删除财务-利润
//     *
//     * @param fiIds 需要删除的财务-利润主键
//     * @return 结果
//     */
//    @Override
//    public int deleteFinancialInterestsTableByFiIds(Long[] fiIds) {
//        return financialInterestsTableMapper.deleteFinancialInterestsTableByFiIds(fiIds);
//    }
//
//    /**
//     * 删除财务-利润信息
//     *
//     * @param fiId 财务-利润主键
//     * @return 结果
//     */
//    @Override
//    public int deleteFinancialInterestsTableByFiId(Long fiId) {
//        return financialInterestsTableMapper.deleteFinancialInterestsTableByFiId(fiId);
//    }
//}


package com.heli.financial.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.excel.ReadExcelCellUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.financial.mapper.FinancialInterestsTableMapper;
import com.heli.financial.domain.FinancialInterestsTable;
import com.heli.financial.service.IFinancialInterestsTableService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 财务-利润Service业务层处理
 *
 * @author hong
 * @date 2024-03-31
 */
@Service
public class FinancialInterestsTableServiceImpl implements IFinancialInterestsTableService {
    @Autowired
    private FinancialInterestsTableMapper financialInterestsTableMapper;


    /**
     * @description: 利润表导入
     * @author: hong
     * @date: 2024/3/31 13:50
     * @param: excel文件
     * @return: 影响的行数
     **/
    @Override
    public int importInterestsTable(MultipartFile excelFile) throws IOException {
        FinancialInterestsTable financialInterestsTable;
        InputStream is = null;
        try {
            System.out.println(excelFile);
            is = excelFile.getInputStream();
            financialInterestsTable = (FinancialInterestsTable) ReadExcelCellUtils.parseExcelToModel("com.heli.financial.domain.FinancialInterestsTable", is, null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }
        financialInterestsTable.setMainRevenue(financialInterestsTable.getInternalMainRevenue().add(financialInterestsTable.getExternalMainRevenue()));
        financialInterestsTable.setCOGS(financialInterestsTable.getCogsProductSalesSd().add(financialInterestsTable.getCogsFreight()).add(financialInterestsTable.getCogsVariation()));
        return financialInterestsTableMapper.insertFinancialInterestsTable(financialInterestsTable);
    }


    /**
     * 查询财务-利润
     *
     * @param fiId 财务-利润主键
     * @return 财务-利润
     */
    @Override
    public FinancialInterestsTable selectFinancialInterestsTableByFiId(Long fiId) {
        return financialInterestsTableMapper.selectFinancialInterestsTableByFiId(fiId);
    }

    /**
     * 查询财务-利润列表
     *
     * @param financialInterestsTable 财务-利润
     * @return 财务-利润
     */
    @Override
    public List<FinancialInterestsTable> selectFinancialInterestsTableList(FinancialInterestsTable financialInterestsTable) {
        return financialInterestsTableMapper.selectFinancialInterestsTableList(financialInterestsTable);
    }

    /**
     * 新增财务-利润
     *
     * @param financialInterestsTable 财务-利润
     * @return 结果
     */
    @Override
    public int insertFinancialInterestsTable(FinancialInterestsTable financialInterestsTable) {
        return financialInterestsTableMapper.insertFinancialInterestsTable(financialInterestsTable);
    }

    /**
     * 修改财务-利润
     *
     * @param financialInterestsTable 财务-利润
     * @return 结果
     */
    @Override
    public int updateFinancialInterestsTable(FinancialInterestsTable financialInterestsTable) {
        return financialInterestsTableMapper.updateFinancialInterestsTable(financialInterestsTable);
    }

    /**
     * 批量删除财务-利润
     *
     * @param fiIds 需要删除的财务-利润主键
     * @return 结果
     */
    @Override
    public int deleteFinancialInterestsTableByFiIds(Long[] fiIds) {
        return financialInterestsTableMapper.deleteFinancialInterestsTableByFiIds(fiIds);
    }

    /**
     * 删除财务-利润信息
     *
     * @param fiId 财务-利润主键
     * @return 结果
     */
    @Override
    public int deleteFinancialInterestsTableByFiId(Long fiId) {
        return financialInterestsTableMapper.deleteFinancialInterestsTableByFiId(fiId);
    }
}
