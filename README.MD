# 员工管理系统
基于javafx，使用exe4j打包成可执行文件

## .exe可执行文件启动说明

1、将整个压缩包解压到同一路径下，双击staff_evaluation.exe执行程序

2、程序只允许管理员账号登录，固定用户名：admin，初始密码：123；密码可修改

## 编写背景

### 具体要求

写一个员工考评系统，具体要求：

1. 程序形式为可独立运行的.exe文件；
   
2. 初始员工数为10人，可以对员工增、删、改；
   
3. 以天为单位，由系统管理员对每个员工进行5项评分，包括卫生、考勤、政治、贡献度、合作度，总分100；
   
4. 可以显示员工周排名和月排名。

### 思路想法

1、要求程序要为.exe文件，故想到使用javafx编写一个具有gui的员工管理程序；

2、本来使用jdbc连接MySQL数据库操作，但发现生成.exe文件后，移植到没有MySQL的电脑上无法显示数据，故考虑采用.csv文件处理数据

## 项目说明

本项目采用的是maven项目结构；

编辑器：IDEA 2020.3；

数据库和版本号：MySQL8.0.22；

java环境：JDK1.8；

使用JDBC数据库连接池操作数据库

javafx 图形化工具：JavaFX Scene Builder 8.5.0

需要在Scene Builder图形化工具中导入两个jar包：fontawesomefx-8.9.jar 和 jfoenix-8.0.8.jar

## 快速启动

1、在本地或者服务器上搭建好MySQL环境

2、使用navicat软件（或其他SQL图形化工具）连接MySQL；

3、在MySQL连接中创建一个新的数据库；

4、选择创建的数据库并运行项目中的library.sql文件，右键刷新后即可创建成功；

5、将schoollibrary项目整个文件import到IDEA中，更改一下schoolLibrary/src/main/resources/config/druid.properties文件中的参数，点击运行即可。

## 项目打包
简单地使用idea将javafx项目打包成jar文件，以及使用exe4j软件将jar打包成exe程序？

### 一、打包生成.jar

1、打开project structure，选择artifact，add一个JAR，From modules with dependencies

2、Main Class选择项目主函数

3、Directory for META-INF/MANIFEST.MF**`一定`**要选到/src目录，不要选到/src/main/java或者/src/main/resources，否则执行.jar时会报缺少主属性清单

4、接下来一直点ok，可以看到多了META-INF这个文件夹，检查其中的MANIFEST.MF文件，其中应有Manifest-Version: 1.0和Main-Class: controller.Main

5、out文件夹中，会生成staff_evaluation.jar，在cmd中执行 java -jar staff_evaluation.jar，文件就可以执行起来了

### 二、生成.exe文件