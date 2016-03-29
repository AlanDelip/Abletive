package abletive.presentation.controller;

/**
 * <strong>[单例模式]</strong>
 * 界面跳转控制器<br>
 *
 * @author Alan
 */
public class UIController {
    public static UIController uiController = new UIController();

    private UIController() {
    }

    /**
     * 获取控制器
     *
     * @return 控制器单例
     */
    public static UIController newInstance() {
        return uiController;
    }

}
