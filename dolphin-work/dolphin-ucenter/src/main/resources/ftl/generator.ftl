<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!--指定特定数据库的jdbc驱动jar包的位置 -->
    <classPathEntry location="${location}"/>
    <context id="default" targetRuntime="MyBatis3">
        <!-- optional，旨在创建class时，对注释进行控制 -->
        <commentGenerator>
            <property name="suppressAllComments" value="true"/><!-- 是否取消注释 -->
            <property name="suppressDate" value="true" /> <!-- 是否生成注释代时间戳-->
        </commentGenerator>
        <!--jdbc的数据库连接 -->
        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="${connectionURL}"
                        userId="${userId}"
                        password="${password}">
        </jdbcConnection>
        <!-- 非必需，类型处理器，在数据库类型和java类型之间的转换控制-->
        <javaTypeResolver >
            <property name="forceBigDecimals" value="false" />
        </javaTypeResolver>
        <!-- Model模型生成器,用来生成含有主键key的类，记录类 以及查询Example类
            targetPackage     指定生成的model生成所在的包名
            targetProject     指定在该项目下所在的路径
        -->
        <javaModelGenerator targetPackage="${javaBeanPackage}" targetProject="src/main/java">
            <!-- 是否对model添加 构造函数 -->
            <property name="constructorBased" value="false"/>
            <!-- 是否允许子包，即targetPackage.schemaName.tableName -->
            <property name="enableSubPackages" value="false"/>
            <!-- 建立的Model对象是否 不可改变  即生成的Model对象不会有 setter方法，只有构造方法 -->
            <property name="immutable" value="false"/>
            <!-- 给Model添加一个父类 -->
            <!--<property name="rootClass" value="org.cs.dp.demo.domain"/>-->
            <!-- 是否对类CHAR类型的列的数据进行trim操作 -->
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

        <!--Mapper映射文件生成所在的目录 为每一个数据库的表生成对应的SqlMap文件 -->
        <sqlMapGenerator targetPackage="${mapperPackage}" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>

        <!-- 客户端代码，生成易于使用的针对Model对象和XML配置文件 的代码
                type="ANNOTATEDMAPPER",生成Java Model 和基于注解的Mapper对象
                type="MIXEDMAPPER",生成基于注解的Java Model 和相应的Mapper对象
                type="XMLMAPPER",生成SQLMap XML文件和独立的Mapper接口
        -->
        <javaClientGenerator targetPackage="${xmlPackage}" targetProject="src/main/java" type="XMLMAPPER">
            <property name="enableSubPackages" value=""/>
            <!-- 方法名计数器 Important note: this property is ignored if the target runtime is MyBatis3.-->
            <property name="methodNameCalculator" value=""/>
            <!--为生成的接口添加父接口-->
            <property name="rootInterface" value=""/>
        </javaClientGenerator>


        <!-- ————————————————————————————重点在这 TODO ————————————————————————————-->
        <table tableName="${tableName}" domainObjectName="${javaBeanName}"
               enableCountByExample="false"
               enableUpdateByExample="false"
               enableDeleteByExample="false"
               enableSelectByExample="false"
               selectByExampleQueryId="false">
            <property name="useActualColumnNames" value="true" />
        </table>
    </context>
</generatorConfiguration>