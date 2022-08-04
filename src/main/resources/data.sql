INSERT INTO measurement_unit (description) VALUES ('');
INSERT INTO measurement_unit (description) VALUES ('tsp'); --Teaspoon
INSERT INTO measurement_unit (description) VALUES ('tbsp'); --Tablespoon
INSERT INTO measurement_unit (description) VALUES ('Cup');
INSERT INTO measurement_unit (description) VALUES ('Pinch');
INSERT INTO measurement_unit (description) VALUES ('Ounce');
INSERT INTO measurement_unit (description) VALUES ('pounds');
INSERT INTO measurement_unit (description) VALUES ('Dash');
INSERT INTO measurement_unit (description) VALUES ('Pint');


INSERT INTO recipe (name, description, cook_time, servings, vegetarian) VALUES ('Lemon Chicken', 'Lemon chicken is really just a simple baked chicken recipe, jazzed up with some punchy ingredients that add lots of flavor without taking away from how super-healthy baked chicken breasts are', 5, 6, 1);
INSERT INTO ingredient (description, amount, measurement_unit_id, recipe_id) VALUES ('boneless chicken breasts (roughly 2.5 lbs)', 4, 1, 1);
INSERT INTO ingredient (description, amount, measurement_unit_id, recipe_id) VALUES ('olive oil', '1/4', 4, 1);
INSERT INTO ingredient (description, amount, measurement_unit_id, recipe_id) VALUES ('oregano, dried', 2, 2, 1);
INSERT INTO ingredient (description, amount, measurement_unit_id, recipe_id) VALUES ('garlic powder', 3, 2, 1);
INSERT INTO ingredient (description, amount, measurement_unit_id, recipe_id) VALUES ('dry white wine, such as Sauvignon Blanc, Pinot Grigio or Pinot Gris', '1/4', 4, 1);
INSERT INTO ingredient (description, amount, measurement_unit_id, recipe_id) VALUES ('lemon zest (2 lemons)', 1, 2, 1);
INSERT INTO ingredient (description, amount, measurement_unit_id, recipe_id) VALUES ('lemon juice, freshly squeezed', 2, 2, 1);
INSERT INTO ingredient (description, amount, measurement_unit_id, recipe_id) VALUES ('brown sugar', 1, 3, 1);

INSERT INTO recipe (name, description, cook_time, servings, vegetarian) VALUES ('Salmon en Papillote (Salmon in Parchment)', 'Cooking salmon in parchment with vegetables is nearly fool-proof—the steam-cooking that happens en papillote guarantees that your fish comes out of the oven beautifully moist and tender.', 12, 4, 0);
INSERT INTO ingredient (description, amount, measurement_unit_id, recipe_id) VALUES ('salmon, cut into 4 pieces', '11/4', 7, 2);
INSERT INTO ingredient (description, amount, measurement_unit_id, recipe_id) VALUES ('dried dill', '1', 2, 2);
INSERT INTO ingredient (description, amount, measurement_unit_id, recipe_id) VALUES ('lemon, thinly sliced', '2', 1, 2);
INSERT INTO ingredient (description, amount, measurement_unit_id, recipe_id) VALUES ('Extra virgin olive oil', '4', 2, 2);