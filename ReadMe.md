### 角色分类

user

super admin   写死

### 前端(user)

#### 功能

- ~~注册登陆~~
- ~~修改密码~~
- ~~车型列表~~
- ~~车型详情页面~~
- ~~预购车辆（每个车型限购一辆）~~
- ~~查看我的预购记录~~
- ~~取消预购~~
- 打开地图

#### 页面

- 首页

  [https://www.volvocars.com/zh-cn?utm_source=baidu-bz-pc&utm_medium=CPT&utm_campaign=20-Campaign-BBP-Brand-BZ&utm_term=HY-SEM-BBP-Brand-BZ&sourceApplicationInformation=HY-SEM-BBP-Brand-BZ&utm_group=BZ-PC-%E6%A0%87%E9%A2%98-%E5%AE%98%E7%BD%91](https://www.volvocars.com/zh-cn?utm_source=baidu-bz-pc&utm_medium=CPT&utm_campaign=20-Campaign-BBP-Brand-BZ&utm_term=HY-SEM-BBP-Brand-BZ&sourceApplicationInformation=HY-SEM-BBP-Brand-BZ&utm_group=BZ-PC-标题-官网)

- 登录页

- 用户信息页

- 车型浏览页

- 车型详情页

- 地图页

- 未来驾驶静态页（视频）

  https://www.volvocars.com/zh-cn/why-volvo/human-innovation/future-of-driving

### 后端(admin)

#### 功能

- ~~登陆~~
- ~~客户管理 -  增、删、改、查（根据用户名筛选）~~
- ~~销售记录管理 -   查（根据时间段筛选）~~
- 库存管理
  - ~~根据车型进行查询~~
  - 车型信息修改 - 修改库存、图片信息
  - 车型信息新增和删除

#### 页面

- 登录页
- 客户管理页面
- 销售记录查询页
- 车型列表页
  - 车型修改页
  - 车型新增页
  
### 项目信息

| key              | value                   |
| ---------------- | ----------------------- |
| IDE              | Intellij IDEA + VS Code |
| 后端开发语言     | java                    |
| 后端开发框架     | springBoot              |
| 前端开发框架     | vue                     |
| 数据库           | mysql                   |
| 数据库可视化工具 | navicat                 |
| 依赖管理工具     | maven                   |

> 你的软件视频我看过了，问题比较多！！！具体如下：
>
> 1. 网站上关于汽车的有用信息，可以说只有型号和图片（假定图与型号是相匹配的），买部手机就几千元还得看看配置呢，难道买车不需要看配置吗？什么油耗，售价，几厢，几座都不关心吗？？？
>
> 2. 我看到你的操作中有预购，这个用户是什么样身份的用户？4S店的销售员？还是顾客？如果是顾客他会买那么多车吗？预购了那么多次？这个预购要付费吗？如果是销售员购那么多车倒是说得通，但是帮客户预定车不留客户信息吗？
>
> 3. 软件整体功能太简单！作为一个4S店的网站至少应该有个查询功能，如按车型号，按车的类型，按售价区间等，查询功能必须要有，否则估计你答辩会过不了。
>
> 4. 我在网上看了一个类似的网站里面还会有贷款计算器，售后服务等，我觉得你也可以再加个售后服务。否则整体软件工作量不够。这几年比较火的电商网站都至少有商品信息展示，商品查询，在线销售，支付等功能了，大部分这类网站都得有增、删、改、查的功能，哪怕是简单点的查询一定得有！我把我找到的这个网站链接发给你，望尽快对照完善你的软件。
>
> http://www.szbowchuang.bmw.com.cn/cn/zh/dealers/szbowchuang/index.html

### 修改需求

1. 车型新增字段

2. 客户点击预购，弹出弹窗，需要填写用户姓名、手机号、邮箱信息

3. 前台查询新增条件

4. 前台新增售后服务，填写:姓名、手机号、邮箱、服务内容，

   后台新增列表



