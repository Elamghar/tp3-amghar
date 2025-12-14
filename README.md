# 🚀 TP3 : Intégration Continue (CI) avec Jenkins et Docker

Ce projet a été réalisé dans le cadre du module d'**Ingénierie Logicielle** (Responsable : **BOUARIFI Walid**) à l'ENSA Marrakech.

L'objectif principal est de mettre en place un pipeline d'Intégration Continue fonctionnel en utilisant Jenkins pour un projet Java simple.

---

## 🎯 Objectifs du TP Réalisés

Les étapes suivantes du TP ont été implémentées et automatisées via le pipeline Jenkins :

| Étape | Description | Statut |
| :--- | :--- | :--- |
| **Étape 1** | Installation et configuration de Jenkins | ✅ Fait (via Docker) |
| **Étape 2** | Création d'un Projet Jenkins (`Freestyle`) | ✅ Fait (`monProjectCI`) |
| **Étape 3** | Automatisation du Build & Tests Unitaires | ✅ Fait (`mvn clean test`) |
| **Étape 4** | Intégration avec Git/GitHub (Webhooks) | ✅ Fait |
| **Étape 5** | Publication des résultats de tests | ✅ Fait (JUnit) |
| **Étape 6** | Rapports et Analyses | ✅ Fait (Rapport de tests) |

> 🔹 Phrase clé : Jenkins permet d’automatiser la compilation et l’exécution des tests unitaires à chaque modification du code source.

---

## 📦 Structure du Projet Java

Le code source est une implémentation simplifiée d'un **Gestionnaire de Bibliothèque** pour démontrer l'automatisation des tests.

