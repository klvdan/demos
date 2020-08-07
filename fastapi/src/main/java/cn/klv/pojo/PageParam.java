package cn.klv.pojo;

public class PageParam {
    private int beginLine;
    private Integer pageSize = 5;
    private Integer currentPage = 0;

    public int getBeginLine() {
        return beginLine;
    }

    public void setBeginLine(int beginLine) {
        this.beginLine = beginLine;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "beginLine=" + beginLine +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}';
    }
}
