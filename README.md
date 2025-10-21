# Lost on Crampteus - Refactoring 2

## À Propos du Projet
Jeu vidéo de type "crafting/survival" développé en Java avec JavaFX.

## Architecture et Design Patterns

### Commit Important : 2caaf28 - Implémentation des Patterns Strategy et Factory

Ce commit représente une refactorisation majeure introduisant deux design patterns essentiels :

#### 1. Pattern Strategy (Déplacements des Entités)
**Problème résolu** : Le code de déplacement était dupliqué dans chaque classe d'entité.

**Solution** : Création d'une hiérarchie de stratégies de déplacement :
- `DeplacementStrategy` (interface)
- `DeplacementTerrestreStrategy` (implémentation pour les déplacements au sol)

**Bénéfices** :
- Code réutilisable et modulaire
- Facilité d'ajout de nouveaux types de déplacement (aquatique, aérien, etc.)
- Respect du principe Open/Closed

#### 2. Pattern Factory (Création des Items)
**Problème résolu** : Création dispersée et inconsistante des objets Item.

**Solution** : Centralisation via :
- `ItemFactory` (interface)
- `BlocFactory` (implémentation pour les blocs)

**Bénéfices** :
- Création centralisée et cohérente
- Facilité de maintenance
- Découplage du code client

### Principes SOLID Appliqués
- **S** (Single Responsibility) : Chaque classe a une responsabilité unique
- **O** (Open/Closed) : Ouvert à l'extension, fermé à la modification
- **L** (Liskov Substitution) : Les stratégies sont interchangeables
- **D** (Dependency Inversion) : Dépendance sur les abstractions, pas les implémentations

## Structure du Projet
```
src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/
├── Modele/
│   ├── Personnage/
│   │   ├── Strategy/
│   │   │   ├── DeplacementStrategy.java
│   │   │   └── DeplacementTerrestreStrategy.java
│   │   ├── Gronfleur.java
│   │   ├── Joueur.java
│   │   └── Pnj.java
│   └── Item/
│       └── Factory/
│           ├── ItemFactory.java
│           └── BlocFactory.java
├── Vue/
└── Controller/
```

## Compilation et Exécution

### Prérequis
- Java 17 ou supérieur
- Maven 3.6+
- JavaFX 21

### Compiler le projet
```bash
mvn clean compile
```

### Exécuter le projet
```bash
mvn javafx:run
```

### Exécuter les tests
```bash
mvn test
```

## Documentation Complète

Pour une explication détaillée du commit 2caaf28, consultez :
**[EXPLICATION_COMMIT_2caaf28.md](EXPLICATION_COMMIT_2caaf28.md)**

Ce document contient :
- Analyse détaillée du problème initial
- Explication des patterns implémentés
- Exemples de code avant/après
- Bénéfices architecturaux
- Évolutions futures possibles

## Contributeurs
- Chikage (lucas.benjebara93@gmail.com)

## Licence
Ce projet est un projet académique réalisé dans le cadre de la SAE Développement à l'IUT de Paris 8.
