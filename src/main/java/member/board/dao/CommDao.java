package member.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import member.board.dto.Comm1;
import member.board.dto.CommDto;

@Mapper
public interface CommDao {
	List<CommDto> selectComm(int no);
	int insertComm(CommDto dto);
	int deleteComm(int cno);
	
	@Select("select * from comm1 where no = #{no}")
	List<Comm1> select(int no);
	
	@Insert("insert into comm1(id, content,regdate, no,ref, re_step, re_level) values(#{id}, #{content}, #{regdate}, #{regdate}, #{ref}, #{re_step}, #{re_level})")
	@Options(useGeneratedKeys = true, keyProperty = "no")
	int insertComm1(Comm1 dto);
	@Delete("delete from comm1 where cno = #{cno}")
	int deleteComm1(int cno);
	@Update("update comm1 set ref = #{no} where no =#{no}")// 제목글일때만
	int updateRef(int no);
	@Update("update comm1 set re_step = re_step+1 where ref =#{ref} and re_step > #{re_step}")
	int updateStep(Comm1 dto);
}