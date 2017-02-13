package punk.devtest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject; 

//import org.json.JSONObject; this jar is required for us to use JSON in our application.

/**
 * Servlet implementation class SignatureServlet
 */
@WebServlet("/SignatureServlet")
public class SignatureServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private EmailSignature signatureObject;
	private JSONObject jsonObject;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignatureServlet() {
        super();
        signatureObject = new EmailSignature(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Now retrieve the session so we can get the image name.
		HttpSession session = request.getSession(true);
		String imageName = session.getAttribute("imageName").toString();
		
		//respond with image to the request client
		response.setContentType("image");
		PrintWriter out = response.getWriter();
		
		//Retrieve our global path and go get the image
		String filepath = signatureObject.getStrPath()+"\\PunkDigital\\WebContent\\images\\"; 
		
		//respond with an attachment.
		response.setHeader("Content-Disposition","attachment; filename=\"" + imageName + "\"");
		
		//put together the image name and the path to get the file stream from the system directory.
		java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath + imageName);  
		int i; 
		//As long as our file stream has some bytes keep writing into the response writer.
		while ((i=fileInputStream.read()) != -1) {
		    out.write(i); 
		} 
		//when we finished we close the reader and the writer, then the get function will respond to the request.
		fileInputStream.close(); 
		out.close(); 		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		//So here we just have to prepare the json object for this function for this function.
		this.jsonObject = new JSONObject();
		
		//get all the request parameter and set them to our signature properties.
		signatureObject.setStrNames(request.getParameter("names"));
		signatureObject.setStrPosition(request.getParameter("position"));
		signatureObject.setStrTell(request.getParameter("tell"));
		signatureObject.setStrEmail(request.getParameter("email"));
		signatureObject.setStrLogoUrl(request.getParameter("logoUrl"));		
		
		//create a session to hold our image name.
		HttpSession session = request.getSession(true);
		
		//call the generate function to create the signature and recieve the image name.
		String imageName = signatureObject.generateSignature();
		try{
			//image name is either a valid image name or a failed string indicating there was an error.
			//based on this variable, we set our status and image name to return to the font end.
			if (imageName == "failed"){
				this.jsonObject.put("status", false);
				session.setAttribute("imageName", imageName);				
			} else {
				session.setAttribute("imageName", imageName);
				this.jsonObject.put("status", true);
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		//Send the response to the request client
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(this.jsonObject);
	}
}
