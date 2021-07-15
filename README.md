# 开发注意事项

## 1、代码规范

### 1.1、注释

**任何函数、变量，都需要添加注释以说明其作用和含义**

**函数类的执行步骤，如有必要的话，需要添加`//`单行注释进行步骤说明**

注释示例：

```java
/**
 * Indicates a {@link Configuration configuration} class that declares one or more
 * {@link Bean @Bean} methods and also triggers {@link EnableAutoConfiguration
 * auto-configuration} and {@link ComponentScan component scanning}. This is a convenience
 * annotation that is equivalent to declaring {@code @Configuration},
 * {@code @EnableAutoConfiguration} and {@code @ComponentScan}.
 *
 * @author Phillip Webb
 * @author Stephane Nicoll
 * @author Andy Wilkinson
 * @since 1.2.0
 */
public @interface SpringBootApplication {

	/**
	 * Exclude specific auto-configuration classes such that they will never be applied.
	 * @return the classes to exclude
	 */
	Class<?>[] exclude() default {};

	/**
	 * Exclude specific auto-configuration class names such that they will never be
	 * applied.
	 * @return the class names to exclude
	 * @since 1.3.0
	 */
	String[] excludeName() default {};
}
```



### 1.2、命名规范

变量命名，使用驼峰命名法。

属性的名字应当为名词形式。

局部变量的命名应简短为主

公共函数命名应以小写动词开头。





## 2、分支管理

每一位开发者都应该从main分支检出新的个人分支进行开发和推送。

每天请从main分支拉取更新后再进行编辑！

每完成一个逻辑块、或者创建一个函数、或者删除一个函数、新建文件、删除文件，都应该执行一次提交（commit）

每天**22点以前**推送（push）最新一次可编译版本的提交（commit）

每次推送的步骤，一般为 “commit”--> “pull”-->“push”

**不要对main分支进行操作！**

由组长每天进行一次分支合并





# 开发环境

JDK 16（Corretto OpenJDK 16) [点击下载连接或使用IDEA自动下载]([Amazon Corretto-OpenJDK 的免费多平台发行版-AWS云服务](https://aws.amazon.com/cn/corretto/))

Node.js v16.4.2 [点击下载连接](http://nodejs.cn/download/)

Vue3.0 [安装 | Vue.js (vuejs.org)](https://v3.cn.vuejs.org/guide/installation.html#vue-devtools)



*其他代码依赖内容，由npn/cnpm/yarn、Gradle管理工具进行管理*

*如有添加内容，请自行添加*

