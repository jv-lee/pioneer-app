libraryConfigure()

dependencies {
    api(project(BuildModules.Library.base))
    api(project(BuildModules.Library.common))
    api(project(BuildModules.Library.router))
    DependenciesEach.processors.forEach { kapt(it) }
}
