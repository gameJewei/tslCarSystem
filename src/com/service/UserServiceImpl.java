package com.service;

import com.dao.PersonalInfoDao;
import com.dao.UserDao;
import com.entity.User;
import com.util.IDUtil;
import com.util.MD5Util;
import com.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserDao dao;

	
	@Resource
	private PersonalInfoDao pidao;
	
	public Result login(String userName, String userPwd) {
		User user = dao.userLogin(userName);
		Result res = null;
		String md5Password=MD5Util.md5(userPwd);
		if (user == null) {
			res = new Result("1", "用户名错误", null);
		}else if (!user.getPassword().equals(md5Password)) {
			res = new Result("1", "密码错误", null);
		}else if (user.getStatus() == 1) {
			res = new Result("1", "该用户已被禁用", null);
		}else {
			Integer type=user.getType();
			String userId=user.getId();
			Map<String,Object> data = new HashMap<String, Object>();
			data.put("type", type);
			data.put("userId", userId);
			res = new Result("0", "登陆成功", data);

		}
		return res;
	}
	
	private static String getTime(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间
		String str = formatter.format(curDate);
		return str;
	}
	

	public Result changePwd(String user_id, String mpass, String newpass) {
		User user=dao.findUser(user_id);
		Result nr=null;
		if(mpass.equals("")){
			nr=new Result("1", "请输入原始密码", null);
		}else if(newpass.equals("")){
			nr=new Result("1", "请输入新密码", null);
		}else if(mpass.equals("") && newpass.equals("")){
			nr=new Result("1", "请输入密码", null);
		}else if(!MD5Util.md5(mpass).equals(user.getPassword())){
			nr=new Result("1", "原始密码不正确", null);
		}else{
			dao.changPwd(user_id, MD5Util.md5(newpass));
			nr=new Result("0", "密码修改成功", null);
		}
		return nr;		
	}

	@Override
	public Result userList() {
		// TODO Auto-generated method stub
		List<User> list=dao.userList();
		String time2 = getTime();
		return new Result("0", "用户信息加载成功", list);
	}

	@Override
	public Result addUser(String name, String password, Integer type) {
		// TODO Auto-generated method stub
		List<User> list=dao.userList();		
		Result nr=null;
		if(name.equals("")){
			nr=new Result("1", "请输入用户名", null);
		}else if(password.equals("")){
			nr=new Result("1", "请输入密码", null);
		}else{
			for(int i=0;i<list.size();i++){
				if(list.get(i).getName().equals(name)){
					nr=new Result("1", "该用户名已存在", null);
					return nr;
				}
			}
			User user=new User();
			String id=IDUtil.createId();
			user.setId(id);
			user.setName(name);
			user.setPassword(MD5Util.md5(password));
			user.setType(type);
			user.setStatus(0);
			dao.addUser(user);
			
			pidao.addUserId(id);
			nr=new Result("0", "用户添加成功", null);
		}			
			return nr;

	}

	@Override
	public Result changeStatus(String id) {
		// TODO Auto-generated method stub
		User user=dao.findUser(id);
		Integer status=user.getStatus();
		if(status==0){
			dao.changStatus(id, 1);
		}else{
			dao.changStatus(id, 0);
		}
		return new Result("0", "用户状态更改成功", null);
	}
	
}
