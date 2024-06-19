plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("morning_routine_plugin") {
            id = name
            implementationClass = "com.lightricks.morning_routine_plugin.MorningRoutinePlugin"
        }
    }
}
