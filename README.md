Micromouse Simulator

Author: Akshay Ben

About:

This is a java based simulator for testing different types of algorithms for the IEEE
micromouse competition. The user has control over 4 things that the mouse can do at any 
given time. 

1. read walls at a cell
2. turn left
3. turn right
4. move forward

By controlling these basic commands the user has to write an algorithm to learn the maze
and then traverse it, finding the best path (shortest time/distance). 

http://sites.ieee.org/r1/files/2013/03/2013-Region-1-Micromouse-Competition-Rules.pdf

The simulator also allows users to create their own mazes and save it. They can then
test their own algorithms using their saved mazes. 

AS OF NOW THE SIMULATOR IS STILL IN TESTING PHASE. THERE MIGHT BE BUGS IN THE SIMULATOR


To run the simulator do the following steps:

Downloading/Cloning

1) Download the project as a zip folder
2) Go to location where the zip folder is and unzip

--- OR ---

1) Open a folder directory on terminal
2) git clone https://github.com/dragonmushu/MicromouseSim.git


Importing To Eclipse

3) Open Eclipse
4) Right click on the project explorer and click import
5) Under General click Existing Projects into Workspace and click next
6) Find the folder directory where the project was unzipped and select the project
7) Press Select All on the right side and then press finish

Creating Algorithm

8) Under the algorithms project folder create a new class that ***extends***
   Algorithm
   ***REMEMBER TO SAVE THE CLASS UNDER THE ALGORITHMS PACKAGE***

9) Implement the unimplemented abstract methods:

	1) void setupMaze(); ---> initialize maze storage here
	2) void init(); ---> initialize variables here
	3) String getName(); ---> return name of algo here
	4) void update (float dt); ---> called after all motions of the mouse are done

10) YOU CAN HAVE A CONSTRUCTOR/YOU DONT NEED ONE BUT MAKE SURE THAT IT REMAINS EMPTY
	INITIALIZE ALL VARIABLES IN SETUPMAZE AND INIT

11) ***The last statement in your init() has to be run(), which 
	will start the update of the algorithm *** 

Take a look at Algorithm class to see the methods you could call