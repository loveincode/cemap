package com.hyf.cemap.service.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyf.cemap.bean.po.log.LogInOut;
import com.hyf.cemap.dao.log.LogInOutDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

@Service("logInOutService")
public class LogInOutServiceImpl extends BaseServiceImpl<LogInOut>{

    @Autowired
    private LogInOutDaoImpl logInOutDao;
    
    public List<LogInOut> listLogInOutByinfo(String username,Integer pageNo,Integer pageSize) {
        return logInOutDao.listLogInOutByinfo(username, pageNo, pageSize);
    }
    
}
