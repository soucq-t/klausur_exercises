plugins {
    id("java")
}

group = "at.htlstp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}