# Refactoring avec Design Patterns

Ce document décrit les design patterns implémentés dans le projet Lost on Crampteus.

## Design Patterns Implémentés

### 1. Strategy Pattern (Stratégie)

**Objectif**: Définir une famille d'algorithmes, les encapsuler et les rendre interchangeables.

**Implémentation**:
- **Interface**: `DeplacementStrategy`
- **Implémentations concrètes**: `DeplacementTerrestreStrategy`
- **Utilisation**: Système de déplacement des PNJ (Pnj, Gronfleur)

**Avantages**:
- Permet de changer dynamiquement le comportement de déplacement des PNJ
- Facilite l'ajout de nouveaux types de déplacement (aérien, aquatique, etc.)
- Sépare l'algorithme de déplacement de la classe qui l'utilise

**Exemple d'utilisation**:
```java
DeplacementStrategy strategy = new DeplacementTerrestreStrategy(1.5, 200);
gronfleur.setDeplacementStrategy(strategy);
gronfleur.seDeplacer();
```

**Fichiers concernés**:
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Personnage/Strategy/DeplacementStrategy.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Personnage/Strategy/DeplacementTerrestreStrategy.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Personnage/Pnj.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Personnage/Gronfleur.java`

---

### 2. Abstract Factory Pattern (Fabrique Abstraite)

**Objectif**: Fournir une interface pour créer des familles d'objets liés sans spécifier leurs classes concrètes.

**Implémentation**:
- **Interface**: `ItemFactory`
- **Fabriques concrètes**: 
  - `BlocFactory` - Création de blocs (Grass, Dirt, Fer, Aluminium, etc.)
  - `ArmeFactory` - Création d'armes (Epee, Pioche, Hache, Arc)
  - `ConsomableFactory` - Création de consomables (Pomme, PotionPv)

**Avantages**:
- Centralise la logique de création des items
- Élimine les switch/case répétitifs dans le code
- Facilite l'ajout de nouveaux types d'items
- Respecte le principe Open/Closed

**Exemple d'utilisation**:
```java
BlocFactory blocFactory = new BlocFactory();
Item fer = blocFactory.createItem("fer", monde);
```

**Fichiers concernés**:
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Item/Factory/ItemFactory.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Item/Factory/BlocFactory.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Item/Factory/ArmeFactory.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Item/Factory/ConsomableFactory.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Personnage/Joueur.java` (méthode `ajouterBlocEnCourCassage`)

---

### 3. Template Method Pattern (Méthode Template)

**Objectif**: Définir le squelette d'un algorithme dans une méthode, en déléguant certaines étapes aux sous-classes.

**Implémentation**:
- **Classe abstraite**: `CraftTemplate`
- **Implémentation concrète**: `CraftStandard`
- **Utilisation**: Processus de craft dans le système de fabrication

**Structure de l'algorithme**:
1. Vérifier les ingrédients
2. Retirer les ingrédients de l'inventaire
3. Créer l'item résultant

**Avantages**:
- Définit un processus de craft standardisé
- Permet de créer des variantes du processus de craft (craft rapide, craft avec bonus, etc.)
- Évite la duplication de code

**Exemple d'utilisation**:
```java
CraftTemplate craftTemplate = new CraftStandard();
Item resultat = craftTemplate.executerCraft(recette, inventaire);
```

**Fichiers concernés**:
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Craft/Template/CraftTemplate.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Craft/Template/CraftStandard.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Craft/Craft.java`

---

### 4. Strategy Pattern - Utilisation d'Items

**Objectif**: Encapsuler le comportement d'utilisation des items.

**Implémentation**:
- **Interface**: `ItemUsageStrategy`
- **Implémentation concrète**: `PlacementBlocStrategy`
- **Utilisation**: Comportement d'utilisation des blocs

**Avantages**:
- Sépare la logique d'utilisation de la classe Item
- Permet d'avoir différentes stratégies d'utilisation pour différents types d'items
- Facilite l'ajout de nouveaux comportements

**Exemple d'utilisation**:
```java
ItemUsageStrategy strategy = new PlacementBlocStrategy("grass", 1);
strategy.utiliser(monde, tileX, tileY);
```

**Fichiers concernés**:
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Item/Strategy/ItemUsageStrategy.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Item/Strategy/PlacementBlocStrategy.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Item/Bloc/Bloc.java`

---

### 5. Decorator Pattern (Décorateur)

**Objectif**: Ajouter dynamiquement de nouvelles fonctionnalités à des objets.

**Implémentation**:
- **Classe abstraite**: `ItemDecorator`
- **Décorateur concret**: `EnchantedItemDecorator`
- **Utilisation**: Ajout d'enchantements aux items

**Avantages**:
- Permet d'ajouter des fonctionnalités aux items sans modifier leur code
- Permet de combiner plusieurs enchantements
- Respecte le principe Open/Closed

**Exemple d'utilisation**:
```java
Item epee = new Epee(monde);
Item epeeEnchantee = new EnchantedItemDecorator(epee, "Feu", 3);
// Nom de l'épée: "épée +3 (Feu)"
```

**Fichiers concernés**:
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Item/Decorator/ItemDecorator.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Item/Decorator/EnchantedItemDecorator.java`

---

### 6. Composite Pattern (Composite)

**Objectif**: Composer des objets en structures arborescentes pour représenter des hiérarchies partie-tout.

**Implémentation**:
- **Classe composite**: `ItemComposite`
- **Composites spécifiques**: 
  - `ItemBundle` - Paquet d'items qui se déballent
  - `ItemContainer` - Conteneur avec capacité limitée

**Avantages**:
- Permet de traiter des groupes d'items comme un seul item
- Facilite la gestion de conteneurs, bundles, et autres collections d'items
- Permet des structures hiérarchiques complexes

**Exemple d'utilisation**:
```java
ItemBundle starterPack = new ItemBundle("Starter Pack");
starterPack.ajouterItem(new Pomme());
starterPack.ajouterItem(new Fer());

ItemContainer chest = new ItemContainer("Coffre", 10);
chest.ajouterItem(new Aluminium());
```

**Fichiers concernés**:
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Item/Composite/ItemComposite.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Item/Composite/ItemBundle.java`
- `src/main/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/Item/Composite/ItemContainer.java`

---

## Tests

Tous les design patterns sont couverts par des tests unitaires dans le package:
`src/test/java/universite_paris8/iut/mcatan/lostoncrampteus/Modele/DesignPatterns/`

### Tests disponibles:
- `StrategyPatternTest.java` - Tests pour le pattern Strategy (déplacement)
- `FactoryPatternTest.java` - Tests pour le pattern Factory
- `TemplateMethodPatternTest.java` - Tests pour le pattern Template Method
- `DecoratorPatternTest.java` - Tests pour le pattern Decorator
- `CompositePatternTest.java` - Tests pour le pattern Composite

---

## Impact et Bénéfices du Refactoring

### Maintenabilité
- Code plus modulaire et organisé
- Séparation des responsabilités claire
- Facilite la compréhension du code

### Extensibilité
- Ajout facile de nouveaux types d'items via les factories
- Ajout facile de nouveaux comportements via les strategies
- Ajout facile de nouvelles fonctionnalités via les decorators

### Flexibilité
- Changement dynamique de comportements
- Composition flexible d'items
- Support pour des fonctionnalités avancées futures

### Qualité du Code
- Réduction de la duplication
- Respect des principes SOLID
- Code plus testable
- Meilleure couverture de tests

---

## Évolutions Futures Possibles

### Avec Strategy Pattern:
- `DeplacementAerienStrategy` - Pour des créatures volantes
- `DeplacementAquatiqueStrategy` - Pour des créatures aquatiques
- `DeplacementTeleportationStrategy` - Pour des déplacements instantanés

### Avec Factory Pattern:
- `EquipementFactory` - Pour créer des équipements
- `MagieFactory` - Pour créer des sorts et objets magiques
- `VehiculeFactory` - Pour créer des véhicules

### Avec Template Method:
- `CraftRapide` - Craft instantané sans vérification
- `CraftAvecBonus` - Craft avec chance d'obtenir des items bonus
- `CraftMultiple` - Craft de plusieurs items à la fois

### Avec Decorator:
- `DurabilityBoostDecorator` - Augmente la durabilité
- `SpeedBoostDecorator` - Augmente la vitesse
- `ElementalDecorator` - Ajoute des effets élémentaires (feu, glace, foudre)

### Avec Composite:
- Système de coffres imbriqués
- Sacs à dos avec compartiments
- Systèmes d'inventaires complexes

---

## Conclusion

Ce refactoring améliore significativement la qualité du code en introduisant des design patterns reconnus. 
Le code est maintenant plus flexible, extensible et maintenable, tout en conservant la compatibilité avec le code existant.
