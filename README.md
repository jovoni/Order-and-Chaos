# Order-and-Chaos

Project for the software development exam.

Order and Chaos is a variant of the game tic-tac-toe, played on a 6x6 gameboard. The player *Order* needs to create a five-in-a-row of either Xs or Os. The opponent *Chaos* needs to prevent this from happening.

## Rules

Order and Chaos is a variant of the game tic-tac-toe on a 6×6 gameboard.
The player Order strives to create a five-in-a-row of either Xs or Os, either vertically, horizontally, or diagonally.
Chaos, on the other hand, needs to block all rows, columns and diagonals.
Order plays first, then turns alternate.
Six-in-a-row does not qualify as a win.

## Tools

- Java
- Gradle
- TravisCI
- Github

## How to run

You can either play using the command line as well as with a graphical interface (recommended version).

### Unix-like systems

**Command line version** can be played using the following command:

`./gradlew runConsole --console plain`

**GUI version** can be played using this other command:

`./gradlew run --console plain`

### Windows systems

**Command line version** can be played using the following command:

`gradlew.bat runConsole --console plain`

**GUI version** can be played using this other command:

`gradlew.bat run --console plain`

## Authors

- Elena Buscaroli
- Benedetta Liberatori
- Ciro Antonio Mami
- Giovanni Santacatterina
- Lucrezia Valeriani
