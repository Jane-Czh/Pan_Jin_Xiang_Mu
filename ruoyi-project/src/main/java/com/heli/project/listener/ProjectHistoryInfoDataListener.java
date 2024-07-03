package com.heli.project.listener;

import com.alibaba.excel.event.AnalysisEventListener;
import com.heli.project.domain.*;
import com.heli.project.mapper.ProjectHistoryInfoTableMapper;
//import lombok.extern.slf4j.Slf4j;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Slf4j
public class ProjectHistoryInfoDataListener extends AnalysisEventListener<ProjectHistoryInfoTable> {


    @Autowired
    private ProjectHistoryInfoTableMapper projectHistoryInfoTableMapper;

    private static final int BATCH_COUNT = 100; // 批处理数量

    //缓存一批数据
    private List<ProjectHistoryInfoTable> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    /**
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
//        log.error("======>>>解析异常：", exception);
        throw exception;
    }


    @Override
    public void invoke(ProjectHistoryInfoTable data, AnalysisContext context) {

        //在此处验证数据格式正确性
        if(data.getRemark() == null){
            data.setRemake("");
        }
        if(data.getManager() == null){
            data.setManager("");
        }
        if(data.getTeamMembers() == null){
            data.setTeamMembers("");
        }
        if(data.getCurrentStatus() == null){
            data.setCurrentStatus("");
        }
        if(data.getGoal() == null){
            data.setGoal("");
        }
        if(data.getScope() == null){
            data.setScope("");
        }
        if(data.getCompletionSummary() == null){
            data.setCompletionSummary("");
        }
        System.out.println(data.getRemark());

        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
//            batchInsert(cachedDataList);
            cachedDataList.clear();
        }
    }



    private void batchInsert(List<ProjectHistoryInfoTable> list) {
        // 实现批量插入到数据库的逻辑
        projectHistoryInfoTableMapper.batchInsert(list);
    }

    /**
     * 当每个sheet所有数据读取完毕后，会调用这个方法，可以在这个方法中进行一些收尾工作，如资源释放、数据汇总等。
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (cachedDataList != null && cachedDataList.size() > 0) {
            batchInsert(cachedDataList);
        }
//        log.info("sheet={} 所有数据解析完成！", context.readSheetHolder().getSheetName());

    }
}
