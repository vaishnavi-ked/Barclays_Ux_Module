/*
 * Copyright (c) 2024 vaishnavi. All Rights Reserved.
 */
import com.tridium.gradle.plugins.grunt.task.GruntBuildTask
import com.tridium.gradle.plugins.module.util.ModulePart.RuntimeProfile.*

plugins {
  // The Niagara Module plugin configures the "moduleManifest" extension and the
  // "jar" and "moduleTestJar" tasks.
  id("com.tridium.niagara-module")

  id("com.tridium.niagara-grunt")
  
    // The signing plugin configures the correct signing of modules. It requires
    // that the plugin also be applied to the root project.
    id("com.tridium.niagara-signing")

    // Configures JaCoCo for the "niagaraTest" task of this module.
    id("com.tridium.niagara-jacoco")

    // The Annotation processors plugin adds default dependencies on "Tridium:nre"
    // for the "annotationProcessor" and "moduleTestAnnotationProcessor"
    // configurations by creating a single "niagaraAnnotationProcessor"
    // configuration they extend from. This value can be overridden by explicitly
    // declaring a dependency for the "niagaraAnnotationProcessor" configuration.
    id("com.tridium.niagara-annotation-processors")

    // The niagara_home repositories convention plugin configures !bin/ext and
    // !modules as flat-file Maven repositories so that projects in this build can
    // depend on already-installed Niagara modules.
    id("com.tridium.convention.niagara-home-repositories")
    
}

description = "New Module Created Without adding Skeleton files"

moduleManifest {
  moduleName.set("Barclays")
  runtimeProfile.set(ux)
}

// See documentation at module://docDeveloper/doc/build.html#dependencies for the supported
// dependency types
dependencies {
  api("Tridium:js-ux")
  api(":baja")
  api(":web-rt")
  implementation(files("C:/Niagara/Niagara-4.14.0.162/bin/ext/nre.jar"))
}

tasks.named<Jar>("jar") {
  from("src") {
    include("rc/")
  }
}

tasks.named<Jar>("moduleTestJar") {
  from("srcTest") {
    include("rc/")
  }
}

tasks.named<GruntBuildTask>("gruntBuild") {
  tasks("babel:dist", "less", "copy:dist", "requirejs")
}