========
5-Tiling
========

This project’s goal was to create a way of tiling sprites in a specific way, i.e. with an inside border, that runs along the border of the whole shape.

It aims at a compromise between ease of texture creation and ease of implementation, while maintaining a perfect result (which a two-texture solution might not have). Although I promised to myself not to create anything new in Java, I thought that Java might be the only choice to get this into the Minecraft mod I want it to be in. (I wouldn’t do this if I had promised to anyone different than myself. Maybe I’ll do this next, or else I’ll never leave behind the curse that Java is)

The basic idea is that chopping tiles in quarters will reduce the number of needed sprites from 40-something to 5. As previously mentioned, there is a two-sprite solution, but it doesn’t honor border widths bigger than 1px, and a two-sprite solution which needs additional information about border width and is very complicated to implement.
