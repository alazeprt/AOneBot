# AOneBot
## 基于Java实现的OneBot 11协议客户端

## 特点
- **高效**: 仅使用正向Websocket进行通信, 效率更高
- **轻量**: 本体大小不到100KB, 加上所有依赖库后大小不到600KB!
- **方便**: 仅需几行代码实现OneBot的各种操作，仅需一个注解就能监听
- **全面**: 包含几乎所有的OneBot公开API接口与事件

## 使用方式
### 1. 引入仓库
Maven (pom.xml):
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Gradle (build.gradle):
```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}
```
Gradle (build.gradle.kts):
```kotlin
repositories {
    mavenCentral()
    maven("https://jitpack.io")
}
```
### 2. 引入依赖
Maven (pom.xml):
```xml
<dependencies>
    <dependency>
        <groupId>com.github.alazeprt</groupId>
        <artifactId>AOneBot</artifactId>
        <version>1.0.1-beta</version>
    </dependency>
</dependencies>
```
Gradle (build.gradle):
```groovy
dependencies {
    implementation 'com.github.alazeprt:AOneBot:1.0.1-beta'
}
```
Gradle (build.gradle.kts):
```kotlin
dependencies {
    implementation("com.github.alazeprt:AOneBot:1.0.1-beta")
}
```
### 3. 开始使用
首先要创建一个OneBot客户端实例：
```java
BotClient client = new BotClient("localhost", 3001);
```
其中第一个参数为主机名（IP地址/域名），第二个参数为开放端口
你也可以只传入一个URI参数（例如 `ws://localhost:3001` ）代表你的服务端地址

接着要连接到一个WebSocket服务端（连接操作不是异步进行）：
```java
client.connect();
```

连接完毕后，你就可以通过 `action()` 方法来执行操作了：
```java
client.action(new SendGroupMessage(123456, "Hello World!")); // 向id为123456的群发送Hello World!
client.action(new GetGroupList(), (groupList) -> { // 获取当前登录账号的所有群组的列表
    for (Group group : groupList) { // 遍历群组列表
        System.out.println(group.getGroupName()); // 输出群名
    }
});
```
如果你想监听事件，则先需要定义一个方法，设置参数为你要监听的事件的类，并为该方法添加 `@SubscribeBotEvent` 注解，最后注册该监听器，以下是一段完整的示例代码：
```java
public class MyListener {
    @SubscribeBotEvent
    public void onGroupMessage(GroupMessageEvent event) { // 监听群组消息事件
        System.out.println(event.getSenderNickName() + ": " + event.getMessage()); // 输出消息内容
    }
    
    public static void main(String[] args) {
        BotClient client = new BotClient("localhost", 3001);
        client.connect();
        client.registerEvent(new MyListener());
    }
}
```
如果你需要断开连接，可直接调用 `BotClient` 类的 `disconnect()` 方法

## 未来计划
- 支持更多OneBot的API接口与事件
- 添加更多通信方式

## 协议
本项目遵循[LGPL-3.0](./LICENSE)协议发行，最终解释权归**alazeprt**所有