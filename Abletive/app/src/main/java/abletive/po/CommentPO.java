package abletive.po;

import abletive.po.AuthorPO;

/**
 * 评论区的信息
 * Created by Alan on 2016/5/4.
 */
public class CommentPO {
    String id;
    String name;
    String date;
    String parent;
    AuthorPO author;
    String agent;

    public CommentPO(String id, String name, String date, String parent, AuthorPO author, String agent) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.parent = parent;
        this.author = author;
        this.agent = agent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
