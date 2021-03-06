# code-generator-gui
一个自带GUI的代码生成器

#### 最新版本：[下载 v0.1.2](https://github.com/chris-peng/code-generator-gui/releases/download/0.1.2/code-generator-gui-0.1.2.jar)

1. 通过读取数据库表结构，根据提供的模板生成代码，如java MVC各层增删查改代码、前端数据维护页面代码、Mybatis的mapper.xml映射文件，等等。
2. 可扩展支持其他数据库或其他数据结构源。已支持的数据库包括：
    * MySQL（内建支持）
    * MsSql(SQLServer)
    * Oracle
    * SybaseASE15
    * HsqlDb
    * McKoi
    * Derby
    * PostgreSql
    * Interbase
    * MaxDB
    * Firebird
    * Cloudscape
    * Sybase
    * DB2
    * Axion
    * SapDB
3. 仅支持freemarker模板，模板文件名需以.ftl结尾，目前支持的模板变量：https://chris-peng.github.io/code-generator-gui/dbContext-data-model.txt 。

    这里有2个模板的例子：
    * [${entity.className}.java.ftl](https://github.com/chris-peng/code-generator-gui/blob/master/docs/testTpl/%24%7Bentity.className%7D.java.ftl)
    * [${entity.className}Controller.java.ftl](https://github.com/chris-peng/code-generator-gui/blob/master/docs/testTpl/%24%7Bentity.className%7DController.java.ftl)
    
    如你所见，文件名也可以是模板。生成的代码文件名将在应用模板后自动去除ftl后缀。

#### 使用指南：
1. 设置好各个选项，注意：MySql的连接串建议加上useInformationSchema=true选项，以读取表注释：
![选项](https://chris-peng.github.io/code-generator-gui/imgs/help1.png)
2. 点击"GENERATE"按钮生成代码：
![生成](https://chris-peng.github.io/code-generator-gui/imgs/help2.png)
3. 可以把当前配置保存待用：
![配置](https://chris-peng.github.io/code-generator-gui/imgs/help3.png)


#### 模板和生成的代码：
![生成](https://chris-peng.github.io/code-generator-gui/imgs/tplANDcode.png)
