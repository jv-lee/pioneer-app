libraryConfigure()

dependencies {
    commonProcessors()
    api(project(BuildModules.Library.base))
    api(project(BuildModules.Library.common))
    api(project(BuildModules.Library.router))
}
