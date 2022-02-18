package com.mars.service.student;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mars.mapper.OptionsMapper;
import com.mars.model.Options;
import com.mars.vo.OptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptionService {

    @Autowired
    private OptionsMapper optionsMapper;

    public List<OptionVo> getOption(String id) {
        QueryWrapper<Options> wrapper = new QueryWrapper<>();
        wrapper.eq("bh_name", id);
        List<Options> options = optionsMapper.selectList(wrapper);

        return options.stream()
                .map(OptionVo::new)
                .collect(Collectors.toList());
    }
}
