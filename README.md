# Lost on Crampteus - Refactoring 2

Un jeu d'aventure et de survie développé en JavaFX.

## 🎯 Refactoring avec Design Patterns

Ce projet a été refactoré pour implémenter plusieurs design patterns reconnus, améliorant ainsi la maintenabilité, l'extensibilité et la qualité du code.

### Design Patterns Implémentés

1. **Strategy Pattern** - Système de déplacement des PNJ
2. **Abstract Factory Pattern** - Création d'items (Blocs, Armes, Consomables)
3. **Template Method Pattern** - Processus de craft
4. **Decorator Pattern** - Extension des capacités d'items (enchantements)
5. **Composite Pattern** - Structure hiérarchique d'items (bundles, conteneurs)

Pour plus de détails sur l'implémentation des design patterns, consultez [DESIGN_PATTERNS.md](DESIGN_PATTERNS.md).

## 🛠️ Technologies

- Java 21
- JavaFX 21
- Maven
- JUnit 5

## 📦 Structure du Projet

```
src/
├── main/java/universite_paris8/iut/mcatan/lostoncrampteus/
│   ├── Modele/
│   │   ├── Craft/
│   │   │   ├── Template/          # Template Method Pattern
│   │   │   ├── Craft.java
│   │   │   └── Recette.java
│   │   ├── Item/
│   │   │   ├── Factory/           # Abstract Factory Pattern
│   │   │   ├── Strategy/          # Strategy Pattern (utilisation items)
│   │   │   ├── Decorator/         # Decorator Pattern
│   │   │   ├── Composite/         # Composite Pattern
│   │   │   ├── Arme/
│   │   │   ├── Bloc/
│   │   │   └── Consomable/
│   │   ├── Personnage/
│   │   │   ├── Strategy/          # Strategy Pattern (déplacement)
│   │   │   ├── Acteur.java
│   │   │   ├── Joueur.java
│   │   │   ├── Pnj.java
│   │   │   └── Gronfleur.java
│   │   └── Monde.java
│   ├── Vue/
│   └── Controller/
└── test/java/
    └── universite_paris8/iut/mcatan/lostoncrampteus/
        └── Modele/
            └── DesignPatterns/    # Tests des design patterns

```

## 🚀 Compilation et Exécution

### Compiler le projet

```bash
mvn clean compile
```

### Exécuter les tests

```bash
mvn test
```

### Lancer l'application

```bash
mvn javafx:run
```

## ✅ Tests

Le projet comprend une suite complète de tests pour tous les design patterns implémentés :

- `StrategyPatternTest` - Tests du pattern Strategy
- `FactoryPatternTest` - Tests du pattern Factory
- `TemplateMethodPatternTest` - Tests du pattern Template Method
- `DecoratorPatternTest` - Tests du pattern Decorator
- `CompositePatternTest` - Tests du pattern Composite

## 📚 Documentation

- [DESIGN_PATTERNS.md](DESIGN_PATTERNS.md) - Documentation détaillée des design patterns

## 🎮 Fonctionnalités

- Système de craft avec recettes
- Inventaire du joueur
- Système de blocs destructibles
- Ennemis avec IA (Gronfleur)
- Système d'items au sol
- Gravité et physique
- Système d'armes et d'outils

## 🏗️ Architecture

Le projet suit une architecture MVC (Modèle-Vue-Contrôleur) et intègre plusieurs design patterns pour améliorer la flexibilité et la maintenabilité :

- **Modèle** : Logique métier et gestion des données
- **Vue** : Interface utilisateur JavaFX
- **Contrôleur** : Gestion des interactions utilisateur

## 🔮 Évolutions Futures

Grâce aux design patterns implémentés, le projet peut facilement évoluer avec :

- Nouveaux types de déplacements pour les PNJ (aérien, aquatique)
- Nouveaux types d'items via les factories
- Système d'enchantements avancé
- Conteneurs et coffres complexes
- Variantes du système de craft

## 📝 Licence

Ce projet est développé dans le cadre d'un projet universitaire à l'IUT de Montreuil (Université Paris 8).

## 👥 Contributeurs

- Équipe de développement IUT Montreuil
