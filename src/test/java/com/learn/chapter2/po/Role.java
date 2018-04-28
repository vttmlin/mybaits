package com.learn.chapter2.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author youxiangxin
 * @Time 2018/4/26  14:44
 * @description
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private Long id;
    private String roleName;
    private String note;
}
