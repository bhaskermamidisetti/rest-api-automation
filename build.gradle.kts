plugins {
    id("java")
}

group = "api-automation"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.rest-assured:rest-assured:5.5.0")
    testImplementation("io.cucumber:cucumber-java:7.18.0")
    testImplementation("io.cucumber:cucumber-junit:7.18.0")
    implementation("io.cucumber:gherkin:32.1.1")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("org.json:json:20241224")
    implementation("net.masterthought:cucumber-reporting:5.7.0")
}

tasks.test {
    useJUnitPlatform()
}

configurations {
    create("cucumberRuntime") {
        extendsFrom(configurations.getByName("testRuntimeClasspath"))
    }
}

tasks.register("cucumber") {
    dependsOn("assemble", "compileTestJava")
    doLast {
        javaexec {
            mainClass.set("io.cucumber.core.cli.Main")
            classpath = configurations.getByName("cucumberRuntime") +
                    sourceSets["main"].output +
                    sourceSets["test"].output
            args = listOf("--plugin", "pretty", "--glue", "com.restapi.automation.test", "src/test/resources")
        }
    }
}
