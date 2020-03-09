package com.flyingideal.servlet.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 *
 * 这个是模拟 Spring 提供的一个 WebApplicationInitializer。
 * Spring 提供这个接口是为了给各个模块统一使用。 例如 Spring MVC 中用来注册 DispatcherServlet，Spring Security 用来注册 DelegatingFilterProxy
 *
 * @author yanchao
 * @date 2020-03-06 23:56
 */
public interface WebApplicationInitializer {

    /**
     * Servlet 容器启动的时候会调用 {@link javax.servlet.ServletContainerInitializer#onStartup(Set, ServletContext)} 方法
     * 在这个方法中会对 {@link WebApplicationInitializer} 实现类进行加载，并调用其实现类的 该方法。
     * @param servletContext    {@link ServletContext} 从 Servlet 3.0 起增强了 ServletContext 功能，可以动态注册 Servlet, Filter, Listener
     * @throws ServletException
     */
    void onStartup(ServletContext servletContext) throws ServletException;
}
