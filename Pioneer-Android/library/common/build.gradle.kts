import configures.libraryConfigure
import configures.paramsConfigure

libraryConfigure()

paramsConfigure()

dependencies {
    commonProcessors()
    implementation(project(build.BuildModules.Library.base))
}
