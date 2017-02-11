# PUNK 
#Dev Test Solution - Java web app

#tool
->Basic react JS component used for the interface.
->JSX is complied by webpack and loaded by barble.
->npm is used as a package manager.
->Jquery and Bootstrap are used as part of the front end.
->Apache tomcat is used as the sever.

#implementation
->I decided on the basis of efficiency, to have a pre-defined template that is used to build the final image.
 
#issues
->Changes in the project directory are not published to the server in real time.
->Download results are not returned to the browser, the file is saved in a system directory in the background. 
disadvantages to this practice are known and re impact of different browser in how to handle downloads. 
For example Fire Fox produce a pop up on download action (unless configured otherwise)
but chrome doesn't require user interaction on download (it start the download immediately).
-> Due to the focus on solving the provided problem, user friendliness was not the main focus (I hope this is found to be in order).