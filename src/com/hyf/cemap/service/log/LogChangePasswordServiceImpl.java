package com.hyf.cemap.service.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyf.cemap.bean.po.log.LogChangePassword;
import com.hyf.cemap.dao.log.LogChangePasswordDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

@Service("logChangePasswordService")
public class LogChangePasswordServiceImpl extends BaseServiceImpl<LogChangePassword>{

    @Autowired
    private LogChangePasswordDaoImpl logChangePasswordDao;
    
    public List<LogChangePassword> listLogChangePasswordByinfo(String username_c,Integer pageNo,Integer pageSize) {
        return logChangePasswordDao.listLogChangePasswordByinfo(username_c, pageNo, pageSize);
    }
    
}
