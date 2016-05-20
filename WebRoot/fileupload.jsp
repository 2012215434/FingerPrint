<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>测试springmvc中上传的实现</title>
	</head>
	<body>
	<h1>上传测试.jsp</h1>
	<p>
<form action="fileUpload.do" method="post" enctype="multipart/form-data" >
			<input type="file" name="file" /><br>
			文件类型（.doc/.docx/.ppt /.pptx/ .xlsx/.xls）：
			<input type="text" name="fileType"/><br>
			<input type="text" name="userID" value="1"/><br>
			<input type="text" name="appKey" value="fingerPrint"/>
			<input type="submit" value="上传文档" />
		</form>
