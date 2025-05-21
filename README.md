# Bibliothèque Java — Projet de gestion de bibliothèque

Ce projet est un exemple de gestion de bibliothèque en Java, utilisant Maven et JUnit 5 pour les tests.

---

## Prérequis

- [Java JDK 8+](https://adoptopenjdk.net/) (recommandé : Java 11+)
- [Maven 3+](https://maven.apache.org/)

---

## Compilation du projet

Ouvrez un terminal à la racine du projet (là où se trouve le fichier `pom.xml`) puis lancez :

```bash
mvn clean compile
```

## Exécution des tests
Pour lancer tous les tests unitaires et fonctionnels avec Maven :

```bash
mvn test
```

Vous verrez les résultats des tests s’afficher dans le terminal. Les rapports détaillés sont disponibles dans le dossier target/surefire-reports/.