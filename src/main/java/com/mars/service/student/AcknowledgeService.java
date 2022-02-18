package com.mars.service.student;

import com.mars.mapper.AcknowledgeMapper;
import com.mars.model.Acknowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcknowledgeService {
    @Autowired
    private AcknowledgeMapper acknowledgeMapper;

    public Acknowledge getAcknowledgeInfo(String id) {
        return acknowledgeMapper.selectById(id);
    }

    public int addAcknowledgeInfo(Acknowledge acknowledge) {
        int result;
        if (acknowledgeMapper.selectById(acknowledge.getStudentId()) == null) {
            result = acknowledgeMapper.insert(acknowledge);
        }else {
            result = acknowledgeMapper.updateById(acknowledge);
        }
        return result;
    }
}
