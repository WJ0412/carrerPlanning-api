package com.mars.vo;

import com.mars.model.Options;
import lombok.Data;

@Data
public class OptionVo {
    private String value;
    private String label;

    public OptionVo(Options options) {
        this.label = options.getValue();
        this.value = options.getValue();
    }
}
