plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {

    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // JUnit Platform Suite (Required for Cucumber Runner)
    testImplementation("org.junit.platform:junit-platform-suite:1.10.0")

    // Cucumber
    testImplementation("io.cucumber:cucumber-java:7.18.1")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.18.1")

    // REST Assured
    implementation("io.rest-assured:rest-assured:5.5.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")

    // MySQL
    implementation("com.mysql:mysql-connector-j:9.3.0")

    // Selenide
    testImplementation("com.codeborne:selenide:7.5.1")

    // Allure
    testImplementation("io.qameta.allure:allure-junit5:2.29.0")
    testImplementation("io.qameta.allure:allure-rest-assured:2.29.0")
    testImplementation("io.qameta.allure:allure-selenide:2.29.0")

    // Testcontainers
    testImplementation("org.testcontainers:junit-jupiter:1.20.1")
    testImplementation("org.testcontainers:mysql:1.20.1")

    // Flyway
    implementation("org.flywaydb:flyway-core:11.0.0")
    implementation("org.flywaydb:flyway-mysql:11.0.0")
}

tasks.test {
    useJUnitPlatform()
}