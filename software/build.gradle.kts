plugins {
    id("java")
}

group = "com.oskarkraak"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.amazonaws:aws-lambda-java-core:1.2.3")
}
