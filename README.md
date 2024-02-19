# AlsaceBungeeCore 插件介绍

## 简介
`AlsaceBungeeCore`是为BungeeCord服务器设计的一个高效插件，旨在增强服务器的多功能性和管理便捷性。它通过提供一系列实用功能，如服务器间快速跳转、玩家事件监听以及动态别名管理，来优化玩家的游戏体验和服务器的运行效率。

## 主要功能

### 服务器别名配置
- 允许为服务器设置别名，简化玩家跳转指令的复杂性。
- 支持动态配置，服务器管理员可以轻松添加或修改服务器别名，无需重启。
```yaml
  project:
    name: '项目服'
    servers:
      - project
  community:
    name: '社区服'
    servers:
      - '1.20'
      - '1.19'
```
- 通过设置别名，进入相应子服的玩家将会提示为name中的值
- 如：使用/server 1.20后会提示
  ![](http://cloud.alsace.team/Picture/pic/20240219211513.png?imageMogr2/format)

### 快速跳转命令
- 实现了`/lobby`命令，允许玩家快速返回大厅服务器。

### 启用通知
- 允许为子服配置通知，在列表中的服务器将会收到玩家跨服提示，否则不显示跨服消息
```yaml
enabledServers:
  - lobby
  - '1.20'
  - '1.19'
  - project
```

## 如何配置

1. **安装插件**：将插件文件放置在BungeeCord的`plugins`目录下，重启BungeeCord实例以加载插件。
2. **配置文件**：插件首次启动后，会在`plugins/AlsaceBungeeCore`目录下生成`config.yml`配置文件。通过编辑此文件，可以添加或修改服务器别名，以及配置哪些服务器启用特定功能。
3. **重载配置**：当前版本需要重启BungeeCord来应用配置文件的更改。

## 开发者信息
- **作者**：Alsace Team
- **BungeeCord版本兼容性**：适用于1.12+的Bungeecord。

---
欢迎加入我们的社区，共同探讨和改进`AlsaceBungeeCore`！

群：916435182
