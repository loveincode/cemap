package com.hyf.cemap.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * 
*  @author  huyifan
*  @ClassName  DateUtil  
*  @date  Mar 24, 2017 1:55:07 PM
 */
public class DateUtil {

    private static final String PATTERN_YYYYMMDD_1 = "yyyy-MM-dd";

    private static Logger log = Logger.getLogger(DateUtil.class);

    /**
     * 字符串转date
     * @param strDate
     * @return
     */
    public static Date stringToDate(String strDate){
        if(StringUtils.isNotEmpty(strDate)){
            try {
                return new SimpleDateFormat(PATTERN_YYYYMMDD_1).parse(strDate);
            } catch (ParseException e) {
                log.error("DateUtil->stringToDate error! " + e.getCause().getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 取当前日期的后一天
     * @param date
     * @return
     */
    public static Date  nextDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }


    public static void st(int a, String... state){
        System.out.println(a);
        System.out.println(state.length);
    }

    public static void main(String[] args) {
//        st(1);
        String inStr = "aaaa,aaa,aa,";
        inStr = inStr.substring(0, inStr.length() -1);
        System.out.println(nextDate(new Date()));
        System.out.println(new Date().compareTo(nextDate(new Date())));

//        Map map = new HashMap<String, String>();
//        map.put("111", "111");
//        map.put("UserId","user");
//        System.out.println(map);
//        String m = "{&quot;BELONG_COMPANY&quot;:&quot;浦东分公司&quot;,&quot;BELONG_AREA&quot;:&quot;待定区域&quot;,&quot;STREET_STATION&quot;:&quot;&quot;,&quot;COMMUNITY&quot;:&quot;&quot;,&quot;IS_NGB&quot;:&quot;全部&quot;,&quot;BB_SIL&quot;:&quot;全部&quot;,&quot;HD_SIL&quot;:&quot;全部&quot;,&quot;SIL_TARGET&quot;:&quot;全部&quot;,&quot;TOTAL_VALUE_LEVEL&quot;:&quot;全部&quot;,&quot;EXPIRE_DATE_START&quot;:&quot;&quot;,&quot;EXPIRE_DATE_END&quot;:&quot;&quot;,&quot;TYPE_DESC&quot;:&quot;全部&quot;,&quot;SERVICETYPE&quot;:&quot;全部&quot;,&quot;BROADBAND_BAND_START&quot;:&quot;&quot;,&quot;BROADBAND_BAND_END&quot;:&quot;&quot;,&quot;IS_OWNER&quot;:&quot;全部&quot;,&quot;BELONG_TYPE&quot;:&quot;全部&quot;,&quot;VOD_EVER&quot;:&quot;全部&quot;,&quot;REP_EVER&quot;:&quot;全部&quot;,&quot;PACKAGENAME&quot;:&quot;&quot;,&quot;VALID_DATE_START&quot;:&quot;&quot;,&quot;VALID_DATE_END&quot;:&quot;&quot;,&quot;FEE_YEAR_START&quot;:&quot;&quot;,&quot;FEE_YEAR_END&quot;:&quot;&quot;}";
//        String ss = StringEscapeUtils.unescapeHtml3("");
//        Map<String, String> map = (Map<String, String>) JSON.parseObject(ss, Map.class);
//        System.out.println(m);

    }


}
