package member.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.board.dao.MemDao;
import member.board.dto.MemDto;

@Service
public class MemService {
	@Autowired
	MemDao dao;
	
	public String idCheck(String id) {
		return dao.idCheck(id);
	}
	public int insertMem(MemDto dto) {
		return dao.insertMem(dto);
	}
	public MemDto login(MemDto dto) {
		return dao.login(dto);
	}
	public int updateMem(MemDto dto) {
		return dao.updateMem(dto);
	}
	public int deleteMem(String formpw, MemDto dto ) {
		String pw = dto.getPassword();
		if(pw.equals(formpw)) {
			return dao.deleteMem(dto.getId());
		}else {
			return 0;
		}
	}
	public MemDto findpw(String id, String name) {
		return dao.findpw(id, name);
	}
	public int updatePw(String pw, String id) {
		return dao.updatePw(pw, id);
	}
	public String getPw(String id) {
		return dao.getPw(id);
	}
	public List<MemDto> memsInfo(){
		return dao.memsInfo();
	}
}
