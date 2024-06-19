// dependsOn
tasks.register("putOnSocks") {
    doLast { println("Putting on socks.") }
}

tasks.register("putOnShoes") {
    dependsOn("putOnSocks")
    doLast { println("Putting on shoes.") }
}

// finalizedBy
tasks.register("eatBreakfast") {
    finalizedBy("brushYourTeeth")
    doLast { println("Eating breakfast.") }
}

tasks.register("brushYourTeeth") {
    doLast { println("Brushing teeth.") }
}

// mustRunAfter
tasks.register("takeShower") {
    doLast { println("Taking a shower.") }
}

tasks.register("putOnDeodorant") {
    shouldRunAfter("takeShower")
    doLast{ println("Putting on deodorant.") }
}

tasks.register("morningRoutine") {
    dependsOn("takeShower", "putOnShoes", "eatBreakfast")
}
