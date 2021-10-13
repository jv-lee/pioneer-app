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
    commonProcessors()
    implementation(project(BuildModules.Library.base))
}
