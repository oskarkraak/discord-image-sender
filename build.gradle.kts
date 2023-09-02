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
    implementation("com.amazonaws:aws-lambda-java-core:1.2.3")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}