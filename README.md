The-Mahogany-Engine
===================

The first engine we created, made as a culminating project

This project required working as a member of a team. It is object oriented and does not use external 3D libraries of any kind to accomplish its tasks. 

STRUCTURE
===================
The structure of the project is a little odd, given that it was our first team project and we were not sure how to structure a project this big. We learned a lot, especially about planning. 
The most recent iteration successfully renders lines grouped into objects or triangular faces grouped into objects in a three dimensional space (perceived as two). The line-based method is newer and faster, and the main classes affected by this method are:
-  GUIWorld (which handles the line-based world)
-  RendererV2 (which is the class that renders the lines)
-  the entire shapes.line_based package
The last of these is incomplete, with many empty class files that are yet to be finished. 
Face-based rendering is completed using:
-  Renderer (the original renderer class)
-  GUIPanel (the class that handles the face-based world)
- the entire shapes.face_based package
This method is slower but more functional right now.

EDITING THE SIMULATION
====================
The easiest way to switch between line-based and face-based rendering is to edit the GUIFrame class located in the gui package. Simply change
add(new GUIWorld()); 
to 
add(new GUIPanel()); 
and the rendering world will change. 

To edit the actual rendering world, open the appropriate file (GUIWorld or GUIPanel) and change the constant variables at the top of the class for number of objects, size of objects, and others. The variables are well names. 

OTHER
=====================
If you are curious as to the parameters and purpose of any of the functions, a fairly complete JavaDoc exists. Since line-based rendering is in alpha right now, it is not completely finished. But all face-based functions are completely documented. 
