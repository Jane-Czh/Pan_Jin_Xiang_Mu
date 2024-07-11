<template>
  <div class="app-container">
      <el-collapse v-model="activeNames">
        <el-collapse-item title="人员检索" name="search">
          <div>

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="序号" prop="Number">-->
<!--        <el-input-->
<!--          v-model="queryParams.Number"-->
<!--          placeholder="请输入序号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="员工编号" prop="employeeId">-->
<!--        <el-input-->
<!--          v-model="queryParams.employeeId"-->
<!--          placeholder="请输入员工编号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="姓名" prop="Name">-->
<!--        <el-input-->
<!--          v-model="queryParams.Name"-->
<!--          placeholder="请输入姓名"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="性别" prop="Gender">
        <el-input
          v-model="queryParams.Gender"
          placeholder="请输入性别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="出生日期" prop="Birthday">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.Birthday"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择出生日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="年龄" prop="Age">
        <el-input
          v-model="queryParams.Age"
          placeholder="请输入年龄"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="档案出生日期" prop="recordBirth">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.recordBirth"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择档案出生日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="身份证号" prop="idCard">-->
<!--        <el-input-->
<!--          v-model="queryParams.idCard"-->
<!--          placeholder="请输入身份证号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="社保编码" prop="socialSecurityNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.socialSecurityNumber"-->
<!--          placeholder="请输入社保编码"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="人事范围" prop="personnelScope">-->
<!--        <el-input-->
<!--          v-model="queryParams.personnelScope"-->
<!--          placeholder="请输入人事范围"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="人事范围文本" prop="personnelScopeText">-->
<!--        <el-input-->
<!--          v-model="queryParams.personnelScopeText"-->
<!--          placeholder="请输入人事范围文本"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="人事子范围" prop="personnelSubScope">-->
<!--        <el-input-->
<!--          v-model="queryParams.personnelSubScope"-->
<!--          placeholder="请输入人事子范围"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="人事子范围文本" prop="personnelSubScopeText">-->
<!--        <el-input-->
<!--          v-model="queryParams.personnelSubScopeText"-->
<!--          placeholder="请输入人事子范围文本"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="工资核算范围" prop="salaryAccountingScope">-->
<!--        <el-input-->
<!--          v-model="queryParams.salaryAccountingScope"-->
<!--          placeholder="请输入工资核算范围"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="工资核算范围文本" prop="salaryAccountingScopeText">-->
<!--        <el-input-->
<!--          v-model="queryParams.salaryAccountingScopeText"-->
<!--          placeholder="请输入工资核算范围文本"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="成本中心" prop="costCenter">-->
<!--        <el-input-->
<!--          v-model="queryParams.costCenter"-->
<!--          placeholder="请输入成本中心"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="成本中心文本" prop="costCenterText">-->
<!--        <el-input-->
<!--          v-model="queryParams.costCenterText"-->
<!--          placeholder="请输入成本中心文本"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="根组织ID" prop="rootTissueId">-->
<!--        <el-input-->
<!--          v-model="queryParams.rootTissueId"-->
<!--          placeholder="请输入根组织ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="根组织" prop="rootTissue">-->
<!--        <el-input-->
<!--          v-model="queryParams.rootTissue"-->
<!--          placeholder="请输入根组织"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="一级组织ID" prop="firstTissueId">-->
<!--        <el-input-->
<!--          v-model="queryParams.firstTissueId"-->
<!--          placeholder="请输入一级组织ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="一级组织" prop="firstTissue">-->
<!--        <el-input-->
<!--          v-model="queryParams.firstTissue"-->
<!--          placeholder="请输入一级组织"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="二级组织ID" prop="secondTissueId">-->
<!--        <el-input-->
<!--          v-model="queryParams.secondTissueId"-->
<!--          placeholder="请输入二级组织ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="二级组织" prop="secondTissue">-->
<!--        <el-input-->
<!--          v-model="queryParams.secondTissue"-->
<!--          placeholder="请输入二级组织"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="三级组织ID" prop="thirdTissueId">
        <el-input
          v-model="queryParams.thirdTissueId"
          placeholder="请输入三级组织ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="三级组织" prop="thirdTissue">
        <el-input
          v-model="queryParams.thirdTissue"
          placeholder="请输入三级组织"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="四级组织ID" prop="fourthTissueId">
        <el-input
          v-model="queryParams.fourthTissueId"
          placeholder="请输入四级组织ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="四级组织" prop="fourthTissue">
        <el-input
          v-model="queryParams.fourthTissue"
          placeholder="请输入四级组织"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="五级组织ID" prop="fifthTissueId">-->
<!--        <el-input-->
<!--          v-model="queryParams.fifthTissueId"-->
<!--          placeholder="请输入五级组织ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="五级组织" prop="fifthTissue">-->
<!--        <el-input-->
<!--          v-model="queryParams.fifthTissue"-->
<!--          placeholder="请输入五级组织"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="六级组织ID" prop="sixthTissueId">-->
<!--        <el-input-->
<!--          v-model="queryParams.sixthTissueId"-->
<!--          placeholder="请输入六级组织ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="六级组织" prop="sixthTissue">-->
<!--        <el-input-->
<!--          v-model="queryParams.sixthTissue"-->
<!--          placeholder="请输入六级组织"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="职务族群ID" prop="positionEthnicGroupId">-->
<!--        <el-input-->
<!--          v-model="queryParams.positionEthnicGroupId"-->
<!--          placeholder="请输入职务族群ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="职务族群" prop="positionEthnicGroup">-->
<!--        <el-input-->
<!--          v-model="queryParams.positionEthnicGroup"-->
<!--          placeholder="请输入职务族群"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="职务类别ID" prop="jobCategoryId">-->
<!--        <el-input-->
<!--          v-model="queryParams.jobCategoryId"-->
<!--          placeholder="请输入职务类别ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="职务类别" prop="jobCategory">-->
<!--        <el-input-->
<!--          v-model="queryParams.jobCategory"-->
<!--          placeholder="请输入职务类别"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="职务" prop="Job">-->
<!--        <el-input-->
<!--          v-model="queryParams.Job"-->
<!--          placeholder="请输入职务"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="职务名称" prop="jobTitle">-->
<!--        <el-input-->
<!--          v-model="queryParams.jobTitle"-->
<!--          placeholder="请输入职务名称"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="技术职称" prop="technicalTitle">-->
<!--        <el-input-->
<!--          v-model="queryParams.technicalTitle"-->
<!--          placeholder="请输入技术职称"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="最高技术职称" prop="theHighestTechnicalTitl">
        <el-input
          v-model="queryParams.theHighestTechnicalTitl"
          placeholder="请输入最高技术职称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="技术等级" prop="technicalLevel">-->
<!--        <el-input-->
<!--          v-model="queryParams.technicalLevel"-->
<!--          placeholder="请输入技术等级"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="最高技术等级" prop="theHighestTechnicalLevel">
        <el-input
          v-model="queryParams.theHighestTechnicalLevel"
          placeholder="请输入最高技术等级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="职级" prop="Rank">-->
<!--        <el-input-->
<!--          v-model="queryParams.Rank"-->
<!--          placeholder="请输入职级"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="职位ID" prop="positionId">-->
<!--        <el-input-->
<!--          v-model="queryParams.positionId"-->
<!--          placeholder="请输入职位ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="职位" prop="Position">-->
<!--        <el-input-->
<!--          v-model="queryParams.Position"-->
<!--          placeholder="请输入职位"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="岗位名称" prop="jobbTitle">-->
<!--        <el-input-->
<!--          v-model="queryParams.jobbTitle"-->
<!--          placeholder="请输入岗位名称"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="薪等" prop="salaryGrade">-->
<!--        <el-input-->
<!--          v-model="queryParams.salaryGrade"-->
<!--          placeholder="请输入薪等"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="薪级" prop="salaryyGrade">-->
<!--        <el-input-->
<!--          v-model="queryParams.salaryyGrade"-->
<!--          placeholder="请输入薪级"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="岗位工资" prop="positionSalary">-->
<!--        <el-input-->
<!--          v-model="queryParams.positionSalary"-->
<!--          placeholder="请输入岗位工资"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="员工组" prop="employeeGroup">
        <el-input
          v-model="queryParams.employeeGroup"
          placeholder="请输入员工组"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="员工子组" prop="employeeSubGroup">
        <el-input
          v-model="queryParams.employeeSubGroup"
          placeholder="请输入员工子组"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="骨干等级" prop="backboneLevel">
        <el-input
          v-model="queryParams.backboneLevel"
          placeholder="请输入骨干等级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="人事管理分类" prop="personnelManagementClassification">-->
<!--        <el-input-->
<!--          v-model="queryParams.personnelManagementClassification"-->
<!--          placeholder="请输入人事管理分类"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="最高学历" prop="theHighestEducationalLevel">
        <el-input
          v-model="queryParams.theHighestEducationalLevel"
          placeholder="请输入最高学历"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="最高学历学校" prop="theHighestEducationalSchool">-->
<!--        <el-input-->
<!--          v-model="queryParams.theHighestEducationalSchool"-->
<!--          placeholder="请输入最高学历学校"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="最高学历专业" prop="theHighestEducationalMajor">-->
<!--        <el-input-->
<!--          v-model="queryParams.theHighestEducationalMajor"-->
<!--          placeholder="请输入最高学历专业"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="最高学位" prop="theHighestDegree">-->
<!--        <el-input-->
<!--          v-model="queryParams.theHighestDegree"-->
<!--          placeholder="请输入最高学位"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="最高学位学校" prop="theHighestDegreeSchool">-->
<!--        <el-input-->
<!--          v-model="queryParams.theHighestDegreeSchool"-->
<!--          placeholder="请输入最高学位学校"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="最高学位专业" prop="theHighestDegreeMajor">-->
<!--        <el-input-->
<!--          v-model="queryParams.theHighestDegreeMajor"-->
<!--          placeholder="请输入最高学位专业"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="第一学历" prop="firstEducationalLevel">
        <el-input
          v-model="queryParams.firstEducationalLevel"
          placeholder="请输入第一学历"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="第一学位" prop="firstHighestDegree">-->
<!--        <el-input-->
<!--          v-model="queryParams.firstHighestDegree"-->
<!--          placeholder="请输入第一学位"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="第一学位学校" prop="firstHighestDegreeSchool">-->
<!--        <el-input-->
<!--          v-model="queryParams.firstHighestDegreeSchool"-->
<!--          placeholder="请输入第一学位学校"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="第一学位专业" prop="firstHighestDegreeMajor">-->
<!--        <el-input-->
<!--          v-model="queryParams.firstHighestDegreeMajor"-->
<!--          placeholder="请输入第一学位专业"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="合同开始时间" prop="contractStartTime">
        <el-date-picker clearable
          v-model="queryParams.contractStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择合同开始时间">
        </el-date-picker>
      </el-form-item>
<!--      <el-form-item label="合同结束时间" prop="contractEndTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.contractEndTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择合同结束时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="合同年限" prop="contractTerm">-->
<!--        <el-input-->
<!--          v-model="queryParams.contractTerm"-->
<!--          placeholder="请输入合同年限"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="合同签订单位" prop="contractSigningUnit">
        <el-input
          v-model="queryParams.contractSigningUnit"
          placeholder="请输入合同签订单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="签订次数" prop="signingFrequency">
        <el-input
          v-model="queryParams.signingFrequency"
          placeholder="请输入签订次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="加入工作时间" prop="workingHours">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.workingHours"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择加入工作时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="加入合力时间" prop="joinedTime">
        <el-date-picker clearable
          v-model="queryParams.joinedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择加入合力时间">
        </el-date-picker>
      </el-form-item>
<!--      <el-form-item label="司龄起算时间" prop="startingTimeOfServiceLife">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.startingTimeOfServiceLife"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择司龄起算时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="退休日期" prop="retirementDate">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.retirementDate"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择退休日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="司龄" prop="siLing">
        <el-input
          v-model="queryParams.siLing"
          placeholder="请输入司龄"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="民族" prop="Nation">
        <el-input
          v-model="queryParams.Nation"
          placeholder="请输入民族"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="政治面貌" prop="politicalLandscape">-->
<!--        <el-input-->
<!--          v-model="queryParams.politicalLandscape"-->
<!--          placeholder="请输入政治面貌"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="入党时间" prop="partyMembershipTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.partyMembershipTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择入党时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="婚姻状况" prop="marriageStatus">
        <el-input
          v-model="queryParams.maritalStatus"
          placeholder="请输入婚姻状况"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <!--      <el-form-item label="籍贯" prop="Hometown">-->
<!--        <el-input-->
<!--          v-model="queryParams.Hometown"-->
<!--          placeholder="请输入籍贯"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="户口性质" prop="householdRegistrationNature">
        <el-input
          v-model="queryParams.householdRegistrationNature"
          placeholder="请输入户口性质"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="常驻地址" prop="residentAddress">-->
<!--        <el-input-->
<!--          v-model="queryParams.residentAddress"-->
<!--          placeholder="请输入常驻地址"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="联系方式" prop="contactInformation">-->
<!--        <el-input-->
<!--          v-model="queryParams.contactInformation"-->
<!--          placeholder="请输入联系方式"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="紧急联系人" prop="emergencyContact">-->
<!--        <el-input-->
<!--          v-model="queryParams.emergencyContact"-->
<!--          placeholder="请输入紧急联系人"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="紧急联系人电话" prop="emergencyContactPhone">-->
<!--        <el-input-->
<!--          v-model="queryParams.emergencyContactPhone"-->
<!--          placeholder="请输入紧急联系人电话"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="工作合同" prop="workContract">-->
<!--        <el-input-->
<!--          v-model="queryParams.workContract"-->
<!--          placeholder="请输入工作合同"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="住房公积金账号" prop="providentFundAccount">-->
<!--        <el-input-->
<!--          v-model="queryParams.providentFundAccount"-->
<!--          placeholder="请输入住房公积金账号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="企业年金账号" prop="enterpriseAnnuityAccount">-->
<!--        <el-input-->
<!--          v-model="queryParams.enterpriseAnnuityAccount"-->
<!--          placeholder="请输入企业年金账号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="是否上传照片" prop="uploadPhoto">-->
<!--        <el-input-->
<!--          v-model="queryParams.uploadPhoto"-->
<!--          placeholder="请输入是否上传照片"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
            </div>
        </el-collapse-item>
      </el-collapse>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['Enterprisemanagement:roster:add']"
        >新增</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['Enterprisemanagement:roster:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['Enterprisemanagement:roster:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['Enterprisemanagement:roster:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-download"
          size="mini"
          @click="showDialog = true"
          v-hasPermi="['Enterprisemanagement:roster:import']"
        >导入Excel文件</el-button>
        <el-dialog
          title="导入Excel文件"
          :visible.sync="showDialog"
          width="30%"
          :before-close="handleClose"
          @close="resetFileInput"
        >
          <i class="el-icon-upload"></i>
          <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />

          <!-- 进度动画条 -->
          <div v-if="progress > 0">
            <el-progress
              :percentage="progress"
              color="rgb(19, 194, 194)"
            ></el-progress>
          </div>

          <span slot="footer" class="dialog-footer">
          <el-button @click="showDialog = false">取 消</el-button>
          <el-button type="primary" @click="fileSend()">确 定</el-button>
        </span>
        </el-dialog>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="rosterList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="emprId" />-->
      <el-table-column label="序号" align="center" prop="number" />
      <el-table-column label="员工编号" align="center" prop="employeeId" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column label="出生日期" align="center" prop="birthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.Birthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column label="档案出生日期" align="center" prop="recordBirth" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordBirth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="身份证号" align="center" prop="idCard" />
      <el-table-column label="社保编码" align="center" prop="socialSecurityNumber" />
      <el-table-column label="人事范围" align="center" prop="personnelScope" />
      <el-table-column label="人事范围文本" align="center" prop="personnelScopeText" />
      <el-table-column label="人事子范围" align="center" prop="personnelSubScope" />
      <el-table-column label="人事子范围文本" align="center" prop="personnelSubScopeText" />
      <el-table-column label="工资核算范围" align="center" prop="salaryAccountingScope" />
      <el-table-column label="工资核算范围文本" align="center" prop="salaryAccountingScopeText" />
      <el-table-column label="成本中心" align="center" prop="costCenter" />
      <el-table-column label="成本中心文本" align="center" prop="costCenterText" />
      <el-table-column label="根组织ID" align="center" prop="rootTissueId" />
      <el-table-column label="根组织" align="center" prop="rootTissue" />
      <el-table-column label="一级组织ID" align="center" prop="firstTissueId" />
      <el-table-column label="一级组织" align="center" prop="firstTissue" />
      <el-table-column label="二级组织ID" align="center" prop="secondTissueId" />
      <el-table-column label="二级组织" align="center" prop="secondTissue" />
      <el-table-column label="三级组织ID" align="center" prop="thirdTissueId" />
      <el-table-column label="三级组织" align="center" prop="thirdTissue" />
      <el-table-column label="四级组织ID" align="center" prop="fourthTissueId" />
      <el-table-column label="四级组织" align="center" prop="fourthTissue" />
      <el-table-column label="五级组织ID" align="center" prop="fifthTissueId" />
      <el-table-column label="五级组织" align="center" prop="fifthTissue" />
      <el-table-column label="六级组织ID" align="center" prop="sixthTissueId" />
      <el-table-column label="六级组织" align="center" prop="sixthTissue" />
      <el-table-column label="职务族群ID" align="center" prop="positionEthnicGroupId" />
      <el-table-column label="职务族群" align="center" prop="positionEthnicGroup" />
      <el-table-column label="职务类别ID" align="center" prop="jobCategoryId" />
      <el-table-column label="职务类别" align="center" prop="jobCategory" />
      <el-table-column label="职务" align="center" prop="job" />
      <el-table-column label="职务名称" align="center" prop="jobTitle" />
      <el-table-column label="技术职称" align="center" prop="technicalTitle" />
      <el-table-column label="最高技术职称" align="center" prop="theHighestTechnicalTitl" />
      <el-table-column label="技术等级" align="center" prop="technicalLevel" />
      <el-table-column label="最高技术等级" align="center" prop="theHighestTechnicalLevel" />
      <el-table-column label="职级" align="center" prop="rank" />
      <el-table-column label="职位ID" align="center" prop="positionId" />
      <el-table-column label="职位" align="center" prop="position" />
      <el-table-column label="岗位名称" align="center" prop="jobbTitle" />
      <el-table-column label="薪等" align="center" prop="salaryGrade" />
      <el-table-column label="薪级" align="center" prop="salaryyGrade" />
      <el-table-column label="岗位工资" align="center" prop="positionSalary" />
      <el-table-column label="员工组" align="center" prop="employeeGroup" />
      <el-table-column label="员工子组" align="center" prop="employeeSubGroup" />
      <el-table-column label="骨干等级" align="center" prop="backboneLevel" />
      <el-table-column label="人事管理分类" align="center" prop="personnelManagementClassification" />
      <el-table-column label="最高学历" align="center" prop="theHighestEducationalLevel" />
      <el-table-column label="最高学历学校" align="center" prop="theHighestEducationalSchool" />
      <el-table-column label="最高学历专业" align="center" prop="theHighestEducationalMajor" />
      <el-table-column label="最高学位" align="center" prop="theHighestDegree" />
      <el-table-column label="最高学位学校" align="center" prop="theHighestDegreeSchool" />
      <el-table-column label="最高学位专业" align="center" prop="theHighestDegreeMajor" />
      <el-table-column label="第一学历" align="center" prop="firstEducationalLevel" />
      <el-table-column label="第一学位" align="center" prop="firstHighestDegree" />
      <el-table-column label="第一学位学校" align="center" prop="firstHighestDegreeSchool" />
      <el-table-column label="第一学位专业" align="center" prop="firstHighestDegreeMajor" />
      <el-table-column label="合同开始时间" align="center" prop="contractStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.contractStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="合同结束时间" align="center" prop="contractEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.contractEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="合同年限" align="center" prop="contractTerm" />
      <el-table-column label="合同签订单位" align="center" prop="contractSigningUnit" />
      <el-table-column label="签订次数" align="center" prop="signingFrequency" />
      <el-table-column label="加入工作时间" align="center" prop="workingHours" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.workingHours, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="加入合力时间" align="center" prop="joinedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.joinedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="司龄起算时间" align="center" prop="startingTimeOfServiceLife" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startingTimeOfServiceLife, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="退休日期" align="center" prop="retirementDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.retirementDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="司龄" align="center" prop="siLing" />
      <el-table-column label="民族" align="center" prop="nation" />
      <el-table-column label="政治面貌" align="center" prop="politicalLandscape" />
      <el-table-column label="入党时间" align="center" prop="partyMembershipTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.partyMembershipTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="婚姻状况" align="center" prop="maritalStatus" />
      <el-table-column label="籍贯" align="center" prop="hometown" />
      <el-table-column label="户口性质" align="center" prop="householdRegistrationNature" />
      <el-table-column label="常驻地址" align="center" prop="residentAddress" />
      <el-table-column label="联系方式" align="center" prop="contactInformation" />
      <el-table-column label="紧急联系人" align="center" prop="emergencyContact" />
      <el-table-column label="紧急联系人电话" align="center" prop="emergencyContactPhone" />
      <el-table-column label="工作合同" align="center" prop="workContract" />
      <el-table-column label="住房公积金账号" align="center" prop="providentFundAccount" />
      <el-table-column label="企业年金账号" align="center" prop="enterpriseAnnuityAccount" />
      <el-table-column label="是否上传照片" align="center" prop="uploadPhoto" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['Enterprisemanagement:roster:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['Enterprisemanagement:roster:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改员工花名单导入对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="序号" prop="number">
          <el-input v-model="form.number" placeholder="请输入序号" />
        </el-form-item>
        <el-form-item label="员工编号" prop="employeeId">
          <el-input v-model="form.employeeId" placeholder="请输入员工编号" />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="form.gender" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="出生日期" prop="birthday">
          <el-date-picker clearable
            v-model="form.birthday"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="form.age" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="档案出生日期" prop="recordBirth">
          <el-date-picker clearable
            v-model="form.recordBirth"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择档案出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="form.idCard" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="社保编码" prop="socialSecurityNumber">
          <el-input v-model="form.socialSecurityNumber" placeholder="请输入社保编码" />
        </el-form-item>
        <el-form-item label="人事范围" prop="personnelScope">
          <el-input v-model="form.personnelScope" placeholder="请输入人事范围" />
        </el-form-item>
        <el-form-item label="人事范围文本" prop="personnelScopeText">
          <el-input v-model="form.personnelScopeText" placeholder="请输入人事范围文本" />
        </el-form-item>
        <el-form-item label="人事子范围" prop="personnelSubScope">
          <el-input v-model="form.personnelSubScope" placeholder="请输入人事子范围" />
        </el-form-item>
        <el-form-item label="人事子范围文本" prop="personnelSubScopeText">
          <el-input v-model="form.personnelSubScopeText" placeholder="请输入人事子范围文本" />
        </el-form-item>
        <el-form-item label="工资核算范围" prop="salaryAccountingScope">
          <el-input v-model="form.salaryAccountingScope" placeholder="请输入工资核算范围" />
        </el-form-item>
        <el-form-item label="工资核算范围文本" prop="salaryAccountingScopeText">
          <el-input v-model="form.salaryAccountingScopeText" placeholder="请输入工资核算范围文本" />
        </el-form-item>
        <el-form-item label="成本中心" prop="costCenter">
          <el-input v-model="form.costCenter" placeholder="请输入成本中心" />
        </el-form-item>
        <el-form-item label="成本中心文本" prop="costCenterText">
          <el-input v-model="form.costCenterText" placeholder="请输入成本中心文本" />
        </el-form-item>
        <el-form-item label="根组织ID" prop="rootTissueId">
          <el-input v-model="form.rootTissueId" placeholder="请输入根组织ID" />
        </el-form-item>
        <el-form-item label="根组织" prop="rootTissue">
          <el-input v-model="form.rootTissue" placeholder="请输入根组织" />
        </el-form-item>
        <el-form-item label="一级组织ID" prop="firstTissueId">
          <el-input v-model="form.firstTissueId" placeholder="请输入一级组织ID" />
        </el-form-item>
        <el-form-item label="一级组织" prop="firstTissue">
          <el-input v-model="form.firstTissue" placeholder="请输入一级组织" />
        </el-form-item>
        <el-form-item label="二级组织ID" prop="secondTissueId">
          <el-input v-model="form.secondTissueId" placeholder="请输入二级组织ID" />
        </el-form-item>
        <el-form-item label="二级组织" prop="secondTissue">
          <el-input v-model="form.secondTissue" placeholder="请输入二级组织" />
        </el-form-item>
        <el-form-item label="三级组织ID" prop="thirdTissueId">
          <el-input v-model="form.thirdTissueId" placeholder="请输入三级组织ID" />
        </el-form-item>
        <el-form-item label="三级组织" prop="thirdTissue">
          <el-input v-model="form.thirdTissue" placeholder="请输入三级组织" />
        </el-form-item>
        <el-form-item label="四级组织ID" prop="fourthTissueId">
          <el-input v-model="form.fourthTissueId" placeholder="请输入四级组织ID" />
        </el-form-item>
        <el-form-item label="四级组织" prop="fourthTissue">
          <el-input v-model="form.fourthTissue" placeholder="请输入四级组织" />
        </el-form-item>
        <el-form-item label="五级组织ID" prop="fifthTissueId">
          <el-input v-model="form.fifthTissueId" placeholder="请输入五级组织ID" />
        </el-form-item>
        <el-form-item label="五级组织" prop="fifthTissue">
          <el-input v-model="form.fifthTissue" placeholder="请输入五级组织" />
        </el-form-item>
        <el-form-item label="六级组织ID" prop="sixthTissueId">
          <el-input v-model="form.sixthTissueId" placeholder="请输入六级组织ID" />
        </el-form-item>
        <el-form-item label="六级组织" prop="sixthTissue">
          <el-input v-model="form.sixthTissue" placeholder="请输入六级组织" />
        </el-form-item>
        <el-form-item label="职务族群ID" prop="positionEthnicGroupId">
          <el-input v-model="form.positionEthnicGroupId" placeholder="请输入职务族群ID" />
        </el-form-item>
        <el-form-item label="职务族群" prop="positionEthnicGroup">
          <el-input v-model="form.positionEthnicGroup" placeholder="请输入职务族群" />
        </el-form-item>
        <el-form-item label="职务类别ID" prop="jobCategoryId">
          <el-input v-model="form.jobCategoryId" placeholder="请输入职务类别ID" />
        </el-form-item>
        <el-form-item label="职务类别" prop="jobCategory">
          <el-input v-model="form.jobCategory" placeholder="请输入职务类别" />
        </el-form-item>
        <el-form-item label="职务" prop="job">
          <el-input v-model="form.job" placeholder="请输入职务" />
        </el-form-item>
        <el-form-item label="职务名称" prop="jobTitle">
          <el-input v-model="form.jobTitle" placeholder="请输入职务名称" />
        </el-form-item>
        <el-form-item label="技术职称" prop="technicalTitle">
          <el-input v-model="form.technicalTitle" placeholder="请输入技术职称" />
        </el-form-item>
        <el-form-item label="最高技术职称" prop="theHighestTechnicalTitl">
          <el-input v-model="form.theHighestTechnicalTitl" placeholder="请输入最高技术职称" />
        </el-form-item>
        <el-form-item label="技术等级" prop="technicalLevel">
          <el-input v-model="form.technicalLevel" placeholder="请输入技术等级" />
        </el-form-item>
        <el-form-item label="最高技术等级" prop="theHighestTechnicalLevel">
          <el-input v-model="form.theHighestTechnicalLevel" placeholder="请输入最高技术等级" />
        </el-form-item>
        <el-form-item label="职级" prop="rank">
          <el-input v-model="form.rank" placeholder="请输入职级" />
        </el-form-item>
        <el-form-item label="职位ID" prop="positionId">
          <el-input v-model="form.positionId" placeholder="请输入职位ID" />
        </el-form-item>
        <el-form-item label="职位" prop="position">
          <el-input v-model="form.position" placeholder="请输入职位" />
        </el-form-item>
        <el-form-item label="岗位名称" prop="jobbTitle">
          <el-input v-model="form.jobbTitle" placeholder="请输入岗位名称" />
        </el-form-item>
        <el-form-item label="薪等" prop="salaryGrade">
          <el-input v-model="form.salaryGrade" placeholder="请输入薪等" />
        </el-form-item>
        <el-form-item label="薪级" prop="salaryyGrade">
          <el-input v-model="form.salaryyGrade" placeholder="请输入薪级" />
        </el-form-item>
        <el-form-item label="岗位工资" prop="positionSalary">
          <el-input v-model="form.positionSalary" placeholder="请输入岗位工资" />
        </el-form-item>
        <el-form-item label="员工组" prop="employeeGroup">
          <el-input v-model="form.employeeGroup" placeholder="请输入员工组" />
        </el-form-item>
        <el-form-item label="员工子组" prop="employeeSubGroup">
          <el-input v-model="form.employeeSubGroup" placeholder="请输入员工子组" />
        </el-form-item>
        <el-form-item label="骨干等级" prop="backboneLevel">
          <el-input v-model="form.backboneLevel" placeholder="请输入骨干等级" />
        </el-form-item>
        <el-form-item label="人事管理分类" prop="personnelManagementClassification">
          <el-input v-model="form.personnelManagementClassification" placeholder="请输入人事管理分类" />
        </el-form-item>
        <el-form-item label="最高学历" prop="theHighestEducationalLevel">
          <el-input v-model="form.theHighestEducationalLevel" placeholder="请输入最高学历" />
        </el-form-item>
        <el-form-item label="最高学历学校" prop="theHighestEducationalSchool">
          <el-input v-model="form.theHighestEducationalSchool" placeholder="请输入最高学历学校" />
        </el-form-item>
        <el-form-item label="最高学历专业" prop="theHighestEducationalMajor">
          <el-input v-model="form.theHighestEducationalMajor" placeholder="请输入最高学历专业" />
        </el-form-item>
        <el-form-item label="最高学位" prop="theHighestDegree">
          <el-input v-model="form.theHighestDegree" placeholder="请输入最高学位" />
        </el-form-item>
        <el-form-item label="最高学位学校" prop="theHighestDegreeSchool">
          <el-input v-model="form.theHighestDegreeSchool" placeholder="请输入最高学位学校" />
        </el-form-item>
        <el-form-item label="最高学位专业" prop="theHighestDegreeMajor">
          <el-input v-model="form.theHighestDegreeMajor" placeholder="请输入最高学位专业" />
        </el-form-item>
        <el-form-item label="第一学历" prop="firstEducationalLevel">
          <el-input v-model="form.firstEducationalLevel" placeholder="请输入第一学历" />
        </el-form-item>
        <el-form-item label="第一学位" prop="firstHighestDegree">
          <el-input v-model="form.firstHighestDegree" placeholder="请输入第一学位" />
        </el-form-item>
        <el-form-item label="第一学位学校" prop="firstHighestDegreeSchool">
          <el-input v-model="form.firstHighestDegreeSchool" placeholder="请输入第一学位学校" />
        </el-form-item>
        <el-form-item label="第一学位专业" prop="firstHighestDegreeMajor">
          <el-input v-model="form.firstHighestDegreeMajor" placeholder="请输入第一学位专业" />
        </el-form-item>
        <el-form-item label="合同开始时间" prop="contractStartTime">
          <el-date-picker clearable
            v-model="form.contractStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择合同开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="合同结束时间" prop="contractEndTime">
          <el-date-picker clearable
            v-model="form.contractEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择合同结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="合同年限" prop="contractTerm">
          <el-input v-model="form.contractTerm" placeholder="请输入合同年限" />
        </el-form-item>
        <el-form-item label="合同签订单位" prop="contractSigningUnit">
          <el-input v-model="form.contractSigningUnit" placeholder="请输入合同签订单位" />
        </el-form-item>
        <el-form-item label="签订次数" prop="signingFrequency">
          <el-input v-model="form.signingFrequency" placeholder="请输入签订次数" />
        </el-form-item>
        <el-form-item label="加入工作时间" prop="workingHours">
          <el-date-picker clearable
            v-model="form.workingHours"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择加入工作时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="加入合力时间" prop="joinedTime">
          <el-date-picker clearable
            v-model="form.joinedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择加入合力时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="司龄起算时间" prop="startingTimeOfServiceLife">
          <el-date-picker clearable
            v-model="form.startingTimeOfServiceLife"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择司龄起算时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="退休日期" prop="retirementDate">
          <el-date-picker clearable
            v-model="form.retirementDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择退休日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="司龄" prop="siLing">
          <el-input v-model="form.siLing" placeholder="请输入司龄" />
        </el-form-item>
        <el-form-item label="民族" prop="nation">
          <el-input v-model="form.nation" placeholder="请输入民族" />
        </el-form-item>
        <el-form-item label="政治面貌" prop="politicalLandscape">
          <el-input v-model="form.politicalLandscape" placeholder="请输入政治面貌" />
        </el-form-item>
        <el-form-item label="入党时间" prop="partyMembershipTime">
          <el-date-picker clearable
            v-model="form.partyMembershipTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择入党时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="籍贯" prop="hometown">
          <el-input v-model="form.hometown" placeholder="请输入籍贯" />
        </el-form-item>
        <el-form-item label="户口性质" prop="householdRegistrationNature">
          <el-input v-model="form.householdRegistrationNature" placeholder="请输入户口性质" />
        </el-form-item>
        <el-form-item label="常驻地址" prop="residentAddress">
          <el-input v-model="form.residentAddress" placeholder="请输入常驻地址" />
        </el-form-item>
        <el-form-item label="联系方式" prop="contactInformation">
          <el-input v-model="form.contactInformation" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="紧急联系人" prop="emergencyContact">
          <el-input v-model="form.emergencyContact" placeholder="请输入紧急联系人" />
        </el-form-item>
        <el-form-item label="紧急联系人电话" prop="emergencyContactPhone">
          <el-input v-model="form.emergencyContactPhone" placeholder="请输入紧急联系人电话" />
        </el-form-item>
        <el-form-item label="工作合同" prop="workContract">
          <el-input v-model="form.workContract" placeholder="请输入工作合同" />
        </el-form-item>
        <el-form-item label="住房公积金账号" prop="providentFundAccount">
          <el-input v-model="form.providentFundAccount" placeholder="请输入住房公积金账号" />
        </el-form-item>
        <el-form-item label="企业年金账号" prop="enterpriseAnnuityAccount">
          <el-input v-model="form.enterpriseAnnuityAccount" placeholder="请输入企业年金账号" />
        </el-form-item>
        <el-form-item label="是否上传照片" prop="uploadPhoto">
          <el-input v-model="form.uploadPhoto" placeholder="请输入是否上传照片" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRoster, getRoster, delRoster, addRoster, updateRoster,uploadFile } from "@/api/Enterprisemanagement/roster";
import axios from "axios";

export default {
  name: "Roster",
  inject: ["reload"],
  data() {
    return {
      activeNames: [], // 默认展开的折叠项的名字
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 员工花名单导入表格数据
      rosterList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        number: null,
        employeeId: null,
        name: null,
        gender: null,
        birthday: null,
        age: null,
        recordBirth: null,
        idCard: null,
        socialSecurityNumber: null,
        personnelScope: null,
        personnelScopeText: null,
        personnelSubScope: null,
        personnelSubScopeText: null,
        salaryAccountingScope: null,
        salaryAccountingScopeText: null,
        costCenter: null,
        costCenterText: null,
        rootTissueId: null,
        rootTissue: null,
        firstTissueId: null,
        firstTissue: null,
        secondTissueId: null,
        secondTissue: null,
        thirdTissueId: null,
        thirdTissue: null,
        fourthTissueId: null,
        fourthTissue: null,
        fifthTissueId: null,
        fifthTissue: null,
        sixthTissueId: null,
        sixthTissue: null,
        positionEthnicGroupId: null,
        positionEthnicGroup: null,
        jobCategoryId: null,
        jobCategory: null,
        job: null,
        jobTitle: null,
        technicalTitle: null,
        theHighestTechnicalTitl: null,
        technicalLevel: null,
        theHighestTechnicalLevel: null,
        rank: null,
        positionId: null,
        position: null,
        jobbTitle: null,
        salaryGrade: null,
        salaryyGrade: null,
        positionSalary: null,
        employeeGroup: null,
        employeeSubGroup: null,
        backboneLevel: null,
        personnelManagementClassification: null,
        theHighestEducationalLevel: null,
        theHighestEducationalSchool: null,
        theHighestEducationalMajor: null,
        theHighestDegree: null,
        theHighestDegreeSchool: null,
        theHighestDegreeMajor: null,
        firstEducationalLevel: null,
        firstHighestDegree: null,
        firstHighestDegreeSchool: null,
        firstHighestDegreeMajor: null,
        contractStartTime: null,
        contractEndTime: null,
        contractTerm: null,
        contractSigningUnit: null,
        signingFrequency: null,
        workingHours: null,
        joinedTime: null,
        startingTimeOfServiceLife: null,
        retirementDate: null,
        siLing: null,
        nation: null,
        politicalLandscape: null,
        partyMembershipTime: null,
        maritalStatus: null,
        hometown: null,
        householdRegistrationNature: null,
        residentAddress: null,
        contactInformation: null,
        emergencyContact: null,
        emergencyContactPhone: null,
        workContract: null,
        providentFundAccount: null,
        enterpriseAnnuityAccount: null,
        uploadPhoto: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        employeeId: [
          { required: true, message: "数量不能为空", trigger: "blur" }
        ],
      },
      //新增参数
      showDialog: false,
      progress: 0
    };
  },
  mounted(){

  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询员工花名单导入列表 */
    getList() {
      this.loading = true;
      listRoster(this.queryParams).then(response => {
        this.rosterList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        emprId: null,
        Number: null,
        employeeId: null,
        Name: null,
        Gender: null,
        Birthday: null,
        Age: null,
        recordBirth: null,
        idCard: null,
        socialSecurityNumber: null,
        personnelScope: null,
        personnelScopeText: null,
        personnelSubScope: null,
        personnelSubScopeText: null,
        salaryAccountingScope: null,
        salaryAccountingScopeText: null,
        costCenter: null,
        costCenterText: null,
        rootTissueId: null,
        rootTissue: null,
        firstTissueId: null,
        firstTissue: null,
        secondTissueId: null,
        secondTissue: null,
        thirdTissueId: null,
        thirdTissue: null,
        fourthTissueId: null,
        fourthTissue: null,
        fifthTissueId: null,
        fifthTissue: null,
        sixthTissueId: null,
        sixthTissue: null,
        positionEthnicGroupId: null,
        positionEthnicGroup: null,
        jobCategoryId: null,
        jobCategory: null,
        Job: null,
        jobTitle: null,
        technicalTitle: null,
        theHighestTechnicalTitl: null,
        technicalLevel: null,
        theHighestTechnicalLevel: null,
        Rank: null,
        positionId: null,
        Position: null,
        jobbTitle: null,
        salaryGrade: null,
        salaryyGrade: null,
        positionSalary: null,
        employeeGroup: null,
        employeeSubGroup: null,
        backboneLevel: null,
        personnelManagementClassification: null,
        theHighestEducationalLevel: null,
        theHighestEducationalSchool: null,
        theHighestEducationalMajor: null,
        theHighestDegree: null,
        theHighestDegreeSchool: null,
        theHighestDegreeMajor: null,
        firstEducationalLevel: null,
        firstHighestDegree: null,
        firstHighestDegreeSchool: null,
        firstHighestDegreeMajor: null,
        contractStartTime: null,
        contractEndTime: null,
        contractTerm: null,
        contractSigningUnit: null,
        signingFrequency: null,
        workingHours: null,
        joinedTime: null,
        startingTimeOfServiceLife: null,
        retirementDate: null,
        siLing: null,
        Nation: null,
        politicalLandscape: null,
        partyMembershipTime: null,
        maritalStatus: null,
        Hometown: null,
        householdRegistrationNature: null,
        residentAddress: null,
        contactInformation: null,
        emergencyContact: null,
        emergencyContactPhone: null,
        workContract: null,
        providentFundAccount: null,
        enterpriseAnnuityAccount: null,
        uploadPhoto: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.emprId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加员工花名单导入";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const emprId = row.emprId || this.ids
      getRoster(emprId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改员工花名单导入";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.emprId != null) {
            updateRoster(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRoster(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const emprIds = row.emprId || this.ids;
      this.$modal.confirm('是否确认删除员工花名单导入编号为"' + emprIds + '"的数据项？').then(function() {
        return delRoster(emprIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('Enterprisemanagement/roster/export', {
        ...this.queryParams
      }, `roster_${new Date().getTime()}.xlsx`)
    }
    ,

    // fileSend() {
    //   const formData = new FormData();
    //   const file = document.getElementById("inputFile").files[0]; // 获取文件对象
    //   console.log(file);
    //   formData.append("file", file);
    //   console.log("file====>",formData)
    //   axios({
    //     method: "post",
    //     // this $axios.post,
    //     url: "http://localhost:8080/Enterprisemanagement/roster/PRimport",
    //     // params:{
    //     //   userName: this.$store.state.user.name,
    //     // },
    //     headers: {
    //       "Content-Type": "multipart/form-data",
    //     },
    //     withCredentials: true,
    //     data: formData,
    //     onUploadProgress: (progressEvent) => {
    //       this.progress = Math.round(
    //         (progressEvent.loaded * 100) / progressEvent.total
    //       );
    //     },
    //   });
    //   // this.$message.success("上传成功");
    //
    //
    //   setTimeout(() => {
    //     this.showDialog = false; // 关闭上传面板
    //
    //     // location.reload(); // 调用此方法刷新页面数据
    //   }, 2000); // 2000毫秒后关闭
    //   this.getList();
    // },

    async fileSend() {
      try {
        const formData = new FormData();
        const file = document.getElementById("inputFile").files[0]; // 获取文件对象
        console.log(file);
        formData.append("file", file);
        console.log("file====>", formData);

        await uploadFile(formData); // 调用 uploadFile API

        // 上传成功后的处理
        this.$message.success("上传成功"); // 提示上传成功
        setTimeout(() => {
          this.showDialog = false; // 关闭上传面板
          // location.reload(); // 调用此方法刷新页面数据
        }, 1000); // 2000毫秒后关闭
        this.getList();
      } catch (error) {
        console.error('There was an error!', error);
      }
    },

    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    //导入excel，取消按钮绑定取消所选的xlsx
    resetFileInput() {
      this.$refs.fileInput.value = "";
    },
    //检查文件是否为excel
    checkFile() {
      const file = this.$refs.fileInput.files[0];
      const fileName = file.name;
      const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

      if (fileExt !== "xlsx" && fileExt !== "xlsm" && fileExt !== "XLSX") {
        this.$message.error("只能上传 Excel 文件！");
        this.$refs.fileInput.value = ""; // 清空文件选择框
      }
    }
  }
};
</script>
