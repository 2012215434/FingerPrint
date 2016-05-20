package nth.FingerPrint.Users.Action;
import nth.FingerPrint.common.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nth.FingerPrint.Email.DESPlus;
import nth.FingerPrint.Email.emailController;
import nth.FingerPrint.Users.Dao.UserDao;
import nth.FingerPrint.Users.Service.UserService;
import nth.FingerPrint.Users.common.MyHttpClient;
import nth.FingerPrint.Users.po.User;
import nth.FingerPrint.Users.po.User_state;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class UserAction {
	@Resource
	UserService userService=new UserService();

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/login.do")
	@ResponseBody
	 public String login(HttpServletRequest request) throws IOException {
		
		String json=request.getParameter("user");
		String appKey=request.getParameter("appkey");
		
		if(appKey.equals("FingerPrint")){
			User_state s=new User_state();
			Gson gson= new Gson();
			Type listType = new TypeToken<User>(){}.getType(); 
		    User user  = gson.fromJson(json,listType);
		
		    User u=userService.getUserByName(user);
		    if(u==null){
		    	s.setState(false);
		    	String RJson=new Gson().toJson(s);
		    	System.out.println(RJson);
			    return RJson;
		    	
		    }else{
		    	if(u.getNotes().equals("0")){//表示没有验证邮箱；
		    		s.setValidate("0");
		    		s.setState(false);
			    	String RJson=new Gson().toJson(s);
			    	System.out.println(RJson);
				    return RJson;
		    	}else{
		    		s.setState(true);
			    	s.setUserID(u.getUserID());
			    	s.setAssociationWeChat(u.getAssociationWeChat());
			    	s.setHeadPicturePath(u.getHeadPicturePath());
			    	s.setNickName(u.getNickName());
			    	s.setUserName(u.getUserName());
			    	s.setRegisterTime(u.getRegisterTime());

			    	String RJson=new Gson().toJson(s);
				    return RJson;
		    	}	
		    } 
		}
		return null;
	   }
	   
	@RequestMapping("/addUser.do")
	@ResponseBody
	public String addUser(HttpServletRequest request) throws Exception{
		User_state s=new User_state();
		String json=request.getParameter("user");
		String appKey=request.getParameter("appkey");
		
		if(appKey.equals(ApplicationKey.appKey)){
			Gson gson= new Gson();
			Type listType = new TypeToken<User>(){}.getType(); 
		    User user  = gson.fromJson(json,listType);
			User u=userService.getUserByName(user);
			
			if(u != null){
				s.setState(false);
				String RJson=new Gson().toJson(s);
				return RJson;
			}else{
				emailController.getEmail(user.getUserName());
				user.setNotes("0");
				userService.addUser(user);
				u=userService.getUserByName(user);
				s.setState(true);
				s.setUserID(u.getUserID());
				s.setNotes("0");
		    	s.setAssociationWeChat(u.getAssociationWeChat());
		    	s.setHeadPicturePath(u.getHeadPicturePath());
		    	s.setNickName(u.getNickName());
		    	s.setUserName(u.getUserName());
		    	s.setRegisterTime(u.getRegisterTime());
				String RJson=new Gson().toJson(s);
				return RJson;
			}		
		}
		
		String RJson=new Gson().toJson(s);
		return RJson;		
	}
	
	  @RequestMapping("/updateUserInfo.do")
	  @ResponseBody
	   public User_state updateUserInfo(HttpServletRequest request) {
		 
		  String json=request.getParameter("user");
		  String appKey=request.getParameter("appKey");
		  User_state state= new User_state();
			if(appKey.equals("FingerPrint")){
				Gson gson= new Gson();
				Type listType = new TypeToken<User>(){}.getType(); 
			    User user  = gson.fromJson(json,listType);
			     if(userService.updateUserInfo(user)){
			    	 state.setState(true);
			    	 return state;
			     }
			     return state;
			}
		    return state;
	   }
	  
	  
	  @RequestMapping("/validate.do")
	  public void isValidate(HttpServletRequest request,HttpServletResponse response) throws Exception{
		  String UID = request.getParameter("UID");
		  DESPlus des = new DESPlus("cao+zhang+li");// 自定义密钥
		  String userName=des.decrypt(UID);
		  User user = new User();
		  user.setUserName(userName);
		  User u=userService.getUserByName(user);
		  if(u!=null){
			  u.setNotes("1");
			  userService.updateUserInfo(u);
			  response.setCharacterEncoding("utf-8");
			  PrintWriter out = response.getWriter();
			  String data="<html>"+
					  "<head>"+
					  "<title>指印账号激活</title>"+
					  "<meta http-equiv='content-type' content='text/html;charset=utf-8'/>"+
					  "<body>"+
					  "<label style='font-size: xx-large'>激活成功!</label><br>"+
					  "<a href='http://59.68.29.68:8080/web/index.html'>返回首页</a>"+
					  "</body>"+
					  "</head>"+
					  "</html>";
			  out.write(data);
		  }else{
			  PrintWriter out = response.getWriter();
			  response.setCharacterEncoding("utf-8");
			  String data="<html>"+
					  "<head>"+
					  "<title>指印账号激活</title>"+
					  "<meta http-equiv='content-type' content='text/html;charset=utf-8'/>"+
					  "<body>"+
					  "<label style='font-size: xx-large'>激活失败!该用户不存在</label><br>"+
					  "<a href='http://59.68.29.68:8080/web/index.html'>返回首页</a>"+
					  "</body>"+
					  "</head>"+
					  "</html>";
			  out.write(data);
		  }
	  }
	  
	  @RequestMapping("/getUserInfo.do")
	  @ResponseBody
	  public String getUserInfo(@RequestParam("userID")String userID,@RequestParam("appkey")String appKey){
		  
		  if(appKey.equals(ApplicationKey.appKey)){
			  User u=new User();
			  u.setUserID(Integer.valueOf(userID));
			  User userInfo= userService.getUserInfoByID(u);
			  String RJson=new Gson().toJson(userInfo);
			  return RJson;
		  }
		 return null;  
	  }
	  
	  @RequestMapping("/bindWeChat.do")
	  @ResponseBody
	  public String bindWeChat(@RequestParam("userName")String userName,@RequestParam("password")String password,
			  @RequestParam("openID")String associationWechat,@RequestParam("appKey")String appKey){
		  if(appKey.equals(ApplicationKey.appKey)){
			  User u=new User();
			  u.setUserName(userName);
			  User u1=userService.getUserByName(u);
			  if(u1!=null){
				  if(u1.getPassword().equals(password)){
					  if(u1.getAssociationWeChat()==null||u1.getAssociationWeChat().equals(null)){
						  //该用户已绑定
						  return "{\"state\":2}";
					  }else if(!u1.getNotes().equals("1")){
						  //该用户未激活
						 return "{\"state\":4}";
					  } else{
						  u1.setAssociationWeChat(associationWechat);
						  userService.updateUserInfo(u1);
						  //该用户绑定成功；
						  return "{\"state\":3" +
					  		",\"userName\":\""+ u1.getUserName()+"\"" +
					  		",\"nickName\":\""+ u1.getNickName()+"\"" +
					  		",\"userID\":\""+ u1.getUserID()+"\"" +
					  		",\"password\":\""+ u1.getPassword() +"\"}";
					  }
				  }else{
					  //用户密码错误；
					  return "{\"state\":1}";
				  }
			  }else{
				  //该用户不存在；
				  return "{\"state\":0}";
			  }
		  }
		  //appkey不正确；
		  return "{\"state\":-1}";
	  }
	  
	  @RequestMapping("/IsBindWeChat.do")
	  @ResponseBody
	  public String IsBindWeChat(@RequestParam("openID")String associationWechat,@RequestParam("appKey")String appKey){
		  if(appKey.equals(ApplicationKey.appKey)){
			  System.out.println(appKey);
			  User u=new User();
			  u.setAssociationWeChat(associationWechat);
			  User u1=userService.getUserByOpenID(u);
			  if(u1!=null){
				  return "{\"state\":1" +
				  		",\"userName\":\""+ u1.getUserName()+"\"" +
				  		",\"nickName\":\""+ u1.getNickName()+"\"" +
				  		",\"userID\":\""+ u1.getUserID()+"\"" +
				  		",\"password\":\""+ u1.getPassword() +"\"}";
			  }else{
				  return "{\"state\":0}"; 
			  }
		  }
		  //appkey不正确；
		  return "{\"state\":-1}"; 
	  }
	  
	  @RequestMapping("/getOpenID.do")
	  @ResponseBody
	  public String getOpenID(@RequestParam("code")String code,@RequestParam("appKey")String appKey){
		  if(appKey.equals(ApplicationKey.appKey)){
			  System.out.println(appKey);
			  String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd0bc49cb010c5d7d&secret=44d191c51b8cdaf8f5420da2f443dba3&code="+ code+"&grant_type=authorization_code";
			  String result=MyHttpClient.get(url);
			  System.out.println(result);
				  return result;
		  }
		  //appkey不正确；
		  return "{\"state\":-1}"; 
	  }
}
