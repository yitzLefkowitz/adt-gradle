plugins {
    java
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "com.lightricks.java_sample.App"
        )
    }
    archiveBaseName.set("app")
    destinationDirectory.set(file("${buildDir}/libs/"))
}
