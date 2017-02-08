package punk.devtest;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;

import org.apache.commons.io.FileUtils;

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
		File htmlTemplateFile = new File("C:\\Users\\William\\workspace\\signature.html");
		File newHtmlFile = null;
		String htmlString = "";
		try {
			htmlString = FileUtils.readFileToString(htmlTemplateFile);
			htmlString = htmlString.replace("$names", this.strNames);
			htmlString = htmlString.replace("$position", this.strPosition);
			htmlString = htmlString.replace("$email", this.strEmail);
			htmlString = htmlString.replace("$tell", this.strTell);
			htmlString = htmlString.replace("$logo", this.strLogoUrl);
			newHtmlFile = new File("C:\\Users\\William\\workspace\\new_signature.html");
			FileUtils.writeStringToFile(newHtmlFile, htmlString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			htmlString = FileUtils.readFileToString(newHtmlFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
        String html = htmlString.toString();		
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
