# Consudoku
A console-based Sudoku

 - [Maths and calculations](http://norvig.com/sudoku.html)  
 - [Box-drawing characters](https://en.wikipedia.org/wiki/Box-drawing_character)  
 - [Number characters](https://www.compart.com/en/unicode/category/Nd?page=1&show=1000)  

## Steps

 1. Logic
 2. GUI Prototype
 3. Mobile
 4. Google Play and App Store
 5. Puzzle recogniser (ML)

## Plan
 - **[Week 1 - 6]** - Main Logic and GUI Prototype  
   - Handling puzzle textual format i/o
   - Filling in a puzzle (core game-play)
   - Verifying a finished puzzle
   - Identifying conflicts in a puzzle
   - Generating puzzles in a chosen difficulty level
   - Tracking basic statistics of completed puzzles
   - Saving progress of incomplete puzzles
 - **[Week 4 - 10]** - Mobile and App Market
   - Developing a moblie UI
   - Handling Google Play and App Store
   - Testing the app
   - Publishing version 1
 - **[Week 10+]** - Development and Maintenance
   - Developing other modules for future version
   - Tracking and handling bugs and feedbacks
   
## Modules
### Version 1 - Minimal Viable Product
 - (Puzzle (State)) Representation
 - Puzzle Solving
 - Puzzles Generation
 - Progress Saving
 - Basic Statistics (best and average times for each difficulties)
 - Basic Settings
### Version 2 - Standard Just-Another Sudoku App
 - Highlighting
 - Pencil Marks (Manual, Auto-fill, Auto-elimination)
 - History (Undos & Redos)
 - (Google Play and App Store) Service (Account, Transfer, Reset)
 - Advertisement (Optional ads only! E.g. an ads button)
 - Settings
 - Puzzle Construction (with solvability and difficulty)
 
All of the modules should be runnable on the GUI prototype where they can also be tested.
### Version 3 - An Application of Machine Learning
 - Puzzle Recognition (via images/camera)
 
This will allow the sudoku app to "import" any sudoku puzzles from images or through the device camera.  
It should be able to differentiate between pre-filled hint numbers and user-filled numbers (if any) (or be able to swap between the two if their only difference is the font colour, say). Users should also be able to import just the hint numbers, ignoring the user-filled numbers that are already on the puzzle on the image.  
The post-recognition should have a polishing stage where users can jump in and modify hints and user-filled numbers (i.e. puzzle construction module).  
The system should allow users to submit reports of errorneous [image, imported-puzzle] pairs for continuous training that can be updated in the subsequent versions.
