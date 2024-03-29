import build.BuildModules
import dependencies.Dependencies
import dependencies.ProcessorsDependencies
import dependencies.TestDependencies
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.exclude
import org.gradle.kotlin.dsl.project

//app依赖扩展配置
fun DependencyHandlerScope.appDependencies() {
    baseService()
    commonProcessors()
    commonModules()
    commonTest()
}

//添加基础服务依赖
fun DependencyHandlerScope.baseService() {
    implementation(project(BuildModules.Library.service))
}

//注解处理器基础依赖
fun DependencyHandlerScope.commonProcessors() {
    kapt(ProcessorsDependencies.annotation)
    kapt(ProcessorsDependencies.room)
    kapt(ProcessorsDependencies.glide)
    kapt(ProcessorsDependencies.autoService)
}

//各业务组建基础依赖
fun DependencyHandlerScope.commonModules() {
    implementation(project(BuildModules.Module.details))
    implementation(project(BuildModules.Module.search))
    implementation(project(BuildModules.Module.home))
    implementation(project(BuildModules.Module.recommend))
    implementation(project(BuildModules.Module.girl))
    implementation(project(BuildModules.Module.me))
}

//基础测试依赖
fun DependencyHandlerScope.commonTest() {
    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.junitAndroid)
    androidTestImplementation(TestDependencies.espresso)
    debugImplementation(TestDependencies.leakcanaryDebug)
    releaseImplementation(TestDependencies.leakcanaryRelease)
}

//基础依赖配置
fun DependencyHandlerScope.commonDependencies() {
    api(Dependencies.coreKtx)
    api(Dependencies.coroutines)

    api(Dependencies.lifecycle)
    api(Dependencies.lifecycleLivedata)
    api(Dependencies.lifecycleViewModel)

    api(Dependencies.activity)
    api(Dependencies.fragment)

    api(Dependencies.multidex)

    api(Dependencies.appcompat)
    api(Dependencies.material)
    api(Dependencies.recyclerview)
    api(Dependencies.constraint)
    api(Dependencies.viewpager2)
    api(Dependencies.swipeRefreshLayout)

    api(Dependencies.navigationFragment)
    api(Dependencies.navigationUi)

    api(Dependencies.room)
    api(Dependencies.roomRuntime)

    api(Dependencies.glide)
    api(Dependencies.glideOkhttp3)
    api(Dependencies.glideAnnotations)

    api(Dependencies.retrofit)
    api(Dependencies.retrofitConverterGson) { exclude("com.google.code.gson") }
    api(Dependencies.retrofitConverterScalars)

    api(Dependencies.gson)

    api(Dependencies.protobuf)

    api(Dependencies.autoService)

    api(Dependencies.imageTools)
} 

