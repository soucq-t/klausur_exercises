plugins {
    id("java")
}

group = "at.htlstp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}