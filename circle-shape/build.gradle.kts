plugins {
    id("java")
    id("io.freefair.lombok") version ("6.6-rc1")
    id("org.openjfx.javafxplugin") version ("0.0.13")
}

group = "at.htlstp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

javafx {
    version = "20"
    modules = mutableListOf("javafx.controls")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}