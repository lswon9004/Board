package member.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import member.board.dto.BoardDto;
import member.board.dto.CommDto;
import member.board.dto.MemDto;
import member.board.service.BoardService;
import member.board.service.CommService;

@SessionAttributes("user")
@Controller
public class BoardController {

	@Autowired
	CommService c_service;
	
	@Autowired
	BoardService service;
	
	@ModelAttribute("user")
	public MemDto getDto() {
		return new MemDto();
	}
	
	@GetMapping("/board/write")
	public String writeForm(@ModelAttribute("user")MemDto dto) {
		return "board/write";
	}
	
	@PostMapping("/board/write")
	public String write(BoardDto dto) {
		service.insert(dto);
		return "redirect:/board/list";// 글목록
	}
	//요청 page 번호를 받아서 페이지에 맞는 글을 갯수에 맞게 꺼내옴
	//전체 글 갯수에 맞춰 페이징 처리
	@GetMapping("/board/list")
	public String list(@RequestParam(name="p", defaultValue = "1") int page, Model m ) {
		
		//글이 있는지 체크
		int count = service.count();
		if(count > 0) {
		
		int perPage = 10; // 한 페이지에 보일 글의 갯수
		int startRow = (page - 1) * perPage;//인덱스 번호
		
		List<BoardDto> boardList = service.boardList(startRow);
		
		m.addAttribute("bList", boardList);

		int pageNum = 5;
		int totalPages = count / perPage + (count % perPage > 0 ? 1 : 0); //전체 페이지 수
		
		int begin = (page - 1) / pageNum * pageNum + 1;
		int end = begin + pageNum -1;
		if(end > totalPages) {
			end = totalPages;
		}
		 m.addAttribute("begin", begin);
		 m.addAttribute("end", end);
		 m.addAttribute("pageNum", pageNum);
		 m.addAttribute("totalPages", totalPages);
		
		}
		m.addAttribute("count", count);
		return "board/list";
	}
	
//	@GetMapping("board/content/{no}")
//	public String content(@ModelAttribute("user")MemDto user, @PathVariable("no") int no, Model m) {
//		BoardDto dto = service.boardOne(no);
//		m.addAttribute("dto", dto);
//		return "board/content";
//	}
	
	@GetMapping("board/update/{no}")
	public String updateForm(@PathVariable("no") int no, Model m) {
		BoardDto dto = service.boardOne(no);
		m.addAttribute("dto", dto);
		return "board/updateForm";
	}
	
	@PutMapping("/board/update")
	public String update(BoardDto dto) {
		service.updateBoard(dto);
		return "redirect:/board/content/"+dto.getNo();
	}
	
	@DeleteMapping("/board/delete")
	@ResponseBody
	public String delete(@RequestParam("no") int no) {
		int i = service.deleteBoard(no); 
		return ""+i;
	}
	@GetMapping("/board/search")
	public String search(@RequestParam("searchn") int searchn,@RequestParam("search") String search,@RequestParam(name="p", defaultValue = "1") int page, Model m) {
		int count = service.countSearch(searchn,search);
		if(count > 0) {
		
		int perPage = 10; // 한 페이지에 보일 글의 갯수
		int startRow = (page - 1) * perPage;
		//int endRow = page * perPage;
		
		List<BoardDto> boardList = service.boardListSearch(searchn,search,startRow, perPage);
		m.addAttribute("bList", boardList);

		int pageNum = 5;
		int totalPages = count / perPage + (count % perPage > 0 ? 1 : 0); //전체 페이지 수
		
		int begin = (page - 1) / pageNum * pageNum + 1;
		int end = begin + pageNum - 1;
		if(end > totalPages) {
			end = totalPages;
		}
		 m.addAttribute("begin", begin);
		 m.addAttribute("pageNum", pageNum);
		 m.addAttribute("totalPages", totalPages);
		 m.addAttribute("end", end);
		}
		m.addAttribute("count", count);
		m.addAttribute("searchn", searchn);
		m.addAttribute("search", search);
		
		return "board/search";
	}
	
	@GetMapping("board/content/{no}")
	public String content(@PathVariable("no") int no, Model m) {
		BoardDto dto = service.boardOne(no);
		m.addAttribute("dto", dto);
		List<CommDto> cList = c_service.selectComm(no);
		m.addAttribute("cList", cList);
		return "board/content";
	}   
}
