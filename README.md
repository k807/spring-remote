# spring-remote
## 介绍:
**spring-remote**可以将接口实现部署在数据库中

**spring**容器在启动时会自动从数据库中获取远程接口实现,并对依赖接口的bean进行装配

**spring-remote**实现了接口实现或子类继承的可配置化,```客户端```只需声明依赖的接口，和使用配置好的spring-remote环境，无需关注实现细节，只需```面向接口编程```，spring-remote会自动从服务器获取接口实现

**spring-remote**```服务端```可将实现配置在数据库中，通过简单的标示即可切换实现，代码更新时，客户端代码也无需重新编译部署，只需重启spring容器

**spring-remote**兼容spring容器的ioc,aop功能，对bean的操作和配置与本地bean一致

## 环境

当前开发版代码是基于自己写的简易版的spring环境,拥有spring容器基础的ioc,aop功能
### 基础springIOC AOP容器
1. ```<property name="" value="">``` 支持String类型
2. ```<property name="" ref=""> ```支持引用类型
3. 类的实例化仅支持空参构造器
4. 支持BeanPostProcessor增强bean

## 使用说明
### 客户端：
#### 配置服务端数据库源
```xml
<db-property>
    <driver>com.mysql.jdbc.Driver</driver>
    <url>jdbc:mysql://localhost:3306/test</url>
    <username>root</username>
    <password>root</password>
</db-property>

```

#### remote-name 远程实现类的标识名称
需要配置remote-name属性,其他与基础spring-bean配置方式一致
接口实现类默认取version值最大的版本
```xml
<bean id="" remote-name="">
    <property></property>
</bean>
```

#### version 指定远程实现类的版本
如果需要指定的remote接口实现版本，则可以指定version版本号
```xml
<bean id="" remote-name="" version="">
    <property></property>
</bean>
```

#### persistent 是否将实现类保存在本地(未实现)
指定persisten关键字，可以将实现类class保存在本地，当指定了version时，加载bean时只会在本地校验class文件的版本号等于version
```xml
<bean id="" remote-name="" version="" persistent="true">
    <property></property>
</bean>
```

如果不指定version,加载bean时会与数据库进行版本比较，如果数据库最新版本大于当前本地版本，则重新获取最新版本class保存在本地
```xml
<bean id="" remote-name="" persistent="true">
    <property></property>
</bean>
```
### 服务端：

按照约定配置bean数据库
库名为```remote_beans```,表结构如下

| 列名 | 列属性 | 是否主键 | 是否可空 | 备注 |
| :-: | :-: | :-: | :-: | :-: |
| id | int | y | n | 可以自己设置递增规则 |
| class_name | varchar | n | n | 类的全路径名 |
| interface_name | varchar | n | y | 实现的接口,没有接口则为空 |
| remote_name | varchar | n | n | 自己设置的名字，可以不唯一，客户端通过remote_name 找到依赖的接口实现 |
| version | int | n | n | 一个实现类的版本, 客户端可以通过制定version指定需要的类,相同remote_name version不能相同 |
| open | boolean | n | n | 是否开放，如果为false，则不会被客户端访问到 |
| class_byte | n | n | n | class文件，不允许修改 |

## 注意事项
1. 客户端预留的接口不可随意更改包位置
2. 客户端不可随意更改接口信息
3. 服务端的中的表class_name填写实现类的类全路径名,例如com.doc包下的ServiceImpl类,需要填写的class_name为com.doc.ServiceImpl
4. 客户端更改实现类版本号只需指定version
5. 服务端想更改客户端默认使用的实现类，只需将实现类的version设为最大值

## 后续版本
1. 支持远程读取github的xml配置文件

1. 支持代码在github的remote版本

2. 服务端可以配置秘钥加密class,remoteClassLoader解密读取class文件

3. 支持autowire, 当bean配置autowireByType,并且在spring容器中找不到对应接口实现时，会自动从remote获取class实现,并对bean自动装配

4. 支持注解形式
