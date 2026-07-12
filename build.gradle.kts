plugins {
    java
    id("io.qameta.allure") version "2.12.0"
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite-api")
    testRuntimeOnly("org.junit.platform:junit-platform-suite-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("io.cucumber:cucumber-java:7.14.0")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.14.0")
    testImplementation("io.cucumber:cucumber-picocontainer:7.14.0")

    testImplementation("com.codeborne:selenide:7.9.3")
    testImplementation("io.rest-assured:rest-assured:5.5.6")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    testImplementation("io.github.cdimascio:dotenv-java:3.2.0")
    testImplementation("io.qameta.allure:allure-cucumber7-jvm:2.35.1")
    testImplementation("io.qameta.allure:allure-rest-assured:2.35.1")
    testImplementation("io.qameta.allure:allure-selenide:2.35.1")
    testImplementation("io.qameta.allure:allure-junit5:2.35.1")
    testImplementation("com.mysql:mysql-connector-j:9.4.0")
    testImplementation("io.github.cdimascio:dotenv-java:3.2.0")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")

}

tasks.test {
    useJUnitPlatform()
    systemProperty("cucumber.plugin", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
}
val testSourceSet = sourceSets.test.get()

fun Test.configureTestTask() {

    testClassesDirs = testSourceSet.output.classesDirs
    classpath = testSourceSet.runtimeClasspath

    useJUnitPlatform()

    systemProperty(
        "cucumber.plugin",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    )
}
val reporting by tasks.registering(Test::class) {

    description = "Runs Reporting framework tests"

    group = "verification"

    configureTestTask()

    include("**/ReportingConfigTest.class")
}
