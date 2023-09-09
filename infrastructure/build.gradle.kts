plugins {
    id("java")
}

group = "com.oskarkraak"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("software.amazon.awscdk:aws-cdk-lib:2.94.0")
}

tasks.register<JavaExec>("runCdkApp") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass = "com.oskarkraak.discordimagesender.ImageSenderApp"
}
