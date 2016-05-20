package nth.FingerPrint.Email;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class emailService {
	@Resource
	private emailDao email = new emailDao();

	public emailDao getEmail() {
		return email;
	}

	public void setEmail(emailDao email) {
		this.email = email;
	}
	
}
