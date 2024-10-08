package com.heli.supply.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.heli.supply.domain.SupplyPurchaseorderTable;
import com.heli.supply.mapper.SupplyMaterialCategoryDictionaryTableMapper;
import com.heli.supply.mapper.SupplyPurchaseorderTableMapper;
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
    @Autowired
    private SupplyPurchaseorderTableMapper supplyPurchaseorderTableMapper;
    @Autowired
    private SupplyMaterialCategoryDictionaryTableMapper supplyMaterialCategoryDictionaryTableMapper;

    @Override
    public void importInterests(MultipartFile excelFile) throws IOException {
        SupplyRatioFormulaTable supplyRatioFormulaTable = new SupplyRatioFormulaTable();
        InputStream is = null;

        try {
            List<SupplyRatioFormulaTable> supplyRatioFormulaTables = ExcelUtils.parseExcel2SupplyRatioFormulaTable(excelFile);

//            System.out.println(supplyRatioFormulaTables);
            int i = 0;
            System.out.println("删除全部记录");
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
    public void Synchronization(){
        List<SupplyRatioFormulaTable> supplyRatioFormulaTables = supplyRatioFormulaTableMapper.selectSupplyRatioFormulaTableList(new SupplyRatioFormulaTable());
        String recode_Material_Class = "";
        List<Integer> Quantity = new ArrayList<>();
        for (SupplyRatioFormulaTable value : supplyRatioFormulaTables) {
            //获取当前需要更新的物料号数据
            String Material_Class = value.getMaterialClass();
            //获取该行物料号对应的统计比例方法
            String Proportion_Statistical_Method = value.getProportionStatisticalMethod();
            //获取该行物料号对应的供货商代码
            String Supplier_Code = value.getSupplierCode();
            //获取该行物料号对应的预期供货比例
            String Supply_Proportion = value.getSupplyProportion();

            //根据不同的比例统计方式查询
            if (Proportion_Statistical_Method.equals("数量")){
                String Actual_Proportion = String.format("%.2f", supplyPurchaseorderTableMapper.calculateSupplierRatioByQuantity(Material_Class,Supplier_Code)) + "%";
                String Difference = subtractPercentages(Actual_Proportion, Supply_Proportion);
//                if (Math.abs(Double.parseDouble(Difference.replace("%", ""))) > 3){
//                    System.out.println(Material_Class + "====>" + Actual_Proportion);
//                }
//                System.out.println("数量"+Material_Class+Supplier_Code+"===>"+supplyPurchaseorderTableMapper.calculateSupplierRatioByQuantity(Material_Class,Supplier_Code));
                value.setActualProportion(Actual_Proportion);
                value.setDifference(Difference);
            }
            if (Proportion_Statistical_Method.equals("金额")){
                String Actual_Proportion = String.format("%.2f", supplyPurchaseorderTableMapper.calculateSupplierRatioByNetValueOrder(Material_Class,Supplier_Code)) + "%";
                String Difference = subtractPercentages(Actual_Proportion, Supply_Proportion);
                value.setActualProportion(Actual_Proportion);
                value.setDifference(Difference);
//                System.out.println("金额"+Material_Class+Supplier_Code+"===>"+supplyPurchaseorderTableMapper.calculateSupplierRatioByNetValueOrder(Material_Class,Supplier_Code));
            }

            supplyRatioFormulaTableMapper.updateSupplyRatioFormulaTable(value);
        }





    }
//    public void Synchronization(List<SupplyPurchaseorderTable> list){
//        SupplyRatioFormulaTable supplyRatioFormulaTable = new SupplyRatioFormulaTable();
//        // 获取当前年份和月份
//        Calendar now = Calendar.getInstance();
//        int currentYear = now.get(Calendar.YEAR);
//        int currentMonth = now.get(Calendar.MONTH) + 1; // 注意：Calendar 中月份是从 0 开始的，所以要加 1
//
//        int year = 2024;
//        int month = 1;
//
//        Map<String, Map<String, List<SupplyPurchaseorderTable>>> collect = list.stream().filter(a -> {
//            //获取当前数据的凭证日期
//            Calendar documentCalendar = Calendar.getInstance();
//            documentCalendar.setTime(a.getDocumentDate());
//            int documentYear = documentCalendar.get(Calendar.YEAR);
//            int documentMonth = documentCalendar.get(Calendar.MONTH) + 1;
//            //过滤出凭证日期年月和当前日期年月相同的数据
//            return documentYear == currentYear && documentMonth == currentMonth;
//        }).filter(a->{
//            String materialclass = supplyMaterialCategoryDictionaryTableMapper.selectSupplyMaterialCategoryDictionaryTableByMaterialNumber(a.getMaterialNumber());
//            return materialclass != null && !materialclass.isEmpty();
//        }) .collect(Collectors.groupingBy(a -> {
//            // 使用物料编号获取物料类别
//            String materialNumber = a.getMaterialNumber();
//            String materialclass = supplyMaterialCategoryDictionaryTableMapper.selectSupplyMaterialCategoryDictionaryTableByMaterialNumber(materialNumber);
//            return materialclass; // 使用物料类别作为分组键
//        }, Collectors.groupingBy(a -> {
//            String Supplier = a.getSupplier();
//            String Supplier_Name = a.getSupplierName();
//            return Supplier + " " + Supplier_Name;
//        })));
//
//
//        System.out.println("删除全部记录");
//        supplyRatioFormulaTableMapper.deleteAll();
//        //循环计算不同物料比例
//        for (Map.Entry<String, Map<String, List<SupplyPurchaseorderTable>>> entry : collect.entrySet()){
////            System.out.println("物料号======"+entry.getKey());
//            //相同物料号（前缀）计算
//            String Material_Class = entry.getKey();
//            //记录该物料总数
//            long amount = 0L;
//            Map<String, List<SupplyPurchaseorderTable>> value = entry.getValue();
//            Map<String, Long> Material_Amount = new HashMap<>();
//            //当前物料在不同公司的占比
//            for (Map.Entry<String, List<SupplyPurchaseorderTable>> entry1 : value.entrySet()){
//                //获取供应商代码
//                String Supplier = entry1.getKey();
//
//                //相同物料、相同供应商数量累计
//                long number = 0L;
//
//                List<SupplyPurchaseorderTable> supplyPurchaseorderTables= entry1.getValue();
//                //
//                for (SupplyPurchaseorderTable supplyPurchaseorderTable : supplyPurchaseorderTables) {

//                    number = number + supplyPurchaseorderTable.getPurchaseQuantity();
//                }
//                Material_Amount.put(Supplier,number);
//
//                amount += number;
////                System.out.println("    供应商代码======="+Supplier_Code + "供应商名称======="+Supplier_Name);
////                System.out.println("           内容========"+entry1.getValue().size());
//            }
//            System.out.println("当前物料为"+Material_Class);
//            System.out.println("当前物料各公司数量"+Material_Amount);
//            System.out.println("当前物料总数"+amount);
//
//            for (Map.Entry<String, Long> entry2 : Material_Amount.entrySet()){
//                String Supplier = entry2.getKey();
//                String Supplier_Code = SplitSupplierCode(Supplier);
//                String Supplier_Name = SplitSupplierName(Supplier);
//                float ratio = (float)entry2.getValue() / amount;
//                String formattedPercentage = String.format("%1.1f%%", ratio * 100);
//
//                supplyRatioFormulaTable.setMaterialClass(Material_Class);
//                supplyRatioFormulaTable.setSupplierCode(Supplier_Code);
//                supplyRatioFormulaTable.setSupplierName(Supplier_Name);
//                supplyRatioFormulaTable.setSupplyProportion(formattedPercentage);
////                supplyRatioFormulaTable.setProportionStatisticalMethod("数量");
//
//
//                insertSupplyRatioFormulaTable(supplyRatioFormulaTable);
//            }
//        }
//
//    }

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
    public String floatToPercentage(float value) {
        // 将 float 数值乘以 100，然后转换为字符串，并在末尾添加百分号
        return String.format("%.1f%%", value * 100);
    }
    public String subtractPercentages(String percent1, String percent2) {
        // 移除百分号
        String numStr1 = percent1.replace("%", "");
        String numStr2 = percent2.replace("%", "");

        // 将字符串转换为double
        double num1 = Double.parseDouble(numStr1);
        double num2 = Double.parseDouble(numStr2);

        // 计算差值
        double result = num1 - num2;

        // 将结果转换回字符串并添加百分号
        return String.format("%.2f%%", result);
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
