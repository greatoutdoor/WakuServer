package waku.controls;


import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import waku.dao.BarCodeDao;
import waku.vo.JsonResult;

@Controller
@RequestMapping("/barcode")
public class BarCodeControl {
Logger log = Logger.getLogger(MyItemControl.class);
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired(required=false)
	BarCodeDao barcodeDao;
	
	@RequestMapping(value="/list.do", produces="application/json")
	public Object ajaxList() throws Exception {
		try{
			JsonResult jr = new JsonResult().setResultStatus(JsonResult.SUCCESS).setData(barcodeDao.selectList());
			return jr;
		}catch(Throwable ex){
			return new JsonResult().setResultStatus(JsonResult.FAIL).setError(ex.getMessage());
		}
	}
	
	@RequestMapping(value="/barcodeDelete.do", produces="application/json")
	public Object ajaxDelete(int cSerial) throws Exception {
		try{
			barcodeDao.delete(cSerial);
			JsonResult jr = new JsonResult().setResultStatus(JsonResult.SUCCESS).setData(barcodeDao.delete(cSerial));
			return jr;
		}catch(Throwable ex){
			return new JsonResult().setResultStatus(JsonResult.FAIL).setError(ex.getMessage());
		}
	}
	
	@RequestMapping(value="/joinGoods.do", produces="application/json")
	public Object ajaxBybarcode(int gNo) throws Exception {
		try{
			JsonResult jr = new JsonResult().setResultStatus(JsonResult.SUCCESS).setData(barcodeDao.ByGoods(gNo));
			return jr;
		}catch(Throwable ex){
			return new JsonResult().setResultStatus(JsonResult.FAIL).setError(ex.getMessage());
		}
	}
	

}
