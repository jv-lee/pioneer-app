import configures.libraryConfigure

libraryConfigure(projectConfigure = {
    dependencies {
        commonProcessors()
        api(project(build.BuildModules.Library.base))
        api(project(build.BuildModules.Library.common))
        api(project(build.BuildModules.Library.router))
    }
})


