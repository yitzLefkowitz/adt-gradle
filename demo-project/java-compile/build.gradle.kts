abstract class JavaCompileTask : DefaultTask() {
    @get:InputDirectory
    abstract var inputDirLocation: File

    @get:OutputDirectory
    abstract var outputDirectory: File

    @TaskAction
    fun compile() {
        val javaFiles = inputDirLocation.walkTopDown()
            .filter { it.isFile && it.extension == "java" }
            .map { it.absolutePath }
            .toList()
            .toTypedArray()
        println("Compiling")
        project.exec {
            commandLine("javac", "-d", outputDirectory.absolutePath, *javaFiles)
        }
    }
}

abstract class JavaJarTask : DefaultTask() {
    @get:InputDirectory
    abstract var inputDir: File

    @get:OutputFile
    abstract var outputJar: File

    @TaskAction
    fun createJar() {
        project.exec {
            commandLine("jar", "cf", outputJar.absolutePath, "-C", inputDir.absolutePath, ".")
        }
    }
}

tasks.register<JavaCompileTask>("compile") {
    inputDirLocation = file("./src/main/java")
    outputDirectory = file("${buildDir}/classes")
}

tasks.register<JavaJarTask>("createJar") {
    dependsOn("compile")
    inputDir = tasks.named<JavaCompileTask>("compile").get().outputDirectory
    outputJar = file("${buildDir}/libs/app.jar")
}
