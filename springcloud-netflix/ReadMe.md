## 版本信息：
时间：2018年10月30日

|   项目   |  版本    |   说明   |
| ---- | ---- | ---- |
| JDK  |    1.8  |      |
| spring-boot   |   2.0.1.RELEASE   |      |
| spring-cloud  |   Finchley.SR2   |      |
| spring-cloud-netflix | 2.1.0.M1 | 本意使用与springcloud相同的版本，但发现spring资源库中还没有|

```groovy
apply from: "${project.projectDir}/common.gradle"
buildscript {
    apply from: "${project.projectDir}/common.gradle"

    repositories {
        mavenCentral()
        maven { url "https://repo.spring.io/libs-milestone/" }
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath "io.spring.gradle:dependency-management-plugin:1.0.6.RELEASE"
    }
}

apply plugin: "java"
apply plugin: 'org.springframework.boot'
apply plugin: "io.spring.dependency-management"

group 'com.sapling'
version '1.0-SNAPSHOT'

sourceCompatibility = 1.8
targetCompatibility = 1.8

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-netflix:${springCloudNetfixVersion}"
    }
}
dependencies {
    compile "org.springframework.cloud:spring-cloud-starter-eureka"
    compile "org.springframework.cloud:spring-cloud-starter-eureka-server"
}

repositories {
    maven {
        url 'https://repo.spring.io/libs-milestone'
    }
}

```

## 问题：
1、如果发现spring-cloud-starter-eureka与spring-cloud-starter-eureka-server包下不下来，可能是版本的问题，可以登录到https://repo.spring.io/libs-milestone，看看使用的版本的jar包是否存在