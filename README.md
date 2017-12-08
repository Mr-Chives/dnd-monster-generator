# dnd-monster-generator
 Welcome to the D&D Monster Generator!
 
 This program is designed to randomly generate a new monster with
 randomized statistics. All of the information is viewable in the
 MonsterFrame, and the data is held using a variety of model classes,
 including Monster, it's few subclasses, AbilityScores, HitPoints, and
 more.
 
 To run this program, run MonsterFrame.
 
 This program was written by Ryan Meeker and Brian White.
 
 Brian wrote MonsterFrame, which interprets model data into a
 functional GUI.
 Ryan wrote the model classes of Monster, DragonTurtle, Grick, Kobold,
 HitPoints, ArmorClass, Abilities, Actions, AbilityScores, Languages, and Senses.
 
 Most of the DungeonMasterController class was written by Ryan, as it relied on
 many of the model classes.
 
 This program incorporates the ability to save and load binary files, and the
 ability to save a JSON file. By default, the save and load buttons in the GUI
 utilize binary.
 
 Future Enhancements:
 - Ability to edit monster stats manually
 - Ability to select specific monsters to create
 - Add more available monsters
