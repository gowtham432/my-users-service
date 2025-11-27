plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.myapp"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

// CRITICAL: Force vulnerable versions to override Spring Boot's dependency management
configurations.all {
    resolutionStrategy {
        force("com.fasterxml.jackson.core:jackson-databind:2.12.7.1")
        force("com.fasterxml.jackson.core:jackson-core:2.12.7.1")
        force("com.fasterxml.jackson.core:jackson-annotations:2.9.0")
        force("mysql:mysql-connector-java:5.1.44")
        
        // Prevent Spring Boot from upgrading these
        eachDependency {
            if (requested.group == "com.fasterxml.jackson.core" && requested.name == "jackson-databind") {
                useVersion("2.9.8")
                because("Intentionally using vulnerable version for security testing")
            }
            if (requested.group == "mysql" && requested.name == "mysql-connector-java") {
                useVersion("5.1.44")
                because("Intentionally using vulnerable version for security testing")
            }
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        // Exclude the default jackson version that comes with spring-boot-starter-web
        exclude(group = "com.fasterxml.jackson.core", module = "jackson-databind")
    }
    
    // Explicitly add vulnerable versions
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.7.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.12.7.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.9.0")
    
    // Vulnerable MySQL connector
    runtimeOnly("mysql:mysql-connector-java:5.1.44")
    
    implementation("org.jetbrains:annotations:24.0.0")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Task to verify vulnerable dependencies are actually being used
tasks.register("verifyVulnerableDependencies") {
    doLast {
        println("=== Verifying Vulnerable Dependencies ===")
        configurations.runtimeClasspath.get().resolvedConfiguration.resolvedArtifacts.forEach {
            if (it.moduleVersion.id.group == "com.fasterxml.jackson.core" && 
                it.moduleVersion.id.name == "jackson-databind") {
                println("✓ jackson-databind version: ${it.moduleVersion.id.version}")
                if (it.moduleVersion.id.version != "2.9.8") {
                    throw GradleException("ERROR: jackson-databind should be 2.12.7.1 but is ${it.moduleVersion.id.version}")
                }
            }
            if (it.moduleVersion.id.group == "mysql" && 
                it.moduleVersion.id.name == "mysql-connector-java") {
                println("✓ mysql-connector-java version: ${it.moduleVersion.id.version}")
                if (it.moduleVersion.id.version != "5.1.44") {
                    throw GradleException("ERROR: mysql-connector-java should be 5.1.44 but is ${it.moduleVersion.id.version}")
                }
            }
        }
    }
}
