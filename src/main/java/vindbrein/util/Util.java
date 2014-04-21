package vindbrein.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;

public class Util {
		
   
	//Implementación para lanzado de mensajes en entorno JSF, probado para Primefaces
	
	public static void lanzarMensaje(String level, String scope, String message){		
		Severity severityMessage = null;
		String scopeMessage = null;
		String headMessage = null;
		
		if(!level.equals("ERROR") && !level.equals("FATAL")
				&& !level.equals("WARN") && !level.equals("INFO")){
			throw new IllegalArgumentException(level+" no es un parámetro válido para la Severidad del Mensaje");
		}else {
			if(!scope.equals("LOCAL") && !scope.equals("GLOBAL")){
				throw new IllegalArgumentException(scope+" no es un parámetro válido para el Alcance del Mensaje");
			}else {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				
				//definiendo el nivel de severidad en base al parametro enviado
				if(level.equals("ERROR")){
					severityMessage = FacesMessage.SEVERITY_ERROR;
					headMessage = "Error";
				}else if (level.equals("FATAL")) {
					severityMessage = FacesMessage.SEVERITY_FATAL;
					headMessage = "Error fatal";
				}else if (level.equals("WARN")) {
					severityMessage = FacesMessage.SEVERITY_WARN;
					headMessage = "Advertencia";
				}else if (level.equals("INFO")) {
					severityMessage = FacesMessage.SEVERITY_INFO;
					headMessage = "Información";
				}
				
				//definiendo el alcance del mensaje
				if(scope.equals("LOCAL")){
					scopeMessage = "local";
				}else{
					scopeMessage = null; //dado que growl con globalTrue se mostrará no tiene clientId
				}				
				
				facesContext.addMessage(scopeMessage,
						new FacesMessage(severityMessage, headMessage, message));			
			}
		}		
	}
	
	//Lanza una variable javascript para su manejo en el lado del navegador	
	public static void lanzarVariableAInterfaz(String variable, Object value){
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		requestContext.addCallbackParam(variable, value);
	}
	
	
	public static String obtenerMontoConFormato(BigDecimal monto){	
		 
		 DecimalFormat df = new DecimalFormat("#,###,##0.00",new DecimalFormatSymbols(new Locale("en", "US")));
		 
		 return df.format(monto);		 
	}
	
	public static Double obtenerMontoSinFormato(String montoTexto) throws ParseException{
		//posicion inicial desde donde se inicia el parseo
		ParsePosition pp = new ParsePosition(0);
		
		DecimalFormat df = new DecimalFormat("#,###,##0.00",new DecimalFormatSymbols(new Locale("en", "US")));	  
		Double valorDouble = df.parse(montoTexto, pp).doubleValue();
				
		//comparación de longitudes pues el parser considera subcadenas como números. Ejem. 123abc como numero 123
		if(montoTexto.length()-Double.toString(valorDouble).length() > 3){
			throw new ParseException("No es un número",Double.toString(valorDouble).length());
		}
		
		return valorDouble;
	}
	
	public static Date convertStringToDate(String source) throws ParseException{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		return formato.parse(source);
	}
	
	public static String obtenerFechaTexto(Date date){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		return formato.format(date);
	}
	
	public static String obtenerMesAnioTexto(Date date){
		SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");

		return formato.format(date);
	}
	
	public static String obtenerAnioTexto(Date date){
		SimpleDateFormat formato = new SimpleDateFormat("yyyy");

		return formato.format(date);
	}
	
	//Los métodos de hora requiere de la librería Joda-time
	public static Date minimizarHora(Date date){		
		DateTime datetime = new DateTime(date);
		datetime = datetime.secondOfDay().withMinimumValue();		
		
		return datetime.toDate();
	}
	
	public static Date maximizarHora(Date date){
		DateTime datetime = new DateTime(date);
		datetime = datetime.secondOfDay().withMaximumValue();
		
		return datetime.toDate();
	}
	
	public static Date minimizarDia(Date date){
		DateTime dateTime = new DateTime(date);
		dateTime = dateTime.dayOfMonth().withMinimumValue();
		dateTime = dateTime.secondOfDay().withMinimumValue();
		
		return dateTime.toDate();
	}
	
	public static Date maximizarDia(Date date){
		DateTime dateTime = new DateTime(date);
		dateTime = dateTime.dayOfMonth().withMaximumValue();
		dateTime = dateTime.secondOfDay().withMaximumValue();
		
		return dateTime.toDate();
	}
	
	public static Date minimizarMes(Date date){		
		DateTime dateTime = new DateTime(date);
		dateTime = dateTime.monthOfYear().withMinimumValue();
		dateTime = dateTime.dayOfMonth().withMinimumValue();
		dateTime = dateTime.secondOfDay().withMinimumValue();
		
		return dateTime.toDate();		
	}
	
	public static Date maximizarMes(Date date){
		DateTime dateTime = new DateTime(date);
		dateTime = dateTime.monthOfYear().withMaximumValue();
		dateTime = dateTime.dayOfMonth().withMaximumValue();
		dateTime = dateTime.secondOfDay().withMaximumValue();
		
		return dateTime.toDate();	
	}
	
	/////////////////////////////////Para JSF primefaces (requiere de librería Apache POI)
	private static HSSFCellStyle obtenerEstiloCeldaGeneral(HSSFWorkbook hssfWorkbook){		
		HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
		
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		return cellStyle;
	}
	
	private static HSSFCellStyle obtenerEstiloCeldaCabecera(HSSFWorkbook hssfWorkbook){
		
		HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
		
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		HSSFFont font = hssfWorkbook.createFont();
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    
	    cellStyle.setFont(font);
	    	
		return cellStyle;
	}
	
	public static void formatearTablaExportadaXLS(HSSFSheet sheet) {
		HSSFWorkbook workbook = sheet.getWorkbook();

		HSSFRow header = sheet.getRow(0);

		int numberRows = sheet.getLastRowNum();
		int numberColumns = header.getLastCellNum();
		
		//obteniendo los estilos definidos para las celdas
		HSSFCellStyle estiloCeldaGeneral = Util.obtenerEstiloCeldaGeneral(workbook);
		HSSFCellStyle estiloCeldaCabecera = Util.obtenerEstiloCeldaCabecera(workbook);
		
		//definiendo estilos a la tabla
		for (int i = 0; i <= numberRows; i++) {
			for (int j = 0; j < numberColumns; j++) {
				sheet.getRow(i).getCell(j).setCellStyle(estiloCeldaGeneral);
			}
		}	
		
		//definiendo estilos a la cabecera y ancho de las columnas
		for (int i = 0; i < numberColumns; i++) {
			//estilo a la cabecera
			header.getCell(i).setCellStyle(estiloCeldaCabecera);
			
			//definiendo ancho de columnas (entre la cabecera y la primera fila de datos)
			sheet.setColumnWidth(i,
					(header.getCell(i).getStringCellValue().length() >
			         sheet.getRow(1).getCell(i).getStringCellValue().length() ? 
			        		header.getCell(i).getStringCellValue().length() :
			        	    sheet.getRow(1).getCell(i).getStringCellValue().length()
			         ) * 400 );
		}
		
		//formateando datos
		for (int i = 0; i <= numberRows; i++) {
			for (int j = 0; j < numberColumns; j++) {
				try {
					double valorNumero = obtenerMontoSinFormato(sheet.getRow(i).getCell(j).getStringCellValue());
					sheet.getRow(i).getCell(j).setCellValue(valorNumero);
				} catch (Exception e) {
					//no se hace nada
				}
				
				try {
					Date valorDate = convertStringToDate(sheet.getRow(i).getCell(j).getStringCellValue());
					
					CreationHelper createHelper = workbook.getCreationHelper();
					HSSFCellStyle cellStyle = workbook.createCellStyle();
					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
					
					sheet.getRow(i).getCell(j).setCellStyle(cellStyle);
					sheet.getRow(i).getCell(j).setCellValue(valorDate);
				} catch (Exception e) {
					//no se hace nada
				}
			}
		}
	}
}
