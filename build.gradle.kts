plugins {
    application
}

repositories {
    mavenCentral()
}

tasks.withType<JavaCompile> {
    val compilerArgs = options.compilerArgs
    //NOTE: This is required for @P
    compilerArgs.addAll(listOf("-parameters"))
}
application {
    mainClass.set("com.splunk.example.MicrometerTimerApp")
    applicationDefaultJvmArgs = listOf(
        "-javaagent:splunk-otel-javaagent-1.24.0.jar",
        "-Dotel.javaagent.debug=true",
        "-Dsplunk.metrics.enabled=true",
        "-Dsplunk.metrics.export.interval=10s",
        "-Dotel.resource.attributes=deployment.environment=micrometer-perc",
        "-Dotel.service.name=MicrometerTimerApp"
    )
}

dependencies {
    implementation("io.micrometer:micrometer-core:1.11.0")
}
