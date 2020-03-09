package com.flyingideal.servlet.client.initializer;

import com.flyingideal.servlet.client.servlet.TestServletOne;
import com.flyingideal.servlet.client.servlet.TestServletTwo;
import com.flyingideal.servlet.initializer.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * {@link WebApplicationInitializer} 实现类
 *
 * 在 {@link com.flyingideal.servlet.initializer.ApplicationInitializer} 中会进行处理，加载这个类并调用其 onStartup() 方法
 * 这个方法中动态注册了 Servlet。
 *
 * 这个实现其实就相当于 Spring MVC 中的 WebApplicationInitializer（Spring 中接口也叫这个名） 实现类，只不过 Spring MVC 中注册的是 DispatcherServlet
 *
 * @author yanchao
 * @date 2020-03-07 00:13
 */
public class TestWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 注册 Servlet
        ServletRegistration.Dynamic testServlet = servletContext.addServlet("testServlet1", TestServletOne.class);
        testServlet.setLoadOnStartup(1);
        testServlet.addMapping("/test1");

        ServletRegistration.Dynamic testServlet2 = servletContext.addServlet("testServlet2", TestServletTwo.class);
        testServlet2.setLoadOnStartup(2);
        testServlet2.addMapping("/test2");
    }

}
