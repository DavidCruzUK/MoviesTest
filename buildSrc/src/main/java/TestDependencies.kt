import version.TestVersions
import version.Versions

object TestDependencies {
    const val JUnit = "junit:junit:${TestVersions.JUnit}"
    const val Mockk = "io.mockk:mockk:${TestVersions.Mockk}"

    const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Coroutines}"
}