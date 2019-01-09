package com.wisdomelon.base.utils.file;

import com.wisdomelon.base.utils.datetime.DateUtils;
import com.wisdomelon.base.utils.file.entity.FileDisposeException;
import com.wisdomelon.base.utils.file.entity.FileExceptionParam;
import com.wisdomelon.base.utils.file.entity.FileInfo;
import com.wisdomelon.base.utils.file.entity.FileParam;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.Boolean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/***
 * 提供操作文件的上传 下载 预览功能
 * 提供表格的excel导出
 * 提供excel导入功能
 * 提供office（word excel ppt）转pdf功能
 * @author P_Xyan
 */
public class FileUtils {
	
	/** 文件流读取缓存大小*/
	private static final int BUFFER = 1024;
	
	/***
	 * 上传单个文件
	 * @param file：被上传的文件，可能是临时文件
	 * @param uploadFileName：被上传的文件真实名称
	 * @param uploadDir：服务器存放文件的根目录
	 * @param useNewFileName：是否使用自定义文件名称，如果为true，则会取出uploadFileName的文件后缀，如果为false，则会直接取uploadFileName
	 * @return
	 * @throws FileDisposeException
	 */
	public static FileInfo uploadFile(File file, String uploadFileName, String uploadDir, boolean useNewFileName) throws FileDisposeException {
		return upload(file, uploadFileName, uploadDir, "", useNewFileName);
	}
	
	/***
	 * 上传单个文件
	 * @param file：被上传的文件，可能是临时文件
	 * @param uploadFileName：被上传的文件真实名称
	 * @param uploadDir：服务器存放文件的根目录
	 * @param filePath：自定义文件保存路径
	 * @return
	 * @throws FileDisposeException
	 */
	public static FileInfo uploadFile(File file, String uploadFileName, String uploadDir, String filePath) throws FileDisposeException{
		return upload(file, uploadFileName, uploadDir, filePath, false);
	}
	
	/***
	 * 处理上传文件路径后上传文件
	 * @param file：被上传的文件，可能是临时文件
	 * @param uploadFileName：被上传的文件真实名称
	 * @param uploadDir：服务器存放文件的根目录
	 * @param filePath：自定义文件保存路径
	 * @param useNewFileName：是否使用自定义文件名称，如果为true，则会取出uploadFileName的文件后缀，如果为false，则会直接取uploadFileName
	 * @return
	 * @throws FileDisposeException 
	 */
	private static FileInfo upload(File file, String uploadFileName, String uploadDir, String filePath, boolean useNewFileName) throws FileDisposeException{
		/** 服务器存放文件的全部路径： rootDir + '/' + fileName*/
		String allFilePath = "";
		/** 服务器存放文件的全目录： uploadDir + saveDir*/
		String rootDir = "";
		/** 服务器存放文件的uploadDir目录后的 自动生成目录或用户自定义目录*/
		String saveDir = "";
		/** 上传文件的加密处理后的新文件名称或 为uploadFileName或 为用户自定义名称（均带后缀）*/
		String fileName = "";
		/** 上传文件的上传时间*/
		String fileUploadTime = "";
		/** 上传文件的大小*/
		double fileSize = 0;
		
		String fileSuffix = uploadFileName.substring(uploadFileName.lastIndexOf(FileParam.dot)+1, uploadFileName.length());
		if(!compareToSuffix(fileSuffix, FileParam.allowSuffix)){
			throw new FileDisposeException(FileExceptionParam.NOT_ALLOW_SUFFIX, FileExceptionParam.NOT_ALLOW_SUFFIX_MSG);
		}
		try {
			if(!"".equals(filePath) && filePath != null){
				if(FileParam.sprit.equals(filePath.substring(0, 1)))
					saveDir = filePath.substring(0, filePath.lastIndexOf(FileParam.sprit));
				else
					saveDir = FileParam.sprit + filePath.substring(0, filePath.lastIndexOf(FileParam.sprit));
				fileName = filePath.substring(filePath.lastIndexOf(FileParam.sprit)+1, filePath.length());
			}else{
				saveDir = FileParam.sprit + DateUtils.getFormatStr(new Date(), "yyyyMMdd");
				if(useNewFileName)
					fileName = System.currentTimeMillis() + Math.round(Math.random() * 8999 + 1000) + FileParam.dot + fileSuffix;
				else
					fileName = uploadFileName;
				filePath = saveDir + FileParam.sprit +fileName;
			}
			rootDir = uploadDir + saveDir;
			File rootDirs = new File(rootDir);
			if(!rootDirs.exists())
				rootDirs.mkdirs();
			allFilePath = rootDir + FileParam.sprit + fileName;
			File copyFile = copyFile(file, new File(allFilePath));
			fileUploadTime = DateUtils.getDateTime24Str(new Date());
			fileSize += copyFile.length()/1024f;
		} catch (FileDisposeException e) {
			throw new FileDisposeException(FileExceptionParam.FILE_UPLOAD_FAILED, e.getMessage());
		}
		return new FileInfo(uploadFileName, uploadDir, filePath, saveDir, rootDir, allFilePath, fileName, fileSuffix, fileUploadTime, fileSize);
	}

	/***
	 * 文件流上传文件
	 * @param uploadFile：待上传的文件
	 * @param newFile：上传后的服务器新文件
	 * @throws FileDisposeException 
	 */
	private static File copyFile(File uploadFile, File newFile) throws FileDisposeException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(uploadFile));
			bos = new BufferedOutputStream(new FileOutputStream(newFile));
			byte[] bytes = new byte[BUFFER];
			int len = 0;
			while((len = bis.read(bytes, 0, BUFFER)) != -1){
				bos.write(bytes, 0, len);
			}
		} catch (Exception e) {
			throw new FileDisposeException(FileExceptionParam.FILE_UPLOAD_FAILED, FileExceptionParam.FILE_UPLOAD_FAILED_MSG);
		} finally {
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bos = null;
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bis = null;
		}
		return newFile;
	}

	/***
	 * 比较文件后缀名的方法
	 * @param fileSuffix：文件后缀
	 * @param allowsuffix：允许上传的文件后缀列表
	 * @return
	 */
	private static boolean compareToSuffix(String fileSuffix, String[] allowsuffix) {
		boolean b = false;
		for (int i = 0; i < allowsuffix.length; i++) {
			if (fileSuffix.equals(allowsuffix[i])) {
				b = true;
				break;
			}
		}
		return b;
	}
	
	/***
	 * 上传多个文件
	 * @param files：被上传的文件列表，可能是临时文件列表
	 * @param uploadFileNames：被上传的文件真实名称列表
	 * @param uploadDir：服务器存放文件的根目录
	 * @param useNewFileName：是否使用自定义文件名称？true，则会取出uploadFileNames的每个文件后缀，false，则会直接取uploadFileNames的文件
	 * @return
	 * @throws FileDisposeException
	 */
	public static List<FileInfo> uploadFiles(List<File> files, List<String> uploadFileNames, String uploadDir, boolean useNewFileName) throws FileDisposeException{
		List<FileInfo> list = new ArrayList<FileInfo>();
		for (int i=0; i<uploadFileNames.size(); i++) {
			list.add(upload(files.get(i), uploadFileNames.get(i), uploadDir, "", useNewFileName));
		}
		return list;
	}
	
	/***
	 * 上传多个文件，并打包为zip
	 * @param files：被上传的文件列表，可能是临时文件列表
	 * @param uploadFileNames：被上传的文件真实名称列表
	 * @param zipFileName：打包成压缩文件的压缩文件名称
	 * @param zipDir：服务器存放文件的根目录
	 * @param deleteNotZipFile：是否删除非压缩文件？true，则压缩后会将上传的文件删除，只保留压缩文件，false，则不删除文件
	 * @return
	 * @throws FileDisposeException
	 */
	public static FileInfo uploadFilesToZip(List<File> files, List<String> uploadFileNames, String zipFileName, String zipDir, boolean deleteNotZipFile) throws FileDisposeException{
		List<String> filePathList = new ArrayList<String>();
		for (int i=0; i<uploadFileNames.size(); i++) {
			FileInfo fileInfo = upload(files.get(i), uploadFileNames.get(i), zipDir, "", false);
			filePathList.add(fileInfo.getAllFilePath());
		}
		return uploadToZip(zipFileName, zipDir, filePathList, "", deleteNotZipFile);
	}
	
	/***
	 * 上传多个文件，并打包为zip
	 * @param files：被上传的文件列表，可能是临时文件列表
	 * @param uploadFileNames：被上传的文件真实名称列表
	 * @param zipFileName：打包成压缩文件的压缩文件名称
	 * @param zipDir：服务器存放文件的根目录
	 * @param filePath：自定义文件保存路径
	 * @param deleteNotZipFile：是否删除非压缩文件？true，则压缩后会将上传的文件删除，只保留压缩文件，false，则不删除文件
	 * @return
	 * @throws FileDisposeException
	 */
	public static FileInfo uploadFilesToZip(List<File> files, List<String> uploadFileNames, String zipFileName, String zipDir, String filePath,  boolean deleteNotZipFile) throws FileDisposeException{
		List<String> filePathList = new ArrayList<String>();
		for (int i=0; i<uploadFileNames.size(); i++) {
			FileInfo fileInfo = upload(files.get(i), uploadFileNames.get(i), zipDir, "", false);
			filePathList.add(fileInfo.getAllFilePath());
		}
		return uploadToZip(zipFileName, zipDir, filePathList, filePath, deleteNotZipFile);
	}
	
	/***
	 * 处理压缩文件的路径后压缩文件
	 * @param zipFileName：打包成压缩文件的压缩文件名称
	 * @param zipDir：服务器存放文件的根目录
	 * @param filePathList：上传文件的文件路径列表
	 * @param filePath：自定义文件保存路径
	 * @param deleteNotZipFile
	 * @return
	 * @throws FileDisposeException 
	 */
	private static FileInfo uploadToZip(String zipFileName, String zipDir, List<String> filePathList, String filePath, boolean deleteNotZipFile) throws FileDisposeException {
		/** 服务器存放文件的全部路径： rootDir + '/' + fileName*/
		String allFilePath = "";
		/** 服务器存放文件的全目录： uploadDir + saveDir*/
		String rootDir = "";
		/** 服务器存放文件的uploadDir目录后的 自动生成目录或用户自定义目录*/
		String saveDir = "";
		/** 上传文件的加密处理后的新文件名称或 为uploadFileName或 为用户自定义名称（均带后缀）*/
		String fileName = "";
		/** 上传文件的上传时间*/
		String fileUploadTime = "";
		/** 上传文件的大小*/
		double fileSize = 0;
		
		String fileSuffix = zipFileName.substring(zipFileName.lastIndexOf(FileParam.sprit)+1, zipFileName.length());
		try {
			if(!"".equals(filePath) && filePath != null){
				if(FileParam.sprit.equals(filePath.substring(0, 1)))
					saveDir = filePath.substring(0, filePath.lastIndexOf(FileParam.sprit));
				else
					saveDir = FileParam.sprit + filePath.substring(0, filePath.lastIndexOf(FileParam.sprit));
				fileName = filePath.substring(filePath.lastIndexOf(FileParam.sprit)+1, filePath.length());
			}else{
				saveDir = FileParam.sprit + DateUtils.getFormatStr(new Date(), "yyyyMMdd");
				fileName = System.currentTimeMillis() + Math.round(Math.random() * 8999 + 1000) + FileParam.dot + fileSuffix;
				filePath = saveDir + FileParam.sprit +fileName;
			}
			rootDir = zipDir + saveDir;
			File rootDirs = new File(rootDir);
			if(!rootDirs.exists())
				rootDirs.mkdirs();
			allFilePath = rootDir + FileParam.sprit + fileName;
			File file = zipFile(filePathList, new File(allFilePath), deleteNotZipFile);
			fileUploadTime = DateUtils.getDateTime24Str(new Date());
			fileSize += file.length()/1024f;
		} catch (FileDisposeException e) {
			throw new FileDisposeException(FileExceptionParam.FILE_COMPRESS_FAILED, e.getMessage());
		}
		return new FileInfo(zipFileName, zipDir, filePath, saveDir, rootDir, allFilePath, fileName, fileSuffix, fileUploadTime, fileSize);
	}

	/***
	 * 通过文件流压缩文件
	 * @param filePathList：被压缩的文件路径列表
	 * @param newFile：压缩文件
	 * @param deleteNotZipFile：是否删除非压缩文件外其他文件
	 * @return
	 * @throws FileDisposeException
	 */
	private static File zipFile(List<String> filePathList, File newFile, boolean deleteNotZipFile) throws FileDisposeException {
		ZipOutputStream zos = null;
		BufferedInputStream bis = null;
		try {
			zos = new ZipOutputStream(newFile);
			ZipEntry entry = null;
			byte[] bytes = new byte[BUFFER];
			int len = 0;
			for (int i = 0; i < filePathList.size(); i++) {
				File file = new File(String.valueOf(filePathList.get(i)));
				entry = new ZipEntry(file.getName());
				entry.setSize(file.length());
				entry.setTime(file.lastModified());
				zos.putNextEntry(entry);
				bis = new BufferedInputStream(new FileInputStream(file));
				while((len = bis.read(bytes, 0, BUFFER)) != -1){
					zos.write(bytes, 0, len);
				}
				bis.close();
			}
			zos.setEncoding(FileParam.GBK);
			if(deleteNotZipFile)
				deleteFiles(filePathList);
		} catch (Exception e) {
			throw new FileDisposeException(FileExceptionParam.FILE_COMPRESS_FAILED, FileExceptionParam.FILE_COMPRESS_FAILED_MSG);
		} finally {
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bis = null;
			if(zos != null){
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			zos = null;
		}
		return newFile;
	}
	
	/***
	 * 删除文件
	 * @param filePath
	 */
	public static void deleteFiles(String filePath) {
		File file = new File(String.valueOf(filePath));
		if(file.exists())
			file.delete();
	}

	/***
	 * 按文件列表删除文件
	 * @param filePathList
	 */
	public static void deleteFiles(List<String> filePathList) {
		for (String filePath : filePathList) {
			deleteFiles(filePath);
		}
	}

	/***
	 * 下载文件
	 * @param downloadFileName：被下载的文件名称
	 * @param downloadFilePath：被下载的文件路径
	 * @param out
	 * @param response
	 * @throws FileDisposeException
	 */
	public static void downloadFile(String downloadFileName, String downloadFilePath, PrintWriter out, HttpServletResponse response) throws FileDisposeException{
		download(downloadFileName, downloadFilePath, out, response);
	}
	
	/***
	 * 下载文件
	 * @param downloadFileName：被下载的文件名称
	 * @param downloadFilePath：被下载的文件路径
	 * @param response
	 * @throws FileDisposeException
	 * @throws IOException 
	 */
	public static void downloadFile(String downloadFileName, String downloadFilePath, HttpServletResponse response) throws FileDisposeException, IOException{
		download(downloadFileName, downloadFilePath, response.getWriter(), response);
	}
	
	
	/***
	 * 将文件输出流以附件的形式输出
	 * @param downloadFileName：被下载的文件名称
	 * @param downloadFilePath：被下载的文件路径
	 * @param out
	 * @param response
	 * @throws FileDisposeException 
	 */
	private static void download(String downloadFileName, String downloadFilePath, PrintWriter out, HttpServletResponse response) throws FileDisposeException{
		FileInputStream fis = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(downloadFileName.getBytes(FileParam.GBK), FileParam.ISO8859_1));
			
			fis = new FileInputStream(new File(downloadFilePath));
			byte[] bytes = new byte[BUFFER];
			int len = 0;
			while((len = fis.read(bytes, 0, BUFFER)) != -1){
				out.write(new String(bytes, FileParam.ISO8859_1), 0, len);
			}
		} catch (Exception e) {
			throw new FileDisposeException(FileExceptionParam.FILE_DOWNNLOAD_FAILED, FileExceptionParam.FILE_DOWNLOAD_FAILED_MSG);
		} finally {
			if(out != null){
				out.close();
			}
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fis = null;
		}
	}
	
	/***
	 * 预览文件
	 * @param previewFileName：被预览的文件名称
	 * @param previewFilePath：被预览的文件路径
	 * @param out
	 * @param response
	 * @throws FileDisposeException
	 */
	public static void previewFile(String previewFileName, String previewFilePath, BufferedOutputStream out, HttpServletResponse response) throws FileDisposeException{
		preview(previewFileName, previewFilePath, out, response);
	}
	
	/***
	 * 预览文件
	 * @param previewFileName：被预览的文件名称
	 * @param previewFilePath：被预览的文件路径
	 * @param response
	 * @throws FileDisposeException
	 * @throws IOException 
	 */
	public static void previewFile(String previewFileName, String previewFilePath, HttpServletResponse response) throws FileDisposeException, IOException{
		preview(previewFileName, previewFilePath, new BufferedOutputStream(response.getOutputStream()), response);
	}
	
	/***
	 * 将文件以流的形式输出在网页中
	 * @param previewFileName：被预览的文件名称
	 * @param previewFilePath：被预览的文件路径
	 * @param out
	 * @param response
	 * @throws FileDisposeException 
	 */
	private static void preview(String previewFileName, String previewFilePath, BufferedOutputStream out, HttpServletResponse response) throws FileDisposeException{
		BufferedInputStream bis = null;
		try {
			chooseResponseHead(previewFileName, response);
			response.addHeader("Content-Disposition", "filename=" + new String(previewFileName.getBytes(FileParam.GBK), FileParam.ISO8859_1));
		
			bis = new BufferedInputStream(new FileInputStream(new File(previewFilePath)));
			byte[] bytes = new byte[BUFFER];
			int len = 0;
			while((len = bis.read(bytes, 0, BUFFER)) != -1){
				out.write(bytes, 0, len);
			}
		} catch (Exception e) {
			throw new FileDisposeException(FileExceptionParam.FILE_PREVIEW_FAILED, FileExceptionParam.FILE_PREVIEW_FAILED_MSG);
		} finally {
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			out = null;
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bis = null;
		}
	}

	/***
	 * 根据文件后缀选择输出流的头
	 * @param previewFileName
	 * @param response
	 */
	private static void chooseResponseHead(String previewFileName, HttpServletResponse response) {
		String suffix = previewFileName.substring(previewFileName.lastIndexOf(FileParam.sprit), previewFileName.length());
		
		if(".doc".equals(suffix) || ".DOC".equals(suffix)){
			response.setHeader("Content-type", "application/msword");
		}else if(".docx".equals(suffix) || ".DOCX".equals(suffix)){
			response.setHeader("Content-type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		}else if(".pdf".equals(suffix) || ".PDF".equals(suffix)){
			response.setHeader("Content-type", "application/pdf");
		}else if(".txt".equals(suffix) || ".TXT".equals(suffix)){
			response.setHeader("Content-type", "application/html");
		}else if(".sql".equals(suffix) || ".SQL".equals(suffix)){
			response.setHeader("Content-type", "application/html");
		}else if(".xls".equals(suffix) || ".XLS".equals(suffix)){
			response.setHeader("Content-type", "application/vnd.ms-excel");
		}else if(".xlsx".equals(suffix) || ".XLSX".equals(suffix)){
			response.setHeader("Content-type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		}else if(".ppt".equals(suffix) || ".PPT".equals(suffix)){
			response.setHeader("Content-type", "application/vnd.ms-powerpoint");
		}else if(".pptx".equals(suffix) || ".PPTX".equals(suffix)){
			response.setHeader("Content-type", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
		}else if(".bmp".equals(suffix) || ".BMP".equals(suffix)){
			response.setHeader("Content-type", "application/bmp");
		}else if(".gif".equals(suffix) || ".GIF".equals(suffix)){
			response.setHeader("Content-type", "application/gif");
		}else if(".ief".equals(suffix) || ".IEF".equals(suffix)){
			response.setHeader("Content-type", "application/ief");
		}else if(".jpg".equals(suffix) || ".JPG".equals(suffix)){
			response.setHeader("Content-type", "application/jpeg");
		}else if(".jpeg".equals(suffix) || ".JPEG".equals(suffix)){
			response.setHeader("Content-type", "application/jpeg");
		}else if(".png".equals(suffix) || ".PNG".equals(suffix)){
			response.setHeader("Content-type", "application/png");
		}else if(".tif".equals(suffix) || ".TIF".equals(suffix)){
			response.setHeader("Content-type", "application/tif");
		}else if(".tief".equals(suffix) || ".TIEF".equals(suffix)){
			response.setHeader("Content-type", "application/tiff");
		}
	}
	
	/***
	 * 将数据导出为excel（单页）
	 * @param exportFileName：导出文件的文件名称
	 * @param head：excel中的表头
	 * @param content：excel中的内容
	 * @param response
	 * @throws FileDisposeException 
	 */
	public static <H, T> void exportExcel(String exportFileName, H head, List<T> content, HttpServletResponse response) throws FileDisposeException {
		String sheetTitle = DateUtils.getDateTime24Str(new Date());
		Map<String, H> mapHead = new HashMap<String, H>();
		mapHead.put(sheetTitle, head);
		
		Map<String, List<T>> mapContent = new HashMap<String, List<T>>();
		mapContent.put(sheetTitle, content);
		
		export(exportFileName, new String[]{sheetTitle}, mapHead, mapContent, response);
		
	}
	
	/***
	 * 将数据导出为excel（单页）
	 * @param exportFileName：导出文件的文件名称
	 * @param sheetTitle：excel中的页名称
	 * @param head：excel中的表头
	 * @param content：excel中的内容
	 * @param exportFileName
	 * @param response
	 * @throws FileDisposeException
	 */
	public static <H, T> void exportExcel(String exportFileName, String sheetTitle, H head, List<T> content, HttpServletResponse response) throws FileDisposeException{
		Map<String, H> mapHead = new HashMap<String, H>();
		mapHead.put(sheetTitle, head);
		
		Map<String, List<T>> mapContent = new HashMap<String, List<T>>();
		mapContent.put(sheetTitle, content);
		
		export(exportFileName, new String[]{sheetTitle}, mapHead, mapContent, response);
	}
	
	/***
	 * 将数据导出为excel（多页）
	 * @param exportFileName：导出文件的文件名称
	 * @param sheetTitle：excel中的页名称列表
	 * @param head：excel中的表头集合
	 * @param content：excel中的内容集合
	 * @param response
	 * @throws FileDisposeException
	 */
	public static <H, T> void exportExcel(String exportFileName, String[] sheetTitle, Map<String, H> head,  Map<String, List<T>> content, HttpServletResponse response) throws FileDisposeException{
		export(exportFileName, sheetTitle, head, content, response);
	}
	
	/***
	 * 将数据导出为excel，处理方法
	 * @param exportFileName：导出文件的文件名称
	 * @param sheetTitle：excel中的页名称列表
	 * @param head：excel中的表头集合
	 * @param content：excel中的内容集合
	 * @param response
	 * @throws FileDisposeException
	 */
	private static <H, T> void export(String exportFileName, String[] sheetTitle, Map<String, H> head,  Map<String, List<T>> content, HttpServletResponse response) throws FileDisposeException{
		if("".equals(exportFileName) || exportFileName == null)
			exportFileName = System.currentTimeMillis() + Math.round(Math.random() * 8999 + 1000) + ".xls";
		else{
			String suffix = exportFileName.substring(exportFileName.lastIndexOf(FileParam.dot)+1, exportFileName.length());
			if(!compareToSuffix(suffix, FileParam.exportSuffix)){
				throw new FileDisposeException(FileExceptionParam.NOT_EXPORT_SUFFIX, FileExceptionParam.NOT_EXPORT_SUFFIX_MSG);
			}
		}
		OutputStream os = null;
		WritableWorkbook workbook = null;
		try {
			response.setContentType("application/msexcel;charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(exportFileName.getBytes(FileParam.GBK), FileParam.ISO8859_1));
			
			os = response.getOutputStream();
			workbook = Workbook.createWorkbook(os);
			
			for (int i = 0; i < sheetTitle.length; i++) {
				String title = sheetTitle[i];
				H mapHead = head.get(title);
				List<T> listContent = content.get(title);
				createExcel(title, i, mapHead, listContent, workbook);
			}
			workbook.write();
		} catch (Exception e) {
			throw new FileDisposeException(FileExceptionParam.FILE_EXPORT_FAILED, e.getMessage());
		} finally {
			if(workbook != null){
				try {
					workbook.close();
				} catch (WriteException | IOException e) {
					e.printStackTrace();
				}
			}
			workbook = null;
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			os = null;
		}
	}
	
	/***
	 * 创建excel内容的方法
	 * @param sheetTitle：excel页名称
	 * @param pageNum：excel的页数
	 * @param head：excel的表头
	 * @param listContent：excel的内容
	 * @param workbook
	 * @throws FileDisposeException
	 */
	@SuppressWarnings("unchecked")
	private static <H, T> void createExcel(String sheetTitle, int pageNum, H head, List<T> listContent, WritableWorkbook workbook) throws FileDisposeException{
		try {
			WritableSheet sheet = workbook.createSheet(sheetTitle, pageNum);
			sheet.getSettings().setDefaultColumnWidth(20);
			
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),12);
			WritableCellFormat headFormat = new WritableCellFormat(font);
			headFormat.setBackground(Colour.YELLOW);
			headFormat.setAlignment(Alignment.CENTRE);
			headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			headFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			WritableCellFormat contentFormat = new WritableCellFormat(font);
			contentFormat.setBackground(Colour.GRAY_25);
			contentFormat.setAlignment(Alignment.CENTRE);
			contentFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			contentFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			String headClassSimpleName = head.getClass().getSimpleName();
			if("String[]".equals(headClassSimpleName)){
				String[] heads = (String[]) head;
				for(int columnNum=0; columnNum<heads.length; columnNum++){
					Label headLabel = new Label(columnNum, 0, heads[columnNum]);
					headLabel.setCellFormat(headFormat);
					sheet.addCell(headLabel);
				}
				
				createExcelContent(listContent, sheet, contentFormat, heads);
			}else if("List".equals(headClassSimpleName) || "ArrayList".equals(headClassSimpleName)){
				List<String> listHead = (List<String>) head;
				String[] headKey = listHead.toArray(new String[]{});
				for(int columnNum=0; columnNum<headKey.length; columnNum++){
					Label headLabel = new Label(columnNum, 0, listHead.get(columnNum));
					headLabel.setCellFormat(headFormat);
					sheet.addCell(headLabel);
				}
				
				createExcelContent(listContent, sheet, contentFormat, headKey);
			}else if("Map".equals(headClassSimpleName) || "HashMap".equals(headClassSimpleName)){
				Map<String, String> mapHead = (Map<String, String>) head;
				String[] headKey = mapHead.keySet().toArray(new String[]{});
				for(int columnNum=0; columnNum<headKey.length; columnNum++){
					Label headLabel = new Label(columnNum, 0, mapHead.get(headKey[columnNum]));
					headLabel.setCellFormat(headFormat);
					sheet.addCell(headLabel);
				}
				
				createExcelContent(listContent, sheet, contentFormat, headKey);
			}else{
				throw new FileDisposeException(FileExceptionParam.FILE_EXPORT_FAILED, FileExceptionParam.FILE_EXPORT_FAILED_MSG);
			}
		} catch (Exception e) {
			throw new FileDisposeException(FileExceptionParam.FILE_EXPORT_FAILED, e.getMessage());
		}
		
	}

	/***
	 * 创建excel表内容的方法
	 * @param listContent：excel的内容
	 * @param sheet：excel的页名称
	 * @param contentFormat：内容样式
	 * @param headKey：excel的头列表
	 * @throws Exception
	 */
	private static <T> void createExcelContent(List<T> listContent, WritableSheet sheet, WritableCellFormat contentFormat, String[] headKey) throws Exception {
		for(int rowNum=1; rowNum<=listContent.size(); rowNum++){
			T t = listContent.get(rowNum-1);
			Class<? extends Object> clazz = t.getClass();
			String contentClassSimpleName = clazz.getSimpleName();
			if("List".equals(contentClassSimpleName) || "ArrayList".equals(contentClassSimpleName)){
				int size = (int) clazz.getMethod("size").invoke(t);
				if(size != headKey.length){
					throw new FileDisposeException(FileExceptionParam.FILE_EXPORT_FAILED, FileExceptionParam.FILE_EXPORT_FAILED_MSG);
				}
				for(int columnNum=0; columnNum<headKey.length; columnNum++){
					String content = (String) clazz.getMethod("get",int.class).invoke(t, columnNum);
					Label contentLabel = new Label(columnNum, rowNum, content);
					contentLabel.setCellFormat(contentFormat);
					sheet.addCell(contentLabel);
				}
			}else{
				Map<String, Method> methods = getDeclaredAssignMethods(clazz,"get");
				Field[] fields = getDeclaredAssignMethodsToFields(clazz, methods);
				for(int columnNum=0; columnNum<headKey.length; columnNum++){
					String fieldNameColumn = headKey[columnNum];
					for(int i=0; i<fields.length; i++){
						String fieldName = fields[i].getName();
						if(fieldNameColumn.equals(fieldName)){
							String firstUpFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
							Method method = methods.get(firstUpFieldName);
							Label contentLabel = new Label(columnNum, rowNum, (String)method.invoke(t));
							contentLabel.setCellFormat(contentFormat);
							sheet.addCell(contentLabel);
						}
					}
				}
			}
		}
	}
	
	/***
	 * 导入excel 单页excel
	 * @param file：导入的文件
	 * @param class1：反射类
	 * @return
	 * @throws FileDisposeException
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> importExcel(File file, Class<?> class1) throws FileDisposeException{
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(class1);
		Map<String, List<Object>> map = importFile(file, list, null);
		String key = map.keySet().iterator().next();
		return (List<T>) map.get(key);
	}
	
	/***
	 * 导入excel 单页excel
	 * @param file：导入的文件
	 * @param class1：反射类
	 *@param reflectFields：反射的单元格对应的字段名
	 * @return
	 * @throws FileDisposeException
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> importExcel(File file, Class<?> class1, String[] reflectFields) throws FileDisposeException{
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(class1);
		List<String[]> listFields = new ArrayList<String[]>();
		listFields.add(reflectFields);
		Map<String, List<Object>> map = importFile(file, list, listFields);
		String key = map.keySet().iterator().next();
		return (List<T>) map.get(key);
	}
	
	/***
	 * 导入excel 单页excel
	 * @param importFilePath：导入的文件路径
	 * @param class1：反射类
	 * @return
	 * @throws FileDisposeException
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> importExcel(String importFilePath, Class<?> class1) throws FileDisposeException{
		File file = new File(importFilePath);
		if(!file.exists())
			throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, FileExceptionParam.FILE_NOT_FOUNT_MSG);
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(class1);
		Map<String, List<Object>> map = importFile(file, list, null);
		String key = map.keySet().iterator().next();
		return (List<T>) map.get(key);
	}
	
	/***
	 * 导入excel 单页excel
	 * @param importFilePath：导入的文件路径
	 * @param class1：反射类
	 * @param reflectFields：反射的单元格对应的字段名
	 * @return
	 * @throws FileDisposeException
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> importExcel(String importFilePath, Class<?> class1, String[] reflectFields) throws FileDisposeException{
		File file = new File(importFilePath);
		if(!file.exists())
			throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, FileExceptionParam.FILE_NOT_FOUNT_MSG);
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(class1);
		List<String[]> listFields = new ArrayList<String[]>();
		listFields.add(reflectFields);
		Map<String, List<Object>> map = importFile(file, list, listFields);
		String key = map.keySet().iterator().next();
		return (List<T>) map.get(key);
	}
	
	/***
	 * 导入excel 多页excel
	 * @param file：导入的文件
	 * @param list：反射类的集合
	 * @return
	 * @throws FileDisposeException
	 */
	public static <T> Map<String, List<T>> importExcel(File file, List<Class<?>> list) throws FileDisposeException{
		return importFile(file, list, null);
	}
	
	/***
	 *
	 * 导入excel 多页excel
	 * @param file：导入的文件
	 * @param list：反射类的集合
	 * @param listFields：反射的单元格对应的字段名的集合
	 * @return
	 * @throws FileDisposeException
	 */
	public static <T> Map<String, List<T>> importExcel(File file, List<Class<?>> list, List<String[]> listFields) throws FileDisposeException{
		return importFile(file, list, listFields);
	}
	
	/***
	 * 导入excel 多页excel
	 * @param importFilePath：导入的文件路径
	 * @param list：反射类的集合
	 * @return
	 * @throws FileDisposeException
	 */
	public static <T> Map<String, List<T>> importExcel(String importFilePath, List<Class<?>> list) throws FileDisposeException{
		File file = new File(importFilePath);
		if(!file.exists())
			throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, FileExceptionParam.FILE_NOT_FOUNT_MSG);
		return importFile(file, list, null);
	}
	
	/***
	 * 导入excel 多页excel
	 * @param importFilePath：导入的文件路径
	 * @param list：反射类的集合
	 * @param listFields：反射的单元格对应的字段名的集合
	 * @return
	 * @throws FileDisposeException
	 */
	public static <T> Map<String, List<T>> importExcel(String importFilePath, List<Class<?>> list, List<String[]> listFields) throws FileDisposeException{
		File file = new File(importFilePath);
		if(!file.exists())
			throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, FileExceptionParam.FILE_NOT_FOUNT_MSG);
		return importFile(file, list, listFields);
	}
	
	/***
	 * 导入excel文件获取内容
	 * @param file：导入的文件
	 * @param list：导入文件中对应sheet页的反射对象的集合
	 * @param listFields：反射的单元格对应的字段名的集合
	 * @return
	 * @throws FileDisposeException
	 */
	private static <T> Map<String, List<T>> importFile(File file, List<Class<?>> list, List<String[]> listFields) throws FileDisposeException{
		Map<String, List<T>> map = new HashMap<String, List<T>>();
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(file);
			Sheet[] sheets = workbook.getSheets();
			if(list.size() < sheets.length){
				throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, FileExceptionParam.FILE_IMPORT_FAILED_MSG);
			}
			if(listFields == null){
				for (int i = 0; i < sheets.length; i++) {
					List<T> returnList = reflectObject(list.get(i),sheets[i], null);
					map.put(sheets[i].getName(), returnList);
				}
			}else{
				if(list.size() != listFields.size()){
					throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, FileExceptionParam.FILE_IMPORT_FAILED_MSG);
				}
				for (int i = 0; i < sheets.length; i++) {
					List<T> returnList = reflectObject(list.get(i),sheets[i], listFields.get(i));
					map.put(sheets[i].getName(), returnList);
				}
			}
		} catch (FileDisposeException e) {
			throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, e.getMessage());
		} catch (Exception e){
			throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, FileExceptionParam.FILE_IMPORT_FAILED_MSG);
		} finally {
			if(workbook != null){
				workbook.close();
			}
		}
		return map;
	}
	
	/***
	 * 通过反射获取每个sheet的内容
	 * @param class1：反射的对象
	 * @param sheet：excel的页
	 * @param reflectFields：反射的单元格对应的字段名
	 * @return
	 * @throws FileDisposeException
	 */
	@SuppressWarnings("unchecked")
	private static <T> List<T> reflectObject(Class<?> class1, Sheet sheet, String[] reflectFields) throws FileDisposeException {
		List<T> list = new ArrayList<T>();
		String simpleName = class1.getSimpleName();
		int rows = sheet.getRows();
		if(rows <= 1)
			return list;
		try {
			if("List".equals(simpleName) || "ArrayList".equals(simpleName)){
				for (int i = 1; i < rows; i++) {
					List<String> contentList = new ArrayList<String>();
					Cell[] cells = sheet.getRow(i);
					for (int j = 0; j < cells.length; j++) {
						String content = sheet.getCell(j, i).getContents();
						contentList.add(content);
					}
					list.add((T) contentList);
				}
			}else{
				for (int i = 1; i < rows; i++) {
					T object = (T) class1.newInstance();
					Map<String, Method> methods = getDeclaredAssignMethods(class1,"set");
					Field[] fields = getDeclaredAssignMethodsToFields(class1, methods);
					Cell[] cells = sheet.getRow(i);
					if(reflectFields != null){
						if(reflectFields.length < cells.length){
							throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, FileExceptionParam.FILE_IMPORT_FAILED_MSG);
						}
						for (int j = 0; j < cells.length; j++) {
							String content = sheet.getCell(j, i).getContents();
							String reflectField = reflectFields[j];
							for (Field field : fields) {
								if(field.getName().equals(reflectField)){
									String firstUpFieldName = reflectField.substring(0, 1).toUpperCase() + reflectField.substring(1);
									Method method = methods.get(firstUpFieldName);
									invokeMethod(object, content, field, method);
									break;
								}
							}
						}
					}else{
						if(fields.length < cells.length){
							throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, FileExceptionParam.FILE_IMPORT_FAILED_MSG);
						}
						for (int j = 0; j < cells.length; j++) {
							String content = sheet.getCell(j, i).getContents();
							Field field = fields[j];
							String fieldName = field.getName();
							String firstUpFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
							Method method = methods.get(firstUpFieldName);
							invokeMethod(object, content, field, method);
						}
					}
					list.add(object);
				}
			}
		} catch (FileDisposeException e) {
			throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, e.getMessage());
		} catch (Exception e){
			throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, FileExceptionParam.FILE_IMPORT_FAILED_MSG);
		}
		return list;
	}
	
	/***
	 * 获取仅包含set方法的method数组
	 * @param class1：反射的对象
	 * @return
	 */
	private static Map<String, Method> getDeclaredAssignMethods(Class<?> class1, String methodFirstName){
		Map<String, Method> map = new HashMap<String, Method>();
		Method[] methods = class1.getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if(methodFirstName.equals(methodName.substring(0, 3))){
				map.put(methodName.substring(3), method);
			}
		}
		return map;
	}
	
	/***
	 * 获取存在set方法的field数组
	 * @param class1：反射的对象
	 * @param map
	 * @return
	 */
	private static Field[] getDeclaredAssignMethodsToFields(Class<?> class1, Map<String, Method> map){
		List<Field> list = new ArrayList<Field>();
		Field[] fields = class1.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String firstUpFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			if(map.containsKey(firstUpFieldName)){
				list.add(field);
			}
		}
		return list.toArray(new Field[]{});
	}
	
	/***
	 * 给反射对象的具体变量赋值
	 * String Byte Short Int Long Float Double Boolean Char Date
	 * @param object：反射后实例化的对象
	 * @param content：值
	 * @param field：反射的字段
	 * @param method：赋值的方法
	 * @throws FileDisposeException
	 */
	private static <T> void invokeMethod(T object, String content, Field field, Method method) throws FileDisposeException{
		Class<?> type = field.getType();
		try {
			if(String.class.equals(type)){
				method.invoke(object, content);
			}else if(Byte.class.equals(type) || byte.class.equals(type)){
				method.invoke(object, content);
			}else if(Short.class.equals(type) || short.class.equals(type)){
				method.invoke(object, Byte.parseByte(content));
			}else if(Integer.class.equals(type) || int.class.equals(type)){
				method.invoke(object, Integer.parseInt(content));
			}else if(Long.class.equals(type) || long.class.equals(type)){
				method.invoke(object, Long.parseLong(content));
			}else if(Float.class.equals(type) || float.class.equals(type)){
				method.invoke(object, Float.parseFloat(content));
			}else if(Double.class.equals(type) || double.class.equals(type)){
				method.invoke(object, Double.parseDouble(content));
			}else if(Character.class.equals(type) || char.class.equals(type)){
				method.invoke(object, content.charAt(0));
			}else if(Boolean.class.equals(type) || boolean.class.equals(type)){
				method.invoke(object, Boolean.parseBoolean(content));
			}else if(Date.class.equals(type)){
				method.invoke(object, DateUtils.getFormatDate(content, DateUtils.DATE_FORMAT));
			}
		} catch (Exception e) {
			throw new FileDisposeException(FileExceptionParam.FILE_IMPORT_FAILED, FileExceptionParam.METHOD_INVOKE_FALIED_MSG);
		}
	}
}
