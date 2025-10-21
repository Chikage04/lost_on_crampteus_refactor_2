# Explication du Commit 2caaf28 : Implémentation des Design Patterns Strategy et Factory

## Contexte du Projet
Ce document explique les motivations et les bénéfices de l'implémentation des design patterns Strategy et Factory dans le commit `2caaf28` du projet "Lost on Crampteus".

## Résumé du Commit
**Message du commit** : "design patern stratégy sur les déplacement des entité (monstre)"
**Date** : 9 octobre 2025
**Modifications** : 115 ajouts, 46 suppressions sur 14 fichiers

---

## 1. Problématique Initiale

### Avant le Refactoring
Avant l'implémentation de ces patterns, le code présentait plusieurs problèmes architecturaux :

1. **Couplage fort** : La logique de déplacement des entités (monstres, PNJ) était directement intégrée dans chaque classe d'entité
2. **Duplication de code** : Plusieurs entités utilisaient des algorithmes de déplacement similaires, entraînant une répétition de code
3. **Faible extensibilité** : Ajouter un nouveau type de déplacement (aquatique, aérien, etc.) nécessitait de modifier directement les classes existantes
4. **Violation du principe Open/Closed** : Les classes n'étaient pas fermées aux modifications mais ouvertes à l'extension

---

## 2. Solution Adoptée : Le Design Pattern Strategy

### Qu'est-ce que le Pattern Strategy ?
Le pattern Strategy est un pattern comportemental qui permet de :
- Définir une famille d'algorithmes
- Encapsuler chaque algorithme
- Rendre ces algorithmes interchangeables

### Implémentation dans le Projet

#### a) Interface `DeplacementStrategy`
```java
public interface DeplacementStrategy {
    void deplacer(Pnj pnj);
}
```

**Rôle** : Définit le contrat que toutes les stratégies de déplacement doivent respecter.

#### b) Implémentation Concrète : `DeplacementTerrestreStrategy`
```java
public class DeplacementTerrestreStrategy implements DeplacementStrategy {
    private double vitesse;
    private int portee;

    public DeplacementTerrestreStrategy(double vitesse, int portee) {
        this.vitesse = vitesse;
        this.portee = portee;
    }

    @Override
    public void deplacer(Pnj pnj) {
        AlgoRecherche.DeplacementTerrestre(pnj, vitesse, portee);
    }
}
```

**Rôle** : Encapsule l'algorithme de déplacement terrestre avec des paramètres configurables (vitesse et portée).

#### c) Utilisation dans la Classe `Gronfleur`
```java
public class Gronfleur extends Ennemis {
    private final DeplacementTerrestreStrategy deplacementStrategy;
    
    public Gronfleur() {
        super(100);
        this.deplacementStrategy = new DeplacementTerrestreStrategy(1.5, 200);
    }

    @Override
    public void seDeplacer() {
        if (estVivant()) {
            deplacementStrategy.deplacer(this);
            attaquer();
        }
    }
}
```

**Rôle** : La classe `Gronfleur` délègue la responsabilité du déplacement à une stratégie, respectant le principe de responsabilité unique.

---

## 3. Bénéfices du Pattern Strategy

### 3.1 Flexibilité
- **Changement dynamique** : Il est maintenant possible de changer le comportement de déplacement d'une entité à l'exécution
- **Paramétrage** : Chaque stratégie peut avoir ses propres paramètres (vitesse, portée) sans affecter les autres

### 3.2 Extensibilité
Pour ajouter un nouveau type de déplacement (par exemple, aquatique ou aérien), il suffit de :
1. Créer une nouvelle classe implémentant `DeplacementStrategy`
2. L'utiliser dans les entités concernées

```java
// Exemple futur d'extension
public class DeplacementAquatiqueStrategy implements DeplacementStrategy {
    @Override
    public void deplacer(Pnj pnj) {
        // Logique de déplacement aquatique
    }
}
```

### 3.3 Testabilité
- Les stratégies peuvent être testées indépendamment des entités
- Il est possible de créer des stratégies de test (mock) facilement

### 3.4 Respect des Principes SOLID

#### S - Single Responsibility Principle
Chaque classe a une seule responsabilité :
- `Gronfleur` : Gérer l'entité monstre
- `DeplacementTerrestreStrategy` : Gérer le déplacement terrestre

#### O - Open/Closed Principle
Le système est ouvert à l'extension (nouvelles stratégies) mais fermé à la modification (pas besoin de modifier `Gronfleur` pour ajouter de nouveaux déplacements).

#### L - Liskov Substitution Principle
Toutes les implémentations de `DeplacementStrategy` sont interchangeables.

#### D - Dependency Inversion Principle
`Gronfleur` dépend de l'abstraction `DeplacementStrategy`, pas d'une implémentation concrète.

---

## 4. Implémentation Complémentaire : Le Pattern Factory

### Interface `ItemFactory`
```java
public interface ItemFactory {
    Item createItem(String type);
}
```

### Implémentation : `BlocFactory`
```java
public class BlocFactory implements ItemFactory {
    @Override
    public Item createItem(String type) {
        return switch (type.toLowerCase()) {
            case "grass" -> new Grass();
            case "dirt" -> new Dirt();
            case "gintoki" -> new Gintoki();
            case "bois" -> new Bois();
            case "aluminium" -> new Aluminium();
            case "fer" -> new Fer();
            case "cramptenium" -> new Cramptenium();
            case "cuivre" -> new Cuivre();
            case "pierre" -> new Pierre();
            default -> null;
        };
    }
}
```

### Bénéfices du Pattern Factory

1. **Centralisation de la création** : Toute la logique de création des blocs est au même endroit
2. **Découplage** : Le code client n'a pas besoin de connaître les classes concrètes de blocs
3. **Facilité de maintenance** : Ajouter un nouveau type de bloc nécessite seulement de modifier la factory
4. **Cohérence** : Garantit que les objets sont créés de manière uniforme

---

## 5. Impact sur l'Architecture du Projet

### Structure avant le refactoring
```
Gronfleur
    └─ Logique de déplacement intégrée
Ennemis
    └─ Logique de déplacement intégrée
```

### Structure après le refactoring
```
Gronfleur
    └─ DeplacementTerrestreStrategy (composition)
        └─ AlgoRecherche.DeplacementTerrestre()

DeplacementStrategy (interface)
    └─ DeplacementTerrestreStrategy
    └─ (Futures stratégies possibles : Aquatique, Aérien, etc.)
```

---

## 6. Évolutions Futures Possibles

Grâce à cette architecture, les évolutions suivantes sont maintenant simplifiées :

### 6.1 Nouveaux Types de Déplacement
```java
public class DeplacementAerienStrategy implements DeplacementStrategy { ... }
public class DeplacementAquatiqueStrategy implements DeplacementStrategy { ... }
public class DeplacementTeleportationStrategy implements DeplacementStrategy { ... }
```

### 6.2 Déplacement Composé
```java
public class DeplacementCompositeStrategy implements DeplacementStrategy {
    private List<DeplacementStrategy> strategies;
    // Permettrait de combiner plusieurs types de déplacement
}
```

### 6.3 Déplacement Conditionnel
```java
public class DeplacementAdaptatifStrategy implements DeplacementStrategy {
    // Pourrait changer de stratégie en fonction du terrain
}
```

---

## 7. Conclusion

### Pourquoi ce Commit est Important ?

1. **Amélioration de la qualité du code** : Le code est plus propre, plus modulaire et plus facile à comprendre
2. **Facilitation de la maintenance** : Corriger un bug dans un algorithme de déplacement n'affecte qu'une seule classe
3. **Préparation pour l'avenir** : L'architecture permet d'ajouter facilement de nouvelles fonctionnalités
4. **Respect des bonnes pratiques** : Application concrète des principes SOLID et des design patterns reconnus
5. **Professionnalisme** : Démontre une compréhension des architectures logicielles évolutives

### Apprentissages Clés

- **Design Patterns** : Application pratique du Strategy Pattern et du Factory Pattern
- **Principes SOLID** : Respect des principes de conception orientée objet
- **Refactoring** : Amélioration du code existant sans changer son comportement externe
- **Architecture logicielle** : Conception d'un système extensible et maintenable

---

## Références

- **Strategy Pattern** : Gang of Four, "Design Patterns: Elements of Reusable Object-Oriented Software"
- **Factory Pattern** : Gang of Four, "Design Patterns: Elements of Reusable Object-Oriented Software"
- **Principes SOLID** : Robert C. Martin, "Clean Code" et "Clean Architecture"

---

**Auteur du commit** : Chikage (lucas.benjebara93@gmail.com)  
**Date** : 9 octobre 2025  
**SHA du commit** : 2caaf289a561a525d1b7ab307ffe5e1522f2bea6
