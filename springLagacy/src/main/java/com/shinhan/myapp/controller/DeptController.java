package com.shinhan.myapp.controller;

//자동import : ctrl+shift+o

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shinhan.myapp.model.DeptService;
import com.shinhan.myapp.vo.DeptDTO;

//부서의 CRUD작업 : Controller-> Service -> DAO
//사용자 요청->DispatcherServlet->Controller찾기 
// component-scan의 의해서 Bean생성된다. 

//@Controller ==>요청을 받아서 응답페이지를 return한다. 
//@RestController=>요청을 받아서 응답데이터를 return한다. @Controller + @ResponseBody


@Controller
public class DeptController {

	@Autowired
	DeptService dService;

	Logger  logger = LoggerFactory.getLogger(DeptController.class);
	
	@GetMapping("/dept/insert.do")
	public String insertGet() {
		return "dept/deptInsert";
	}
	@PostMapping("/dept/insert.do")
	public String insertPost(DeptDTO dept) {
		dService.insertService(dept);
		return "redirect:/dept/list.do"; //재요청 
	}
	
	//삭제?
	@RequestMapping(value = "/dept/delete.do", 
			method = {RequestMethod.GET, RequestMethod.POST} )
	public String delete(int deptid) {
		dService.deleteService(deptid);
		return "redirect:/dept/list.do";
	}
	
	
	@GetMapping("/dept/detail.do")
	public String detail(int deptid, Model model) {
	    model.addAttribute("deptInfo", dService.selectByIdService(deptid));
	    return "dept/deptDetail";
	}
	/* 상세보기후에 결과를 보여주고 1초후 list로 가기 
	@PostMapping("/dept/detail.do")
	public String detailPost(@ModelAttribute("dept") DeptDTO dept) {
	    logger.info(dept.toString());	    
	    dService.updateService(dept);
	    return "dept/result";
	}*/
	@PostMapping("/dept/detail.do")
	public String detailPost(DeptDTO dept) {
	    logger.info(dept.toString());	    
	    dService.updateService(dept);
	    return "redirect:/dept/list.do";  //재요청하기 response.sendRedirect()
	}
	
	@RequestMapping("/dept/list.do")
	public String f1(Model model) {
		List<DeptDTO> deptlist = dService.selectAllService();
		model.addAttribute("deptlist", deptlist);
		return "dept/deptList";  //forward, include
		///WEB-INF/views/dept/deptList.jsp
	
	}
}





