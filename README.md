# code-generator-gui
一个Java实现的基于freemarker模板的代码生成器

#### 最新版本：[下载 v0.02](https://github.com/chris-peng/code-generator-gui/releases/download/0.0.2/code-generator-gui-0.0.2.zip)

1. 通过读取数据库表（MySql）结构，根据提供的模板生成代码，如java MVC各层增删查改代码、前端数据维护页面代码、Mybatis的mapper.xml映射文件，等等。
2. 可扩展支持其他数据库或其他数据结构源。
3. 仅支持freemarker模板，模板文件名需以.ftl结尾，目前支持的模板变量：https://chris-peng.github.io/code-generator-gui/dbContext-data-model.txt 。

    这里有2个模板的例子：
    * [${entity.className}.java.ftl](https://github.com/chris-peng/code-generator-gui/blob/master/docs/testTpl/%24%7Bentity.className%7D.java.ftl)
    * [${entity.className}.java.ftl](https://github.com/chris-peng/code-generator-gui/blob/master/docs/testTpl/%24%7Bentity.className%7D.java.ftl)
    
    如你所见，文件名也可以是模板。

#### 使用指南：
1. 设置好各个选项，注意：MySql的连接串建议加上useInformationSchema=true选项，以读取表注释：
![选项](https://chris-peng.github.io/code-generator-gui/imgs/help1.png)
2. 点击"GENERATE"按钮生成代码：
![生成](https://chris-peng.github.io/code-generator-gui/imgs/help2.png)


#### 模板和生成的代码：
![生成](https://chris-peng.github.io/code-generator-gui/imgs/tplANDcode.png)
