import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.beanutils.Converter;
/**
 * ClassName:DateConverter <br/>
 * Reason: 日期转换对象，使用该转换器，在ConvertUtils当中做一下注册，系统自动地帮助字符的日期表示转换为java.util.Date对象.
 * Date: 2017年5月6日 下午1:03:25 <br/>
 * @author lijian@12301.cc
 * @version V0.1
 * @since JDK 1.8
 * @see
 */
public class StringToDateConverter implements Converter {
    /**
     * 日期格式化对象.
     */
    private static SimpleDateFormat df = new SimpleDateFormat();
    /**
     * 模式集合.
     */
    private static Set<String> patterns = new HashSet<String>(4);
    // 注册一下日期的转换格式
    static {
        StringToDateConverter.patterns.add("yyyy-MM-dd");
        StringToDateConverter.patterns.add("yyyy-MM-dd HH:mm");
        StringToDateConverter.patterns.add("yyyy-MM-dd HH:mm:ss");
        StringToDateConverter.patterns.add("yyyy/MM/dd HH:mm:ss");
    }
    /**
     * 日期转换器.
     * 
     * @param type
     *            Class
     * @param value
     *            Object return Date Object.
     */
    @SuppressWarnings("rawtypes")
    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            Object dateObj = null;
            Iterator it = patterns.iterator();
            while (it.hasNext()) {
                try {
                    String pattern = (String) it.next();
                    df.applyPattern(pattern);
                    dateObj = df.parse((String) value);
                    break;
                } catch (ParseException ex) {
                    // do iterator continue
                }
            }
            return dateObj;
        }
        return null;
    }
}