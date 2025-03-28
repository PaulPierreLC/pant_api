-- Delete from tables that reference other tables first
DELETE FROM `plat_regimes`;
DELETE FROM `plat_cuisines`;
DELETE FROM `plat`;
DELETE FROM `cuisine`;
DELETE FROM `restaurant`;
DELETE FROM `utilisateur_adresses`;
DELETE FROM `adresse`;
DELETE FROM `login`;
DELETE FROM `utilisateur`;
DELETE FROM `vehicule`;
DELETE FROM `vehicule_type`;
DELETE FROM `regime`;
DELETE FROM `statut`;
DELETE FROM `paiement_type`;
DELETE FROM `ville`;
DELETE FROM `role`;
