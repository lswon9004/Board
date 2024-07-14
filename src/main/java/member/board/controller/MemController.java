package member.board.controller;

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpServletRequest;
import member.board.dto.MemDto;
import member.board.service.EmailService;
import member.board.service.MemService;
import member.board.vo.EmailVO;

@Controller
@SessionAttributes("user")
public class MemController {
	@Autowired
	MemService service;
	@Autowired
	private EmailService emailService;
	@ModelAttribute("user")
	public MemDto getDto() {
		return new MemDto();
	}
	
	@GetMapping("/insert")
	public String joinform() {
		return "mem/joinform";
	}
	@GetMapping("/idCheck")
	@ResponseBody
	public String idCheck(@RequestParam("id") String id) {
		String checkid = service.idCheck(id);
		return checkid;
	}
	
	@PostMapping("/insert")
	public String insert(MemDto dto) {
		service.insertMem(dto);
		return "redirect:loginform";
	}
	@GetMapping("/loginform")
	public String loginform() {
		return "mem/loginform";
	}
	@PostMapping("/login")
	public String login(@ModelAttribute("command") @Validated MemDto dto, BindingResult error, Model m, HttpServletRequest res) {
		System.out.println(dto);
		
		
		if(error.hasErrors()) {
			return "mem/loginform";
		}
		MemDto resultDto = service.login(dto);
		if(resultDto == null) {
			error.reject("nocode", "로그인 실패: 아이디나 비밀번호가 틀림");	
			return "mem/loginform";
		}else {//로그인 성공
			res.getSession().setAttribute("result", true);
			m.addAttribute("user", resultDto);
		}
		return "redirect:/main";
	}
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/main";
	}
	
	@GetMapping("/update")
	public String updateform(@ModelAttribute("user") MemDto dto) {
		return "mem/updateform";
	}
	@PutMapping("/update")
	public String update(@ModelAttribute("user") MemDto dto) {
		service.updateMem(dto);
		return "redirect:/main";
	}
	@GetMapping("/delete")
	public String deleteform(@RequestParam(value="result", required = false) String result, Model m) {
		m.addAttribute("result",result);
		return "mem/deleteform";
	}
	@DeleteMapping("/delete")
	public String delete(@RequestParam("formpw") String formpw, @ModelAttribute("user") MemDto dto, SessionStatus status) {
	
		int i = service.deleteMem(formpw, dto);
		if(i == 0) {
			
			return "redirect:/delete?result=false";
		}else {
			status.setComplete();
			return "redirect:/main";
		}
	}
	@GetMapping("/main")
	public String main(@ModelAttribute("user") MemDto dto) {
		if(dto.getId() != null) {
			return "mem/main";
		}else {
			return "mem/loginform";
		}
	}
	@GetMapping("/findpw")
	public String findpw(@RequestParam(value="resultq", required = false) String result, Model m) {
		m.addAttribute("resultq",result);
		return "mem/findpw";
	}
	@PostMapping("/findpw")
	public String newpw(@RequestParam("id") String id, @RequestParam("name") String name, Model m) {
		MemDto dto = service.findpw(id, name);
		if(dto==null) {
			return "redirect:/findpw?resultq=false";
		}else {
			String pw = createNewPassword();
			int i = service.updatePw(pw, id);
			m.addAttribute("id", id);
			return "mem/getEmail";
		}
		
	}
	@PostMapping("/sendw")
	public String sendMail2(@RequestParam("emailAddress") String emailAddress, @RequestParam("id") String id) throws Exception {

		EmailVO email = new EmailVO();

		String receiver = emailAddress; // Receiver.

		String subject = "비밀 번호가 초기화되었습니다.";

		String number = service.getPw(id);

		String content = "초기화된 비밀 번호는 "+number+"입니다.";
		
		email.setReceiver(receiver);
		email.setSubject(subject);
		email.setContent(content);

		emailService.sendMail(email);
		return "mem/loginform";

	}
	private String createNewPassword() {
	      char[] chars = new char[] {
	            '0','1','2','3','4','5','6','7','8','9',
	            'a','b','c','d','e','f','g','h','i','j',
	            'k','l','m','n','o','p','q','r','s','t',
	            'u','v','w','x','y','z'   };
	      
	      StringBuffer stringBuffer = new StringBuffer();
	      SecureRandom secureRandom = new SecureRandom();
	      secureRandom.setSeed(new Date().getTime());

	      int index = 0;
	      int length = chars.length;
	      for(int i = 0; i < 8; i++) {
	         index = secureRandom.nextInt(length);
	         
	         if(index % 2 == 0) {
	            stringBuffer.append(String.valueOf(chars[index]).toUpperCase());
	         }else {
	            stringBuffer.append(String.valueOf(chars[index]).toLowerCase());
	         }
	      }
	      
	      System.out.println("newPASSWORD : " + stringBuffer.toString());
	      
	      return stringBuffer.toString();
	   }
}