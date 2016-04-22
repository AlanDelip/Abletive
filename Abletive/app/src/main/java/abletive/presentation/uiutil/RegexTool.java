package abletive.presentation.uiutil;

/**
 * 正则表达工具
 * Created by Alan on 2016/4/23.
 */
public class RegexTool {
    public static boolean containCharacters(String content) {
        String regChina = "/^[a-zA-Z\\u4e00-\\u9fa5]+$/";
        if (content.matches(regChina)) {
            return true;
        } else {
            return false;
        }
    }
}
