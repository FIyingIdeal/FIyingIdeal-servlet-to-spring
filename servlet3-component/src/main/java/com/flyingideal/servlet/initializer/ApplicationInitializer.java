package com.flyingideal.servlet.initializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * {@link ServletContainerInitializer} 从 Servlet 3.0 开始提供。用它来做 web.xml 的替代，
 * 因为 Servlet 3.0 对 ServletContext 做了功能扩展，允许动态添加 Servlet, Filter, Listener 等，
 * 而这个方法参数刚好包含了 ServletContext.
 *
 * ServletContainerInitializer 需要配合 {@link HandlesTypes} 注解一起使用，在容器启动的时候，
 * 会将 {@link HandlesTypes} 指定的类及其实现类都加载进来，并赋值为 onStartup 方法的第一个参数。
 *
 * Spring 中有相应的实现： org.springframework.web.SpringServletContainerInitializer
 *
 * @see <a href=https://www.logicbig.com/tutorials/java-ee-tutorial/java-servlet/servlet-container-initializer-example.html>Understanding ServletContainerInitializer with an Example Project</a>
 *
 * @author yanchao
 * @date 2020-03-06 23:51
 */
@HandlesTypes(WebApplicationInitializer.class)
public class ApplicationInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        if (set == null || set.isEmpty()) {
            return;
        }

        for (Class clazz : set) {
            // 直接跳过接口，抽象类 和 非 WebApplicationInitializer 实现类（@HandlesTypes 指定的）
            if (clazz.isInterface()
                    || Modifier.isAbstract(clazz.getModifiers())
                    || !WebApplicationInitializer.class.isAssignableFrom(clazz)) {
                continue;
            }
            try {
                WebApplicationInitializer webApplicationInitializer = (WebApplicationInitializer) clazz.newInstance();
                System.out.println("=========ApplicationInitializer loading WebApplicationInitializer : "
                        + webApplicationInitializer.getClass().getName());
                webApplicationInitializer.onStartup(servletContext);
            } catch (Exception e) {
                throw new ServletException("Failed to instantiate WebApplicationInitializer class", e);
            }
        }

    }
}
