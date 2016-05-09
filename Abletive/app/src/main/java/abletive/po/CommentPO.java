package abletive.po;

import abletive.vo.CommentListVO;

/**
 * 评论区的信息
 * Created by Alan on 2016/5/4.
 */
public class CommentPO {
    int id;
    String name;
    String date;
    String parent;
    String content;
    AuthorPO author;
    String agent;

    public CommentPO(int id, String name, String date, String parent, String content, AuthorPO author, String agent) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.parent = parent;
        this.content = content;
        this.author = author;
        this.agent = agent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public AuthorPO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorPO author) {
        this.author = author;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentListVO toCommentListVO() {
        //暂时设置parentName为空字符串
        if(author!=null){
            return new CommentListVO(author.avatar, author.name, parent, "",
                    content, agent, date, id + "", author.membership);
        }else{
            return new CommentListVO("", "", parent, "",
                    content, agent, date, id + "", "");
        }
    }
}
