plugins {
    id("java")
    id("maven-publish")
    id("com.gradleup.shadow") version "8.3.5"
}

group = "top.alazeprt.aonebot"
version = "1.0.19-beta"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.16")
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

tasks.shadowJar {
    relocate("com.google", "top.alazeprt.aonebot.lib.com.google")
    relocate("org.java_websocket", "top.alazeprt.aonebot.lib.org.java_websocket")
}