package com.dm.framework.register;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地注册 接口与实现类映射关系存在Map里面
 *
 * @author dm
 * @date 2023/12/14
 */
public class LocalRegister {

    private static Map<String, Class> map = new HashMap<>(4);

    public static void regist(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }
}
