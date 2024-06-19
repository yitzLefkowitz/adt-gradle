// dependsOn
tasks.register<MorningRoutineTask>("putOnSocks") {
    action = "Putting on socks."
    mustRunAfter("takeShower")
}

tasks.register("putOnShoes", MorningRoutineTask::class.java) {
    action = "Putting on shoes."
    dependsOn("putOnSocks")
}

// finalizedBy
tasks.register<MorningRoutineTask>("eatBreakfast") {
    action = "Eating breakfast. Nom nom nom."
    finalizedBy("brushYourTeeth")
}

tasks.register<MorningRoutineTask>("brushYourTeeth") {
    action = "Brushing teeth."
}

// mustRunAfter
tasks.register<MorningRoutineTask>("takeShower") {
    action = "Taking a shower."
}

tasks.register<MorningRoutineTask>("putOnDeodorant") {
    action = "Putting on deodorant."
    shouldRunAfter("takeShower")
}

tasks.register("morningRoutine") {
    dependsOn("takeShower", "putOnDeodorant", "putOnShoes", "eatBreakfast")
}

abstract class MorningRoutineTask: DefaultTask() {
    override fun getGroup(): String {
        return "Morning Routine"
    }

    @Input
    lateinit var action: String

    @TaskAction
    fun doMorningStuff() {
        println(action)
    }
}
