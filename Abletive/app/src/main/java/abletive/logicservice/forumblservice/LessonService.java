package abletive.logicservice.forumblservice;

import java.util.ArrayList;

import abletive.vo.LessonListVO;
import abletive.vo.LessonVO;

/**
 * 教程接口
 *
 * @author Alan
 */
public interface LessonService {
    /**
     * 获得教程列表
     *
     * @param page 第几页
     * @return 教程列表数组
     */
    ArrayList<LessonListVO> getLessonList(int page);

    /**
     * 获得教程内容
     *
     * @param lessonID 教程ID
     * @return 教程内容
     */
    LessonVO getLesson(String lessonID);
}
