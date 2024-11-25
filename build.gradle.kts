plugins {
    id("java")
    id("io.github.goooler.shadow") version "8.1.7"
    id("maven-publish") // Add ./gradlew publishToMavenLocal
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("org.sonarqube") version "5.0.0.4638"
}

group="fr.formiko.minecraftssh"
version="1.1.2"
description="Allow some shell commands as if minecraft console was an ssh connexion."

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://repo.aikar.co/content/groups/aikar/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.3-R0.1-SNAPSHOT")
    implementation("org.bstats:bstats-bukkit:3.0.2")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
    implementation("com.github.FormikoLudo:Utils:0.0.9")
}

java {
  // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
  toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    shadowJar {
        val prefix = "${project.group}.lib"
        sequenceOf(
            "co.aikar",
            "org.bstats",
            "fr.formiko.utils"
        ).forEach { pkg ->
            relocate(pkg, "$prefix.$pkg")
        }

        archiveFileName.set("${project.name}-${project.version}.jar")
    }
    assemble {
        dependsOn(shadowJar)
    }
    processResources {
        val props = mapOf(
            "name" to project.name,
            "version" to project.version,
            "description" to project.description,
            "apiVersion" to "1.18",
            "group" to project.group
        )
        inputs.properties(props)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("1.21.3")
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}