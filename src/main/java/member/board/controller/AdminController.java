package member.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import member.board.dto.MemDto;
import member.board.service.MemService;

@Controller
public class AdminController {

	@Autowired
	MemService service;

	@GetMapping("/mem/info")
	public void memsInfo(HttpServletResponse response) {

		String fileName = "";
		try {
			fileName = new String("회원 정보.xlsx".getBytes("utf-8"), "iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setContentType("ms-vnd/excel");

		//response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");

		List<MemDto> mlist = service.memsInfo();

		Workbook workbook = new SXSSFWorkbook(); 

		Sheet sheet = workbook.createSheet("첫번째 시트");
		Row row = null;
		Cell cell = null;
		int rowNum = 0;

		// Header
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("id");
		cell = row.createCell(1);
		cell.setCellValue("name");
		cell = row.createCell(2);
		cell.setCellValue("birth");
		cell = row.createCell(3);
		cell.setCellValue("address");

		// Body
		for (int i = 0; i < mlist.size(); i++) {
			row = sheet.createRow(rowNum++);
			MemDto m = mlist.get(i);
			cell = row.createCell(0);
			cell.setCellValue(m.getId());
			cell = row.createCell(1);
			cell.setCellValue(m.getName());
			cell = row.createCell(2);
			cell.setCellValue(format(m.getBirth()));
			cell = row.createCell(3);
			cell.setCellValue(m.getAddress());
		}

		// Excel File Output
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String format(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		return sdf.format(d);
	}

}