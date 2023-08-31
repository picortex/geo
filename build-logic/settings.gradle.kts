dependencyResolutionManagement {
    versionCatalogs {
        File(rootDir.parentFile.parentFile, "versions/gradle/versions").listFiles().map {
            it.nameWithoutExtension to it.absolutePath
        }.forEach { (name, path) ->
            create(name) { from(files(path)) }
        }
    }
}
