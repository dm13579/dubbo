package com.dm.framework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * rpc远程调用 传输信息
 *
 * @author dm
 * @date 2023/12/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invocation implements Serializable {

    /**
     * 接口名
     */
    private String interfaceName;

    /**
     * 接口方法
     */
    private String methodName;

    /**
     * 参数类型列表
     */
    private Class[] paramTypes;

    /**
     * 参数
     */
    private Object[] params;
}
