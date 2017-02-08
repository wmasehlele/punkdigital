package punk.devtest;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;

public class EmailSignature implements SignatureInterface{
	private String strNames;
	private String strPosition;
	private String strTell;
	private String strEmail;
	private String strLogoUrl;
	
	public String getStrNames() {
		return strNames;
	}
	public void setStrNames(String strNames) {
		this.strNames = strNames;
	}
	public String getStrPosition() {
		return strPosition;
	}
	public void setStrPosition(String strPosition) {
		this.strPosition = strPosition;
	}
	public String getStrTell() {
		return strTell;
	}
	public void setStrTell(String strTell) {
		this.strTell = strTell;
	}
	public String getStrLogoUrl() {
		return strLogoUrl;
	}
	public void setStrLogoUrl(String strLogoUrl) {
		this.strLogoUrl = strLogoUrl;
	}
	public String getStrEmail() {
		return strEmail;
	}
	public void setStrEmail(String strEmail) {
		this.strEmail = strEmail;
	}
	
	@Override
	public String generateSignature() throws MalformedURLException {	
		Random rand = new Random();
		int tempid = rand.nextInt(1000) + 1;
		String imageName = "image_"+tempid+".png";
	    try {
	    	InputStream in = new URL(this.strLogoUrl).openStream();
	    	String sRootPath = System.getProperty("user.dir");
			Files.copy(in, Paths.get("C:\\Users\\William\\workspace\\PunkDigital\\WebContent\\Images\\"+imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}	
        String html = "<html>"
				+ "<head>"
        		+ "</head></body><table><tr><td>"
        		+ "<div style=\"padding:10px;float:left;border:1px solid black;width:200px;\\\">"
	        		+ "<img src=\"C:/Users/William/workspace/PunkDigital/WebContent/Images/"+imageName+" class=\"img-responsive\\\" heigt=\"250px\\\" width=\"100px\\\">"
        		+ "</div></td><td>"
        		+ "<div style=\"padding:10px;float:right;color:blue;border:1px solid black;width:300px;margin-top:-100px;\\\">"
	        		+ "<h2 style=\"text-align:right;\\\">"+this.strNames+"</h2>"
	        		+ "<h2 style=\"text-align:right;\\\">"+this.strPosition+"</h2>"
	        		+ "<h2 style=\"text-align:right;\\\">"+this.strTell +"</h2>"
	        		+ "<h2 style=\"text-align:right;\\\">"+this.strEmail +"</h2>"
        		+ "</div></td></tr></table>"
        		+ "</body></html>";		
        int width = 700, height = 220;
        // Create a `BufferedImage` and create the its `Graphics`
        BufferedImage image_ = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration()
                .createCompatibleImage(width, height);
        Graphics graphics = image_.createGraphics();
        // Create an `JEditorPane` and invoke `print(Graphics)`
        JEditorPane jep = new JEditorPane("text/html", html);
        jep.setSize(width, height);
        jep.print(graphics);
        // Output the `BufferedImage` via `ImageIO`
		rand = new Random();
		tempid = rand.nextInt(1000) + 1;
		imageName = "image_"+tempid+".png";      
        try {
            ImageIO.write(image_, "png", new File("C:\\Users\\William\\testimages\\"+imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(imageName);		
		
		return imageName;
	}
}
