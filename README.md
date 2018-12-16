# onDraw06


> 添加 ObjectBox 数据库填坑：

- 编译不通过：
```
Program type already present: io.objectbox.android.AndroidScheduler$Runner
```
先解决 Java Compiler 的 errors . 这里的情况分很多种：比如我遇到的
1、在根 gradle 文件下添加
```
maven { url "http://objectbox.net/beta-repo/" }
```
2、添加 dex 合并
```
multiDexEnabled true
```
3、按照文档添加依赖,就是找不到 `MyObjectBox` . 删除 app 下的 build 文件,关闭 AS 重新打开构建。