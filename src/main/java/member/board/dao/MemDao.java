package member.board.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import member.board.dto.MemDto;

@Mapper
public interface MemDao {

	String idCheck(String id);
	int insertMem(MemDto dto);
	MemDto login(MemDto dto);
	int updateMem(MemDto dto);
	int deleteMem(String id);
	@Select("select id, name from mem where id = #{id} and name = #{name}")
	MemDto findpw(@Param("id") String id, @Param("name") String name);
	@Update("update mem set password = #{password} where id = #{id}")
	int updatePw(@Param("password") String pw, @Param("id") String id);
	@Select("select password from mem where id = #{id}")
	String getPw(@Param("id") String id);
	@Select("select id,name, birth, address from mem")
	List<MemDto> memsInfo();
}
