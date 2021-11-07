|订单表|    |    |    |    |    |    |    |    |
|:----|:----|:----|:----|:----|:----|:----|:----|:----|
|商品<br>名称<br>product_name|商品数量product_number|商品<br>价格product_price|支付<br>方式payment_method|支付<br>金额payment_amount|订单<br>积分order_point|优惠<br>金额discount_amount|下单时间order_time|支付<br>时间payment_time |



CREATE TABLE order_master(

order_id INT UNSIGNED AUTO_INCREMENT NOT NULL ,

product_name VARCHAR(20) NOT NULL ,

product_number INT UNSIGNED NOT NULL ,

product_price DECIMAL(8,2) NOT NULL ,

payment_method TINYINT NOT NULL COMMENT '1 Cash，2alipay，3wechatPay',

payment_amount DECIMAL(8,2) NOT NULL,

order_point INT UNSIGNED NOT NULL ,

discount_amount INT UNSIGNED NOT NULL,

order_time DATETIME ,

payment_time DATETIME ,

PRIMARY KEY pk_order_id(order_id)

) ENGINE = innodb 


1. 作业题目: 按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
2. 打开 Spring 官网: [https://spring.io/](https://spring.io/)
3. 找到 Projects --> Spring Initializr:  [https://start.spring.io/](https://start.spring.io/)
4. 填写项目信息:
* Project: Maven Project
* Language: Java
* Spring Boot版本: 2.5.4
* Group: 自己的包名, 以做标识;
* Artifact: mysql-demo
* JDK版本: 8
* Dependencies: 添加依赖, 比如 MyBatis, Spring Web, MySQL, JDBC
* 生成 maven 项目; 下载并解压。 参考: [mysql-demo项目Share信息](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.5.4&packaging=jar&jvmVersion=1.8&groupId=com.cncounter&artifactId=mysql-demo&name=mysql-demo&description=MySQL%20Demo&packageName=com.cncounter.mysql-demo&dependencies=mybatis,web,mysql,data-jdbc)
1. Idea或者Eclipse从已有的Source导入Maven项目。
1. 搜索依赖， 推荐 mvnrepository: [https://mvnrepository.com/](https://mvnrepository.com/)
1. 搜索 fastjson , 然后在 pom.xml 之中增加对应的依赖。
1. 准备MySQL环境, 创建订单表

导入itelligi工程后，增加 fastjson依赖后  进行编译出错:

[Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured](https://stackoverflow.com/questions/51221777/failed-to-configure-a-datasource-url-attribute-is-not-specified-and-no-embedd)

**错误原因1：DataSourceAutoConfiguration**会自动加载.

 

**错误原因2：**没有配置**spring - datasource - url** 属性.

 

**错误原因3：spring - datasource - url** 配置的地址格式有问题.

 

**错误原因4：**配置 **spring - datasource - url**的文件没有加载.


OrderController 还在调试当中，后续补齐











