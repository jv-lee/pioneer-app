import configures.libraryConfigure

libraryConfigure()

dependencies {
    commonProcessors()
    implementation(project(build.BuildModules.Library.base))
}
