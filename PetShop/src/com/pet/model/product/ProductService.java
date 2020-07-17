package com.pet.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pet.domain.Event;
import com.pet.domain.Product;
import com.pet.exception.DMLException;
import com.pet.exception.FileException;
import com.pet.model.common.file.FileManager;
import com.pet.model.event.EventDAO;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private FileManager fileManager;
	@Autowired
	private EventDAO eventDAO;
	
	public void regist(Product product , MultipartFile myFile, String realPath) throws DMLException , FileException{
		String filename = FileManager.saveFile(myFile, realPath);		
		product.setFilename(filename);
		productDAO.insert(product);		
	}
	
	public List selectAll() {
		return productDAO.selectAll();
	}
	
	public Product select(int product_id) {
		return productDAO.select(product_id);
	}
	
	public void delete(int product_id) throws DMLException{
		productDAO.delete(product_id);
	}
	
	public void update(Product product, String realPath) throws DMLException , FileException{		
		MultipartFile multi = product.getMyFile();
		
		if(multi.isEmpty()) {//업로드를 안할경우는 DB만 업데이트
			productDAO.update(product);
		}else {//업로드 할경우엔 DB+ 파일도 처리			
			//기존 파일은 삭제하자!!
			FileManager.removeFile(realPath+product.getFilename());
			
			String filename = FileManager.saveFile(multi, realPath);
			product.setFilename(filename);
			productDAO.update(product);			
		}	
	}
	
	//이벤트 등록
	public void registEvent(Event event) throws DMLException{
		eventDAO.insert(event);
	}
	//이벤트 목록
	public List getEventList() {
		return eventDAO.selectAll();
	}

}












