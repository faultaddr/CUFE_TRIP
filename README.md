#CUFE_TRIP
1.1版本
----

##修复了很多bug，诸如 Fragment闪退等

##加入了新的图片，通过网络异步加载获取，是从携程上爬下来的，对应着北京2000多个景点

##添加了listview 分页、下拉刷新、上拉加载更多等新功能

1.0版本
----

##这是一款针对中央财经大学在校学生的旅行出游软件，通过有效的与旅行社对接来创造更好的服务和用户体验。

###这款软件在注册上还是有一些问题：(`已经解决了`)

- **1.注册完成后再登录会出现密码错误（逻辑问题）（现在偶尔会出现这个问题是因为网络延迟的问题，只要把登陆的延迟时间弄长一些就好）**

- **2.登录时会出现多次失败情况**

###这款软件在优化方面也还有很长一段路要走：

- **1.main Thread 上做的工作太多，下一步要调整所有Thread+Handle 为 doinBackground()**`已经改好`

- **2.在设置上还有很长一段路要走**

- **3.支付问题没有解决**
