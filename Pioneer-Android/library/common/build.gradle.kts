libraryConfigure()

androidConfigure {
    buildTypes {
        getByName(BuildType.DEBUG) {
            BuildDebug.paramsMap.onEach {
                buildConfigField("String",it.key,"\"" + it.value + "\"")
            }
        }
        getByName(BuildType.RELEASE) {
            BuildRelease.paramsMap.onEach {
                buildConfigField("String",it.key,"\"" + it.value + "\"")
            }
        }
    }
}

dependencies {
    implementation(project(BuildModules.Library.base))
    DependenciesEach.processors.forEach { kapt(it) }
}
