libraryConfigure()

dependencies {
    DependenciesEach.support.forEach { api(it) }
    DependenciesEach.processors.forEach { kapt(it) }
}
