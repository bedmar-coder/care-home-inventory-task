#How to Run It
I chose to build this as a vanilla Java console application because it is the technology I have the most experience with. To run the app, simply compile execute the App class inside SRC
#How the Alert Logic Works
Instead of hardcoding a universal "low stock" definition, I designed the system to let the user set a custom minimum for each supply
The alert logic is integrated directly into the main menu loop: at the ending of every interaction, the system checks the current stock of all items against their specific minimun and if any supply is at or below its minimum, a warning  is automatically displayed to the user

(Note on time: I spent a little over the suggested 2-3 hours. The weather was terrible, so I stayed in and used the extra time to build a custom CSV persistence layer from scratch. I have an upcoming exam on Java data streams, so this served as practice)

#What I Would Add With More Time
*Graphical User Interface transitioning from a terminal app to a fully visual interface

*Implementing a screen clear function between interactions so the menu doesn't continuously stack up in the terminal history

*Adding a menu option to set an exact stock quantity directly, rather than just adding or subtracting from the current amount

*Replacing my manual parsing manager with a dedicated library like OpenCSV for more scalable data handling
