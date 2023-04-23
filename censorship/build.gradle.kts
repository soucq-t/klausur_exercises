plugins {
    id("java")
    id("io.freefair.lombok") version("6.6-rc1")
}

group = "at.htlstp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}