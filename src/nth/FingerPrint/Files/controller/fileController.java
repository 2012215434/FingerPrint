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
	 �ļ����ϴ�
	��ʽת��
	PDFҳ����
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
			System.out.println("��Կ��ȷ��ִ�иò���");
			
			// ����fileID
			String uuid = util2Get.getUUID();
			String currentDate = util2Get.getCurrentDate();
			String fileID = currentDate + "-" + uuid;
			
			
			String p1 = this.servletContext.getRealPath("/tmp/"); //�ļ��ϴ��ݴ��ַ
			String p2 = this.servletContext.getRealPath("/tmp/PDF/");  //PDF��ʽ�ļ���ŵ�ַ

			String storagePath = p1;
			
			// *********** �����ϴ� ************//*
			if (!file.isEmpty()) {
				
				String fileName=file.getOriginalFilename();
				String fileType= fileName.substring(fileName.lastIndexOf("."));
				fileName = fileID; // ��ȡ�ļ���
				
				File folder = new File(storagePath);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				File file2 = new File(storagePath, fileName + fileType); // �½�һ���ļ� 
																	
				try {
					file.getFileItem().write(file2); // ���ϴ����ļ�д���½����ļ���
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("success");
			/*	System.out.println("�洢�ɹ�");
				System.out.print("java.library.pathΪ��");
				System.out.println(System.getProperty("java.library.path"));
				System.out.println("�ļ�����Ϊ��" + fileType);*/
				
				
				if (fileType.equals(".doc")|| fileType.equals(".docx")) {
					System.out.println("*****word2pdf test*****");
					//��ʽת��
					nth.FingerPrint.Files.controller.go2Pdf.word2pdf(storagePath +"\\"+ fileID + fileType, p2
							+ fileID + ".pdf");
					//�ҳ�PDFҳ����
					PdfReader inputPDF = new PdfReader(p2+ fileID + ".pdf");
					int totalPages = inputPDF.getNumberOfPages();
					 
					/*System.out.println("�ļ���ַΪ��" + storagePath + fileID+ fileType);
					System.out.println("�ļ��洢��ַΪ��" + p2 + fileID+ ".pdf");
					System.out.println("PDFҳ����Ϊ��"+totalPages);*/
					String storePath = p2 + fileID + ".pdf";
					
					/*
					 * ת�浽ftp�������ϣ�
					 */
					InputStream in= new FileInputStream(new File(storePath));
					FTPupload.uploadFile(in, fileID+".pdf");
					
					/*
					 * ���ļ�·�����浽���ݿ���
					 */
					String ftpStorePath="ftp://59.68.29.68/test/"+fileID+".pdf";
					int userID2= fileInService.fileUpload(userID,fileID,ftpStorePath,totalPages);
					
					/*
					 * ɾ�����ڱ����������ϴ��ļ�
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
					//��ʽת��
					nth.FingerPrint.Files.controller.go2Pdf.ppt2pdf(storagePath + "\\"+fileID + fileType, p2
							+ fileID + ".pdf");
			
					//�ҳ�PDFҳ����
					PdfReader inputPDF = new PdfReader(p2+ fileID + ".pdf");
					int totalPages = inputPDF.getNumberOfPages();
					/*
					System.out.println("�ļ���ַΪ��" + storagePath + fileID+ fileType);
					System.out.println("�ļ��洢��ַΪ��" + p2 + fileID+ ".pdf");
					System.out.println("PDFҳ����Ϊ��"+totalPages);*/
					
					//String storePath = storagePathWord + fileID + fileType;
					String storePath = p2 + fileID + ".pdf";
					
					/*
					 * ת�浽ftp�������ϣ�
					 */
					InputStream in= new FileInputStream(new File(storePath));
					FTPupload.uploadFile(in, fileID+".pdf");
					
					/*
					 * ���ļ�·�����浽���ݿ���
					 */
					String ftpStorePath="ftp://59.68.29.68/test/"+fileID+".pdf";
					int userID2= fileInService.fileUpload(userID,fileID,ftpStorePath,totalPages);
					
					/*
					 * ɾ�����ڱ����������ϴ��ļ�
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
					//��ʽת��
					nth.FingerPrint.Files.controller.go2Pdf.excel2pdf(storagePath +"\\"+ fileID + fileType, p2
							+ fileID + ".pdf");
				
					//�ҳ�PDFҳ����
					PdfReader inputPDF = new PdfReader(p2+ fileID + ".pdf");
							
					int totalPages = inputPDF.getNumberOfPages();
					
				/*	System.out.println("�ļ���ַΪ��" + storagePath+ fileID+ fileType);	
					System.out.println("�ļ��洢��ַΪ��" + p2 + fileID+ ".pdf");	
					System.out.println("PDFҳ����Ϊ��"+totalPages);*/
					
					String storePath = p2 + fileID + ".pdf";
					/*
					 * ת�浽ftp�������ϣ�
					 */
					InputStream in= new FileInputStream(new File(storePath));
					FTPupload.uploadFile(in, fileID+".pdf");
					
					/*
					 * ���ļ�·�����浽���ݿ���
					 */
					String ftpStorePath="ftp://59.68.29.68/test/"+fileID+".pdf";
					int userID2= fileInService.fileUpload(userID,fileID,ftpStorePath,totalPages);
					
					/*
					 * ɾ�����ڱ����������ϴ��ļ�
					 */
					DeleteFile.deleteFile(storagePath+fileID+fileType);
					DeleteFile.deleteFile(storePath);
					
					ShoppingCart sh= fileInService.findShoppingCart(ftpStorePath,userID2);
					String RJson=new Gson().toJson(sh);
					System.out.println(RJson+"dayin");
					return RJson;
				}
			} else {
				System.out.println("�ļ������ڣ�");
			}
			//return fileInService.fileUpload(user,fileID,storage,totalPages);
		} else {
			System.out.println("��Կ����,��ֹ���ʣ�");
		}
		return null;
		//return fileInService.fileUpload(user);
	}

	/*
	 * PDFԤ������  д���ϴ���������  �ϴ��ɹ� ��ͬʱ ��PDF·������ȥ
	 */
	/*@RequestMapping(value = "/returnPath.do",method = RequestMethod.POST)
	@ResponseBody
	public ShoppingCart returnPath(String appKey,String userID){
		System.out.println("************returnPath*********");
		return fileInService.returnPath(userID);
	}*/
	/*
	 *�޸Ĵ�ӡ�ĵ���Ϣ
	      ��ӡ����.��ӡ��ʽ
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
