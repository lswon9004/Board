package member.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import member.board.dao.RefboardDao;
import member.board.dto.RefboardDto;

@Service
public class RefboardService {

	@Autowired
	RefboardDao dao;
	@Transactional
	public void insert(RefboardDto dto) {
		
		if(dto.getRef() == 0 ) { // 제목글
			dao.insert(dto);
			//System.out.println("no :: "+dto.getNo());
			dao.updateRef(dto.getNo());
		}else { //답글
			dao.updateStep(dto);
			dto.setRe_step(dto.getRe_step()+1);
			dto.setRe_level(dto.getRe_level()+1);
			dao.insert(dto);
		}	
	}
		
	public int count() {
		return dao.count();
	}
			
	public List<RefboardDto> selectList(int start){
		Map<String, Object> m = new HashMap<>();
		m.put("start", start);
		m.put("count", 10);
		return dao.selectList(m);
	}
			
	public RefboardDto selectOne(int no) {
		dao.updateReadcount(no);
		return dao.selectOne(no);
	}
	public int deleteOne(int no) {
		return dao.deleteOne(no);
	}
	public int updateOne(RefboardDto dto) {
		return dao.updateOne(dto);
	}
}