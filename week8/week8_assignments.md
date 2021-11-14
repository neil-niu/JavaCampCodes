1.  创建数据库：
    1. DDL:
```plain
      create table ordermaster
(
    order_id        int unsigned auto_increment
        primary key,
    product_name    varchar(20)   not null,
    product_number  int unsigned  not null,
    product_price   decimal(8, 2) not null,
    payment_method  tinyint       not null comment '1 Cash，2alipay，3wechatPay',
    payment_amount  decimal(8, 2) not null,
    order_point     int unsigned  not null,
    discount_amount int unsigned  not null,
    order_time      datetime      null,
    payment_time    datetime      null
);
```

2. 创建sprint boot 项目访问数据库

      代码库： com.neil.batchdemo为针对 订单数据创建的sprintboot工程。

3. 插入百万条数据

      目前插入100万条数据需要88s左右，后续进行优化

4. 水平分库分表

       还没有完成分库分表，下周一完成。

