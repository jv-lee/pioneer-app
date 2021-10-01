libraryConfigure()

dependencies {
    implementation(project(BuildModules.Library.base))
    DependenciesEach.processors.forEach { kapt(it) }
}
