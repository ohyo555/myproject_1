package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {

	@Autowired
	private Rq rq;
	
	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		Rq rq = (Rq) req.getAttribute("rq");

		if (!rq.isLogined()) {
			return Ut.jsHistoryBack("F-A", "이미 로그아웃 상태입니다");
		}

		rq.logout();

		return Ut.jsReplace("S-1", "로그아웃 되었습니다", "/");
	}

	@RequestMapping("/usr/member/login")
	public String showLogin(HttpServletRequest req) {

		Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined()) {
			return Ut.jsHistoryBack("F-A", "이미 로그인 함");
		}
		
		return "usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, HttpSession httpSession, String loginId, String loginPw) {

		Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined()) {
			return Ut.jsHistoryBack("F-A", "이미 로그인 함");
		}

		if (Ut.isNullOrEmpty(loginId)) {
			return Ut.jsHistoryBack("F-1", "아이디를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(loginPw)) {
			return Ut.jsHistoryBack("F-2", "비밀번호를 입력해주세요");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Ut.jsHistoryBack("F-3", Ut.f("%s(은)는 존재하지 않는 아이디입니다", loginId));
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-4", Ut.f("비밀번호가 일치하지 않습니다"));
		}

		rq.login(member);

		return Ut.jsReplace("S-1", Ut.f("%s님 환영합니다", member.getLoginId()), "/");
	}

	@RequestMapping("/usr/member/join")
	public String showJoin(HttpServletRequest req) {

		Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined()) {
			return Ut.jsHistoryBack("F-A", "이미 로그인 함");
		}

		return "usr/member/join";
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(HttpServletRequest req, String loginId, String loginPw, String birth, String mname, 
			String cellphoneNum, String email, String address) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		if (rq.isLogined()) {
			return Ut.jsHistoryBack("F-A", "이미 로그인 상태입니다");
		}
		if (Ut.isNullOrEmpty(loginId)) {
			return Ut.jsHistoryBack("F-1", "아이디를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(loginPw)) {
			return Ut.jsHistoryBack("F-2", "비밀번호를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(mname)) {
			return Ut.jsHistoryBack("F-3", "이름을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(birth)) {
			return Ut.jsHistoryBack("F-4", "생년월일을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(email)) {
			return Ut.jsHistoryBack("F-5", "이메일을 입력해주세요");
		}
 
		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, birth, mname, cellphoneNum, email, address);

		if (joinRd.isFail()) {
			return Ut.jsHistoryBack(joinRd.getResultCode(), joinRd.getMsg());
		}

		Member member = memberService.getMember(joinRd.getData1());

		return Ut.jsReplace(joinRd.getResultCode(), joinRd.getMsg(), "../member/login");
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/usr/member/doAction")
	@ResponseBody
	public String doAction(String loginId) {
		
		Member existsMember = memberService.getMemberByLoginId(loginId);
		
		String msg = "중복된 아이디가 존재합니다.";
		System.out.println("#$#$#$#$#$#" + loginId);
		if (existsMember == null) {
			if (loginId == "") {
				msg = "아이디는 필수 정보입니다.";
				return msg;
			}
			msg = "사용가능한 아이디입니다.";
			return msg;
		}

		return msg;
	}
	
	@RequestMapping("/usr/member/mypage")
	public String showMypage(HttpServletRequest req, Model model) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		int id = rq.getLoginedMemberId();
		
		Member member = memberService.getMember(id);
		
		model.addAttribute("member", member);
		
		return "usr/member/mypage";
	}
	
	@RequestMapping("/usr/member/modify")
	public String showModify(HttpServletRequest req, Model model) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		int id = rq.getLoginedMemberId();
		
		Member member = memberService.getMember(id);
		
		model.addAttribute("member", member);
		
		return "usr/member/modify";
	}
	
	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, String loginPw, String mname,
			String cellphoneNum, String email) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		int id = rq.getLoginedMemberId();
		
		if (Ut.isNullOrEmpty(loginPw)) {
			return Ut.jsHistoryBack("F-1", "비밀번호를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(mname)) {
			return Ut.jsHistoryBack("F-2", "이름을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(cellphoneNum)) {
			return Ut.jsHistoryBack("F-4", "전화번호를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(email)) {
			return Ut.jsHistoryBack("F-5", "이메일을 입력해주세요");
		}
		
		
		return Ut.jsReplace("S-1", "회원정보가 수정되었습니다", "/");
	}
	
	@RequestMapping("/usr/member/findId")
	public String showFindId(HttpServletRequest req) {

		Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined()) {
			return Ut.jsHistoryBack("F-A", "이미 로그인 함");
		}

		return "usr/member/findId";
	}
	
	@RequestMapping("/usr/member/dofindId")
	@ResponseBody
	public String doFindId(HttpServletRequest req, String mname, String cellphoneNum, String email) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		int id = rq.getLoginedMemberId();
		
		if (Ut.isNullOrEmpty(mname)) {
			return Ut.jsHistoryBack("F-1", "이름을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(cellphoneNum)) {
			return Ut.jsHistoryBack("F-2", "전화번호를 입력해주세요");
		}
		
		return Ut.jsReplace("S-1", "회원정보가 수정되었습니다", "/");
	}
	
	@RequestMapping("/usr/member/findPw")
	public String showFindPw(HttpServletRequest req) {

		Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined()) {
			return Ut.jsHistoryBack("F-A", "이미 로그인 함");
		}

		return "usr/member/findPw";
	}
	
	@RequestMapping("/usr/member/dofindPw")
	@ResponseBody
	public String doFindPw(HttpServletRequest req, String loginId) {
		
//		Rq rq = (Rq) req.getAttribute("rq");
//		
//		int id = rq.getLoginedMemberId();
		
		if (Ut.isNullOrEmpty(loginId)) {
			return Ut.jsHistoryBack("F-1", "아이디를 입력해주세요");
		}
		
		return Ut.jsReplace("S-1", "회원정보가 수정되었습니다", "/");
	}
	
}