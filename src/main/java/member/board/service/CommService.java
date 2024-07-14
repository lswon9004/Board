package member.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import member.board.dao.CommDao;
import member.board.dto.Comm1;
import member.board.dto.CommDto;

@Service
public class CommService {
	@Autowired
	CommDao dao;
	
	public List<CommDto> selectComm(int no){
		return dao.selectComm(no);
	}
	public int insertComm(CommDto dto) {
		return dao.insertComm(dto);
	}
	public int deleteComm(int cno) {
		return dao.deleteComm(cno);
	}
	@Transactional
	public void insertComm1(Comm1 dto) {
		if(dto.getRef() == 0 ) { // 제목글
			dao.insertComm1(dto);
			//System.out.println("no :: "+dto.getNo());
			dao.updateRef(dto.getNo());
		}else { //답글
			dao.updateStep(dto);
			dto.setRe_step(dto.getRe_step()+1);
			dto.setRe_level(dto.getRe_level()+1);
			dao.insertComm1(dto);
		}
	}
	public List<Comm1> selectComm1(int no){
		return dao.select(no);
	}
}
