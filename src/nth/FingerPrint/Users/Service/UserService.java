package nth.FingerPrint.Users.Service;

import javax.annotation.Resource;

import nth.FingerPrint.Users.Dao.UserDao;
import nth.FingerPrint.Users.po.User;


import org.springframework.stereotype.Component;
/***********************************************************************
 * Module:  UserService.java
 * Author:  уей╓
 * Purpose: Defines the Class UserService
 ***********************************************************************/
@Component
public class UserService {
	@Resource
	UserDao userDao=new UserDao();

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean addUser(User user) {
       return userDao.addUser(user);
   }
   
   public boolean updateUserInfo(User user) {
	   return userDao.updateUser(user);
   }
 
   public User getUserInfoByID(User user) {
	   return userDao.queryUserByID(user);
   }
   
   public User getUserByName(User user){
	   return userDao.queryUserByName(user);
   }
   public User getUserByOpenID(User user){
	   return userDao.queryUserByOpenID(user);
   }
}
