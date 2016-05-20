package nth.FingerPrint.print.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import nth.FingerPrint.Files.common.FTPupload;
import nth.FingerPrint.print.Service.PrintService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class printConller {
	@Resource
	private PrintService printService = new PrintService();
	
	public PrintService getPrintService() {
		return printService;
	}

	public void setPrintService(PrintService printService) {
		this.printService = printService;
	}

	@RequestMapping(value = "/print.do", method = RequestMethod.POST)
	@ResponseBody
	public String print(HttpServletRequest request){
		
		int userID = Integer.parseInt(request.getParameter("userID"));
		String code = request.getParameter("code");
		System.out.println(userID+"¡¡¡¡¡¡"+code);
		
		String path=printService.judgeCode(userID, code);
		if(path==null||path.equals("")){
			return "{\"state\":false}";
		}else{
			FTPupload.testDownload(path);
			return "{\"state\":true}";
		}	
	}	
}
