# TP2 : Agence de location #

### Ce TP a pris place lors du cours R6.06 Maintenance Applicative de ma 3ème année de BUT Informatique. ###

Les objectifs de ce TP :
- Créer un projet java avec Gradle
- Utiliser différentes librairies et plugins pour tester une application Java

<br>Les différentes technologies utilisées lors de ce projet :
- Java 17 (https://www.oracle.com/fr/java/technologies/downloads/#jdk17-windows)
- JDK Amazon Corretto 17 (https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- Gradle 8.6 (https://gradle.org/install/)
- Jacoco 
- AssertJ core 3.25.3
- Mockito 5.10.0
- IntelliJ 2023.3.4 (https://www.jetbrains.com/fr-fr/idea/download/?section=windows)
<br>OU
- Eclipse 2023-12 (https://www.eclipse.org/downloads/)

<br>Créer un nouveau projet Java avec Gradle :
- Ouvrir un terminal
- Créer un nouveau dossier avec le nom de son choix (ici rentalAgency)
- Se déplacer dans le nouveau dossier
- Exécuter la commande `gradle init` et sélectionner les options suivantes
  - Type du projet : application
  - Langage : Java
  - Génerer plusieurs sous-projets : no
  - Build script DSL : Groovy
  - Test framework : JUnit Jupiter
  - Nom du projet : default
  - Version de Java : 17
  - Générer le build avec de nouvelles APIs : no

![init project](https://github.com/JulietteWalquan/rentalAgency/assets/92599126/2c2af580-dd0d-4da5-b540-1cf6a0dc20a0)

<br>Ouvrir un projet Gradle existant avec IntelliJ :
- Ouvrir InterlliJ
- Cliquer sur `File > New > Project from Existing Sources`
- Sélectionner le dossier qui contient votre projet Gradle puis cliquer sur `OK`
- Sélectionner `Import project from external model` puis sélectionner `Gradle` dans la liste en dessous
- Cliquer sur `Create`
- Cliquer sur `Help > Find Action`
- Taper dans la barre de recherche : `Choose Boot Java runtime`
- Cliquer sur la barre de recherche à côté de `New` et cliquer sur `Add Custom Runtime`
- Cliquer sur `Add JDK...` puis chercher `Amazon Corretto` dans vos fichiers
- Cliquer sur `OK`
<br>Le projet est prêt sur IntelliJ !

<br>Ouvrir un projet Gradle existant avec Eclipse :
- Créer un nouveau Workspace (dossier) puis l'ouvrir avec Eclipse
- Cliquer sur `File > Import > Gradle > Existing Gradle Project` puis `Next`
- Chercher le dossier racine de votre projet puis cliquer sur `Finish`
- Cliquer sur `Window > Preferences > Java > Installed JREs` puis cocher `Amazon Corretto`, s'il n'est pas présent, l'ajouter en cliqueant sur `Add`
- Cliquer sur `Help > Eclipse Marketplace` puis chercher le plugin `EclEmma` et l'installer
<br>Le projet est prêt sur Eclipse !<br><br>

#### Plus de détails pour ajouter des plugins, librairies ou nouvelles tâches : ####
Importer les différentes librairies et plugins :
- Ouvrir le fichier `build.gradle`
- Ajouter `id 'jacoco'` dans la catégorie `plugins`
- Ajouter `testImplementation group: 'org.assertj', name: 'assertj-core', version: '3.25.3'` dans la catégorie `dependencies`
- Ajouter `testImplementation group: 'org.mockito', name: 'mockito-core', version: '5.10.0'` dans la catégorie `dependencies`
- Build le projet pour prendre en compte les changements

![jacoco 1](https://github.com/JulietteWalquan/rentalAgency/assets/92599126/30ab46f4-73db-4726-a8a4-807642684d3b) 

![assertj mockito](https://github.com/JulietteWalquan/rentalAgency/assets/92599126/a4533b3f-4f5a-4409-ac12-ac76e47c1361)


<br>Créer des procédures pour lancer des tests spécifiques avec Gradle et générer des rapports
- Sous la tâche `'test'`, ajouter les 2 tâches suivantes :
  - ```gradle
    tasks.register('agencyTest', Test) {
      useJUnitPlatform() {
        includeTags("agency")
      }
    }
    ```
  - ```gradle
    tasks.register('utilsTest', Test) {
      useJUnitPlatform() {
        includeTags("utils")
      }
    }
    ```
- Ajouter en dessous :
    ```gradle
    tasks.jacocoTestReport {
      reports {
        html.outputLocation = layout.buildDirectory.dir('../jacocoReports/reportHTML')
        xml.required = true
        xml.outputLocation =  layout.buildDirectory.file('../jacocoReports/reportXML/report.xml')
        csv.required = false
      }
    }
    ```
- Build le projet pour prendre en compte les changements
- Ajouter en dessous une nouvelle tâche : `tasks.test { finalizedBy jacocoTestReport // report is always generated after tests run }`

![new tasks](https://github.com/JulietteWalquan/rentalAgency/assets/92599126/837a5211-c13e-498d-9b03-c9fe6a489d6f)

![reports](https://github.com/JulietteWalquan/rentalAgency/assets/92599126/669dfd6e-c3df-4931-a1a0-ba2f262410ef)


<br>Lancer les tests :
- Ouvrir un terminal à la racine du projet
- Exécuter la commande `gradle testsALancer` en remplaçant testsALancer par le groupe de tests que vous souhaitez lancer
  - `test` : pour lancer tous les tests
  - `agencyTest` : pour lancer les tests qui concernent uniquenement le packacge agency
  - `utilsTest` : pour lancer les tests qui consernent uniquement le package utils



