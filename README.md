# Task Manage

基于SpringBoto的定时任务管理系统

## 技术选型
- springboot 2.3.1
- SpringSecurity 2.3.1
- httpclient 4.5.12
- commons-lang3 3.10
- quartz 2.3.2
- druid 1.1.22
- fastjson 1.2.71

## 项目功能
1. springboot集成quartz，使用druid连接池 
2. 支持http请求任务定时调度，当前支持get、post表单(formdata)、post Json三种请求类型，并记录返回内容
3. 通过web界面进行任务管理，包括任务启动、暂停、立即运行、新增、修改、历史记录、历史任务、执行日志查看功能
4. 支持调用接口(/quartz/httpJob/add)进行http任务添加  
5. 根据jobname或jobgroup进行查询

## 部署方式
1. 执行sql目录下的task_manage.sql文件，建立quartz以及httpjob需要的数据库表  
2. 修改application.yml中的数据库连接方式
3. 访问 http://localhost:8080/idnex 可通过web界面进行定时任务的管理

## 最后致谢
感谢以下开源项目提供的项目参考
- https://github.com/helloflygit/springboot-quartz
- https://gitee.com/52itstyle/spring-boot-task
- https://github.com/simonsfan/springboot-quartz-demo

