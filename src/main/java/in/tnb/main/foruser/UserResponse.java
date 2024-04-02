package in.tnb.main.foruser;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public class UserResponse {
	
    private int id;
    private String response;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
    
    
}
