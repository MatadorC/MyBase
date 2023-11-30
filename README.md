# MyBase
自己使用的基础类

引用规则：
Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
dependencyResolutionManagement {
repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
repositories {
mavenCentral()
maven { url 'https://www.jitpack.io' }
}
}
Step 2. Add the dependency
dependencies {
implementation 'com.github.MatadorC:MyBase:Tag'
}