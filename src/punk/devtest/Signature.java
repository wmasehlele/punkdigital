package punk.devtest;

public class Signature{
	
	//This is our email signature object inherited my our email signature class.
	//this are the properties of our signature.
	
	protected String strNames;
	protected String strPosition;
	protected String strTell;
	protected String strEmail;
	protected String strLogoUrl;
	protected String strPath;
	
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
	public String getStrPath() {
		return strPath;
	}
	public void setStrPath(String strPath) {
		this.strPath = strPath;
	}
}
