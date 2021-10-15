import configures.libraryConfigure

libraryConfigure(projectConfigure = {
    dependencies {
        commonProcessors()
        implementation(project(build.BuildModules.Library.base))
    }
})


