package cn.edu.lnu.dto;

import java.util.Date;

/**
 * 问题模板，用来验证及传输
 * @Author zyp created by 2018/12/11
 */
public class Module {

    private int id; //序号
    private String title; //标题
    private String content; //内容
    private Date date; //日期
    private String keywords; //关键字
    private int fid; //门类编号
    private int mid; //学科编号
    private String author; //填表人姓名

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "id:" + id +",title:" + title;
    }
}
