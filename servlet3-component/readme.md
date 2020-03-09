# servlet3-component 模块介绍

该模块会提供 Servlet 的一些功能（不止 servlet 3.0）以便测试，已提供的功能会在下边一一罗列：

## ServletContainerInitializer

该模块中提供了 `ServletContainerInitializer` 的实现类及其处理类 `WebApplicationInitializer`，其会被 servlet3 模块引用并提供 `WebApplicationInitializer` 的实现。

由于 `ServletContainerInitializer` 的实现类是通过 SPI 方法引入的，所以其必须在相应的类路径下才能被加载到。

如果想要测试 `ServletContainerInitializer` 的效果，请启动 servlet3 模块进行测试。可以通过外置的 Tomcat 或 直接通过 `mvn tomcat7:run` 命令运行嵌入 Tomcat。

详细测试步骤请参照 [servlet3/ServletContainerInitializer 相关介绍](https://github.com/FIyingIdeal/servlet-to-spring/tree/master/servlet3#servletcontainerinitializer)。

