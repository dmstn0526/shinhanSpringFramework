package com.shinhan.myapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.myapp.vo.DeptDTO;

@Service 
public class DeptService {
    
	@Autowired   
	DeptDAO deptDao ;

	// 1.λͺ¨λμ‘°ν
	public List<DeptDTO> selectAllService() {
		return deptDao.selectAll();
	}

	// 2.??Έλ³΄κΈ°
	public DeptDTO selectByIdService(int deptid) {
		return deptDao.selectById(deptid);
	}

	// 3.?? ₯
	public int insertService(DeptDTO dept) {
		
		return deptDao.insert(dept);
	}

	// 4.?? 
	public int updateService(DeptDTO dept) {
		return deptDao.update(dept);
	}

	// 5.?­? 
	public int deleteService(int deptid) {
		return deptDao.delete(deptid);
	}
}






