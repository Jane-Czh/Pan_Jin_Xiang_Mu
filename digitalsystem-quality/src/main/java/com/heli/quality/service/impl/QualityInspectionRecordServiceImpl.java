package com.heli.quality.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.heli.quality.controller.QualityAfterSalesRecordController;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.excel.ReadExcelCellUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.quality.mapper.QualityInspectionRecordMapper;
import com.heli.quality.domain.QualityInspectionRecord;
import com.heli.quality.service.IQualityInspectionRecordService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 质检部分字段Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-16
 */
@Service
public class QualityInspectionRecordServiceImpl implements IQualityInspectionRecordService {
    @Autowired
    private QualityInspectionRecordMapper qualityInspectionRecordMapper;

    private static final Logger log = LoggerFactory.getLogger(QualityInspectionRecordServiceImpl.class);


    @Override
    public QualityInspectionRecord selectMaxMonthInspection() {
        return qualityInspectionRecordMapper.selectMaxMonthInspection();
    }

    @Override
    public void importQualityInspectionTable(MultipartFile excelFile, Date yearAndMonth, String username) throws IOException {
        QualityInspectionRecord qualityInspectionRecord;
        InputStream is = null;

        System.out.println("开始");
        try {
            System.out.println(excelFile);
            is = excelFile.getInputStream();
            qualityInspectionRecord = (QualityInspectionRecord) ReadExcelCellUtils.parseExcelToModel("com.heli.quality.domain.QualityInspectionRecord", is, "问题数据统计");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }

        qualityInspectionRecord.setYearAndMonth(yearAndMonth);
        qualityInspectionRecord.setCreateBy(username);
        qualityInspectionRecord.setCreateTime(DateUtils.getNowDate());
        //计算
        long problemNum = qualityInspectionRecord.getK2lessthan5tonProblemVehicles() + qualityInspectionRecord.getK2largetonnageProblemVehicles() + qualityInspectionRecord.getElectricCarProblemVehicles();
        long productionNum = qualityInspectionRecord.getElectricCarProductionQuantity() + qualityInspectionRecord.getK2lessthan5tonProductionQuantity() + qualityInspectionRecord.getK2largetonnageProductionQuantity();
        qualityInspectionRecord.setSingleInspectionPassRate(BigDecimal.valueOf((1 - (double) problemNum / (double) productionNum) * 100));
        qualityInspectionRecord.setElectricCarPassRate(BigDecimal.valueOf((1 - (double) qualityInspectionRecord.getElectricCarProblemVehicles() / (double) qualityInspectionRecord.getElectricCarProductionQuantity()) * 100));
        qualityInspectionRecord.setLargeTonPassRate(BigDecimal.valueOf((1 - (double) (qualityInspectionRecord.getK2largetonnageProblemVehicles() + qualityInspectionRecord.getK2lessthan5tonProblemVehicles())
                / (double) (qualityInspectionRecord.getK2largetonnageProductionQuantity() + qualityInspectionRecord.getK2lessthan5tonProductionQuantity())) * 100));

        qualityInspectionRecord.setIcvPassRate(BigDecimal.valueOf((1 - (double) qualityInspectionRecord.getIcvProblemVehicles() / (double) qualityInspectionRecord.getIcvProductionQuantity()) * 100));

        qualityInspectionRecord.setAllPassRate(BigDecimal.valueOf((1 - (double) (problemNum + qualityInspectionRecord.getIcvProblemVehicles()) / (double) (productionNum + qualityInspectionRecord.getIcvProductionQuantity())) * 100));

        log.info(String.valueOf("读取质检表并计算" + qualityInspectionRecord));
        qualityInspectionRecordMapper.insertQualityInspectionRecord(qualityInspectionRecord);
    }

    @Override
    public Boolean checkQualityInspectionTableIsExisted(Date date) {
        return qualityInspectionRecordMapper.checkQualityInspectionTableIsExisted(date);
    }

    /**
     * 查询质检部分字段
     *
     * @param qiId 质检部分字段主键
     * @return 质检部分字段
     */
    @Override
    public QualityInspectionRecord selectQualityInspectionRecordByQiId(Long qiId) {
        return qualityInspectionRecordMapper.selectQualityInspectionRecordByQiId(qiId);
    }

    /**
     * 查询质检部分字段列表
     *
     * @param qualityInspectionRecord 质检部分字段
     * @return 质检部分字段
     */
    @Override
    public List<QualityInspectionRecord> selectQualityInspectionRecordList(QualityInspectionRecord qualityInspectionRecord) {
        return qualityInspectionRecordMapper.selectQualityInspectionRecordList(qualityInspectionRecord);
    }

    /**
     * 新增质检部分字段
     *
     * @param qualityInspectionRecord 质检部分字段
     * @return 结果
     */
    @Override
    public int insertQualityInspectionRecord(QualityInspectionRecord qualityInspectionRecord) {
        qualityInspectionRecord.setCreateTime(DateUtils.getNowDate());
        log.info("新增质检表" + qualityInspectionRecord);

        long problemNum = qualityInspectionRecord.getK2lessthan5tonProblemVehicles() + qualityInspectionRecord.getK2largetonnageProblemVehicles() + qualityInspectionRecord.getElectricCarProblemVehicles();
        long productionNum = qualityInspectionRecord.getElectricCarProductionQuantity() + qualityInspectionRecord.getK2lessthan5tonProductionQuantity() + qualityInspectionRecord.getK2largetonnageProductionQuantity();
        if (qualityInspectionRecord.getLargeTonPassRate() == null) {
            qualityInspectionRecord.setLargeTonPassRate(BigDecimal.valueOf((1 - (double) (qualityInspectionRecord.getK2largetonnageProblemVehicles() + qualityInspectionRecord.getK2lessthan5tonProblemVehicles())
                    / (double) (qualityInspectionRecord.getK2largetonnageProductionQuantity() + qualityInspectionRecord.getK2lessthan5tonProductionQuantity())) * 100));
        }
        if (qualityInspectionRecord.getElectricCarPassRate() == null) {
            qualityInspectionRecord.setElectricCarPassRate(BigDecimal.valueOf((1 - (double) qualityInspectionRecord.getElectricCarProblemVehicles() / (double) qualityInspectionRecord.getElectricCarProductionQuantity()) * 100));
        }
        if (qualityInspectionRecord.getSingleInspectionPassRate() == null) {
            qualityInspectionRecord.setSingleInspectionPassRate(BigDecimal.valueOf((1 - (double) problemNum / (double) productionNum) * 100));
        }
        if (qualityInspectionRecord.getIcvPassRate() == null) {
            qualityInspectionRecord.setIcvPassRate(BigDecimal.valueOf((1 - (double) qualityInspectionRecord.getIcvProblemVehicles() / (double) qualityInspectionRecord.getIcvProductionQuantity()) * 100));
        }
        if (qualityInspectionRecord.getAllPassRate() == null) {
            qualityInspectionRecord.setAllPassRate(BigDecimal.valueOf((1 - (double) (problemNum + qualityInspectionRecord.getIcvProblemVehicles()) / (double) (productionNum + qualityInspectionRecord.getIcvProductionQuantity())) * 100));
        }


//        long problemNum = qualityInspectionRecord.getK2lessthan5tonProblemVehicles() + qualityInspectionRecord.getK2largetonnageProblemVehicles() + qualityInspectionRecord.getElectricCarProblemVehicles();
//        long productionNum = qualityInspectionRecord.getElectricCarProductionQuantity() + qualityInspectionRecord.getK2lessthan5tonProductionQuantity() + qualityInspectionRecord.getK2largetonnageProductionQuantity();
//        qualityInspectionRecord.setSingleInspectionPassRate(BigDecimal.valueOf((1 - (double) problemNum / (double) productionNum) * 100));
//        qualityInspectionRecord.setElectricCarPassRate(BigDecimal.valueOf((1 - (double) qualityInspectionRecord.getElectricCarProblemVehicles() / (double) qualityInspectionRecord.getElectricCarProductionQuantity()) * 100));
//        qualityInspectionRecord.setLargeTonPassRate(BigDecimal.valueOf((1 - (double) qualityInspectionRecord.getK2largetonnageProblemVehicles() / (double) qualityInspectionRecord.getK2largetonnageProductionQuantity()) * 100));
        return qualityInspectionRecordMapper.insertQualityInspectionRecord(qualityInspectionRecord);
    }

    /**
     * 修改质检部分字段
     *
     * @param qualityInspectionRecord 质检部分字段
     * @return 结果
     */
    @Override
    public int updateQualityInspectionRecord(QualityInspectionRecord qualityInspectionRecord) {
        qualityInspectionRecord.setUpdateTime(DateUtils.getNowDate());
        long problemNum = qualityInspectionRecord.getK2lessthan5tonProblemVehicles() + qualityInspectionRecord.getK2largetonnageProblemVehicles() + qualityInspectionRecord.getElectricCarProblemVehicles();
        long productionNum = qualityInspectionRecord.getElectricCarProductionQuantity() + qualityInspectionRecord.getK2lessthan5tonProductionQuantity() + qualityInspectionRecord.getK2largetonnageProductionQuantity();
        if (qualityInspectionRecord.getLargeTonPassRate() == null) {
            qualityInspectionRecord.setLargeTonPassRate(BigDecimal.valueOf((1 - (double) (qualityInspectionRecord.getK2largetonnageProblemVehicles() + qualityInspectionRecord.getK2lessthan5tonProblemVehicles())
                    / (double) (qualityInspectionRecord.getK2largetonnageProductionQuantity() + qualityInspectionRecord.getK2lessthan5tonProductionQuantity())) * 100));
        }
        if (qualityInspectionRecord.getElectricCarPassRate() == null) {
            qualityInspectionRecord.setElectricCarPassRate(BigDecimal.valueOf((1 - (double) qualityInspectionRecord.getElectricCarProblemVehicles() / (double) qualityInspectionRecord.getElectricCarProductionQuantity()) * 100));
        }
        if (qualityInspectionRecord.getSingleInspectionPassRate() == null) {
            qualityInspectionRecord.setSingleInspectionPassRate(BigDecimal.valueOf((1 - (double) problemNum / (double) productionNum) * 100));
        }
        if (qualityInspectionRecord.getIcvPassRate() == null) {
            qualityInspectionRecord.setIcvPassRate(BigDecimal.valueOf((1 - (double) qualityInspectionRecord.getIcvProblemVehicles() / (double) qualityInspectionRecord.getIcvProductionQuantity()) * 100));
        }
        if (qualityInspectionRecord.getAllPassRate() == null) {
            qualityInspectionRecord.setAllPassRate(BigDecimal.valueOf((1 - (double) (problemNum + qualityInspectionRecord.getIcvProblemVehicles()) / (double) (productionNum + qualityInspectionRecord.getIcvProductionQuantity())) * 100));
        }
//        long problemNum = qualityInspectionRecord.getK2lessthan5tonProblemVehicles() + qualityInspectionRecord.getK2largetonnageProblemVehicles() + qualityInspectionRecord.getElectricCarProblemVehicles();
//        long productionNum = qualityInspectionRecord.getElectricCarProductionQuantity() + qualityInspectionRecord.getK2lessthan5tonProductionQuantity() + qualityInspectionRecord.getK2largetonnageProductionQuantity();
//        qualityInspectionRecord.setSingleInspectionPassRate(BigDecimal.valueOf((1 - (double) problemNum / (double) productionNum) * 100));
//        qualityInspectionRecord.setElectricCarPassRate(BigDecimal.valueOf((1 - (double) qualityInspectionRecord.getElectricCarProblemVehicles() / (double) qualityInspectionRecord.getElectricCarProductionQuantity()) * 100));
//        qualityInspectionRecord.setLargeTonPassRate(BigDecimal.valueOf((1 - (double) qualityInspectionRecord.getK2largetonnageProblemVehicles() / (double) qualityInspectionRecord.getK2largetonnageProductionQuantity()) * 100));
        return qualityInspectionRecordMapper.updateQualityInspectionRecord(qualityInspectionRecord);
    }

    /**
     * 批量删除质检部分字段
     *
     * @param qiIds 需要删除的质检部分字段主键
     * @return 结果
     */
    @Override
    public int deleteQualityInspectionRecordByQiIds(Long[] qiIds) {
        return qualityInspectionRecordMapper.deleteQualityInspectionRecordByQiIds(qiIds);
    }

    /**
     * 删除质检部分字段信息
     *
     * @param qiId 质检部分字段主键
     * @return 结果
     */
    @Override
    public int deleteQualityInspectionRecordByQiId(Long qiId) {
        return qualityInspectionRecordMapper.deleteQualityInspectionRecordByQiId(qiId);
    }
}
