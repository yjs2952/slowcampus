package com.slowcampus.dto;

public class Pagination {
    /**
     * Required Fields
     * - 이 필드들은 페이징 계산을 위해 반드시 입력되어야 하는 필드 값들이다.
     *
     * currentPageNo : 현재 페이지 번호
     * recordCountPerPage : 한 페이지당 게시되는 게시물 건 수
     * pageSize : 페이지 리스트에 게시되는 페이지 건수,
     * totalRecordCount : 전체 게시물 건 수.
     */
    private int currentPageNo;
    private int recordCountPerPage;
    private int pageSize;
    private int totalRecordCount;
    private String searchOption;
    private String keyword;

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Pagination() {
        if(currentPageNo < 1) {
            currentPageNo = 1;
        }

        recordCountPerPage = 10;
        pageSize = 10;
    }

    public Pagination(int recordCountPerPage, int pageSize){
        if(currentPageNo < 1) {
            currentPageNo = 1;
        }

        this.recordCountPerPage = recordCountPerPage;
        this.pageSize = pageSize;
    }

    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }

    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    /**
     * Not Required Fields
     * - 이 필드들은 Required Fields 값을 바탕으로 계산해서 정해지는 필드 값이다.
     *
     * totalPageCount: 페이지 개수
     * firstPageNoOnPageList : 페이지 리스트의 첫 페이지 번호
     * lastPageNoOnPageList : 페이지 리스트의 마지막 페이지 번호
     * firstRecordIndex : 페이징 SQL의 조건절에 사용되는 시작 rownum.
     */
    private int totalPageCount;
    private int firstPageNoOnPageList;
    private int lastPageNoOnPageList;
    private int firstRecordIndex;

    public int getTotalPageCount() {
        totalPageCount = ((getTotalRecordCount()-1)/getRecordCountPerPage()) + 1;
        return totalPageCount;
    }

    public int getFirstPageNo(){
        return 1;
    }

    public int getLastPageNo(){
        return getTotalPageCount();
    }

    public int getFirstPageNoOnPageList() {
        firstPageNoOnPageList = ((getCurrentPageNo()-1)/getPageSize())*getPageSize() + 1;
        return firstPageNoOnPageList;
    }

    public int getLastPageNoOnPageList() {
        lastPageNoOnPageList = getFirstPageNoOnPageList() + getPageSize() - 1;
        if(lastPageNoOnPageList > getTotalPageCount()){
            lastPageNoOnPageList = getTotalPageCount();
        }
        return lastPageNoOnPageList;
    }

    public int getFirstRecordIndex() {
        firstRecordIndex = (getCurrentPageNo() - 1) * getRecordCountPerPage();
        return firstRecordIndex;
    }

    @Override
    public String toString() {
        return "PaginationInfo [currentPageNo=" + currentPageNo + ", recordCountPerPage=" + recordCountPerPage
                + ", pageSize=" + pageSize + ", totalRecordCount=" + totalRecordCount + ", searchOption=" + searchOption
                + ", keyword=" + keyword + ", totalPageCount=" + totalPageCount + ", firstPageNoOnPageList="
                + firstPageNoOnPageList + ", lastPageNoOnPageList=" + lastPageNoOnPageList + ", firstRecordIndex="
                + firstRecordIndex + "]";
    }
}
