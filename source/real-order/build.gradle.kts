import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "2.5.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.20"
    kotlin("plugin.spring") version "1.5.20"
}

group = "edu.jmu.seven"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public/") }
    maven {
        url = uri("https://maven.aliyun.com/repository/spring/")
    }
    mavenLocal()
    mavenCentral()
}

dependencies {

    // https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-generator
//    implementation("com.baomidou:mybatis-plus-generator:3.5.0")
    // https://mvnrepository.com/artifact/org.mybatis/mybatis
    implementation("org.mybatis:mybatis:3.5.7")
    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui
//    implementation("org.springdoc:springdoc-openapi-ui:1.5.9")

//    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
//    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    // https://mvnrepository.com/artifact/org.apache.spark/spark-core
    implementation("org.apache.spark:spark-core_2.12:3.1.2")
// https://mvnrepository.com/artifact/org.apache.spark/spark-launcher
    implementation("org.apache.spark:spark-launcher_2.12:3.1.2")
// https://mvnrepository.com/artifact/org.apache.spark/spark-mllib
    compileOnly("org.apache.spark:spark-mllib_2.12:3.1.2")
// https://mvnrepository.com/artifact/org.apache.spark/spark-streaming
    compileOnly("org.apache.spark:spark-streaming_2.12:3.1.2")

    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-boot-starter
    implementation("com.baomidou:mybatis-plus-boot-starter:3.4.3.1")

    // Lombok
    implementation("org.projectlombok:lombok:1.18.18")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

//    developmentOnly("org.springframework.boot:spring-boot-devtools")
    // https://mvnrepository.com/artifact/eu.bitwalker/UserAgentUtils
    // 浏览器解析
    implementation("eu.bitwalker:UserAgentUtils:1.21")
    // FastJson
    implementation("com.alibaba:fastjson:1.2.76")


}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "16"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootBuildImage> {
    builder = "paketobuildpacks/builder:tiny"
    environment = mapOf("BP_NATIVE_IMAGE" to "true")
}
