package ${basepackage}.common.pojo;

//import com.cnpc.sys.common.DateConvertUtils;

//静态导入日期转换方法
@SuppressWarnings("serial")
public class BaseEntity implements java.io.Serializable {
      
      protected static final String DATE_FORMAT = "yyyy-MM-dd";
      
      protected static final String TIME_FORMAT = "HH:mm:ss";
      
      protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
      
      protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
      
      public static String date2String(java.util.Date date,String dateFormat) {
              return null;//DateConvertUtils.format(date,dateFormat);
      }
      
      public static <T extends java.util.Date> T string2Date(String dateString,String dateFormat,Class<T> targetResultType) {
              return null;//DateConvertUtils.parse(dateString,dateFormat,targetResultType);
      }
}
