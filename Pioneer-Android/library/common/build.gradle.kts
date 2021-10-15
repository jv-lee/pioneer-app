import configures.libraryConfigure
import configures.plugins.paramsConfigure

libraryConfigure(projectConfigure = {
    paramsConfigure()

    dependencies {
        commonProcessors()
        implementation(project(build.BuildModules.Library.base))
    }
})


