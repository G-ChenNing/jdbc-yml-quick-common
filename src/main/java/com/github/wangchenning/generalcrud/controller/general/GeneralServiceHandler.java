package com.github.wangchenning.generalcrud.controller.general;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.github.wangchenning.generalcrud.GeneralService;
import com.github.wangchenning.leaf.util.json.JsonObj;
import com.github.wangchenning.leaf.util.json.JsonUtil;

@RestController
@RequestMapping("/service")
public class GeneralServiceHandler {

	@Autowired
	GeneralService generalService;
	
	private static final Logger logger = LoggerFactory.getLogger(GeneralServiceHandler.class);
	@CrossOrigin(origins = "*", maxAge = 3600) // 允许跨域
	@RequestMapping("/select")
	public String select(String id, @RequestBody String params) throws IOException {
		try{
			String a= generalService.select(id, new JsonObj(params));
//			logger.info("/service/select:"+a);
			return a;
		}catch(Exception e){
			e.printStackTrace();
			return "GeneralServiceHandler，select出错："+e;
		}
	}

	//导致数据缓存不同步问题，20170930
	@CrossOrigin(origins = "*", maxAge = 3600) // 允许跨域
	@RequestMapping("/delete")
	public String delete(String id, @RequestBody String params) throws IOException {
		try{
			generalService.delete(id, new JsonObj(params));
//			return "删除完成";
			return JsonUtil.getJsonStr("删除完成");
		}catch(Exception e){
			return JsonUtil.getJsonStr("GeneralServiceHandler，delete出错："+e);
		}
	}
	//导致数据缓存不同步问题，20170930
	@CrossOrigin(origins = "*", maxAge = 3600) // 允许跨域
	@RequestMapping("/insert")
	public String insert(String id, @RequestBody String params) throws IOException {
		try{
			return generalService.create(id, new JsonObj(params));
		}catch(Exception e){
			return JsonUtil.getJsonStr("GeneralServiceHandler，insert出错："+e);
		}
		
	}
	//同上可能会导致数据缓存不同步问题，未验证，20170930
	@CrossOrigin(origins = "*", maxAge = 3600) // 允许跨域
	@RequestMapping("/update")
	public String update(String id, @RequestBody String params) throws IOException {
		try{
			generalService.update(id, new JsonObj(params));
			return JsonUtil.getJsonStr("更新完成");
		}catch(Exception e){
			return JsonUtil.getJsonStr("GeneralServiceHandler，update出错："+e);
		}
	}
}
