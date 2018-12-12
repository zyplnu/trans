package cn.edu.lnu.vo;

import java.util.Date;

/**
 * 问题实体，数据库真正需要的问题
 * @AUthor zyp
 */
public class Problem {

    private int id; //序号
    private String title; //标题
    private String content; //内容
    private String file; //文件
    private Date date; //日期
    private String keywords; //关键字
    private int fid; //门类编号
    private int mid; //学科编号
    private String tel; //电话
    private String desc; //描述
    private int checked; //是否通过检查？ 0：未检查  1：检查
    private int clickNo; //点击量
    private int likeNo; //喜欢数
    private int solutionNo; //解决方案数

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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getClickNo() {
        return clickNo;
    }

    public void setClickNo(int clickNo) {
        this.clickNo = clickNo;
    }

    public int getLikeNo() {
        return likeNo;
    }

    public void setLikeNo(int likeNo) {
        this.likeNo = likeNo;
    }

    public int getSolutionNo() {
        return solutionNo;
    }

    public void setSolutionNo(int solutionNo) {
        this.solutionNo = solutionNo;
    }
}
