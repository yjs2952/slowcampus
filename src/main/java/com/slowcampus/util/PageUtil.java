package com.slowcampus.util;

import com.slowcampus.dto.Pagination;
import lombok.extern.java.Log;
import org.springframework.util.StringUtils;

@Log
public class PageUtil {
    public static String getPageNavigation(Pagination pageInfo, String url, String linkString) {

        StringBuffer result = new StringBuffer();

        int page = pageInfo.getCurrentPageNo();			// 현재 페이지 번호
        int totalCount = pageInfo.getLastPageNo();		// 전체 페이지 수
        int pagePerPageSize = pageInfo.getPageSize();	// 페이지바에 보여줄 페이지 수
        int pageSize = (int) Math.ceil((double) page / pagePerPageSize);	// 현재 페이지 그룹 수
        int totalPageSize = 0;												// 전체 페이지 그룹 수

        if(pageSize > 0 && totalCount > 0) {
            //linkString = StringUtils.isEmpty(linkString) ? "?" : "?"+linkString+"&";

            int startPage = 1;

            if(totalCount != 0) {
                totalPageSize = (totalCount % pageSize > 0) ? 1 : 0;
                totalPageSize = totalPageSize + totalCount / pageSize;
                startPage = pageInfo.getFirstPageNoOnPageList();
            }

            int prevPage = (page - 1);
            int nextPage = (page + 1);

    		/*if (page == totalCount) 	{
    			nextPage = totalCount;
    		}
    		if (prevPage == 0){
    			prevPage = 1;
    		}*/

            int endPage = pageInfo.getLastPageNoOnPageList();

            result.append("<nav style=\"text-align:center\"><ul class=\"pagination\" >\n");

            log.info("page : " + page +", prevPage : " + prevPage + ", nextPage : " + nextPage +", totalCount : " + totalCount + ", totalPageSize : " + totalPageSize);

            //처음
            if(1 < page) {
                result.append("\n <li><a href=\"" + url+ "?currentPageNo=1&category="+linkString +"\" alt=\"처음페이지\"><span aria-hidden=\"true\">&lt;&lt;</span></a></li>\n");
            } else {
                result.append("\n <li><a href=\"#\" onclick=\"return false;\" alt=\"처음페이지\"><span aria-hidden=\"true\">&lt;&lt;</span></a></li>\n");
            }

            //이전페이지 그룹
            if(0 < prevPage) {
                result.append("\n <li><a href=\"" + url + "?currentPageNo=" + prevPage + "&category="+linkString+"\" alt=\"이전\">&lt;</a></li>\n");
            } else {
                result.append("\n <li><a href=\"#\" onclick=\"return false;\" alt=\"이전\">&lt;</a></li>\n");
            }

            for(int i=startPage; i <= endPage ; i++) {
                if(i == page) { // 현재페이지
                    result.append("\n <li class=\"active\"><a href=\"#\" >"+i+"<span class=\"sr-only\">(current)</span></a></li>\n");
                } else {
                    result.append("\n <li><a href=\""+url+"?currentPageNo="+i+ "&category="+linkString+"\">"+i+"</a></li>\n");
                }
            }

            //다음페이지 그룹
            if(nextPage <= totalCount) {
                result.append("\n <li><a href=\"" + url + "?currentPageNo=" + nextPage + "&category="+linkString +"\" alt=\"다음\">&gt;</a></li>\n");
            } else {
                result.append("\n <li><a href=\"#\" onclick=\"return false;\" alt=\"다음\">&gt;</a></li>\n");
            }

            //마지막 페이지
            if(page < totalCount) {
                result.append("\n <li><a href=\"" + url + "?currentPageNo=" + totalPageSize + "&category=\""+linkString +"\" alt=\"마지막페이지\"><span aria-hidden=\"true\">&gt;&gt;</span></a></li>\n");
            } else {
                result.append("\n <li><a href=\"#\" onclick=\"return false;\" alt=\"마지막페이지\"><span aria-hidden=\"true\">&gt;&gt;</span></a></li>\n");
            }

            result.append("</ul></nav>");
        }

        return result.toString();
    }
}
