package punk.devtest;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
		File htmlTemplateFile = new File("C:\\Users\\William\\workspace\\PunkDigital\\WebContent\\signature.html");
		File newHtmlFile = null;
		String htmlString = "";
		try {
			htmlString = FileUtils.readFileToString(htmlTemplateFile);
			htmlString = htmlString.replace("$names", this.strNames);
			htmlString = htmlString.replace("$position", this.strPosition);
			htmlString = htmlString.replace("$email", this.strEmail);
			htmlString = htmlString.replace("$tell", this.strTell);
			htmlString = htmlString.replace("$logo", this.strLogoUrl);
			newHtmlFile = new File("C:\\Users\\William\\workspace\\PunkDigital\\WebContent\\new_signature.html");
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
        int width = 600, height = 150;
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
            ImageIO.write(image_, "png", new File("C:\\Users\\William\\workspace\\PunkDigital\\WebContent\\images\\"+imageName));
        } catch (IOException e) {
        	imageName = "failed";
            e.printStackTrace();
        }
        System.out.println(imageName);		
		return imageName;
	}
	
    public int downloadFile(String imageName) throws IOException {
    	String fileURL = "http://localhost:8080/PunkDigital/Images/"+imageName;
    	String saveDir = "C:\\Users\\William\\testimages\\";
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
 
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();
 
            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }
 
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);
 
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;
             
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
 
            int bytesRead = -1;
            byte[] buffer = new byte[4096];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
 
            outputStream.close();
            inputStream.close();
 
            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
        return responseCode;
    }	
}
