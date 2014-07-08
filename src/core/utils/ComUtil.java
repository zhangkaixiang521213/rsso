package core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.utils.id.UUIDHexGenerator;

/**
 *<p>Title: 常用工具类</p>
 * <p>Description: 常用工具类</p>
 * <p>Copyriht: Copyright (c) 2012</p>
 * <p>Company: XXXX Co., Ltd</p>
 * @author wangxz
 * @version 1.0 Date: 2012-11-27 15:42
 * (non-javadoc)
 */
public class ComUtil {
	private static final Log log = LogFactory.getLog(ComUtil.class);
    private static final String DATE_STYLE = "yyyy-MM-dd";
    private static final String TIME_STYLE = "yyyy-MM-dd HH:mm:ss";
    
    public static String EMPTY = "";
    public static String SEMICOLON = ";";
    
    /**
     * 取得不重复的32位字符串
     * @return
     */
    public static String getId() {
        UUIDHexGenerator uuid = new UUIDHexGenerator();
        return (String) uuid.generate();
    }
    /**
     * null字符串转化为""
     * @param s 输入字符串
     * @return 输出字符串
     */
    public static String replaceNull2Space(String s) {
        if (s == null)
            return "";
        if (s.trim().toUpperCase().equals("NULL"))
            return "";
        return s.trim();
    }
    /**
     * 日期转字符串
     * @param dt 日期类型
     * @return yyyy-MM-dd类型
     */
    public static String Date2String(Date dt) {
        if (dt == null || dt.equals(""))
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STYLE);
        try {
            return sdf.format(dt);
        } catch (Exception ex) {
            log.error("==ComUtil:Date2String==：" + ex);
            return "";
        }
    }
    /**
     * 时间转字符串
     * @param dt 日期类型
     * @return yyyy-MM-dd类型
     */
    public static String Time2String(Date dt) {
        if (dt == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_STYLE);
        try {
            return sdf.format(dt);
        } catch (Exception ex) {
            log.error("==ComUtil:Time2String==：" + ex);
            return "";
        }
    }
    
    
    
    /**
     * 字符串转时间
     * @param date String类型
     * @return Date
     */
    public static Date String2Time(String time) {
    	if("".equals(replaceNull2Space(time))){
    		return null;
    	}
        SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE);
        Date d = null;
        if (time != null && !time.equals("")) {
            try {
                d = format.parse(time);
            } catch (Exception ex) {
                log.error("==ComUtil:String2Time==：" + ex);
            }
        }
        return d;
    }
    
    
    /**
     * 字符串转时间
     * @param date String类型
     * @return Date
     */
    public static Date String2Date(String date) {
    	if("".equals(replaceNull2Space(date))){
    		return null;
    	}
        SimpleDateFormat format = new SimpleDateFormat(DATE_STYLE);
        Date d = null;
        if (date != null && !date.equals("")) {
            try {
                d = format.parse(date);
            } catch (Exception ex) {
                log.error("==ComUtil:String2Date==：" + ex);
            }
        }
        return d;
    }
    
	public static String formatDateString(String oldDate) {
		String newDate = "";
		if (oldDate.length() >= 8 && oldDate.length() <= 10) {
			String beforeDate = oldDate;
			String s = ""; // 当前的分割符是什么
			String st = ""; // 存上一次查到的分割符
			int nos = 0; // 共有几个分割符
			char c = ' '; // 分割符是什么符号
			// 以下for循环的作用是查看该日期字符串是以何种字符分割的。
			try {
				for (int i = 0; i < oldDate.length(); i++) {
					if (!java.lang.Character.isDigit(oldDate.charAt(i))) {
						c = oldDate.charAt(i);
						s = oldDate.substring(i, i + 1);
						if ("".equals(st)) {
							st = s;
							nos++;
						} else {
							if (st.equals(s)) {
								nos++;
							} else {
								s = "";
								nos = 0;
								break;
							}
						}
					}
				}
				if (!"".equals(s) && nos == 2) {
					String new_yyyy = ""; // 输入的年
					String new_mm = ""; // 输入的月
					String new_dd = ""; // 输入的日
					String[] new_date = new String[6];
					String[] mm = { "JANUARY", "FEBRUARY", "MARCH", "APRIL",
							"MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER",
							"OCTOBER", "NOVEMBER", "DECEMBER" };
					// 正常输入2008-6-9时，系统会将其变成09/06/2008这种情况。
					if (oldDate.split("/").length == 3
							&& oldDate.split("/")[2].length() == 4
							&& Integer.parseInt(oldDate.split("/")[1]
									.toString()) < 13
							&& Integer.parseInt(oldDate.split("/")[0]
									.toString()) < 32 && oldDate.length() == 10) {
						String[] tmp = oldDate.split("/");
						new_yyyy = tmp[2];
						new_mm = tmp[1];
						new_dd = tmp[0];
						java.util.Date x = new java.util.Date();
						String[] datestr = (x.toString()).split("\\s");
						for (int i = 0; i < datestr.length; i++) {
							if (datestr[i].length() == 8) {
								datestr[i] = "00:00:00";
							}
							new_date[i] = datestr[i];
						}
					} else {
						oldDate = oldDate.replace(c, '-'); // 无论原来是以什么做为分割符，都用“-”来替换
						beforeDate = oldDate;
						String[] xxx = oldDate.split("-");
						if (xxx[0].length() == 1) {
							xxx[0] = "0" + xxx[0];
						} else if (xxx[0].length() == 2 || xxx[0].length() == 4) {
							xxx[0] = xxx[0];
						} else {
							newDate = "errorDate";
						}
						if (xxx[1].length() == 1) {
							xxx[1] = "0" + xxx[1];
						} else if (xxx[1].length() == 2) {
							xxx[1] = xxx[1];
						} else {
							newDate = "errorDate";
						}
						if (xxx[2].length() == 1) {
							xxx[2] = "0" + xxx[2];
						} else if (xxx[2].length() == 2 || xxx[2].length() == 4) {
							xxx[2] = xxx[2];
						} else {
							newDate = "errorDate";
						}
						if ("".equals(newDate)) {
							oldDate = xxx[0] + "-" + xxx[1] + "-" + xxx[2];
							java.util.Date x = new java.util.Date();
							String[] datestr = (x.toString()).split("\\s");
							for (int i = 0; i < datestr.length; i++) {
								if (datestr[i].length() == 8) {
									datestr[i] = "00:00:00";
								}
								new_date[i] = datestr[i];
							}
							String str[] = oldDate.split("-");
							if (str.length == 3 && str[0].length() == 4) { // 年月日的情况
								new_yyyy = str[0];
								new_mm = str[1];
								new_dd = str[2];
							} else if (str.length == 3 && str[2].length() == 4) { // 其它情况-->默认为月日年
								new_yyyy = str[2];
								new_mm = str[0];
								new_dd = str[1];
								java.util.Date dat = String2Date(new_yyyy + "-"+ new_mm + "-" + new_dd);
								String change =  Date2String(dat);
								int resy = oldDate.indexOf("-"
										+ change.substring(0, 4));
								int resm = oldDate.indexOf(change.substring(5,
										8));
								int resd = oldDate.indexOf(change.substring(7,
										10)
										+ "-");
								if (resy == -1 || resm == -1 || resd == -1
										|| Integer.parseInt(new_mm) > 12) { // 如果以上情况不满足-->日月年
									new_mm = str[1];
									new_dd = str[0];
								}
								if (change.substring(5, 7).equals(new_mm)
										|| change.substring(8, 10).equals(
												new_dd)
										|| change.substring(8, 10).equals(
												new_mm)
										|| change.substring(5, 7)
												.equals(new_mm)) {
									java.util.Date chkdate = String2Date(new_yyyy + "-"+ new_mm + "-" + new_dd);
									String chk =  Date2String(dat);
									if (!chk.equals(new_yyyy + "-" + new_mm
											+ "-" + new_dd)) {
										newDate = "errorDate";
									}
								} else {
									newDate = "errorDate";
								}
							} else {
								newDate = "errorDate";
							}
						}
					}
					if ("".equals(newDate)) {
						try {
							new_mm = mm[Integer.parseInt(new_mm) - 1]
									.substring(0, new_date[1].length());
							String oldstr = new_date[0] + " " + new_mm + " "
									+ (Integer.parseInt(new_dd)) + "" + " "
									+ new_date[3] + " " + new_date[4] + " "
									+ new_yyyy;
							java.util.Date date = new java.util.Date(oldstr);
							java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat();
							formater.applyPattern("yyyy-MM-dd");
							newDate = formater.format(date);
						} catch (Exception ex) {
							newDate = "errorDate";
						}
					}
				} else if (oldDate.length() == 8 && "".equals(s)) {
					int digitnum = 0;
					for (int i = 0; i < oldDate.length(); i++) {
						if (java.lang.Character.isDigit(oldDate.charAt(i))) {
							digitnum++;
						}
					}
					if (digitnum == oldDate.length()) {
						String new_yyyy = oldDate.substring(0, 4);
						String new_mm = oldDate.substring(4, 6);
						String new_dd = oldDate.substring(6, 8);
						newDate = new_yyyy + "-" + new_mm + "-" + new_dd;
					} else {
						newDate = "errorDate";
					}
				} else {
					newDate = "errorDate";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			newDate = "errorDate";
		}
		return newDate;
	
	}
    
    /**
     * 字符串中的汉字转化成16进制的ascii编码
     * @param s
     * @return
     */
    public static String str2HexAscii(String s) {
		String str = "";
		if (s == null || s.trim().equals(""))
			return str;
		for (int i = 0; i < s.length(); i++) {
			int ch;
			String s4;
			byte[] bytes = (String.valueOf(s.charAt(i))).getBytes();
			if (bytes.length == 1) { //英文字符不转化

				s4 = String.valueOf(s.charAt(i));
			} else {
				ch = (int) s.charAt(i);
				s4 = "\\u" + Integer.toHexString(ch);
			}
			str = str + s4;
		}
		return str;
	}  
    
	public static String getRequestPath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return basePath;
	}
	
	
	
	
	public static int getPageSize(String pageSize){
		return Integer.parseInt((pageSize == null || pageSize == "0") ? "20":pageSize); 
	}

	
	public static int getPageNo(String pageNo){
		return Integer.parseInt((pageNo == null || pageNo == "0") ? "1":pageNo); 
	}
}
