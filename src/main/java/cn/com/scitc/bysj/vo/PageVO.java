package cn.com.scitc.bysj.vo;

public class PageVO {

    private Integer keywords = 0;
    private Integer pageNum = 0;
    private Integer pageSize = 10;

    public PageVO() {
    }

    public Integer getKeywords() {
        return keywords;
    }

    public void setKeywords(Integer keywords) {
        this.keywords = keywords;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageVO(Integer keywords, Integer pageNum, Integer pageSize) {
        this.keywords = keywords;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
