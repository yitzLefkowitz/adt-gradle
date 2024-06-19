package com.lightricks.morning_routine_plugin

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.register

class MorningRoutinePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val morningRoutine =
            target.extensions.create(MORNING_ROUTINE_EXTENSION_NAME, MorningRoutineExtension::class.java)

        target.tasks.register<MorningRoutineTask>("putOnSocks") {
            action = "Putting on socks."
            mustRunAfter("takeShower")
        }

        target.tasks.register("putOnShoes", MorningRoutineTask::class.java) {
            action = "Putting on shoes."
            dependsOn("putOnSocks")
        }

        target.tasks.register<MorningRoutineTask>("makeCoffee") {
            action = "Making a coffee."
        }

        target.tasks.register<MorningRoutineTask>("eatBreakfast") {
            action = "Eating breakfast. Nom nom nom."
            if (morningRoutine.withCoffee) {
                dependsOn("makeCoffee")
            }
            finalizedBy("brushYourTeeth")
        }

        target.tasks.register<MorningRoutineTask>("brushYourTeeth") {
            action = "Brushing teeth."
        }

        target.tasks.register<MorningRoutineTask>("takeShower") {
            action = "Taking a shower."
        }

        target.tasks.register<MorningRoutineTask>("putOnDeodorant") {
            action = "Putting on deodorant."
            shouldRunAfter("takeShower")
        }

        target.tasks.register("morningRoutine") {
            group = MORNING_ROUTINE_GROUP_NAME
            dependsOn("takeShower", "putOnDeodorant", "putOnShoes", "eatBreakfast")
        }
    }

    internal abstract class MorningRoutineTask : DefaultTask() {
        @Internal
        override fun getGroup(): String {
            return MORNING_ROUTINE_GROUP_NAME
        }

        @Input
        lateinit var action: String

        @TaskAction
        fun doMorningStuff() {
            println(action)
        }
    }
}

private const val MORNING_ROUTINE_EXTENSION_NAME = "morningRoutine"
private const val MORNING_ROUTINE_GROUP_NAME = "Morning Routine"

public abstract class MorningRoutineExtension {
    var withCoffee: Boolean = false
}