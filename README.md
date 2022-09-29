# dexmaker

基于dexmaker [2.28.3], 将依赖库合并,直接在java环境下编译

## 项目分为几个部分

1. `dexmaker` 及 依赖的`dalvik-dx`源码
2. mirror库,需要部分安卓系统的源码
3. 工具类
4. 测试类及目标

## 开发测试方法步骤

1. 在`case_me.sun`中新建个包名
2. 新建测试类，编写测试代码
3. 在类`case_me.sun.MainCase`中调用对应方法即可

## 依赖及鸣谢

* dexmaker: https://github.com/linkedin/dexmaker
* dalvik-dx: https://github.com/JakeWharton/dalvik-dx