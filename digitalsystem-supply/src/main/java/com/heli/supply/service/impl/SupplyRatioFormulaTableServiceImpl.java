package com.heli.supply.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.heli.supply.domain.SupplyPurchaseorderTable;
import com.heli.supply.utils.ExcelUtils;
import com.heli.supply.utils.GenerateId;
import com.ruoyi.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.supply.mapper.SupplyRatioFormulaTableMapper;
import com.heli.supply.domain.SupplyRatioFormulaTable;
import com.heli.supply.service.ISupplyRatioFormulaTableService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 月供货比例统计Service业务层处理
 *
 * @author Teandron
 * @date 2024-04-24
 */
@Service
public class SupplyRatioFormulaTableServiceImpl implements ISupplyRatioFormulaTableService
{
    @Autowired
    private SupplyRatioFormulaTableMapper supplyRatioFormulaTableMapper;


    @Override
    public void importInterests(MultipartFile excelFile) throws IOException {
        SupplyRatioFormulaTable supplyRatioFormulaTable = new SupplyRatioFormulaTable();
        InputStream is = null;

        try {
            List<SupplyRatioFormulaTable> supplyRatioFormulaTables = ExcelUtils.parseExcel2SupplyRatioFormulaTable(excelFile);

            int i = 0;
            supplyRatioFormulaTableMapper.deleteAll();
            while (i < supplyRatioFormulaTables.size()){
                supplyRatioFormulaTable = supplyRatioFormulaTables.get(i);

                insertSupplyRatioFormulaTable(supplyRatioFormulaTable);
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
    }

    @Override
    public void Synchronization(List<SupplyPurchaseorderTable> list){
        SupplyRatioFormulaTable supplyRatioFormulaTable = new SupplyRatioFormulaTable();
        // 获取当前年份和月份
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
        int currentMonth = now.get(Calendar.MONTH) + 1; // 注意：Calendar 中月份是从 0 开始的，所以要加 1

//        int year = 2024;
//        int month = 1;

        Map<String, Map<String, List<SupplyPurchaseorderTable>>> collect = list.stream().filter(a -> {
            //获取当前数据的凭证日期
            Calendar documentCalendar = Calendar.getInstance();
            documentCalendar.setTime(a.getDocumentDate());
            int documentYear = documentCalendar.get(Calendar.YEAR);
            int documentMonth = documentCalendar.get(Calendar.MONTH) + 1;
            //过滤出凭证日期年月和当前日期年月相同的数据
            return documentYear == currentYear && documentMonth == currentMonth;
        }).collect(Collectors.groupingBy(SupplyPurchaseorderTable::getMaterialNumber, Collectors.groupingBy(SupplyPurchaseorderTable::getSupplier)));

//        supplyRatioFormulaTableMapper.deleteAll();
        for (Map.Entry<String, Map<String, List<SupplyPurchaseorderTable>>> entry : collect.entrySet()){
//            System.out.println("物料号======"+entry.getKey());
            //相同物料号（前缀）计算
            String Material_Number = entry.getKey();
            //记录该物料总数
            long amount = 0L;
            Map<String, List<SupplyPurchaseorderTable>> value = entry.getValue();
            Map<String, Long> Material_Amount = new HashMap<>();
            for (Map.Entry<String, List<SupplyPurchaseorderTable>> entry1 : value.entrySet()){
                //获取供应商代码和名称
                String Supplier = entry1.getKey();
//                String Supplier_Code = SplitSupplierCode(Supplier);
//                String Supplier_Name = SplitSupplierName(Supplier);
                //相同物料、相同供应商数量累计
                long number = 0L;
                List<SupplyPurchaseorderTable> supplyPurchaseorderTables= entry1.getValue();
                for (SupplyPurchaseorderTable supplyPurchaseorderTable : supplyPurchaseorderTables) {
                    number = number + supplyPurchaseorderTable.getQuantity();
                }
                Material_Amount.put(Supplier,number);

                amount = amount + number;
//                System.out.println("    供应商代码======="+Supplier_Code + "供应商名称======="+Supplier_Name);
//                System.out.println("           内容========"+entry1.getValue().size());
            }
            for (Map.Entry<String, Long> entry2 : Material_Amount.entrySet()){
                String Supplier = entry2.getKey();
                String Supplier_Code = SplitSupplierCode(Supplier);
                String Supplier_Name = SplitSupplierName(Supplier);
                float ratio = (float)entry2.getValue() / amount;
                String formattedPercentage = String.format("%.1f%%", ratio * 100);
                supplyRatioFormulaTable.setMaterialNumber(Material_Number);
                supplyRatioFormulaTable.setSupplierCode(Supplier_Code);
                supplyRatioFormulaTable.setSupplierName(Supplier_Name);
                supplyRatioFormulaTable.setSupplyProportion(formattedPercentage);
                insertSupplyRatioFormulaTable(supplyRatioFormulaTable);

            }
        }

    }

    //正则表达式拆分供应商代码
    public String SplitSupplierCode(String Supplier){
        // 使用正则表达式匹配空格并进行拆分
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(Supplier);

        // 找到第一个匹配的空格位置
        int spaceIndex = 0;
        if (matcher.find()) {
            spaceIndex = matcher.start();
        }
        // 拆分出前面的部分
        return Supplier.substring(0, spaceIndex);
    }

    //正则表达式拆分供应商名称
    public String SplitSupplierName(String Supplier){
        // 使用正则表达式匹配空格并进行拆分
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(Supplier);

        // 找到第一个匹配的空格位置
        int spaceIndex = 0;
        if (matcher.find()) {
            spaceIndex = matcher.start();
        }
        // 拆分出后面的部分
        return Supplier.substring(spaceIndex).trim();
    }

    //float转百分数字符串
    public static String floatToPercentage(float value) {
        // 将 float 数值乘以 100，然后转换为字符串，并在末尾添加百分号
        return String.format("%.1f%%", value * 100);
    }
    /**
     * 查询月供货比例统计
     *
     * @param srftId 月供货比例统计主键
     * @return 月供货比例统计
     */
    @Override
    public SupplyRatioFormulaTable selectSupplyRatioFormulaTableBySrftId(Long srftId)
    {
        return supplyRatioFormulaTableMapper.selectSupplyRatioFormulaTableBySrftId(srftId);
    }

    /**
     * 查询月供货比例统计列表
     *
     * @param supplyRatioFormulaTable 月供货比例统计
     * @return 月供货比例统计
     */
    @Override
    public List<SupplyRatioFormulaTable> selectSupplyRatioFormulaTableList(SupplyRatioFormulaTable supplyRatioFormulaTable)
    {
        return supplyRatioFormulaTableMapper.selectSupplyRatioFormulaTableList(supplyRatioFormulaTable);
    }

    /**
     * 新增月供货比例统计
     *
     * @param supplyRatioFormulaTable 月供货比例统计
     * @return 结果
     */
    @Override
    public int insertSupplyRatioFormulaTable(SupplyRatioFormulaTable supplyRatioFormulaTable)
    {
        Long lastid = supplyRatioFormulaTableMapper.selectLastId();
        if (lastid == null){
            lastid = 0L;
        }
        Long insertid = GenerateId.getNextId(lastid);
        supplyRatioFormulaTable.setSrftId(insertid);
        return supplyRatioFormulaTableMapper.insertSupplyRatioFormulaTable(supplyRatioFormulaTable);
    }

    /**
     * 修改月供货比例统计
     *
     * @param supplyRatioFormulaTable 月供货比例统计
     * @return 结果
     */
    @Override
    public int updateSupplyRatioFormulaTable(SupplyRatioFormulaTable supplyRatioFormulaTable)
    {
        return supplyRatioFormulaTableMapper.updateSupplyRatioFormulaTable(supplyRatioFormulaTable);
    }

    /**
     * 批量删除月供货比例统计
     *
     * @param srftIds 需要删除的月供货比例统计主键
     * @return 结果
     */
    @Override
    public int deleteSupplyRatioFormulaTableBySrftIds(Long[] srftIds)
    {
        return supplyRatioFormulaTableMapper.deleteSupplyRatioFormulaTableBySrftIds(srftIds);
    }

    /**
     * 删除月供货比例统计信息
     *
     * @param srftId 月供货比例统计主键
     * @return 结果
     */
    @Override
    public int deleteSupplyRatioFormulaTableBySrftId(Long srftId)
    {
        return supplyRatioFormulaTableMapper.deleteSupplyRatioFormulaTableBySrftId(srftId);
    }
}
