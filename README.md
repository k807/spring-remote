# spring-remote
## 介绍:
**spring-remote**可以将接口实现部署在数据库中

**spring**容器在启动时会自动从数据库中获取远程接口实现,并对依赖接口的bean进行装配

**spring-remote**实现了接口实现的可配置化,```客户端```只需声明依赖的接口，和使用配置好的spring-remote环境，无需关注接口实现细节，只需```面向接口编程```，spring-remote会自动从服务器获取接口实现

**spring-remote**```服务端```可将接口实现配置在数据库中，通过简单的标示即可切换接口实现，代码更新时，客户端代码也无需重新编译部署，只需重启spring容器

## 环境

当前开发版代码是基于自己写的简易版的spring环境
### 基础springIOC AOP容器
1. ```<property name="" value="">``` 支持String类型
2. ```<property name="" ref=""> ```支持引用类型
3. 类的实例化仅支持空参构造器
4. 支持BeanPostProcessor增强bean

## 使用说明
客户端：
1. 配置服务端数据库源
```xml
<db-property>
    <driver>com.mysql.jdbc.Driver</driver>
    <url>jdbc:mysql://localhost:3306/test</url>
    <username>root</username>
    <password>root</password>
</db-property>

```
2. 配置远程bean

需要配置remote-name属性,其他与基础spring-bean配置方式一致
```xml
<bean id="" remote-name="">
    <property></property>
</bean>
```

如果需要制定的remote接口实现版本，则可以
```xml
<bean id="" remote-name="" version="">
    <property></property>
</bean>
```

服务端：

按照约定配置bean数据库
库名任意,表结构如下

| 列名 | 列属性 | 是否主键 | 是否可空 | 备注 |
| :-: | :-: | :-: | :-: | :-: |
| id | int | y | n | 可以自己设置递增规则 |
| class_name | varchar | n | n | 类的全路径名 |
| interface_name | varchar | n | n | 实现的接口 |
| remote_name | varchar | n | n | 自己设置的名字，可以不唯一，客户端通过remote_name 找到依赖的接口实现 |
| version | varchar | n | n | 一个实现类的版本, 客户端可以通过制定version指定需要的类,相同remote_name version不能相同 |
| open | boolean | n | n | 是否开放，如果为false，则不会被客户端访问到 |
| class_byte | n | n | n | class文件，不允许修改 |

## 后续版本
1. 支持github的remote版本

2. 服务端可以配置秘钥加密class,remoteClassLoader解密读取class文件

3. 支持autowire, 当bean配置autowireByType,并且在spring容器中找不到对应接口实现时，会自动从remote获取class实现,并对bean自动装配

4. 支持注解形式