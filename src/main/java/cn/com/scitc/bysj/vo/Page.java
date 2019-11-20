package cn.com.scitc.bysj.vo;

import java.util.List;

public class Page {
    private int offset;
    private int size;
    private int totalPage;
    private long count;
    private int current;
    private List<?> list;


    @Override
    public String toString() {
        return "Page{" +
                "offset=" + offset +
                ", size=" + size +
                ", totalPage=" + totalPage +
                ", count=" + count +
                ", current=" + current +
                ", list=" + list +
                '}';
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = (this.current - 1) * this.size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
        this.totalPage = (int)count / this.size + ((count % this.size) > 0 ? 1:0);
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
