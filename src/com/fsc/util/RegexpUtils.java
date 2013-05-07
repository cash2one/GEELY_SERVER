package com.fsc.util;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.Perl5Substitution;
import org.apache.oro.text.regex.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:正则表达式工具类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class RegexpUtils {
    private static RegexpUtils instance = null;
    private static String key = "key";

    /**
     * 保存四级对应分隔符
     */
    static final Set<String> SEPARATOR_SET = new TreeSet<String>();

    /**
     * 存放各种正规表达式（以key -> value的形式）
     */
    public static Map<String, String> regexpHash = new HashMap<String, String>();

    /**
     * 匹配图象
     *
     *
     * 格式: /相对路径/文件名.后缀 (后缀为gif,dmp,png)
     *
     * 匹配 : /forum/head_icon/admini2005111_ff.gif 或 admini2005111.dmp
     *
     *
     * 不匹配: c:/admins4512.gif
     *
     */
    public static final String icon_regexp = "^(/{0,1}\\w){1,}\\.(gif|dmp|png|jpg)$|^\\w{1,}\\.(gif|dmp|png|jpg)$";

    /**
     * 匹配email地址
     *
     *
     * 格式: XXX@XXX.XXX.XX
     *
     * 匹配 : foo@bar.com 或 foobar@foobar.com.au
     *
     *
     * 不匹配: foo@bar 或 $$$@bar.com
     *
     */
    public static final String email_regexp = "(?:\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3}$)";

    /**
     * 匹配匹配并提取url
     *
     *
     * 格式: XXXX://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX
     *
     * 匹配 : http://www.suncer.com 或news://www
     *
     *
     * 提取(MatchResult matchResult=matcher.getMatch()): matchResult.group(0)=
     * http://www.suncer.com:8080/index.html?login=true matchResult.group(1) =
     * http matchResult.group(2) = www.suncer.com matchResult.group(3) = :8080
     * matchResult.group(4) = /index.html?login=true
     *
     * 不匹配: c:\window
     *
     */
    public static final String url_regexp = "(\\w+)://([^/:]+)(:\\d*)?([^#\\s]*)";

    /**
     * 匹配并提取http
     *
     *
     * 格式: http://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX 或 ftp://XXX.XXX.XXX 或
     * https://XXX
     *
     * 匹配 : http://www.suncer.com:8080/index.html?login=true
     *
     *
     * 提取(MatchResult matchResult=matcher.getMatch()): matchResult.group(0)=
     * http://www.suncer.com:8080/index.html?login=true matchResult.group(1) =
     * http matchResult.group(2) = www.suncer.com matchResult.group(3) = :8080
     * matchResult.group(4) = /index.html?login=true
     *
     * 不匹配: news://www
     *
     */
    public static final String http_regexp = "(http|https|ftp)://([^/:]+)(:\\d*)?([^#\\s]*)";

    /**
     * 匹配日期
     *
     *
     * 格式(首位不为0): XXXX-XX-XX 或 XXXX XX XX 或 XXXX-X-X
     *
     *
     * 范围:1900--2099
     *
     *
     * 匹配 : 2005-04-04
     *
     *
     * 不匹配: 01-01-01
     *
     */
    public static final String date_regexp = "^((((19){1}|(20){1})d{2})|d{2})[-\\s]{1}[01]{1}d{1}[-\\s]{1}[0-3]{1}d{1}$";

    /**
     * 匹配电话
     *
     *
     * 格式为: 0XXX-XXXXXX(10-13位首位必须为0) 或0XXX XXXXXXX(10-13位首位必须为0) 或
     *
     * (0XXX)XXXXXXXX(11-14位首位必须为0) 或 XXXXXXXX(6-8位首位不为0) 或
     * XXXXXXXXXXX(11位首位不为0)
     *
     *
     * 匹配 : 0371-123456 或 (0371)1234567 或 (0371)12345678 或 010-123456 或
     * 010-12345678 或 12345678912
     *
     *
     * 不匹配: 1111-134355 或 0123456789
     *
     */
    public static final String phone_regexp = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";

    /**
     * 匹配身份证
     *
     *
     * 格式为: XXXXXXXXXX(10位) 或 XXXXXXXXXXXXX(13位) 或 XXXXXXXXXXXXXXX(15位) 或
     * XXXXXXXXXXXXXXXXXX(18位)
     *
     *
     * 匹配 : 0123456789123
     *
     *
     * 不匹配: 0123456
     *
     */
    public static final String ID_card_regexp = "^\\d{10}|\\d{13}|\\d{15}|\\d{18}$";

    /**
     * 匹配邮编代码
     *
     *
     * 格式为: XXXXXX(6位)
     *
     *
     * 匹配 : 012345
     *
     *
     * 不匹配: 0123456
     *
     */
    public static final String ZIP_regexp = "^[0-9]{6}$";

    /**
     * 不包括特殊字符的匹配 (字符串中不包括符号 数学次方号^ 单引号' 双引号" 分号; 逗号, 帽号: 数学减号- 右尖括号> 左尖括号<
     * 反斜杠\ 即空格,制表符,回车符等 )
     *
     *
     * 格式为: x 或 一个一上的字符
     *
     *
     * 匹配 : 012345
     *
     *
     * 不匹配: 0123456
     *
     */
    public static final String non_special_char_regexp = "^[^'\"\\;,:-<>\\s].+$";

    /**
     * 匹配非负整数（正整数 + 0)
     */
    public static final String non_negative_integers_regexp = "^\\d+$";

    /**
     * 匹配不包括零的非负整数（正整数 > 0)
     */
    public static final String non_zero_negative_integers_regexp = "^[1-9]+\\d*$";

    /**
     *
     * 匹配正整数
     *
     */
    public static final String positive_integer_regexp = "^[0-9]*[1-9][0-9]*$";

    /**
     *
     * 匹配非正整数（负整数 + 0）
     *
     */
    public static final String non_positive_integers_regexp = "^((-\\d+)|(0+))$";

    /**
     *
     * 匹配负整数
     *
     */
    public static final String negative_integers_regexp = "^-[0-9]*[1-9][0-9]*$";

    /**
     *
     * 匹配整数
     *
     */
    public static final String integer_regexp = "^-?\\d+$";

    /**
     *
     * 匹配非负浮点数（正浮点数 + 0）
     *
     */
    public static final String non_negative_rational_numbers_regexp = "^\\d+(\\.\\d+)?$";

    /**
     *
     * 匹配正浮点数
     *
     */
    public static final String positive_rational_numbers_regexp = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";

    /**
     *
     * 匹配非正浮点数（负浮点数 + 0）
     *
     */
    public static final String non_positive_rational_numbers_regexp = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";

    /**
     *
     * 匹配负浮点数
     *
     */
    public static final String negative_rational_numbers_regexp = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";

    /**
     *
     * 匹配浮点数
     *
     */
    public static final String rational_numbers_regexp = "^(-?\\d+)(\\.\\d+)?$";

    /**
     *
     * 匹配由26个英文字母组成的字符串
     *
     */
    public static final String letter_regexp = "^[A-Za-z]+$";

    /**
     *
     * 匹配由26个英文字母的大写组成的字符串
     *
     */
    public static final String upward_letter_regexp = "^[A-Z]+$";

    /**
     *
     * 匹配由26个英文字母的小写组成的字符串
     *
     */
    public static final String lower_letter_regexp = "^[a-z]+$";

    /**
     *
     * 匹配由数字和26个英文字母组成的字符串
     *
     */
    public static final String letter_number_regexp = "^[A-Za-z0-9]+$";

    /**
     *
     * 匹配由数字、26个英文字母或者下划线组成的字符串
     *
     */
    public static final String letter_number_underline_regexp = "^\\w+$";

    {
        SEPARATOR_SET.add("(");
        SEPARATOR_SET.add(")");
        SEPARATOR_SET.add("[");
        SEPARATOR_SET.add("]");
        SEPARATOR_SET.add("{");
        SEPARATOR_SET.add("}");
        SEPARATOR_SET.add("<");
        SEPARATOR_SET.add(">");
    }

    private RegexpUtils() {
    }

    public static RegexpUtils getInstance() {
        if (instance == null) {
            synchronized (key) {
                if (instance == null) {
                    instance = new RegexpUtils();
                }
            }
        }

        return instance;
    }

    /**
     * 添加正规表达式 (以key->value的形式存储)
     *
     * @param regexpName
     *            该正规表达式名称 `
     * @param regexp
     *            该正规表达式内容
     */
    public void putRegexpHash(String regexpName, String regexp) {
        regexpHash.put(regexpName, regexp);
    }

    /**
     * 得到正规表达式内容 (通过key名提取出value[正规表达式内容])
     *
     * @param regexpName
     *            正规表达式名称
     *
     * @return 正规表达式内容
     */
    public String getRegexpHash(String regexpName) {
        if (regexpHash.get(regexpName) != null) {
            return ((String) regexpHash.get(regexpName));
        } else {
            System.out.println("在regexpHash中没有此正规表达式");

            return "";
        }
    }

    /**
     * 清除正规表达式存放单元
     */
    public void clearRegexpHash() {
        regexpHash.clear();

        return;
    }

    /**
     * 大小写敏感的正规表达式批配
     *
     * @param source
     *            批配的源字符串
     *
     * @param regexp
     *            批配的正规表达式
     *
     * @return 如果源字符串符合要求返回真,否则返回假 如:
     *         Regexp.isHardRegexpValidate("ygj@suncer.com.cn",email_regexp) 返回真
     */
    public static boolean isHardRegexpValidate(String source, String regexp) {
        try {
            // 用于定义正规表达式对象模板类型
            PatternCompiler compiler = new Perl5Compiler();

            // 正规表达式比较批配对象
            PatternMatcher matcher = new Perl5Matcher();

            // 实例大小大小写敏感的正规表达式模板
            Pattern hardPattern = compiler.compile(regexp);

            // 返回批配结果
            return matcher.contains(source, hardPattern);
        } catch (MalformedPatternException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 不区分大小写的正规表达式批配
     *
     * @param source
     *            批配的源字符串
     *
     * @param regexp
     *            批配的正规表达式
     *
     * @return 如果源字符串符合要求返回真,否则返回假
     *         Regexp.isHardRegexpValidate("ygj@suncer.com.cn",email_regexp) 返回真
     */
    public static boolean isSoftRegexpValidate(String source, String regexp) {
        try {
            // 用于定义正规表达式对象模板类型
            PatternCompiler compiler = new Perl5Compiler();

            // 正规表达式比较批配对象
            PatternMatcher matcher = new Perl5Matcher();

            // 实例不区分大小写的正规表达式模板
            Pattern softPattern = compiler.compile(regexp,
                    Perl5Compiler.CASE_INSENSITIVE_MASK);

            // 返回批配验证值
            return matcher.contains(source, softPattern);
        } catch (MalformedPatternException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 返回许要的批配结果集(大小写敏感的正规表达式批配)
     *
     * @param source
     *            批配的源字符串
     *
     * @param regexp
     *            批配的正规表达式
     *
     * @return 如果源字符串符合要求返回许要的批配结果集,否则返回 "null"关键字 <br>
     *         如 : MatchResult matchResult =
     *         getHardRegexpMatchResult("http://www.suncer.com:8080/index.html?login=true",Regexp.url_regexp)
     *         得到取出的匹配值为 matchResult.group(0)=
     *         http://www.suncer.com:8080/index.html?login=true
     *         matchResult.group(1) = http matchResult.group(2) = www.suncer.com
     *         matchResult.group(3) = :8080 matchResult.group(4) =
     *         /index.html?login=true
     *
     */
    public static MatchResult getHardRegexpMatchResult(String source,
        String regexp) {
        try {
            // 用于定义正规表达式对象模板类型
            PatternCompiler compiler = new Perl5Compiler();

            // 正规表达式比较批配对象
            PatternMatcher matcher = new Perl5Matcher();

            // 实例大小大小写敏感的正规表达式模板
            Pattern hardPattern = compiler.compile(regexp);

            // 如果批配结果正确,返回取出的批配结果
            if (matcher.contains(source, hardPattern)) {
                // MatchResult result=matcher.getMatch();
                return matcher.getMatch();
            }
        } catch (MalformedPatternException e) {
            e.printStackTrace();
        }

        return null;
    }

    /** */
    /**
     * 返回许要的批配结果集(不区分大小写的正规表达式批配)
     *
     * @param source
     *            批配的源字符串
     *
     * @param regexp
     *            批配的正规表达式
     *
     * @return 如果源字符串符合要求返回许要的批配结果集,否则返回 "null"关键字 如 : MatchResult matchResult =
     *         getHardRegexpMatchResult("http://www.suncer.com:8080/index.html?login=true",Regexp.url_regexp)
     *         得到取出的匹配值为 matchResult.group(0)=
     *         http://www.suncer.com:8080/index.html?login=true
     *         matchResult.group(1) = http matchResult.group(2) = www.suncer.com
     *         matchResult.group(3) = :8080 matchResult.group(4) =
     *         /index.html?login=true
     */
    public static MatchResult getSoftRegexpMatchResult(String source,
        String regexp) {
        try {
            // 用于定义正规表达式对象模板类型
            PatternCompiler compiler = new Perl5Compiler();

            // 正规表达式比较批配对象
            PatternMatcher matcher = new Perl5Matcher();

            // 实例不区分大小写的正规表达式模板
            Pattern softPattern = compiler.compile(regexp,
                    Perl5Compiler.CASE_INSENSITIVE_MASK);

            // 如果批配结果正确,返回取出的批配结果
            if (matcher.contains(source, softPattern)) {
                // MatchResult result=matcher.getMatch();
                return matcher.getMatch();
            }
        } catch (MalformedPatternException e) {
            e.printStackTrace();
        }

        return null;
    }

    /** */
    /**
     * 返回许要的批配结果集(大小写敏感的正规表达式批配)
     *
     * @param source
     *            批配的源字符串
     *
     * @param regexp
     *            批配的正规表达式
     *
     * @return 如果源字符串符合要求返回许要的批配结果集,{@link #getHardRegexpMatchResult(String, String) 使用方法参见getHardRegexpMatchResult(String, String)}
     */
    public static ArrayList<String> getHardRegexpArray(String source,
        String regexp) {
        List<String> tempList = new ArrayList<String>();

        try {
            // 用于定义正规表达式对象模板类型
            PatternCompiler compiler = new Perl5Compiler();

            // 实例大小大小写敏感的正规表达式模板
            Pattern hardPattern = compiler.compile(regexp);

            // 正规表达式比较批配对象
            PatternMatcher matcher = new Perl5Matcher();

            // 如果批配结果正确,返回取出的批配结果
            if (matcher.contains(source, hardPattern)) {
                // 存放取出值的正规表达式比较批配结果对象
                MatchResult matchResult = matcher.getMatch();

                //System.out.println("size:"+matchResult.length());
                for (int i = 0; i < matchResult.length(); i++) {
                    //System.out.println(matchResult.group(i));
                    if (matchResult.group(i) != null) {
                        tempList.add(matchResult.group(i));
                    }
                }
            }
        } catch (MalformedPatternException e) {
            e.printStackTrace();
        }

        return (ArrayList<String>) tempList;
    }

    /** */
    /**
     * 返回许要的批配结果集(不区分大小写的正规表达式批配)
     *
     * @param source
     *            批配的源字符串
     *
     * @param regexp
     *            批配的正规表达式
     *
     * @return 如果源字符串符合要求返回许要的批配结果集{@link #getHardRegexpMatchResult(String, String) 使用方法参见getHardRegexpMatchResult(String, String)}
     */
    public static ArrayList<String> getSoftRegexpArray(String source,
        String regexp) {
        List<String> tempList = new ArrayList<String>();

        try {
            // 用于定义正规表达式对象模板类型
            PatternCompiler compiler = new Perl5Compiler();

            // 正规表达式比较批配对象
            PatternMatcher matcher = new Perl5Matcher();

            // 实例不区分大小写的正规表达式模板
            Pattern softPattern = compiler.compile(regexp,
                    Perl5Compiler.CASE_INSENSITIVE_MASK);

            if (matcher.contains(source, softPattern)) {
                // 如果批配结果正确,返回取出的批配结果
                MatchResult matchResult = matcher.getMatch();

                for (int i = 0;
                        (i < matchResult.length()) &&
                        (matchResult.group(i) != null); i++) {
                    tempList.add(i, matchResult.group(i));
                }
            }
        } catch (MalformedPatternException e) {
            e.printStackTrace();
        }

        return (ArrayList<String>) tempList;
    }

    /**
     * <pre>
     * 得到指定分隔符中间的字符串的集合,
     *              说明: 1.分隔符为 ()，[]，{}，&lt;&gt; 中的一组
     *                        2.得到的集合以 ASCII 的升序排列
     *              如       String originStr=&quot;((([a]+[b])/[c])-24)+[d]&quot;;
     *              则          getStrBetweenSeparator(originStr,&quot;[&quot;,&quot;]&quot;));
     *                           返回结果集合有四个元素  [a, b, c, d],
     *                          以 ASCII 的升序排列
     * </pre>
     *
     * @param originStr
     *            要提取的源字符串
     * @param leftSeparator
     *            左边的分隔符
     * @param rightSeparator
     *            右边的分隔符
     * @return 指定分隔符中间的字符串的集合
     */
    public static Set<String> getBetweenSeparatorStr(final String originStr,
        final char leftSeparator, final char rightSeparator) {
        Set<String> variableSet = new TreeSet<String>();

        if ((null == originStr) || "".equals(originStr)) {
            return variableSet;
        }

        String[] sTempArray = originStr.split("(\"+leftSeparator+\")");

        for (int i = 1; i < sTempArray.length; i++) {
            int endPosition = sTempArray[i].indexOf(rightSeparator);
            String sTempVariable = sTempArray[i].substring(0, endPosition);
            variableSet.add(sTempVariable);
        }

        return variableSet;
    }

    public static String replaceAll(String source, String dest, String pattern)
        throws Exception {
        // 用于定义正规表达式对象模板类型
        PatternCompiler compiler = new Perl5Compiler();

        // 正规表达式模板
        Pattern hardPattern = compiler.compile(pattern);

        // 正规表达式比较批配对象
        PatternMatcher matcher = new Perl5Matcher();
        Perl5Substitution substitution = new Perl5Substitution(dest);
        String result = Util.substitute(matcher, hardPattern, substitution,
                source, 1);

        return result;
    }

    public static List<String> getAllMatch(String source, String pattern)
        throws Exception {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        List<String> result = new ArrayList<String>();

        while (m.find()) {
            result.add(m.group()); //匹配第1项
                                   //System.out.println(m.group());
        }

        //System.out.println(result.size());
        return result;
    }
    
    public static void main(String[] args) throws Exception{
		System.out.println(RegexpUtils.getHardRegexpArray("【来自小明家长（15675854445）的回复短信】短信内容我孩子最近可好？","1\\d{10,10}"));
	}
}
