package com.shinhan.myapp.controller;

//�ڵ�import : ctrl+shift+o

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

//�μ��� CRUD�۾� : Controller-> Service -> DAO
//����� ��û->DispatcherServlet->Controllerã�� 
// component-scan�� ���ؼ� Bean�����ȴ�. 

//@Controller ==>��û�� �޾Ƽ� ������������ return�Ѵ�. 
//@RestController=>��û�� �޾Ƽ� ���䵥���͸� return�Ѵ�. @Controller + @ResponseBody


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
		return "redirect:/dept/list.do"; //���û 
	}
	
	//����?
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
	/* �󼼺����Ŀ� ����� �����ְ� 1���� list�� ���� 
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
	    return "redirect:/dept/list.do";  //���û�ϱ� response.sendRedirect()
	}
	
	@RequestMapping("/dept/list.do")
	public String f1(Model model) {
		List<DeptDTO> deptlist = dService.selectAllService();
		model.addAttribute("deptlist", deptlist);
		return "dept/deptList";  //forward, include
		///WEB-INF/views/dept/deptList.jsp
	
	}
}





