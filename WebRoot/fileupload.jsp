<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>����springmvc���ϴ���ʵ��</title>
	</head>
	<body>
	<h1>�ϴ�����.jsp</h1>
	<p>
<form action="fileUpload.do" method="post" enctype="multipart/form-data" >
			<input type="file" name="file" /><br>
			�ļ����ͣ�.doc/.docx/.ppt /.pptx/ .xlsx/.xls����
			<input type="text" name="fileType"/><br>
			<input type="text" name="userID" value="1"/><br>
			<input type="text" name="appKey" value="fingerPrint"/>
			<input type="submit" value="�ϴ��ĵ�" />
		</form>
