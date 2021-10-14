package build

object BuildModules {
    const val app = ":app"

    object Library {
        const val base = ":library:base"
        const val common = ":library:common"
        const val router = ":library:router"
        const val service = ":library:service"
    }

    object Module {
        const val home = ":module:home"
        const val recommend = ":module:recommend"
        const val girl = ":module:girl"
        const val me = ":module:me"
        const val search = ":module:search"
        const val details = ":module:details"
    }

}


