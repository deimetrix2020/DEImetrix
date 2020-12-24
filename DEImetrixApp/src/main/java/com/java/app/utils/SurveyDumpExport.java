package com.java.app.utils;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.java.app.beans.trans.TransSurveyQuestionsDO;

public class SurveyDumpExport extends AbstractExcelView{
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String text_val = "Common/Individual ";
		String text_val_leader = "Leader ";
		Map<Integer,List<TransSurveyQuestionsDO>> total_dump_report_unsort = (Map<Integer,List<TransSurveyQuestionsDO>>) model.get("survey_dump_list");
		//create a wordsheet
		HSSFSheet sheet = workbook.createSheet("Survey Report");
		
		Map<Integer,List<TransSurveyQuestionsDO>> total_dump_report = new TreeMap<Integer,List<TransSurveyQuestionsDO>>(
			new Comparator<Integer>() 
			{
	            @Override
	            public int compare(Integer o1, Integer o2) {
	                return o2.compareTo(o1);
	            }
        });
		
		total_dump_report.putAll(total_dump_report_unsort);
		
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("S.no");
		header.createCell(1).setCellValue("Survey date & time");
		header.createCell(2).setCellValue("Email id");
		
		int rowNum = 1;
		int display_sno = 1;
		int header_dynamicvalue = 0;
		int leader_header_dynamicvalue = 0;
		int header_satrt_value = 3;
		int header_demo_reportvalue = 0;
		//diversity
		int diversity_count = 1;
		int equity_count = 1;
		int inclusion_count = 1;
		//diversity leader
		int diversity_count_leader = 1;
		int equity_count_leader = 1;
		int inclusion_count_leader = 1;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		// header
		int dynamic_cell_count = 3;
		HSSFRow dynamic_row = sheet.createRow(rowNum++);
		
		first:
		for (Map.Entry<Integer,List<TransSurveyQuestionsDO>> entry : total_dump_report.entrySet()) 
		{
			List<TransSurveyQuestionsDO> fetch_report_list = entry.getValue();
			
			second:
			for(TransSurveyQuestionsDO surveyQuestions_val : fetch_report_list)
			{
				String[] split_demo_val = surveyQuestions_val.getsDemoGraphicsValues().split("#");
				
				for(int i=0;i<split_demo_val.length;i++)
				{
					if(i < 5)
					{
						String[] split_demo = split_demo_val[i].split("\\|");
						if(header_demo_reportvalue == 0)
						{
							header.createCell(header_satrt_value++).setCellValue("Direct Reports");
							header.createCell(header_satrt_value++).setCellValue(split_demo[0]);
							dynamic_row.createCell(dynamic_cell_count++).setCellValue("");
						} else
						{
							header.createCell(header_satrt_value++).setCellValue(split_demo[0]);
						}
						dynamic_row.createCell(dynamic_cell_count++).setCellValue("");
						header_demo_reportvalue++;						
					}
				}
				break second;
			}
			
			//Diversity
			for(TransSurveyQuestionsDO reportDO : fetch_report_list)
			{
				//create the row data
				if(header_dynamicvalue == 0 && reportDO.getcIsLeaderShip() == 'N')
				{
					if(reportDO.getsDomains().substring(0, 1).toUpperCase().equals("D"))
					{
						header.createCell(header_satrt_value++).setCellValue(text_val+" D"+(diversity_count++));
						dynamic_row.createCell(dynamic_cell_count++).setCellValue(isNullOrEmpty(reportDO.getsQuestions()));
					}
				}
			}
			//equity
			for(TransSurveyQuestionsDO reportDO : fetch_report_list)
			{
				//create the row data
				if(header_dynamicvalue == 0 && reportDO.getcIsLeaderShip() == 'N')
				{
					if(reportDO.getsDomains().substring(0, 1).toUpperCase().equals("E"))
					{
						header.createCell(header_satrt_value++).setCellValue(text_val+" E"+(equity_count++));
						dynamic_row.createCell(dynamic_cell_count++).setCellValue(isNullOrEmpty(reportDO.getsQuestions()));
					}
				}
			}
			//Inclusion
			for(TransSurveyQuestionsDO reportDO : fetch_report_list)
			{
				//create the row data
				if(header_dynamicvalue == 0 && reportDO.getcIsLeaderShip() == 'N')
				{
					if(reportDO.getsDomains().substring(0, 1).toUpperCase().equals("I"))
					{
						header.createCell(header_satrt_value++).setCellValue(text_val+" I"+(inclusion_count++));
						dynamic_row.createCell(dynamic_cell_count++).setCellValue(isNullOrEmpty(reportDO.getsQuestions()));
					}
				}
			}
			header_dynamicvalue++;
			break;
        }
		
		int leader_count = 0;
		// leader header
		for (Map.Entry<Integer,List<TransSurveyQuestionsDO>> entry : total_dump_report.entrySet()) 
		{
			List<TransSurveyQuestionsDO> fetch_report_list = entry.getValue();
			for(TransSurveyQuestionsDO reportDO : fetch_report_list)
			{
				//create the row data
				if(leader_header_dynamicvalue == 0 && reportDO.getcIsLeaderShip() == 'Y')
				{
					leader_count = 1;
					if(reportDO.getsDomains().substring(0, 1).toUpperCase().equals("D"))
					{
						header.createCell(header_satrt_value++).setCellValue(text_val_leader+" D"+(diversity_count_leader++));
						dynamic_row.createCell(dynamic_cell_count++).setCellValue(isNullOrEmpty(reportDO.getsQuestions()));
					}
				}
			}
			//equity
			for(TransSurveyQuestionsDO reportDO : fetch_report_list)
			{
				//create the row data
				if(leader_header_dynamicvalue == 0 && reportDO.getcIsLeaderShip() == 'Y')
				{
					leader_count = 1;
					if(reportDO.getsDomains().substring(0, 1).toUpperCase().equals("E"))
					{
						header.createCell(header_satrt_value++).setCellValue(text_val_leader+" E"+(equity_count_leader++));
						dynamic_row.createCell(dynamic_cell_count++).setCellValue(isNullOrEmpty(reportDO.getsQuestions()));
					}
				}
			}
			//inclusion
			for(TransSurveyQuestionsDO reportDO : fetch_report_list)
			{
				//create the row data
				if(leader_header_dynamicvalue == 0 && reportDO.getcIsLeaderShip() == 'Y')
				{
					leader_count = 1;
					if(reportDO.getsDomains().substring(0, 1).toUpperCase().equals("I"))
					{
						header.createCell(header_satrt_value++).setCellValue(text_val_leader+" I"+(inclusion_count_leader++));
						dynamic_row.createCell(dynamic_cell_count++).setCellValue(isNullOrEmpty(reportDO.getsQuestions()));
					}
				}
			}
			if(leader_count >= 1)
			{
				leader_header_dynamicvalue++;
				break;
			}
        }
		int header_demo_reportvalue_common = 0;
		// common value
		first:
		for (Map.Entry<Integer,List<TransSurveyQuestionsDO>> entry : total_dump_report.entrySet()) 
		{
			HSSFRow dynamic_row_value = sheet.createRow(rowNum++);
			int dynamic_cell_ans_count = 3;
			header_demo_reportvalue_common = 0;
			dynamic_row_value.createCell(0).setCellValue(display_sno++);
			List<TransSurveyQuestionsDO> fetch_report_list = entry.getValue();

			second:
			for(TransSurveyQuestionsDO surveyQuestions_val : fetch_report_list)
			{
				dynamic_row_value.createCell(1).setCellValue((dateFormat.format(surveyQuestions_val.getdCreatedDate())));
				dynamic_row_value.createCell(2).setCellValue(isNullOrEmpty(surveyQuestions_val.getsEmailId()));
				
				String[] split_demo_val = surveyQuestions_val.getsDemoGraphicsValues().split("#");

				System.out.println("System.out.println(header_demo_reportvalue_common); : " + header_demo_reportvalue);
				
				for(int i=0;i<split_demo_val.length;i++)
				{
					if(i < 5)
					{
						String[] split_demo = split_demo_val[i].split("\\|");
						if(header_demo_reportvalue_common == 0)
						{
							if(isNullOrEmpty(split_demo[2]).equals("Y"))
							{
								dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue("Yes");
							} else
							{
								dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue("No");
							}
							dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(isNullOrEmpty(split_demo[1]));
						} else
						{
							dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(isNullOrEmpty(split_demo[1]));							
						}
						header_demo_reportvalue_common++;
					}
				}
				break second;
			}
			
			//Diversity
			for(TransSurveyQuestionsDO reportDO : fetch_report_list)
			{
				//create the row data
				if(reportDO.getsDomains().substring(0, 1).toUpperCase().equals("D") && reportDO.getcIsLeaderShip() == 'N')
				{
					if(reportDO.getcIsResponse1() == 'Y')
					{
						dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(reportDO.getsAsnwerMark1());			
					} else
					{
						dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(reportDO.getsAsnwerMark2());
					}	
				}
			}
			//equity
			for(TransSurveyQuestionsDO reportDO : fetch_report_list)
			{
				//create the row data
				if(reportDO.getsDomains().substring(0, 1).toUpperCase().equals("E") && reportDO.getcIsLeaderShip() == 'N')
				{
					if(reportDO.getcIsResponse1() == 'Y')
					{
						dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(reportDO.getsAsnwerMark1());			
					} else
					{
						dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(reportDO.getsAsnwerMark2());
					}	
				}
			}
			//Inclusion
			for(TransSurveyQuestionsDO reportDO : fetch_report_list)
			{
				//create the row data
				if(reportDO.getsDomains().substring(0, 1).toUpperCase().equals("I") && reportDO.getcIsLeaderShip() == 'N')
				{
					if(reportDO.getcIsResponse1() == 'Y')
					{
						dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(reportDO.getsAsnwerMark1());
					} else
					{
						dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(reportDO.getsAsnwerMark2());
					}	
				}
			}
			
			//leader Diversity
			for(TransSurveyQuestionsDO reportDO : fetch_report_list)
			{
				//create the row data
				if(reportDO.getsDomains().substring(0, 1).toUpperCase().equals("D") && reportDO.getcIsLeaderShip() == 'Y')
				{
					if(reportDO.getcIsResponse1() == 'Y')
					{
						dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(reportDO.getsAsnwerMark1());			
					} else
					{
						dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(reportDO.getsAsnwerMark2());
					}	
				}
			}
			//leader equity
			for(TransSurveyQuestionsDO reportDO : fetch_report_list)
			{
				//create the row data
				if(reportDO.getsDomains().substring(0, 1).toUpperCase().equals("E") && reportDO.getcIsLeaderShip() == 'Y')
				{
					if(reportDO.getcIsResponse1() == 'Y')
					{
						dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(reportDO.getsAsnwerMark1());			
					} else
					{
						dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(reportDO.getsAsnwerMark2());
					}	
				}
			}
			//leader Inclusion
			for(TransSurveyQuestionsDO reportDO : fetch_report_list)
			{
				//create the row data
				if(reportDO.getsDomains().substring(0, 1).toUpperCase().equals("I") && reportDO.getcIsLeaderShip() == 'Y')
				{
					if(reportDO.getcIsResponse1() == 'Y')
					{
						dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(reportDO.getsAsnwerMark1());
					} else
					{
						dynamic_row_value.createCell(dynamic_cell_ans_count++).setCellValue(reportDO.getsAsnwerMark2());
					}	
				}
			}
        }		
	}
	
	public String isNullOrEmpty(Object str) 
	{
        if(str == null)
        {
        	str = "";
        }
    	return str.toString();
    }
}