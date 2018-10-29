# apiggs-gradle-plugin
[ ![Download](https://api.bintray.com/packages/apiggs/maven/apiggs-gradle-plugin/images/download.svg) ](https://bintray.com/apiggs/maven/apiggs-gradle-plugin/_latestVersion)

easy use apiggs with gradle

## Usage
install build.gradle
```groovy
buildscript {
    dependencies {
        classpath 'com.github.apiggs:apiggs-gradle-plugin:latest.release'
    }
}

apply plugin: 'com.github.apiggs'

apiggs{
 // options here
}

```

cmd
```groovy
gradle apiggs
```

tasks
```
Tasks/documentation/apiggs
```

### options

1. id 项目id，生成id.html文件
1. title 文档标题
1. description 文档描述
1. production 输出文件夹，默认为 apiggs
1. out 输出目录，默认为 target
1. source 源码目录
1. dependency 源码依赖的代码目录，以逗号隔开
1. jar 源码依赖的jar包目录，以逗号隔开
1. ignore 忽略某些类型
1. version 文档版本号