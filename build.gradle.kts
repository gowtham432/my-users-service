plugins {
    java
    id("org.springframework.boot") version "3.3.11" // Updated Spring Boot version
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.myapp"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Latest annotations
    implementation("org.jetbrains:annotations:24.1.0")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    runtimeOnly("com.mysql:mysql-connector-j:8.4.0")

    // Fully compatible and secure version
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    // Updated dependencies to fix vulnerabilities
    implementation("org.apache.tomcat.embed:tomcat-embed-core:10.1.47") // Updated
    implementation("ch.qos.logback:logback-core:1.5.19") // Updated
    implementation("org.springframework:spring-web:6.1.21") // Updated
    implementation("org.springframework:spring-security-core:6.3.5") // Updated
    implementation("org.springframework:spring-security-crypto:6.3.8") // Updated
    implementation("org.springframework:spring-context:6.1.20") // Updated
    implementation("org.apache.commons:commons-lang3:3.18.0") // Updated
    implementation("com.nimbusds:nimbus-jose-jwt:9.37.4") // Updated
    implementation("net.minidev:json-smart:2.5.2") // Updated

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
