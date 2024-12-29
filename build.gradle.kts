plugins {
    id("java")
    id("maven-publish")
    id("com.gradleup.shadow") version "8.3.5"
}

group = "top.alazeprt.aonebot"
version = "1.0.6-beta"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.java-websocket:Java-WebSocket:1.5.7")
    implementation("com.google.code.gson:gson:2.11.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}