package member.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import member.board.dto.Comm1;
import member.board.dto.CommDto;
import member.board.service.CommService;

@RestController
public class CommController {
   @Autowired
   CommService service;
   
   @GetMapping("/comm/insert")
   //@ResponseBody
   public String insertComm(CommDto dto) {
      service.insertComm(dto);
      List<CommDto> cList = service.selectComm(dto.getNo());
      Gson gson = new Gson();
      return gson.toJson(cList); // List -> js.array
   }
   
   @GetMapping("/comm/delete/{cno}")
   //@ResponseBody
   public int deleteComm(@PathVariable("cno") int cno) {
      service.deleteComm(cno);
      return cno;
   }
   @GetMapping("/comm1/insert")
   //@ResponseBody
   public String insertComm1(Comm1 dto) {
      Gson gson = new Gson();
      return gson.toJson("cList"); // List -> js.array
   }
   
   @GetMapping("/comm1/delete/{cno}")
   //@ResponseBody
   public int deleteComm1(@PathVariable("cno") int cno) {
      return cno;
   }
}
