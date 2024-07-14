package member.board.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import member.board.dto.Comm1;
import member.board.dto.RefboardDto;
import member.board.service.CommService;
import member.board.service.RefboardService;

@Controller
public class RefBoardController {

	@Autowired
	RefboardService service;
	@Autowired
	CommService c_service;

	@GetMapping("/ref/write")
	public String writeform(@ModelAttribute("dto") RefboardDto dto) {
		return "ref/write";
	}

	@PostMapping("/ref/write")
	public String write(RefboardDto dto) {
		service.insert(dto);
		return "redirect:list";
	}

	@GetMapping("/ref/list")
	public String list(@RequestParam(name = "p", defaultValue = "1") int page, Model m) {
		// 글이 있는지 체크
		int count = service.count();
		if (count > 0) {

			int perPage = 10; // 한 페이지에 보일 글의 갯수
			int startRow = (page - 1) * perPage;

			List<RefboardDto> boardList = service.selectList(startRow);
			m.addAttribute("start", startRow+1);
			m.addAttribute("bList", boardList);

			int pageNum = 5;
			int totalPages = count / perPage + (count % perPage > 0 ? 1 : 0); // 전체 페이지 수

			int begin = (page - 1) / pageNum * pageNum + 1;
			int end = begin + pageNum - 1;
			if (end > totalPages) {
				end = totalPages;
			}
			m.addAttribute("begin", begin);
			m.addAttribute("end", end);
			m.addAttribute("pageNum", pageNum);
			m.addAttribute("totalPages", totalPages);
		}
		m.addAttribute("count", count);

		return "ref/boardList";
	}

	@GetMapping("/ref/content/{no}")
	public String content(@PathVariable("no") int no, Model m) {
		RefboardDto dto = service.selectOne(no);
		List<Comm1> clist = c_service.selectComm1(no);
		m.addAttribute("clist", clist);
		m.addAttribute("article", dto);
		return "ref/content";
	}
	@GetMapping("ref/delete/{no}")
	public String delete(@PathVariable("no") int no) {
		service.deleteOne(no);
		return "redirect:/ref/list";
	}
	@GetMapping("/ref/update/{no}")
	public String updateSetting(@PathVariable("no") int no, Model m) {
		RefboardDto dto = service.selectOne(no);
		m.addAttribute("dto", dto);
		return "ref/updateForm";
	}
	@PostMapping("/ref/update/")
	public String update(RefboardDto dto) {
		service.updateOne(dto);
		return "redirect:/ref/list";
	}
	
}
