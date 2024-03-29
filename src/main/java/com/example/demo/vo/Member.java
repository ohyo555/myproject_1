package com.example.demo.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int id;
	private String loginId;
	private String loginPw;
	private Date birth;
	private String mname;
	private String cellphoneNum;
	private String email;
	private String address;
	private int authLevel;
	private String membercode;
	private String regDate;
	private String updateDate;
	private boolean delStatus;
	private String delDate;

}