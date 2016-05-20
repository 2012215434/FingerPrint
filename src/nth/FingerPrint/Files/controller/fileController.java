package nth.FingerPrint.Files.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import nth.FingerPrint.Files.po.ShoppingCart;
import nth.FingerPrint.Files.service.fileInService;
import nth.FingerPrint.Files.common.DeleteFile;
import nth.FingerPrint.Files.common.FTPupload;
import nth.FingerPrint.Files.common.util2Get;
import nth.FingerPrint.Files.controller.appKey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.itextpdf.text.pdf.PdfReader;

@Controller
public class fileController implements ServletContextAware{
	
	@Resource
	private fileInService fileInService;
	appKey AppKey = new appKey();
	
	private ServletContext servletContext;

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/************************************* file 
	 * @throws Exception **********************************/
	/*
	 文件的上传
	格式转换
	PDF页码数
	*@RequestParam("file"),@RequestParam("appKey") String appKey,
			 CommonsMultipartFile file,String fileType, UserInfo user
	*/
	@RequestMapping(value = "/fileUpload.do", method = RequestMethod.POST)
	// @RequestMapping("/fileUpload.do")
	@ResponseBody
	public String fileUpload(@RequestParam("filea")CommonsMultipartFile file,@RequestParam("appkey") String appKey,
			@RequestParam("userID")String user)
			throws Exception {
		int userID=Integer.valueOf(user);
		System.out.println(userID);
		
		System.out.println("**************fileUploadAction  here!*************");
		if (appKey.equals(AppKey.appKeyForJava)) {
			System.out.println("秘钥正确，执行该操作");
			
			// 设置fileID
			String uuid = util2Get.getUUID();
			String currentDate = util2Get.getCurrentDate();
			String fileID = currentDate + "-" + uuid;
			
			
			String p1 = this.servletContext.getRealPath("/tmp/"); //文件上传暂存地址
			String p2 = this.servletContext.getRealPath("/tmp/PDF/");  //PDF格式文件存放地址

			String storagePath = p1;
			
			// *********** 本地上传 ************//*
			if (!file.isEmpty()) {
				
				String fileName=file.getOriginalFilename();
				String fileType= fileName.substring(fileName.lastIndexOf("."));
				fileName = fileID; // 获取文件名
				
				File folder = new File(storagePath);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				File file2 = new File(storagePath, fileName + fileType); // 新建一个文件 
																	
				try {
					file.getFileItem().write(file2); // 将上传的文件写入新建的文件中
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("success");
			/*	System.out.println("存储成功");
				System.out.print("java.library.path为：");
				System.out.println(System.getProperty("java.library.path"));
				System.out.println("文件类型为：" + fileType);*/
				
				
				if (fileType.equals(".doc")|| fileType.equals(".docx")) {
					System.out.println("*****word2pdf test*****");
					//格式转换
					nth.FingerPrint.Files.controller.go2Pdf.word2pdf(storagePath +"\\"+ fileID + fileType, p2
							+ fileID + ".pdf");
					//找出PDF页码数
					PdfReader inputPDF = new PdfReader(p2+ fileID + ".pdf");
					int totalPages = inputPDF.getNumberOfPages();
					 
					/*System.out.println("文件地址为：" + storagePath + fileID+ fileType);
					System.out.println("文件存储地址为：" + p2 + fileID+ ".pdf");
					System.out.println("PDF页码数为："+totalPages);*/
					String storePath = p2 + fileID + ".pdf";
					
					/*
					 * 转存到ftp服务器上；
					 */
					InputStream in= new FileInputStream(new File(storePath));
					FTPupload.uploadFile(in, fileID+".pdf");
					
					/*
					 * 将文件路径保存到数据库中
					 */
					String ftpStorePath="ftp://59.68.29.68/test/"+fileID+".pdf";
					int userID2= fileInService.fileUpload(userID,fileID,ftpStorePath,totalPages);
					
					/*
					 * 删除存在本服务器的上传文件
					 */
					DeleteFile.deleteFile(storagePath+fileID+fileType);
					DeleteFile.deleteFile(storePath);
					
					
					ShoppingCart sh= fileInService.findShoppingCart(ftpStorePath,userID2);
					String RJson=new Gson().toJson(sh);
					System.out.println(RJson+"dayin");
					return RJson;
				}
				else if (fileType.equals(".ppt") || fileType.equals(".pptx")) {
					System.out.println("*****ppt2pdf test*****");
					//格式转换
					nth.FingerPrint.Files.controller.go2Pdf.ppt2pdf(storagePath + "\\"+fileID + fileType, p2
							+ fileID + ".pdf");
			
					//找出PDF页码数
					PdfReader inputPDF = new PdfReader(p2+ fileID + ".pdf");
					int totalPages = inputPDF.getNumberOfPages();
					/*
					System.out.println("文件地址为：" + storagePath + fileID+ fileType);
					System.out.println("文件存储地址为：" + p2 + fileID+ ".pdf");
					System.out.println("PDF页码数为："+totalPages);*/
					
					//String storePath = storagePathWord + fileID + fileType;
					String storePath = p2 + fileID + ".pdf";
					
					/*
					 * 转存到ftp服务器上；
					 */
					InputStream in= new FileInputStream(new File(storePath));
					FTPupload.uploadFile(in, fileID+".pdf");
					
					/*
					 * 将文件路径保存到数据库中
					 */
					String ftpStorePath="ftp://59.68.29.68/test/"+fileID+".pdf";
					int userID2= fileInService.fileUpload(userID,fileID,ftpStorePath,totalPages);
					
					/*
					 * 删除存在本服务器的上传文件
					 */
					DeleteFile.deleteFile(storagePath+fileID+fileType);
					DeleteFile.deleteFile(storePath);
					
					ShoppingCart sh= fileInService.findShoppingCart(ftpStorePath,userID2);
					String RJson=new Gson().toJson(sh);
					System.out.println(RJson+"dayin");
					return RJson;
				}
				else if (fileType.equals(".xls")|| fileType.equals(".xlsx")) {
					System.out.println("*****excel2pdf test*****");
					//格式转换
					nth.FingerPrint.Files.controller.go2Pdf.excel2pdf(storagePath +"\\"+ fileID + fileType, p2
							+ fileID + ".pdf");
				
					//找出PDF页码数
					PdfReader inputPDF = new PdfReader(p2+ fileID + ".pdf");
							
					int totalPages = inputPDF.getNumberOfPages();
					
				/*	System.out.println("文件地址为：" + storagePath+ fileID+ fileType);	
					System.out.println("文件存储地址为：" + p2 + fileID+ ".pdf");	
					System.out.println("PDF页码数为："+totalPages);*/
					
					String storePath = p2 + fileID + ".pdf";
					/*
					 * 转存到ftp服务器上；
					 */
					InputStream in= new FileInputStream(new File(storePath));
					FTPupload.uploadFile(in, fileID+".pdf");
					
					/*
					 * 将文件路径保存到数据库中
					 */
					String ftpStorePath="ftp://59.68.29.68/test/"+fileID+".pdf";
					int userID2= fileInService.fileUpload(userID,fileID,ftpStorePath,totalPages);
					
					/*
					 * 删除存在本服务器的上传文件
					 */
					DeleteFile.deleteFile(storagePath+fileID+fileType);
					DeleteFile.deleteFile(storePath);
					
					ShoppingCart sh= fileInService.findShoppingCart(ftpStorePath,userID2);
					String RJson=new Gson().toJson(sh);
					System.out.println(RJson+"dayin");
					return RJson;
				}
			} else {
				System.out.println("文件不存在！");
			}
			//return fileInService.fileUpload(user,fileID,storage,totalPages);
		} else {
			System.out.println("秘钥错误,禁止访问！");
		}
		return null;
		//return fileInService.fileUpload(user);
	}

	/*
	 * PDF预览功能  写到上传功能里面  上传成功 的同时 把PDF路径传回去
	 */
	/*@RequestMapping(value = "/returnPath.do",method = RequestMethod.POST)
	@ResponseBody
	public ShoppingCart returnPath(String appKey,String userID){
		System.out.println("************returnPath*********");
		return fileInService.returnPath(userID);
	}*/
	/*
	 *修改打印文档信息
	      打印分数.打印格式
	*/
	@RequestMapping(value="/updateDocument.do",method = RequestMethod.POST)
	@ResponseBody
	public String updateDocument(String appKey,ShoppingCart sh){
		
		return fileInService.updateDocument(sh);
	}

	public fileInService getFileInService() {
		return fileInService;
	}

	public void setFileInService(fileInService fileInService) {
		this.fileInService = fileInService;
	}
	
}
