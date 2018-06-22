package com.inc.dayary.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.inc.dayary.domain.Member;
import com.inc.dayary.repository.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	//starter dependency를 등록해줘야 사용할 수 있다.

	@Override
	public Member findOne(String id) {
		return memberDao.findOne(id);
	}

	@Override
	public boolean emailDupCheck(String email) {
		Member member = memberDao.getFindOneByEmail(email);
		return member != null; //멤버가 null이 아니면 true, null이면 false
	}

	@Override
	public String sendCertifyEmail(String email) {
		//이메일 보내는 작업
		String from = "lycorise@gmail.com";
		String subject = "Dayary 이메일 인증";
		String emailCode = makeRandomCode();
		String content = "인증코드: " + emailCode;
		
		
		try {
			//메일 추상화 객체
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
			helper.setFrom(from);
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(content);
			javaMailSender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
		return emailCode;
	}
	
	private String makeRandomCode() {
		StringBuffer sb = new StringBuffer();
		for (int i= 0; i <4 ; i++) {
			sb.append((int)(Math.random()*10));
		}
		return sb.toString();
	}

	@Override
	public void signup(Member member) {
		memberDao.signup(member);
	}
}
