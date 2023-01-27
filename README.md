<h1 align="center">Project-Skeleton</h1>
<p>Current project can be used to start any new project you may want. It contains configurations for signing application, basic logic for implementing data, domain and presentation layers.</p>

# Technology stack
**MVVM** and is using with Clean architecture for structure of project.
### Automatic build system
[Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) in combination with Gradle is using for automatic build system.
All classes with dependencies are lie in the directory [root/buildSrc/src/main/kotlin](https://github.com/yuriysurzhikov/Project-Skeleton/tree/master/buildSrc/src/main/kotlin). 

### Project structure

# Usage of project
1. First thing you need to do is overwrite project id in the whole project. First of all you have to change project name to your own, which you preffer, then you have to change config
```
rootProject.name = "Your-Project-Name"
```
in the file [settings.gradle.kts](https://github.com/yuriysurzhikov/Project-Skeleton/blob/master/settings.gradle.kts). After that you need to override the package name for root project. By default it has com.yuriisurzhykov.projectskeleton id, and you have to override it to your own. 
2. The next step is to create signing file(e.g. _.jks_), then put this file to root/app folder.
3. The next step is to create signing.properties file in folder [root/app](https://github.com/yuriysurzhikov/Project-Skeleton/tree/master/app).
In this file you have to have the next configs:
```
keystoreFile="filename.jks" // use your own file name for
keystorePassword="password" // your password for file
keyAlias="keyAlias"         // your alias name
keyPassword="password"      // your password for alias
```
4. After that in the file [ProjectConfigs](https://github.com/yuriysurzhikov/Project-Skeleton/blob/master/buildSrc/src/main/kotlin/ProjectConfigs.kt) configure settings for your project, i.e. min version, max version and the most important is application id.
