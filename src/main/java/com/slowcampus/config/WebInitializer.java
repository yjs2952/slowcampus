package com.slowcampus.config;

import org.springframework.context.ApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {ApplicationConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebMvcContextConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }


    // 파일 업로드에서  submit 눌렀을때 파일이름을 로그로 나오게 했는데
    // 그 한글이 깨졌을때 써주는 부분. Filter 추가.
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");

        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();



        return new Filter[]{encodingFilter,hiddenHttpMethodFilter};
    }


}
