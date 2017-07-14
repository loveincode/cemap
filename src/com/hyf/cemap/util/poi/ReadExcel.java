package com.hyf.cemap.util.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hyf.cemap.bean.po.GraduateInformation;
import com.hyf.cemap.bean.po.base.AdministrativeClass;
import com.hyf.cemap.bean.po.base.College;
import com.hyf.cemap.bean.po.base.Profession;
import com.hyf.cemap.service.GraduateInformationServiceImpl;
import com.hyf.cemap.service.base.AdministrativeClassServiceImpl;
import com.hyf.cemap.service.base.CollegeServiceImpl;
import com.hyf.cemap.service.base.ProfessionServiceImpl;
import com.hyf.cemap.util.common.CEMAPConstants;
import com.hyf.cemap.util.common.FileDetele;

/**
 * 读取excel数据 保存到数据库
*  @author  huyifan
*  @ClassName  ReadExcel  
*  @date  Mar 28, 2017 10:51:47 AM
 */
@Component
public class ReadExcel {
    
    @Autowired
    private CollegeServiceImpl collegeService;
    
    @Autowired
    private ProfessionServiceImpl professionService;
    
    @Autowired
    private AdministrativeClassServiceImpl administrativeClassService;
    
    @Autowired
    private GraduateInformationServiceImpl graduateInformationService;
    
    
    /**
     * 将导入的excel 所有封装到 List<GraduateInformation>中
     * 然后再调回Controller Service 保存 List<GraduateInformation>
     * @param filename
     * @return
     * @throws IOException
     */
    public List<Object> read(String filename) throws IOException {
        System.out.println("服务器中文件名"+filename);
        InputStream is = new FileInputStream(CEMAPConstants.fileDir+filename);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        
        List<Object> list = new ArrayList<Object>();
        int effictivecount = 0;
        int correctcount = 0;
        String errornews = "";
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            System.out.println("poi读取行数"+hssfSheet.getPhysicalNumberOfRows());
            for (int rowNum = 1; rowNum <= hssfSheet.getPhysicalNumberOfRows(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if(hssfRow == null){
                    System.out.println("第"+(rowNum+1)+"行为空 break");
                    errornews +="第"+(rowNum+1)+"行为空,跳出执行</br>";
                    break;
                }
                if (hssfRow != null) {
                    
                    HSSFCell studentId = hssfRow.getCell(0);
                    HSSFCell name = hssfRow.getCell(1);
                    HSSFCell sex = hssfRow.getCell(2);
                    HSSFCell birthday = hssfRow.getCell(3);
                    
                    HSSFCell collegeName = hssfRow.getCell(4);
                    HSSFCell professionName = hssfRow.getCell(5);
                    HSSFCell administrativeClassName = hssfRow.getCell(6);
                    
                    HSSFCell politicalStatus = hssfRow.getCell(7);
                    HSSFCell nativePlace = hssfRow.getCell(8);
                    HSSFCell nation = hssfRow.getCell(9);
                    HSSFCell identificationNumber = hssfRow.getCell(10);
                    HSSFCell accountproperty = hssfRow.getCell(11);
                    HSSFCell accountLocation = hssfRow.getCell(12);
                    HSSFCell originPlace = hssfRow.getCell(13);
                    HSSFCell educationBackground = hssfRow.getCell(14);
                    HSSFCell phone = hssfRow.getCell(15);
                    HSSFCell email = hssfRow.getCell(16);
                    HSSFCell homeAddress = hssfRow.getCell(17);
                    HSSFCell homePhone = hssfRow.getCell(18);
                    HSSFCell graduationSession = hssfRow.getCell(19);
                    
                    //第一行为空
                    if(getValue(studentId)==null || getValue(studentId).equals("")){
                        System.out.println("第"+(rowNum+1)+"行"+"第一列为空 break");
                        errornews +="第"+(rowNum+1)+"行"+"第一列为空,跳出执行</br>";
                        break;
                    }
                    effictivecount++;
                    
                    System.out.println(
                            "第"+(rowNum+1)+"行 信息："+
                            getValue(studentId) + getValue(name) + 
                            getValue(sex) + getValue(birthday) + 
                            getValue(collegeName) + getValue(professionName) +
                            getValue(administrativeClassName)+getValue(politicalStatus)+
                            getValue(nativePlace)+getValue(nation)+getValue(nation)+
                            getValue(identificationNumber)+getValue(accountproperty)+
                            getValue(accountLocation)+getValue(originPlace)+ 
                            getValue(educationBackground)+getValue(phone)+
                            getValue(email)+getValue(homeAddress)+
                            getValue(homePhone)+getValue(graduationSession));
                    
                    GraduateInformation graduateInformation = new GraduateInformation();
                    
                    
                    try {
                        // 检查学号是否重复
                        //学号存在 跳过 不存
                        if(graduateInformationService.getByStudentId(getValue(studentId))!=null){
                            System.out.println("第"+(rowNum+1)+"行"+"学号存在 跳过该条信息");
                            errornews +="第"+(rowNum+1)+"行"+"学号已存在，跳过该条信息</br>";
                            continue;
                        }
                        
                        // 学院 取数据库中查询
                        College college = collegeService.getByName(getValue(collegeName));
                        if(college!=null){
                            graduateInformation.setCollegeId(college.getId());
                        }
                        else{
                            System.out.println("第"+(rowNum+1)+"行"+"学院不存在，continue");
                            errornews +="第"+(rowNum+1)+"行"+"学院不存在，跳过该条信息</br>";
                            continue;
                        }
                        Profession profession =professionService.getByName(getValue(professionName));
                        if(profession!=null){
                            graduateInformation.setProfessionId(profession.getId());
                            
                        }
                        else{
                            System.out.println("第"+(rowNum+1)+"行"+"专业不存在，continue");
                            errornews +="第"+(rowNum+1)+"行"+"专业不存在，跳过该条信息</br>";
                            continue;
                        }
                        AdministrativeClass administrativeClass = administrativeClassService.getByName(getValue(administrativeClassName));
                        if(administrativeClass!=null){
                            graduateInformation.setAdministrativeClassId(administrativeClass.getId());
                        }
                        else{
                            System.out.println("第"+(rowNum+1)+"行"+"班级不存在，continue");
                            errornews +="第"+(rowNum+1)+"行"+"班级不存在，跳过该条信息</br>";
                            continue;
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    // 学号 姓名 性别 生日
                    graduateInformation.setStudentId(getValue(studentId));
                    graduateInformation.setName(getValue(name));
                    graduateInformation.setSex(getValue(sex));
                    graduateInformation.setBirthday(getValue(birthday));
                    graduateInformation.setPoliticalStatus(getValue(politicalStatus));
                    graduateInformation.setNativePlace(getValue(nativePlace));
                    graduateInformation.setNation(getValue(nation));
                    graduateInformation.setIdentificationNumber(getValue(identificationNumber));
                    graduateInformation.setAccountproperty(getValue(accountproperty));
                    graduateInformation.setAccountLocation(getValue(accountLocation));
                    graduateInformation.setOriginPlace(getValue(originPlace));
                    graduateInformation.setEducationBackground(getValue(educationBackground));
                    graduateInformation.setPhone(getValue(phone));
                    graduateInformation.setEmail(getValue(email));
                    graduateInformation.setHomeAddress(getValue(homeAddress));
                    graduateInformation.setHomePhone(getValue(homePhone));
                    graduateInformation.setGraduationSession(getValue(graduationSession));
                    correctcount++;
                    list.add(graduateInformation);
                }
            }
        }
        list.add(errornews);
        System.out.println("有效行数" +effictivecount);
        System.out.println("信息无误" +correctcount);
        
        FileDetele fileDetele = new FileDetele();
        if(fileDetele.delele(CEMAPConstants.fileDir+filename)){
            System.out.println("删除成功");
        }
        return list;
    }
    
    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if(hssfCell!=null){
            if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
                  // 返回布尔类型
                return String.valueOf(hssfCell.getBooleanCellValue());
            } 
            else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
                if(HSSFDateUtil.isCellDateFormatted(hssfCell)){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format(HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue()));
                }
                //返回数值的值
                DecimalFormat df = new DecimalFormat("0");
                String strCell = df.format(hssfCell.getNumericCellValue());
                return String.valueOf(strCell);
            } 
            else {
                //返回字符串类型的值
                return String.valueOf(hssfCell.getStringCellValue());
            }
        }
        else{
           return null;
        }
    }
}
