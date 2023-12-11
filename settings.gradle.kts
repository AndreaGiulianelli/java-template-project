plugins {
    id("org.danilopianini.gradle-pre-commit-git-hooks") version "1.1.16"
    id("com.gradle.enterprise") version "3.16"
}

rootProject.name = "java-template-project"

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishOnFailure() // Always publish Gradle Build Scan if there is a failure.
    }
}

gitHooks {
    preCommit {
        tasks("checkstyleMain")
        tasks("checkstyleTest")
        tasks("pmdMain")
        tasks("pmdTest")
        tasks("cpdJavaCheck")
        tasks("spotbugsMain")
        tasks("spotbugsTest")
    }

    commitMsg {
        conventionalCommits()
    }

    hook("post-commit") {
        from {
            "git verify-commit HEAD &> /dev/null; " +
                    "if (( $? == 1 )); then echo -e '\\033[0;31mWARNING(COMMIT UNVERIFIED): commit NOT signed\\033[0m';" +
                    "else echo -e '\\033[0;32mOK COMMIT SIGNED\\033[0m'; fi"
        }
    }

    createHooks(true)
}
