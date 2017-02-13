package punk.devtest;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import org.apache.commons.io.FileUtils;

//import org.apache.commons.io.FileUtils; We need this to be able to convert ou html content to string.

/*
 * Programmer: William Moela
 * Description: The program accept user input from a web page and generate email signature as an image (.PNG) file.
 * Comments: I just though it would be appropriate to throw in some OOP concept such as inheritance. 
 * 
 * For more details see the README file.
 */

public class EmailSignature extends Signature{
	
	private Random rand;
	
	//In our constructor we initialize the project directory.
	public EmailSignature(){
		//the getClass function will point us to the class deployment library folder.
		//getClassLoader will prepare our path to the destination
		//getResource will referee to the actual file, but in this case we dont need the file, hence we invoke the getPath function so we can just retrieve the path.
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[] = null;
		try {
			//We need to get only the relevant portion of the path that will help us to point to our required locations.
			//To do this, we split the path into two portions. The split function will return an array.
			//We are interested with first index of the array.
			//As we know from the path we might come across some funny characters, we need to standardize this chars. 
			//UTF-8 is used to decode or rather say to standardize the path characters
			//UTF-8 uses 8bit to represent each character which is found to be file for the path and we expecting mostly forward and back slashes in the path
			pathArr = URLDecoder.decode(path, "UTF-8").split(".metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PunkDigital/WEB-INF/classes/");
			//we globally set the path once so we do do unnecessary processing during runtime. 
			this.setStrPath(pathArr[0]);
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}		
	}
	
	public String generateSignature(){	
		
		//Create image name using random number suffix.
		//initialize the random only when is required to avoid multiple objects of the same reference.
		if (null == rand){
			rand = new Random();
		}
		
		int tempid = rand.nextInt(1000) + 1;
		String imageName = "image_"+tempid+".png";		
		
		//Read in a template from html to build our signature
		File htmlTemplateFile = new File(this.strPath+"\\PunkDigital\\WebContent\\signature.html");
		
		//carete and initialize a new html file to hold the updated template
		File newHtmlFile = null;
		String htmlString = "";
		
		try {
			//Read the template file and convert the content to string
			htmlString = FileUtils.readFileToString(htmlTemplateFile);
			
			//We start replacing our template place holders withn the actual content from the user
			htmlString = htmlString.replace("$names", this.strNames);
			htmlString = htmlString.replace("$position", this.strPosition);
			htmlString = htmlString.replace("$email", this.strEmail);
			htmlString = htmlString.replace("$tell", this.strTell);
			htmlString = htmlString.replace("$logo", this.strLogoUrl);
			
			//Commit the changes to a new html file template. 
			//At this a html file with our signature is ready to be converted into an image
			newHtmlFile = new File(this.strPath+"\\PunkDigital\\WebContent\\new_signature.html");
			FileUtils.writeStringToFile(newHtmlFile, htmlString);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
		String html = "";
		try {
			//Now we read in our updated template and convert the content to string
			html = FileUtils.readFileToString(newHtmlFile).toString();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//set the image size.
        int width = 600, height = 150;
        // Now we create a buffered image with the sizes specified above. 
        //At this point we have an image container waiting for our content to be loaded and converted to image
        BufferedImage image_ = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(width, height);

        //Our image is being prepared to load the image graphics.
        Graphics graphics = image_.createGraphics();
        
        //To load the graphics we need a container to lead our image graphics from the template.
        //We will do this with a JEditorPane from the java swing packgae.
        JEditorPane jep = new JEditorPane("text/html", html);
        //Our pane size should equate our image's size.
        jep.setSize(width, height);
        //The print function is invoked to set or color our pane with the template graphic.
        jep.print(graphics);
        
        try {
        	//Now our image is ready, we just have to write it into the system directory with the appropriate format.
            ImageIO.write(image_, "png", new File(this.strPath+"\\PunkDigital\\WebContent\\images\\"+imageName));
        } catch (IOException e) {
        	imageName = "failed";
            e.printStackTrace();
        }
        System.out.println(imageName);	
        //We return the image name for later use.
		return imageName;
	}	
}
