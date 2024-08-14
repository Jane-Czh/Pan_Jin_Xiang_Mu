package com.ruoyi.Enterprisemanagement.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工花名单导入对象 Enterprise_Management_Personnel_roster
 *
 * @author ruoyi
 * @date 2024-08-09
 */
public class EnterpriseManagementPersonnelRoster extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long emprId;

    /** 序号 */
    @Excel(name = "序号")
    private Long Number;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String employeeId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String Name;

    /** 性别 */
    @Excel(name = "性别")
    private String Gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date Birthday;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long Age;

    /** 档案出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "档案出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordBirth;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 社保编码 */
    @Excel(name = "社保编码")
    private String socialSecurityNumber;

    /** 人事范围 */
    @Excel(name = "人事范围")
    private String personnelScope;

    /** 人事范围文本 */
    @Excel(name = "人事范围文本")
    private String personnelScopeText;

    /** 人事子范围 */
    @Excel(name = "人事子范围")
    private String personnelSubScope;

    /** 人事子范围文本 */
    @Excel(name = "人事子范围文本")
    private String personnelSubScopeText;

    /** 工资核算范围 */
    @Excel(name = "工资核算范围")
    private String salaryAccountingScope;

    /** 工资核算范围文本 */
    @Excel(name = "工资核算范围文本")
    private String salaryAccountingScopeText;

    /** 成本中心 */
    @Excel(name = "成本中心")
    private String costCenter;

    /** 成本中心文本 */
    @Excel(name = "成本中心文本")
    private String costCenterText;

    /** 根组织ID */
    @Excel(name = "根组织ID")
    private String rootTissueId;

    /** 根组织 */
    @Excel(name = "根组织")
    private String rootTissue;

    /** 一级组织ID */
    @Excel(name = "一级组织ID")
    private String firstTissueId;

    /** 一级组织 */
    @Excel(name = "一级组织")
    private String firstTissue;

    /** 二级组织ID */
    @Excel(name = "二级组织ID")
    private String secondTissueId;

    /** 二级组织 */
    @Excel(name = "二级组织")
    private String secondTissue;

    /** 三级组织ID */
    @Excel(name = "三级组织ID")
    private String thirdTissueId;

    /** 三级组织 */
    @Excel(name = "三级组织")
    private String thirdTissue;

    /** 四级组织ID */
    @Excel(name = "四级组织ID")
    private String fourthTissueId;

    /** 四级组织 */
    @Excel(name = "四级组织")
    private String fourthTissue;

    /** 五级组织ID */
    @Excel(name = "五级组织ID")
    private String fifthTissueId;

    /** 五级组织 */
    @Excel(name = "五级组织")
    private String fifthTissue;

    /** 六级组织ID */
    @Excel(name = "六级组织ID")
    private String sixthTissueId;

    /** 六级组织 */
    @Excel(name = "六级组织")
    private String sixthTissue;

    /** 职务族群ID */
    @Excel(name = "职务族群ID")
    private String positionEthnicGroupId;

    /** 职务族群 */
    @Excel(name = "职务族群")
    private String positionEthnicGroup;

    /** 职务类别ID */
    @Excel(name = "职务类别ID")
    private String jobCategoryId;

    /** 职务类别 */
    @Excel(name = "职务类别")
    private String jobCategory;

    /** 职务 */
    @Excel(name = "职务")
    private String Job;

    /** 职务名称 */
    @Excel(name = "职务名称")
    private String jobTitle;

    /** 技术职称 */
    @Excel(name = "技术职称")
    private String technicalTitle;

    /** 最高技术职称 */
    @Excel(name = "最高技术职称")
    private String theHighestTechnicalTitl;

    /** 技术等级 */
    @Excel(name = "技术等级")
    private String technicalLevel;

    /** 最高技术等级 */
    @Excel(name = "最高技术等级")
    private String theHighestTechnicalLevel;

    /** 职级 */
    @Excel(name = "职级")
    private String Rankked;

    /** 职位ID */
    @Excel(name = "职位ID")
    private String positionId;

    /** 职位 */
    @Excel(name = "职位")
    private String Positionno;

    /** 岗位名称 */
    @Excel(name = "岗位名称")
    private String jobbTitle;

    /** 薪等 */
    @Excel(name = "薪等")
    private String salaryGrade;

    /** 薪级 */
    @Excel(name = "薪级")
    private String salaryyGrade;

    /** 岗位工资 */
    @Excel(name = "岗位工资")
    private Long positionSalary;

    /** 员工组 */
    @Excel(name = "员工组")
    private String employeeGroup;

    /** 员工子组 */
    @Excel(name = "员工子组")
    private String employeeSubGroup;

    /** 骨干等级 */
    @Excel(name = "骨干等级")
    private String backboneLevel;

    /** 人事管理分类 */
    @Excel(name = "人事管理分类")
    private String personnelManagementClassification;

    /** 最高学历 */
    @Excel(name = "最高学历")
    private String theHighestEducationalLevel;

    /** 最高学历学校 */
    @Excel(name = "最高学历学校")
    private String theHighestEducationalSchool;

    /** 最高学历专业 */
    @Excel(name = "最高学历专业")
    private String theHighestEducationalMajor;

    /** 最高学位 */
    @Excel(name = "最高学位")
    private String theHighestDegree;

    /** 最高学位学校 */
    @Excel(name = "最高学位学校")
    private String theHighestDegreeSchool;

    /** 最高学位专业 */
    @Excel(name = "最高学位专业")
    private String theHighestDegreeMajor;

    /** 第一学历 */
    @Excel(name = "第一学历")
    private String firstEducationalLevel;

    /** 第一学位 */
    @Excel(name = "第一学位")
    private String firstHighestDegree;

    /** 第一学位学校 */
    @Excel(name = "第一学位学校")
    private String firstHighestDegreeSchool;

    /** 第一学位专业 */
    @Excel(name = "第一学位专业")
    private String firstHighestDegreeMajor;

    /** 合同开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractStartTime;

    /** 合同结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractEndTime;

    /** 合同年限 */
    @Excel(name = "合同年限")
    private String contractTerm;

    /** 合同签订单位 */
    @Excel(name = "合同签订单位")
    private String contractSigningUnit;

    /** 签订次数 */
    @Excel(name = "签订次数")
    private Long signingFrequency;

    /** 加入工作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "加入工作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date workingHours;

    /** 加入合力时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "加入合力时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date joinedTime;

    /** 司龄起算时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "司龄起算时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startingTimeOfServiceLife;

    /** 退休日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退休日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date retirementDate;

    /** 司龄 */
    @Excel(name = "司龄")
    private Long siLing;

    /** 民族 */
    @Excel(name = "民族")
    private String Nation;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String politicalLandscape;

    /** 入党时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入党时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date partyMembershipTime;

    /** 婚姻状况 */
    @Excel(name = "婚姻状况")
    private String maritalStatus;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String Hometown;

    /** 户口性质 */
    @Excel(name = "户口性质")
    private String householdRegistrationNature;

    /** 常驻地址 */
    @Excel(name = "常驻地址")
    private String residentAddress;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactInformation;

    /** 紧急联系人 */
    @Excel(name = "紧急联系人")
    private String emergencyContact;

    /** 紧急联系人电话 */
    @Excel(name = "紧急联系人电话")
    private String emergencyContactPhone;

    /** 工作合同 */
    @Excel(name = "工作合同")
    private String workContract;

    /** 住房公积金账号 */
    @Excel(name = "住房公积金账号")
    private String providentFundAccount;

    /** 企业年金账号 */
    @Excel(name = "企业年金账号")
    private String enterpriseAnnuityAccount;

    /** 是否上传照片 */
    @Excel(name = "是否上传照片")
    private String uploadPhoto;

    public void setEmprId(Long emprId)
    {
        this.emprId = emprId;
    }

    public Long getEmprId()
    {
        return emprId;
    }
    public void setNumber(Long Number)
    {
        this.Number = Number;
    }

    public Long getNumber()
    {
        return Number;
    }
    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }

    public String getEmployeeId()
    {
        return employeeId;
    }
    public void setName(String Name)
    {
        this.Name = Name;
    }

    public String getName()
    {
        return Name;
    }
    public void setGender(String Gender)
    {
        this.Gender = Gender;
    }

    public String getGender()
    {
        return Gender;
    }
    public void setBirthday(Date Birthday)
    {
        this.Birthday = Birthday;
    }

    public Date getBirthday()
    {
        return Birthday;
    }
    public void setAge(Long Age)
    {
        this.Age = Age;
    }

    public Long getAge()
    {
        return Age;
    }
    public void setRecordBirth(Date recordBirth)
    {
        this.recordBirth = recordBirth;
    }

    public Date getRecordBirth()
    {
        return recordBirth;
    }
    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public String getIdCard()
    {
        return idCard;
    }
    public void setSocialSecurityNumber(String socialSecurityNumber)
    {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getSocialSecurityNumber()
    {
        return socialSecurityNumber;
    }
    public void setPersonnelScope(String personnelScope)
    {
        this.personnelScope = personnelScope;
    }

    public String getPersonnelScope()
    {
        return personnelScope;
    }
    public void setPersonnelScopeText(String personnelScopeText)
    {
        this.personnelScopeText = personnelScopeText;
    }

    public String getPersonnelScopeText()
    {
        return personnelScopeText;
    }
    public void setPersonnelSubScope(String personnelSubScope)
    {
        this.personnelSubScope = personnelSubScope;
    }

    public String getPersonnelSubScope()
    {
        return personnelSubScope;
    }
    public void setPersonnelSubScopeText(String personnelSubScopeText)
    {
        this.personnelSubScopeText = personnelSubScopeText;
    }

    public String getPersonnelSubScopeText()
    {
        return personnelSubScopeText;
    }
    public void setSalaryAccountingScope(String salaryAccountingScope)
    {
        this.salaryAccountingScope = salaryAccountingScope;
    }

    public String getSalaryAccountingScope()
    {
        return salaryAccountingScope;
    }
    public void setSalaryAccountingScopeText(String salaryAccountingScopeText)
    {
        this.salaryAccountingScopeText = salaryAccountingScopeText;
    }

    public String getSalaryAccountingScopeText()
    {
        return salaryAccountingScopeText;
    }
    public void setCostCenter(String costCenter)
    {
        this.costCenter = costCenter;
    }

    public String getCostCenter()
    {
        return costCenter;
    }
    public void setCostCenterText(String costCenterText)
    {
        this.costCenterText = costCenterText;
    }

    public String getCostCenterText()
    {
        return costCenterText;
    }
    public void setRootTissueId(String rootTissueId)
    {
        this.rootTissueId = rootTissueId;
    }

    public String getRootTissueId()
    {
        return rootTissueId;
    }
    public void setRootTissue(String rootTissue)
    {
        this.rootTissue = rootTissue;
    }

    public String getRootTissue()
    {
        return rootTissue;
    }
    public void setFirstTissueId(String firstTissueId)
    {
        this.firstTissueId = firstTissueId;
    }

    public String getFirstTissueId()
    {
        return firstTissueId;
    }
    public void setFirstTissue(String firstTissue)
    {
        this.firstTissue = firstTissue;
    }

    public String getFirstTissue()
    {
        return firstTissue;
    }
    public void setSecondTissueId(String secondTissueId)
    {
        this.secondTissueId = secondTissueId;
    }

    public String getSecondTissueId()
    {
        return secondTissueId;
    }
    public void setSecondTissue(String secondTissue)
    {
        this.secondTissue = secondTissue;
    }

    public String getSecondTissue()
    {
        return secondTissue;
    }
    public void setThirdTissueId(String thirdTissueId)
    {
        this.thirdTissueId = thirdTissueId;
    }

    public String getThirdTissueId()
    {
        return thirdTissueId;
    }
    public void setThirdTissue(String thirdTissue)
    {
        this.thirdTissue = thirdTissue;
    }

    public String getThirdTissue()
    {
        return thirdTissue;
    }
    public void setFourthTissueId(String fourthTissueId)
    {
        this.fourthTissueId = fourthTissueId;
    }

    public String getFourthTissueId()
    {
        return fourthTissueId;
    }
    public void setFourthTissue(String fourthTissue)
    {
        this.fourthTissue = fourthTissue;
    }

    public String getFourthTissue()
    {
        return fourthTissue;
    }
    public void setFifthTissueId(String fifthTissueId)
    {
        this.fifthTissueId = fifthTissueId;
    }

    public String getFifthTissueId()
    {
        return fifthTissueId;
    }
    public void setFifthTissue(String fifthTissue)
    {
        this.fifthTissue = fifthTissue;
    }

    public String getFifthTissue()
    {
        return fifthTissue;
    }
    public void setSixthTissueId(String sixthTissueId)
    {
        this.sixthTissueId = sixthTissueId;
    }

    public String getSixthTissueId()
    {
        return sixthTissueId;
    }
    public void setSixthTissue(String sixthTissue)
    {
        this.sixthTissue = sixthTissue;
    }

    public String getSixthTissue()
    {
        return sixthTissue;
    }
    public void setPositionEthnicGroupId(String positionEthnicGroupId)
    {
        this.positionEthnicGroupId = positionEthnicGroupId;
    }

    public String getPositionEthnicGroupId()
    {
        return positionEthnicGroupId;
    }
    public void setPositionEthnicGroup(String positionEthnicGroup)
    {
        this.positionEthnicGroup = positionEthnicGroup;
    }

    public String getPositionEthnicGroup()
    {
        return positionEthnicGroup;
    }
    public void setJobCategoryId(String jobCategoryId)
    {
        this.jobCategoryId = jobCategoryId;
    }

    public String getJobCategoryId()
    {
        return jobCategoryId;
    }
    public void setJobCategory(String jobCategory)
    {
        this.jobCategory = jobCategory;
    }

    public String getJobCategory()
    {
        return jobCategory;
    }
    public void setJob(String Job)
    {
        this.Job = Job;
    }

    public String getJob()
    {
        return Job;
    }
    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle()
    {
        return jobTitle;
    }
    public void setTechnicalTitle(String technicalTitle)
    {
        this.technicalTitle = technicalTitle;
    }

    public String getTechnicalTitle()
    {
        return technicalTitle;
    }
    public void setTheHighestTechnicalTitl(String theHighestTechnicalTitl)
    {
        this.theHighestTechnicalTitl = theHighestTechnicalTitl;
    }

    public String getTheHighestTechnicalTitl()
    {
        return theHighestTechnicalTitl;
    }
    public void setTechnicalLevel(String technicalLevel)
    {
        this.technicalLevel = technicalLevel;
    }

    public String getTechnicalLevel()
    {
        return technicalLevel;
    }
    public void setTheHighestTechnicalLevel(String theHighestTechnicalLevel)
    {
        this.theHighestTechnicalLevel = theHighestTechnicalLevel;
    }

    public String getTheHighestTechnicalLevel()
    {
        return theHighestTechnicalLevel;
    }
    public void setRankked(String Rankked)
    {
        this.Rankked = Rankked;
    }

    public String getRankked()
    {
        return Rankked;
    }
    public void setPositionId(String positionId)
    {
        this.positionId = positionId;
    }

    public String getPositionId()
    {
        return positionId;
    }
    public void setPositionno(String Positionno)
    {
        this.Positionno = Positionno;
    }

    public String getPositionno()
    {
        return Positionno;
    }
    public void setJobbTitle(String jobbTitle)
    {
        this.jobbTitle = jobbTitle;
    }

    public String getJobbTitle()
    {
        return jobbTitle;
    }
    public void setSalaryGrade(String salaryGrade)
    {
        this.salaryGrade = salaryGrade;
    }

    public String getSalaryGrade()
    {
        return salaryGrade;
    }
    public void setSalaryyGrade(String salaryyGrade)
    {
        this.salaryyGrade = salaryyGrade;
    }

    public String getSalaryyGrade()
    {
        return salaryyGrade;
    }
    public void setPositionSalary(Long positionSalary)
    {
        this.positionSalary = positionSalary;
    }

    public Long getPositionSalary()
    {
        return positionSalary;
    }
    public void setEmployeeGroup(String employeeGroup)
    {
        this.employeeGroup = employeeGroup;
    }

    public String getEmployeeGroup()
    {
        return employeeGroup;
    }
    public void setEmployeeSubGroup(String employeeSubGroup)
    {
        this.employeeSubGroup = employeeSubGroup;
    }

    public String getEmployeeSubGroup()
    {
        return employeeSubGroup;
    }
    public void setBackboneLevel(String backboneLevel)
    {
        this.backboneLevel = backboneLevel;
    }

    public String getBackboneLevel()
    {
        return backboneLevel;
    }
    public void setPersonnelManagementClassification(String personnelManagementClassification)
    {
        this.personnelManagementClassification = personnelManagementClassification;
    }

    public String getPersonnelManagementClassification()
    {
        return personnelManagementClassification;
    }
    public void setTheHighestEducationalLevel(String theHighestEducationalLevel)
    {
        this.theHighestEducationalLevel = theHighestEducationalLevel;
    }

    public String getTheHighestEducationalLevel()
    {
        return theHighestEducationalLevel;
    }
    public void setTheHighestEducationalSchool(String theHighestEducationalSchool)
    {
        this.theHighestEducationalSchool = theHighestEducationalSchool;
    }

    public String getTheHighestEducationalSchool()
    {
        return theHighestEducationalSchool;
    }
    public void setTheHighestEducationalMajor(String theHighestEducationalMajor)
    {
        this.theHighestEducationalMajor = theHighestEducationalMajor;
    }

    public String getTheHighestEducationalMajor()
    {
        return theHighestEducationalMajor;
    }
    public void setTheHighestDegree(String theHighestDegree)
    {
        this.theHighestDegree = theHighestDegree;
    }

    public String getTheHighestDegree()
    {
        return theHighestDegree;
    }
    public void setTheHighestDegreeSchool(String theHighestDegreeSchool)
    {
        this.theHighestDegreeSchool = theHighestDegreeSchool;
    }

    public String getTheHighestDegreeSchool()
    {
        return theHighestDegreeSchool;
    }
    public void setTheHighestDegreeMajor(String theHighestDegreeMajor)
    {
        this.theHighestDegreeMajor = theHighestDegreeMajor;
    }

    public String getTheHighestDegreeMajor()
    {
        return theHighestDegreeMajor;
    }
    public void setFirstEducationalLevel(String firstEducationalLevel)
    {
        this.firstEducationalLevel = firstEducationalLevel;
    }

    public String getFirstEducationalLevel()
    {
        return firstEducationalLevel;
    }
    public void setFirstHighestDegree(String firstHighestDegree)
    {
        this.firstHighestDegree = firstHighestDegree;
    }

    public String getFirstHighestDegree()
    {
        return firstHighestDegree;
    }
    public void setFirstHighestDegreeSchool(String firstHighestDegreeSchool)
    {
        this.firstHighestDegreeSchool = firstHighestDegreeSchool;
    }

    public String getFirstHighestDegreeSchool()
    {
        return firstHighestDegreeSchool;
    }
    public void setFirstHighestDegreeMajor(String firstHighestDegreeMajor)
    {
        this.firstHighestDegreeMajor = firstHighestDegreeMajor;
    }

    public String getFirstHighestDegreeMajor()
    {
        return firstHighestDegreeMajor;
    }
    public void setContractStartTime(Date contractStartTime)
    {
        this.contractStartTime = contractStartTime;
    }

    public Date getContractStartTime()
    {
        return contractStartTime;
    }
    public void setContractEndTime(Date contractEndTime)
    {
        this.contractEndTime = contractEndTime;
    }

    public Date getContractEndTime()
    {
        return contractEndTime;
    }
    public void setContractTerm(String contractTerm)
    {
        this.contractTerm = contractTerm;
    }

    public String getContractTerm()
    {
        return contractTerm;
    }
    public void setContractSigningUnit(String contractSigningUnit)
    {
        this.contractSigningUnit = contractSigningUnit;
    }

    public String getContractSigningUnit()
    {
        return contractSigningUnit;
    }
    public void setSigningFrequency(Long signingFrequency)
    {
        this.signingFrequency = signingFrequency;
    }

    public Long getSigningFrequency()
    {
        return signingFrequency;
    }
    public void setWorkingHours(Date workingHours)
    {
        this.workingHours = workingHours;
    }

    public Date getWorkingHours()
    {
        return workingHours;
    }
    public void setJoinedTime(Date joinedTime)
    {
        this.joinedTime = joinedTime;
    }

    public Date getJoinedTime()
    {
        return joinedTime;
    }
    public void setStartingTimeOfServiceLife(Date startingTimeOfServiceLife)
    {
        this.startingTimeOfServiceLife = startingTimeOfServiceLife;
    }

    public Date getStartingTimeOfServiceLife()
    {
        return startingTimeOfServiceLife;
    }
    public void setRetirementDate(Date retirementDate)
    {
        this.retirementDate = retirementDate;
    }

    public Date getRetirementDate()
    {
        return retirementDate;
    }
    public void setSiLing(Long siLing)
    {
        this.siLing = siLing;
    }

    public Long getSiLing()
    {
        return siLing;
    }
    public void setNation(String Nation)
    {
        this.Nation = Nation;
    }

    public String getNation()
    {
        return Nation;
    }
    public void setPoliticalLandscape(String politicalLandscape)
    {
        this.politicalLandscape = politicalLandscape;
    }

    public String getPoliticalLandscape()
    {
        return politicalLandscape;
    }
    public void setPartyMembershipTime(Date partyMembershipTime)
    {
        this.partyMembershipTime = partyMembershipTime;
    }

    public Date getPartyMembershipTime()
    {
        return partyMembershipTime;
    }
    public void setMaritalStatus(String maritalStatus)
    {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus()
    {
        return maritalStatus;
    }
    public void setHometown(String Hometown)
    {
        this.Hometown = Hometown;
    }

    public String getHometown()
    {
        return Hometown;
    }
    public void setHouseholdRegistrationNature(String householdRegistrationNature)
    {
        this.householdRegistrationNature = householdRegistrationNature;
    }

    public String getHouseholdRegistrationNature()
    {
        return householdRegistrationNature;
    }
    public void setResidentAddress(String residentAddress)
    {
        this.residentAddress = residentAddress;
    }

    public String getResidentAddress()
    {
        return residentAddress;
    }
    public void setContactInformation(String contactInformation)
    {
        this.contactInformation = contactInformation;
    }

    public String getContactInformation()
    {
        return contactInformation;
    }
    public void setEmergencyContact(String emergencyContact)
    {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContact()
    {
        return emergencyContact;
    }
    public void setEmergencyContactPhone(String emergencyContactPhone)
    {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getEmergencyContactPhone()
    {
        return emergencyContactPhone;
    }
    public void setWorkContract(String workContract)
    {
        this.workContract = workContract;
    }

    public String getWorkContract()
    {
        return workContract;
    }
    public void setProvidentFundAccount(String providentFundAccount)
    {
        this.providentFundAccount = providentFundAccount;
    }

    public String getProvidentFundAccount()
    {
        return providentFundAccount;
    }
    public void setEnterpriseAnnuityAccount(String enterpriseAnnuityAccount)
    {
        this.enterpriseAnnuityAccount = enterpriseAnnuityAccount;
    }

    public String getEnterpriseAnnuityAccount()
    {
        return enterpriseAnnuityAccount;
    }
    public void setUploadPhoto(String uploadPhoto)
    {
        this.uploadPhoto = uploadPhoto;
    }

    public String getUploadPhoto()
    {
        return uploadPhoto;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("emprId", getEmprId())
                .append("Number", getNumber())
                .append("employeeId", getEmployeeId())
                .append("Name", getName())
                .append("Gender", getGender())
                .append("Birthday", getBirthday())
                .append("Age", getAge())
                .append("recordBirth", getRecordBirth())
                .append("idCard", getIdCard())
                .append("socialSecurityNumber", getSocialSecurityNumber())
                .append("personnelScope", getPersonnelScope())
                .append("personnelScopeText", getPersonnelScopeText())
                .append("personnelSubScope", getPersonnelSubScope())
                .append("personnelSubScopeText", getPersonnelSubScopeText())
                .append("salaryAccountingScope", getSalaryAccountingScope())
                .append("salaryAccountingScopeText", getSalaryAccountingScopeText())
                .append("costCenter", getCostCenter())
                .append("costCenterText", getCostCenterText())
                .append("rootTissueId", getRootTissueId())
                .append("rootTissue", getRootTissue())
                .append("firstTissueId", getFirstTissueId())
                .append("firstTissue", getFirstTissue())
                .append("secondTissueId", getSecondTissueId())
                .append("secondTissue", getSecondTissue())
                .append("thirdTissueId", getThirdTissueId())
                .append("thirdTissue", getThirdTissue())
                .append("fourthTissueId", getFourthTissueId())
                .append("fourthTissue", getFourthTissue())
                .append("fifthTissueId", getFifthTissueId())
                .append("fifthTissue", getFifthTissue())
                .append("sixthTissueId", getSixthTissueId())
                .append("sixthTissue", getSixthTissue())
                .append("positionEthnicGroupId", getPositionEthnicGroupId())
                .append("positionEthnicGroup", getPositionEthnicGroup())
                .append("jobCategoryId", getJobCategoryId())
                .append("jobCategory", getJobCategory())
                .append("Job", getJob())
                .append("jobTitle", getJobTitle())
                .append("technicalTitle", getTechnicalTitle())
                .append("theHighestTechnicalTitl", getTheHighestTechnicalTitl())
                .append("technicalLevel", getTechnicalLevel())
                .append("theHighestTechnicalLevel", getTheHighestTechnicalLevel())
                .append("Rankked", getRankked())
                .append("positionId", getPositionId())
                .append("Positionno", getPositionno())
                .append("jobbTitle", getJobbTitle())
                .append("salaryGrade", getSalaryGrade())
                .append("salaryyGrade", getSalaryyGrade())
                .append("positionSalary", getPositionSalary())
                .append("employeeGroup", getEmployeeGroup())
                .append("employeeSubGroup", getEmployeeSubGroup())
                .append("backboneLevel", getBackboneLevel())
                .append("personnelManagementClassification", getPersonnelManagementClassification())
                .append("theHighestEducationalLevel", getTheHighestEducationalLevel())
                .append("theHighestEducationalSchool", getTheHighestEducationalSchool())
                .append("theHighestEducationalMajor", getTheHighestEducationalMajor())
                .append("theHighestDegree", getTheHighestDegree())
                .append("theHighestDegreeSchool", getTheHighestDegreeSchool())
                .append("theHighestDegreeMajor", getTheHighestDegreeMajor())
                .append("firstEducationalLevel", getFirstEducationalLevel())
                .append("firstHighestDegree", getFirstHighestDegree())
                .append("firstHighestDegreeSchool", getFirstHighestDegreeSchool())
                .append("firstHighestDegreeMajor", getFirstHighestDegreeMajor())
                .append("contractStartTime", getContractStartTime())
                .append("contractEndTime", getContractEndTime())
                .append("contractTerm", getContractTerm())
                .append("contractSigningUnit", getContractSigningUnit())
                .append("signingFrequency", getSigningFrequency())
                .append("workingHours", getWorkingHours())
                .append("joinedTime", getJoinedTime())
                .append("startingTimeOfServiceLife", getStartingTimeOfServiceLife())
                .append("retirementDate", getRetirementDate())
                .append("siLing", getSiLing())
                .append("Nation", getNation())
                .append("politicalLandscape", getPoliticalLandscape())
                .append("partyMembershipTime", getPartyMembershipTime())
                .append("maritalStatus", getMaritalStatus())
                .append("Hometown", getHometown())
                .append("householdRegistrationNature", getHouseholdRegistrationNature())
                .append("residentAddress", getResidentAddress())
                .append("contactInformation", getContactInformation())
                .append("emergencyContact", getEmergencyContact())
                .append("emergencyContactPhone", getEmergencyContactPhone())
                .append("workContract", getWorkContract())
                .append("providentFundAccount", getProvidentFundAccount())
                .append("enterpriseAnnuityAccount", getEnterpriseAnnuityAccount())
                .append("uploadPhoto", getUploadPhoto())
                .toString();
    }
}