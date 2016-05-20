package nth.FingerPrint.Users.Dao;

import java.util.List;

import javax.annotation.Resource;

import nth.FingerPrint.Users.po.User;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
	@Resource
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean addUser(User user) {
//		boolean b; 
//		if( b=hibernateTemplate.save(user) != null){
//			return b;
//		}
//		return false;
		System.out.println(this.getHibernateTemplate());
		System.out.println(user.getUserName());
		hibernateTemplate.save(user);
		return true;
	}

	public boolean updateUser(User user) {  
		try{
			hibernateTemplate.update(user);  
			}catch(Exception e){
			return false;
		}
		return true;
	}  
	
	public User queryUserByID(User user){
		String hql="from User u where u.userID=?";
		if(hibernateTemplate.find(hql,user.getUserID()).isEmpty()){
			return null;
		}
		return (User) hibernateTemplate.find(hql,user.getUserID()).get(0);
	}
	
	public User queryUserByName(User user){
		String hql="from User u where u.userName=?";
		List<User> list=hibernateTemplate.find(hql,user.getUserName());
		if(list.isEmpty()){
			return null;
		}
		return (User) list.get(0);
	}

	public User queryUserByOpenID(User user) {
		System.out.println(user.getAssociationWeChat());
		String hql="from User u where u.associationWeChat=?";
		List<User> list=hibernateTemplate.find(hql,user.getAssociationWeChat());
		if(list.isEmpty()){
			return null;
		}
		return (User) list.get(0);
	}
	
}
