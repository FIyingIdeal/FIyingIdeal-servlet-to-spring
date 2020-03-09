# servlet3 模块介绍

servlet3 模块主要用来实践 Servlet 3.0 以来的新特性。

## ServletContainerInitializer

### ServletContainerInitializer 简单介绍
`ServletContainerInitializer` 是 Servlet 3.0 新增的接口，其通过编码的方式来替代 web.xml，因此该 web 项目中并没有 web.xml 文件。其实现类会在 Servlet 容器启动的时候通过 SPI 方式加载。

### ServletContainerInitializer 实现代码结构说明
由于 SPI 加载指定类路径下的类文件，所以 `ServletContainerInitializer` 的实现类是在该项目的依赖模块 servlet3-component 中提供，但 servlet3 模块中提供 `WebApplicationInitializer` 接口（`HandlesTypes` 指定的 ServletContainerInitializer 处理类型）的具体实现 -- `com.flyingideal.servlet.client.initializer.TestWebApplicationInitializer`。

在 `TestWebApplicationInitialization` 中利用 Servlet 3.0 对 `ServletContext` 的增强特性，动态注册了两个 servlet（Servlet 3.0 之前需要在 web.xml 中配置）：
- TestServletOne : /test1
- TestServletTwo : /test2

### ServletContainerInitializer 测试运行

**特别说明：测试时必须使用支持 Servlet 3.0 特性的 Servlet 容器**

- 方式一：配置本地 Tomcat，将项目部署到本地 Tomcat 下运行；
- 方式二：由于使用 maven 配置了 Tomcat 相关 plugin，所以可以直接运行 `mvn tomcat7:run` 来运行项目；

### ServletContainerInitializer 测试效果

- 项目启动时

项目启动时会输出如下内容（在代码中硬编码输出的）：

> =========ApplicationInitializer loading WebApplicationInitializer : com.flyingideal.servlet.client.initializer.TestWebApplicationInitializer

表示 ServletContainerInitializer 实现类通过 SPI 方式被加载了。如果没有相关输出，请检查相关实现类是否存在或是否被防止到了对应的目录。

- 项目运行时

项目正常启动后，可以直接访问 `http://localhost:8080/servlet3/test1` 和 `http://localhost:8080/servlet3/test2` 观察输出情况。如果可以访问表示动态注册的 servlet 被正常加载了，输出内容请查看具体 servlet 实现类。

如果无法访问请注意检查 Application Context 是否是 `servlet3`，以及访问端口是否是 `8080`。