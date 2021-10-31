**6.（必做）**基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。

用户表设计

|用户表|    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
|:----|:----|:----|:----|:----|:----|:----|:----|:----|:----|:----|:----|:----|:----|:----|:----|:----|
|用户名|登录名|密码|手机号|邮箱|性别|邮编|省|市|区|地址|生日|用户状态|会员级别|本级别积分上限|本级别积分下限|会员积分|

按照第三范式，优化传递依赖：

|用户信息表（user master table）|    |    |    |    |    |    |    |
|:----|:----|:----|:----|:----|:----|:----|:----|
|用户名<br>user_name|登录名<br>login_name|密码<br>password|手机号<br>phone_number|邮箱<br>email_address|生日<br>birthday|注册时间<br>register_time|用户状态<br>user_status|

CREATE TABLE user_master(

user_id INT UNSIGNED AUTO_INCREMENT NOT NULL ,

user_name VARCHAR(20) NOT NULL ,

login_name VARCHAR(20) NOT NULL ,

password CHAR(32) NOT NULL ,

phone_number VARCHAR(20) NOT NULL,

email_address VARCHAR(20) NOT NULL,

birthday VARCHAR(20) NOT NULL,

register_time VARCHAR(20) NOT NULL ,

user_status INT NOT NULL,

PRIMARY KEY pk_user_id(user_id)

) ENGINE = innodb 


|会员信息表(member_info table)|    |    |    |
|:----|:----|:----|:----|
|会员级别<br>member_level_name|会员积分<br>member_point|会员积分级别上限<br>point_max|会员积分级别下限<br>point_min|

CREATE TABLE member_info (

member_level_id INT UNSIGNED AUTO_INCREMENT NOT NULL ,

user_id INT UNSIGNED NOT NULL,

member_level_name VARCHAR(20) NOT NULL,

member_point INT UNSIGNED NOT NULL ,

point_max INT UNSIGNED NOT NULL,

point_min INT UNSIGNED NOT NULL ,

PRIMARY KEY pk_member_level_id(member_level_id)

) ENGINE = innodb 

|用户地址表(user_address table)|    |    |    |    |
|:----|:----|:----|:----|:----|
|邮编<br>zip_code    |省<br>province|市<br>city|区<br>district|地址详情<br>address_detail_info<br>|

CREATE TABLE user_address (

user_address_id INT UNSIGNED AUTO_INCREMENT NOT NULL ,

user_id INT UNSIGNED NOT NULL,

zip_code SMALLINT NOT NULL NOT NULL,

province SMALLINT NOT NULL ,

city SMALLINT NOT NULL,

district SMALLINT NOT NULL ,

address_detail_info VARCHAR(100) NOT NULL,

PRIMARY KEY pk_user_address_id(user_address_id)

) ENGINE = innodb 


商品表设计

|商品主表(product_master table)|    |    |    |    |    |    |    |    |    |    |
|:----|:----|:----|:----|:----|:----|:----|:----|:----|:----|:----|
|商品<br>名称<br>production_name|商品<br>价格<br>product_privce|商品详情product_detailed_info|商品<br>描述<br>product_description|商品<br>生产时间<br>product_date|商品<br>有效期<br>production_period|商品<br>分类<br>production_category|商品<br>编号<br>prouction_number|商品<br>产地<br>production_address|商品品牌<br>production_brand|商品<br>型号<br>production_model_number|

CREATE TABLE product_master(

product_id INT UNSIGNED AUTO_INCREMENT NOT NULL ,

production_name VARCHAR(20) NOT NULL ,

product_price DECIMAL(8,2) NOT NULL ,

product_description VARCHAR(32) NOT NULL ,

product_date DATETIME ,

production_period INT UNSIGNED NOT NULL,

production_category  INT UNSIGNED NOT NULL,

prouction_number INT UNSIGNED NOT NULL ,

production_address INT NOT NULL,

production_model_number INT NOT NULL,

production_brand VARCHAR(20) NOT NULL,

PRIMARY KEY pk_product_id(product_id)

) ENGINE = innodb 

|商品详情表(product_detailed_info table)|    |    |    |    |    |
|:----|:----|:----|:----|:----|:----|
|商品重量<br>product_weight|商品<br>长度<br>product_length|商品<br>宽度<br>product_width|商品<br>高度<br>product_height|图片<br>信息<br>product_pic_info|商品<br>颜色<br>product_color|

CREATE TABLE product_detailed_info(

product_detailed_info_id INT UNSIGNED AUTO_INCREMENT NOT NULL ,

product_id INT UNSIGNED NOT NULL ,

product_weight FLOAT NOT NULL ,

product_length FLOAT NOT NULL ,

product_width FLOAT NOT NULL ,

product_height FLOAT NOT NULL ,

product_pic_info  VARCHAR(60) NOT NULL,

product_color ENUM ( 'red','yellow', 'blue', 'black'),

PRIMARY KEY pk_product_detailed_id(product_detailed_info_id)

) ENGINE = innodb 



订单表设计

|物流信息表(shipping info table)|    |    |    |    |    |    |    |    |
|:----|:----|:----|:----|:----|:----|:----|:----|:----|
|发货人<br>姓名<br>shipper_name|发货人地址<br>shipper_address|发货人电话<br>shipper_phone_number|收货人<br>姓名<br>receiver_name|收货人地址<br>reveiver_address|收货人电话<br>receiver_phone_numer|运费金额<br>shipping_amount|发货<br>时间<br>shipping_time|收货<br>时间<br>receiving_time|

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

|物流信息表(shipping info table)|    |    |    |    |    |    |    |    |
|:----|:----|:----|:----|:----|:----|:----|:----|:----|
|发货人<br>姓名<br>shipper_name|发货人地址<br>shipper_address|发货人电话<br>shipper_phone_number|收货人<br>姓名<br>receiver_name|收货人地址<br>reveiver_address|收货人电话<br>receiver_phone_numer|运费金额<br>shipping_amount|发货<br>时间<br>shipping_time|收货<br>时间<br>receiving_time|

CREATE TABLE shipping_info(

shipping_info_id INT UNSIGNED AUTO_INCREMENT NOT NULL ,

order_id INT UNSIGNED NOT NULL ,

shipper_name VARCHAR(20) NOT NULL ,

shipper_address VARCHAR(60)  NOT NULL ,

shipper_phone_number INT UNSIGNED NOT NULL,

receiver_name VARCHAR(20) NOT NULL ,

receiver_address VARCHAR(60)  NOT NULL ,

receiver_phone_number INT UNSIGNED NOT NULL,

shipping_amount DECIMAL(8,2) NOT NULL,

shipping_time DATETIME ,

receiving_time DATETIME ,

PRIMARY KEY pk_shipping_info_id(shipping_info_id)

) ENGINE = innodb 



