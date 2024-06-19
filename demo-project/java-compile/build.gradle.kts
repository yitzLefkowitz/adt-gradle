abstract class JavaCompileTask : DefaultTask() {
    @get:InputDirectory
    abstract var inputDirLocation: File

    @get:OutputDirectory
    abstract var outputDirectory: File

    @TaskAction
    fun compile() {

    }
}

abstract class JavaJarTask : DefaultTask() {
    @get:InputDirectory
    abstract var inputDir: File

    @get:OutputFile
    abstract var outputJar: File

    @TaskAction
    fun createJar() {

    }
}

tasks.register<JavaCompileTask>("compile") {

}

tasks.register<JavaJarTask>("createJar") {

}
