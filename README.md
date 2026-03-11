# YaNkap
A platform responsible for making transfers of credit rapid and accesible
Name :YaNkap. 
We present to you an App that aims to speed up and increase the amount of transactions performed by callboxers, lambda users and individuals.
Instead of typing long constraining codes like *126*14*681785552*1000#, the user will just select opération enter necessary variables and execute
with the correct Sim card the confirm with his pin code .We aim to solve the problems of hard to remember codes,slow transactions,typing errors and confusing menus.
What to note is that unlike Qr codes we will be less constraining and availaible even offline for regions orperiods when network is innaccesible or  unstable.
We also aim to target as listed above not only users but also momo operators
This is a Kotlin Multiplatform project targeting Android, iOS, Web.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* [/shared](./shared/src) is for the code that will be shared between all targets in the project.
  The most important subfolder is [commonMain](./shared/src/commonMain/kotlin). If preferred, you
  can add code to the platform-specific folders here too.

* [/webApp](./webApp) contains web React application. It uses the Kotlin/JS library produced
  by the [shared](./shared) module.
