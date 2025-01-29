📌 Overview

This is a text-based inventory and upgrade system for a game where players can:

Collect items by opening chests.

Store items in their inventory.

Upgrade items according to predefined rules.

🛠️ Features

Chests with Random Loot: Open chests to receive random items.

Inventory Management: Add, remove, and display items.

Item Upgrades: Upgrade items based on specific conditions.

Game Interaction: Console-based prompts guide the player.

🏗️ Project Structure

📦 game-project
┣ 📂 core
┃ ┣ 📜 Game.java
┃ ┣ 📜 UpgradeManager.java
┣ 📂 model
┃ ┣ 📜 Chest.java
┃ ┣ 📜 Inventory.java
┃ ┣ 📜 Item.java
┃ ┣ 📜 Rarity.java
┣ 📂 view
┃ ┣ 📜 Display.java
┣ 📜 Main.java
┣ 📜 README.md

🎮 How to Run the Game

Compile the project

javac Main.java

Run the game

java Main

🧪 Running Manual Tests

Since this project doesn’t use JUnit, I performed manual testing.

Run the Main class, which executes the project in console.
Test cases for inventory, upgrades, and chests are below, so to run any of them, call them before the game.start().
The console will display test results.

🎭 Gameplay Flow

The game welcomes the player with a dialogue.

The player interacts via console commands to:

Display Inventory

Open a chest.

Display Upgrading Rules

Upgrade items.

Exit the game.

📜 License

This project is open-source under the MIT License.

Happy coding & enjoy the game! 🎮🔥