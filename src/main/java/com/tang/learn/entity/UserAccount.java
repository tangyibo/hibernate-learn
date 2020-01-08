package com.tang.learn.entity;

import java.util.Date;
import lombok.Data;

@Data
public class UserAccount {
	private Integer id;
	private String username;
	private String password;
	private String nickname;
	private String phone;
	private String email;
	private Boolean enabled;
	private Date createTime;
	private Date updateTime;
}
