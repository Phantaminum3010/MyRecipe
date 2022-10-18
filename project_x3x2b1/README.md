## Recipe Manager Application Project

## CPSC 210 Personal Project

My project is a recipe manager application. It allows the users to view their menu on the desktop, add or remove
recipes of dishes or drinks. This project interests me because it serves as a tool to manage my work when I
have so many things to remember.





### User Stories:
* The user is able to view a menu with titles of recipes on the desktop
* The user is able to select a dish in the menu and view its recipe in details
* The user is able to add a recipe in the menu
* The user is able to delete a recipe in the menu
* The user is able to save the recipe list to file
* The user is able to load the recipe list from file

### Phase 4: Task 2
* Option that I chose: test and design a class in my model package so that it is robust
* Class: Ingredient
* Method that throws an exception: addAmount
* Specific detail: the method will be thrown in the addAmount method when the amount added < 0

### Phase 4: Task 3
* If I have more time to work on this project, I would modify the Recipe, and RecipeList classes so that
there is an aggregation between Recipe and RecipeList classes. (Recipe should be a part of RecipeList)
* Moreover, since all the classes that end with ...Panel(AddRecipePanel, DeleteRecipePanel, ...) all share the methods
getButtons and getMessage, so I might create an abstract class ProjectPanel so that all classes can inherit the
common methods (ProjectPanel extends JPanel) 