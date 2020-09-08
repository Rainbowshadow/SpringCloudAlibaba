package com.lpx.springcloud.mo;

import java.io.Serializable;


public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer userid;         //用户ID
	private String username;	    //用户名
	private String openid;			//openid
	private String code;			//用户code
	
	private String codePuls;			//用户code
	
	private String password;	    //用户密码
	private String nickname;		//昵称
	private String gender;          //性别
	private Double weight;		    //体重
	private Double targetweight;   //目标体重
	private Double height;			//身高
	private String birthday;		//出生日期 
	private String nativecity;	    //出生地
	private String usphone;           //手机号
	private Integer businessid;     //店铺id
	private String u_picture;		//图片

	private Integer foodid;
	
	private Integer motionid;
	
	private Integer target_kcal;    //目标摄入卡路里
	
	private String u_role; //角色
	
	private String u_permission; //权限
	
	private String u_state;  //员工得状态（在职/离职）
	
	private String u_jobnum; //员工工号
	
	private String u_realName;//员工真实姓名

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodePuls() {
		return codePuls;
	}

	public void setCodePuls(String codePuls) {
		this.codePuls = codePuls;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getTargetweight() {
		return targetweight;
	}

	public void setTargetweight(Double targetweight) {
		this.targetweight = targetweight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNativecity() {
		return nativecity;
	}

	public void setNativecity(String nativecity) {
		this.nativecity = nativecity;
	}

	public String getUsphone() {
		return usphone;
	}

	public void setUsphone(String usphone) {
		this.usphone = usphone;
	}

	public Integer getBusinessid() {
		return businessid;
	}

	public void setBusinessid(Integer businessid) {
		this.businessid = businessid;
	}

	public String getU_picture() {
		return u_picture;
	}

	public void setU_picture(String u_picture) {
		this.u_picture = u_picture;
	}

	public Integer getFoodid() {
		return foodid;
	}

	public void setFoodid(Integer foodid) {
		this.foodid = foodid;
	}

	public Integer getMotionid() {
		return motionid;
	}

	public void setMotionid(Integer motionid) {
		this.motionid = motionid;
	}

	public Integer getTarget_kcal() {
		return target_kcal;
	}

	public void setTarget_kcal(Integer target_kcal) {
		this.target_kcal = target_kcal;
	}

	public String getU_role() {
		return u_role;
	}

	public void setU_role(String u_role) {
		this.u_role = u_role;
	}

	public String getU_permission() {
		return u_permission;
	}

	public void setU_permission(String u_permission) {
		this.u_permission = u_permission;
	}

	public String getU_state() {
		return u_state;
	}

	public void setU_state(String u_state) {
		this.u_state = u_state;
	}

	public String getU_jobnum() {
		return u_jobnum;
	}

	public void setU_jobnum(String u_jobnum) {
		this.u_jobnum = u_jobnum;
	}

	public String getU_realName() {
		return u_realName;
	}

	public void setU_realName(String u_realName) {
		this.u_realName = u_realName;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username
				+ ", openid=" + openid + ", code=" + code + ", codePuls="
				+ codePuls + ", password=" + password + ", nickname="
				+ nickname + ", gender=" + gender + ", weight=" + weight
				+ ", targetweight=" + targetweight + ", height=" + height
				+ ", birthday=" + birthday + ", nativecity=" + nativecity
				+ ", usphone=" + usphone + ", businessid=" + businessid
				+ ", u_picture=" + u_picture + ", foodid=" + foodid
				+ ", motionid=" + motionid + ", target_kcal=" + target_kcal
				+ ", u_role=" + u_role + ", u_permission=" + u_permission
				+ ", u_state=" + u_state + ", u_jobnum=" + u_jobnum
				+ ", u_realName=" + u_realName + "]";
	}
	



}
