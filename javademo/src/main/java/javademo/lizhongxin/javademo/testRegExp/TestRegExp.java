package javademo.lizhongxin.javademo.testRegExp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class TestRegExp {

  public static void main(String[] arg) {
//    String input = "啊";
////    boolean b = checkSpecialChar(input);
////    System.out.println("checkSpecialChar:" + b);
//    System.out.println("SPECIAL_CHARACTER:" + isMatch(USER_NAME, input));
//    System.out.println("SPECIAL_CHARACTER:" + isMatch(U, input));
  }

  public static boolean checkSpecialChar(String str) throws PatternSyntaxException {
    // 清除掉所有特殊字符
    String regEx = "[\\n\\s*\\r`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(str);
    return m.matches();
  }

  //  public static boolean checkSpecialCehar(String str) throws PatternSyntaxException {
//    // 清除掉所有特殊字符
//    String regEx =  "[\\n\\s*\\r`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]";
//    Pattern p = Pattern.compile(regEx);
//    Matcher m = p.matcher(str);
//    return m.matches();
//  }
  public static final String SPECIAL_CHARACTER =
      "[\\n\\s*\\r`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]";
  public static final String USER_NAME =
      "^[A-Za-z0-9\\u4e00-\\u9fa5]";
  public static String U =
      "^[\\u4e00-\\u9fa5]";

  public static boolean isMatch(final String regex, final CharSequence input) {
    return input != null && input.length() > 0 && Pattern.matches(regex, input);
  }
}
