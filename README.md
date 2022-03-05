# 项目中需要修改的配置
* logback.xml模板，放在项目根目录下，其中保存路径记得修改
```
<configuration>
    <!-- 日志保存格式 -->
    <property name="LOG_PATTERN" value="%date{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n" />
    <!-- 日志保存的路径在这里，记得改 -->
    <property name="FILE_PATH" value="F:/Slf4j/QA_System/%d{yyyy-MM-dd}.%i.log" />

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <!-- 按照上面配置的LOG_PATTERN来打印日志 -->
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- 按照上面配置的FILE_PATH路径来保存日志 -->
            <fileNamePattern>${FILE_PATH}</fileNamePattern>
            <!-- 日志保存15天 -->
            <maxHistory>15</maxHistory>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <!-- 单个日志文件的最大，超过则新建日志文件存储 -->
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>

        <encoder>
            <!-- 按照上面配置的LOG_PATTERN来打印日志 -->
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <logger name="edu.njnu.qaserver" level="INFO" />
    <root level="INFO">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="FILE" />
    </root>
</configuration>
```
* springboot 配置文件 application.yml
```
spring:
    datasource:
        username: mysql 数据库用户名
        password: mysql 数据库密码
        url: jdbc:mysql://localhost:（端口）/数据库名称
```
