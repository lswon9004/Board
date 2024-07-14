package member.board.dto;

import java.util.Date;
import lombok.Data;

@Data
public class Comm1 {
	private int cno;
	private String id;
	private String content;
	private Date regdate;
	private int no;
	private int ref;//그룹 번호 
	private int re_step; //그룹 내 순서
	private int re_level;// 레벨
}