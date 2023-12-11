plugins {
    application
    alias(libs.plugins.java.qa)
}

group = "io.github.andreagiulianelli"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.bundles.java.testing)
    testRuntimeOnly(libs.junit.engine)
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        showCauses = true
        showStackTraces = true
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}