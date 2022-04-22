package convertion;

import java.io.FileInputStream;
import java.io.File;

import com.northconcepts.datapipeline.core.DataReader;
import com.northconcepts.datapipeline.core.DataWriter;
import com.northconcepts.datapipeline.core.FieldType;
import com.northconcepts.datapipeline.excel.ExcelDocument;
import com.northconcepts.datapipeline.excel.ExcelWriter;
import com.northconcepts.datapipeline.job.JobTemplate;
import com.northconcepts.datapipeline.transform.BasicFieldTransformer;
import com.northconcepts.datapipeline.transform.MoveFieldBefore;
import com.northconcepts.datapipeline.transform.RemoveFields;
import com.northconcepts.datapipeline.transform.SetCalculatedField;
import com.northconcepts.datapipeline.transform.TransformingReader;
import com.northconcepts.datapipeline.xml.XmlReader;


public class demoTest {

	public static void main(String[] args) {
	
			/*
			 * 1) Define a reader from an XML file
			 */
		
		System.out.println("Working Directory = " + System.getProperty("user.dir")+"‪\\xtest.xml");
		
			DataReader xmlReader = new XmlReader(new File(System.getProperty("user.dir")+"‪\\xtest.xml"))
						.addField("Student Id", "//student/id")
						.addField("FirstName", "//student/firstname")
						.addField("LastName", "//student/lastname")
						.addField("Subject", "//student/subject")
						.addField("Marks", "//student/marks")
						.addRecordBreak("//student");

			/*
			 * 2) Transform the input data on-the-fly (optional)
			 */
//			DataReader transformingReader = new TransformingReader(xmlReader)
//						.add(
//
//							new BasicFieldTransformer("EBITDA")
//								.replaceAll("M", "E6")
//								.replaceAll("B", "E9")
//								.stringToDouble()
//								.numberToInt(),
//
//							new BasicFieldTransformer("last trade date")
//								.stringToDate("M/d/y"),
//							
//							new BasicFieldTransformer("bid")
//								.stringToDouble(),
//							
//							new BasicFieldTransformer("ask")
//								.stringToDouble(),
//							
//							new SetCalculatedField("bid-ask spread", "bid-ask"),
//							
//							new RemoveFields("ask"),
//							
//							new MoveFieldBefore("bid-ask spread", "day range"),
//							
//							new BasicFieldTransformer("day range")
//								.replaceAll(" - ", ", ")
//								.prepend("[")
//								.append("]")
//			);

			/*
			 * 3) Define a writer to an Excel file
			 */
			ExcelDocument excelDocument = new ExcelDocument(); //default is .xlsx format (Excel 2007+)
			DataWriter excelWriter = new ExcelWriter(excelDocument)
							.setSheetName("TestData")
							.setAutofitColumns(true)
							.setStyleFormat(FieldType.INT, "0.00E+00")
							.setStyleFormat(FieldType.DOUBLE, "0.00")
							.setStyleFormat(FieldType.DATE, "yyyy-mmm-dd, ddd");
			

			/*
			 * 4) Transfer the data from the XML file to the Excel file and save the result
			 */
			JobTemplate.DEFAULT.transfer(xmlReader, excelWriter);
			excelDocument.save(new File("‪E:\\toolsqa\\testsuites\\FileDetails.xlsx"));
		}
}
