// dependsOn
tasks.register<PutOnSocks>("putOnSocks") {
    mustRunAfter("takeShower")
}

tasks.register<PutOnShoes>("putOnShoes") {
    dependsOn("putOnSocks")
}

// finalizedBy
tasks.register<EatBreakfast>("eatBreakfast") {
    finalizedBy("brushYourTeeth")
}

tasks.register<BrushYourTeeth>("brushYourTeeth")

// mustRunAfter
tasks.register<TakeShower>("takeShower")

tasks.register<PutOnDeodorant>("putOnDeodorant") {
    shouldRunAfter("takeShower")
}

tasks.register("morningRoutine") {
    dependsOn("takeShower", "putOnDeodorant", "putOnShoes", "eatBreakfast")
}

abstract class PutOnSocks: DefaultTask() {
    @TaskAction
    fun putOnSocks() {
        println("Putting on socks.")
    }
}

abstract class PutOnShoes: DefaultTask() {
    @TaskAction
    fun putOnShoes() {
        println("Putting on shoes.")
    }
}

abstract class EatBreakfast: DefaultTask() {
    @TaskAction
    fun eatBreakfast() {
        println("Eating breakfast.")
    }
}

abstract class BrushYourTeeth: DefaultTask() {
    @TaskAction
    fun brushYourTeeth() {
        println("Brushing teeth.")
    }
}

abstract class TakeShower: DefaultTask() {
    @TaskAction
    fun takeShower() {
        println("Taking a shower.")
    }
}

abstract class PutOnDeodorant: DefaultTask() {
    @TaskAction
    fun putOnDeodorant() {
        println("Putting on deodorant.")
    }
}
