package punk.devtest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.*;
import com.google.gson.Gson;

/**
 * Servlet implementation class SignatureServlet
 */
@WebServlet("/SignatureServlet")
public class SignatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmailSignature signatureObject;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignatureServlet() {
        super();
        signatureObject = new EmailSignature(); 
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String imageName = session.getAttribute("imageName").toString();
		int httpResponsede = signatureObject.downloadFile(imageName);
System.out.println("Session image name: "+imageName); 		
		JSONObject jsonObject = new JSONObject();
		try {
			if (httpResponsede != 200){
				jsonObject.put("status", false);
				jsonObject.put("status_code", httpResponsede);
			} else {
				jsonObject.put("status", true);
				jsonObject.put("status_code", httpResponsede);
			}
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		String json = new Gson().toJson(jsonObject);
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		signatureObject.setStrNames(request.getParameter("names"));
		signatureObject.setStrPosition(request.getParameter("position"));
		signatureObject.setStrTell(request.getParameter("tell"));
		signatureObject.setStrEmail(request.getParameter("email"));
		signatureObject.setStrLogoUrl(request.getParameter("logoUrl"));		
		
		HttpSession session = request.getSession(true);
		String imageName = signatureObject.generateSignature();
		try{
			if (imageName == "failed"){
				jsonObject.put("status", false);
				session.setAttribute("imageName", imageName);				
			} else {
				session.setAttribute("imageName", imageName);
				jsonObject.put("status", true);
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		String json = new Gson().toJson(jsonObject);
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);		
	}

}
