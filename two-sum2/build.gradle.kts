plugins {
    id("java-library")
}

tasks.register<JavaExec>("run") {
    mainClass = "by.semenyukna.ts2.Solution"
    classpath = project.extensions.getByType(JavaPluginExtension::class.java)
        .sourceSets.getByName("main")
        .runtimeClasspath
}
